package com.basejava.webapp.storage;


import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private final String CLASS_NAME = getClass().getName();
    private final Logger LOG = Logger.getLogger(CLASS_NAME);

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        LOG.info(CLASS_NAME +": " + " clear");
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        LOG.log(Level.INFO, CLASS_NAME +": " + " get, uuid = {0}", uuid);
        return sqlHelper.execute("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        String value = rs.getString("value");
                        if (value != null) {
                            ContactType type = ContactType.valueOf(rs.getString("type"));
                            resume.addContact(type, value);
                        }
                    } while (rs.next());
                    return resume;
                });
    }

    @Override
    public void update(Resume resume) {
        LOG.log(Level.INFO, CLASS_NAME +": " + " update, uuid = {0}", resume.getUuid());
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                        ps.setString(2, resume.getUuid());
                        ps.setString(1, resume.getFullName());
                        if (ps.executeUpdate() != 1) {
                            throw new NotExistStorageException(resume.getUuid());
                        };
                    }

                    try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact c WHERE c.resume_uuid = ?")) {
                        ps.setString(1, resume.getUuid());
                        ps.execute();
                    }

                    insertContact(resume, conn);
                    return null;
                }
        );
    }

    @Override
    public void save(Resume resume) {
        LOG.log(Level.INFO, CLASS_NAME +": " + " save, uuid = {0}", resume.getUuid());
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    insertContact(resume, conn);
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        LOG.log(Level.INFO, CLASS_NAME +": " + " delete, uuid = {0}", uuid);
        sqlHelper.execute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info(CLASS_NAME +": " + " getAllSorted");
        return sqlHelper.execute("SELECT r.uuid,r.full_name, c.type, c.value FROM resume r LEFT JOIN contact c on r.uuid = c.resume_uuid ORDER BY full_name,uuid",
        (ps) -> {
                ResultSet rs = ps.executeQuery();
                Map<String, Resume> resumes = new LinkedHashMap<>();
                while (rs.next()) {
                    Resume resume = resumes.get(rs.getString(1));
                    if (resume != null) {
                        addContact(resume, rs);
                    } else {
                        resume = new Resume(rs.getString(1), rs.getString(2));
                        addContact(resume, rs);
                        resumes.put(resume.getUuid(), resume);
                    }
                }
                return  new LinkedList<>(resumes.values());
            });
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


    private void insertContact(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }

    }

    private void addContact(Resume resume, ResultSet resultSet) throws SQLException {
        if (resultSet.getString(3) != null & resultSet.getString(4) != null) {
            resume.addContact(ContactType.valueOf(resultSet.getString(3)),
                    resultSet.getString(4));
        }
    }

}