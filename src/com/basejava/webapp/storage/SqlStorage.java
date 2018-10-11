package com.basejava.webapp.storage;


import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private SqlHelper sqlHelper = null;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    public SqlHelper getSqlHelper() {
        return sqlHelper;
    }

    public void setSqlHelper(SqlHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", (ps) -> {
            ps.executeUpdate();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume WHERE uuid = ?",
                (ps) -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            } else {
                return new Resume(rs.getString("uuid"),
                        rs.getString("full_name"));
            }

        });
    }

    @Override
    public void update(Resume resume) {
        if (!isResumeExist(resume.getUuid())) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            sqlHelper.execute("UPDATE resume SET full_name = ? WHERE uuid = ?", (ps) -> {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                int count = ps.executeUpdate();
                if (count == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
                if (ps.executeUpdate() != 1) {
                    throw new StorageException("Error during UPDATE operation");
                }
                return null;
            });
        }
    }

    @Override
    public void save(Resume resume) {
        if (isResumeExist(resume.getUuid())) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            sqlHelper.execute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", (ps) -> {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
                return null;
            });
        }
    }

    @Override
    public void delete(String uuid) {
        if (!isResumeExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }  else {
            sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", (ps) -> {
                ps.setString(1, uuid);
                if (ps.executeUpdate() != 1) {
                    throw new StorageException("Error during DELETE operation");
                }
                return null;
            });
        }

    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume ORDER BY full_name",
                (ps) -> mapResultSetToList(ps.executeQuery()));

    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) AS count FROM resume",
                (ps) -> {
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    return rs.getInt("count");
                });
    }

    private boolean isResumeExist(String uuid) {
        return sqlHelper.execute("SELECT COUNT(*) AS count FROM resume WHERE uuid = ? ", (ps) ->{
           ps.setString(1, uuid);
           ResultSet rs = ps.executeQuery();
           rs.next();
           return rs.getInt("count") > 0;
        });
    }

    private List<Resume> mapResultSetToList(ResultSet rs) throws SQLException {
        List<Resume> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
        }
        return list;
    }
}