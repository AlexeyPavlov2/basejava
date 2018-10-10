package com.basejava.webapp.storage;


import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.sql.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r WHERE r.uuid =?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void update(Resume resume) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE resume r SET uuid = ?, full_name = ? WHERE r.uuid =?")) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.setString(3, resume.getUuid());
            int count = ps.executeUpdate();
            if (count == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume resume) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                throw new ExistStorageException(resume.getUuid());
            }
            PreparedStatement ps1 = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)");
            ps1.setString(1, resume.getUuid());
            ps1.setString(2, resume.getFullName());
            if (ps1.executeUpdate() != 1) {
                throw new StorageException("Erroe during INSERT operation");
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }

    @Override
    public void delete(String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM resume WHERE uuid = ?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            PreparedStatement ps1 = conn.prepareStatement("DELETE * FROM resume WHERE uuid = ?");
            ps1.setString(1, uuid);
            if (ps1.executeUpdate() != 1) {
                throw new StorageException("Error during DELETE operation");
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"),
                        rs.getString("full_name")));
            }
            return resumes;

        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int size() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM resume")) {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return 0;
            } else {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }
}