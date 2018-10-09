package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.basejava.webapp.storage.SQLHelper.QueryMode.*;

public class SqlStorage implements Storage {
    private SQLHelper sqlHelper;

    protected SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SQLHelper(dbUrl, dbUser, dbPassword);
    }

    public SQLHelper getSqlHelper() {
        return sqlHelper;
    }

    public void setSqlHelper(SQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }

    @Override
    public void clear() {
        try {
            sqlHelper.doQuery(WITHOUT_PARAMS_WITHOUT_RESULT,"DELETE FROM resume",
                    new Object[]{});
        } catch (SQLException e) {
            throw new StorageException("Error during clear database", e);
        }
    }

    @Override
    public Resume get(String uuid) {
        ResultSet rs = null;
        try {
            rs = sqlHelper.doQuery(WITH_PARAMS_WITH_RESULT,"SELECT * FROM resume r WHERE r.uuid =?",
                    new Object[]{uuid});
        } catch (SQLException e) {
            throw new StorageException("Error during get operation ", e);
        }

        try {
            if (rs.next()) {
                return new Resume(uuid, rs.getString("full_name"));
            }

        } catch (SQLException e) {
            throw new NotExistStorageException(uuid);
        }
        return null;
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {
        try {
            sqlHelper.doQuery(WITH_PARAMS_WITHOUT_RESULT,"INSERT INTO resume (uuid, full_name) VALUES (?,?)",
                    r.getUuid(), r.getFullName());
        } catch (SQLException e) {
            throw new StorageException("Error during save operation ", e);
        }
    }

    @Override
    public void delete(String uuid) {
        try {
            sqlHelper.doQuery(WITH_PARAMS_WITHOUT_RESULT, "DELETE FROM resume WHERE uuid = ?",
                    new Object[]{uuid});
        } catch (SQLException e) {
            throw new StorageException("Error during delete operation ", e);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        ResultSet rs = null;
        try {
            rs = sqlHelper.doQuery(WITHOUT_PARAMS_WITH_RESULT, "SELECT * FROM resume ORDER BY full_name",
                    new Object[]{});
        } catch (SQLException e) {
            throw new StorageException("Error during getAll operation ", e);
        }
        List<Resume> resumes = new ArrayList<>();

        try {
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
        } catch (SQLException e) {
            throw new StorageException("Error during getAll operation ", e);
        }

        return resumes;
    }

    @Override
    public int size() {
        int count = 0;
        ResultSet rs = null;
        try {
            rs = sqlHelper.doQuery(WITHOUT_PARAMS_WITH_RESULT, "SELECT count(*), uuid as count FROM resume GROUP BY uuid",
                    new Object[]{});
        } catch (SQLException e) {
            throw new StorageException("Error during getSize operation ", e);
        }

        try {

            while (rs.next()) {
                count++;
            }
            return count;

        } catch (SQLException e) {
            throw new StorageException("Error during getSize operation ", e);
        }

    }
}