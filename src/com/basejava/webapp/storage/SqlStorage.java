package com.basejava.webapp.storage;


import com.basejava.webapp.Config;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.*;
import com.basejava.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private final String CLASS_NAME = getClass().getName();
    private final Logger LOG = Logger.getLogger(CLASS_NAME);
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> {
            try {
                Class.forName(Config.get().getDbDriver());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        }
        );
    }

    @Override
    public void clear() {
        LOG.info(CLASS_NAME + ": " + " clear");
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        LOG.log(Level.INFO, CLASS_NAME + ": " + " get, uuid = {0}", uuid);
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid = ?");
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));

                ps = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid = ?");
                ps.setString(1, uuid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(resume, rs);
                }

                ps = conn.prepareStatement("SELECT * FROM section WHERE resume_uuid = ?");
                ps.setString(1, uuid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(resume, rs);
                }
            } catch (SQLException e) {
                throw new StorageException(e);
            }
            return resume;
        });
    }

    @Override
    public void update(Resume resume) {
        LOG.log(Level.INFO, CLASS_NAME + ": " + " update, uuid = {0}", resume.getUuid());
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                        ps.setString(2, resume.getUuid());
                        ps.setString(1, resume.getFullName());
                        if (ps.executeUpdate() != 1) {
                            throw new NotExistStorageException(resume.getUuid());
                        }
                    }
                    deleteContacts(resume, conn);
                    insertContacts(resume, conn);
                    deleteSections(resume, conn);
                    insertSections(resume, conn);
                    return null;
                }
        );
    }

    @Override
    public void save(Resume resume) {
        LOG.log(Level.INFO, CLASS_NAME + ": " + " save, uuid = {0}", resume.getUuid());
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    insertContacts(resume, conn);
                    insertSections(resume, conn);
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        LOG.log(Level.INFO, CLASS_NAME + ": " + " delete, uuid = {0}", uuid);
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
        LOG.info(CLASS_NAME + ": " + " getAllSorted");
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }

                ps = conn.prepareStatement("SELECT * FROM contact");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = resumes.get(rs.getString("resume_uuid"));
                    addContact(resume, rs);
                }

                ps = conn.prepareStatement("SELECT * FROM section");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = resumes.get(rs.getString("resume_uuid"));
                    addSection(resume, rs);
                }
            } catch (SQLException e) {
                throw new StorageException(e);
            }
            return new ArrayList<>(resumes.values());
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

    private void insertContacts(Resume resume, Connection connection) throws SQLException {
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
        if (resultSet.getString("type") != null
                & resultSet.getString("value") != null) {
            resume.addContact(ContactType.valueOf(resultSet.getString("type")),
                    resultSet.getString("value"));
        }
    }

    private void deleteContacts(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM contact c WHERE c.resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void insertSections(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : resume.getSections().entrySet()) {
                SectionType key = e.getKey();
                Section value = e.getValue();
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                switch (key) {
                    case PERSONAL:
                    case OBJECTIVE:
                        ps.setString(3, ((TextSection) value).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ps.setString(3,
                                String.join("\n", ((ListSection<String>) value)
                                        .getItems()));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        break;
                    default:
                        throw new StorageException("Unknown SectionType name <" + key + ">");
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteSections(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE  FROM section WHERE resume_uuid=?")) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void addSection(Resume resume, ResultSet resultSet) throws SQLException {
        String type = resultSet.getString("type");
        String value = resultSet.getString("value");
        if (type != null & value != null) {
            switch (type) {
                case "PERSONAL":
                case "OBJECTIVE":
                    resume.putSection(SectionType.valueOf(type), new TextSection(value));
                    break;
                case "ACHIEVEMENT":
                case "QUALIFICATIONS":
                    resume.putSection(SectionType.valueOf(type),
                            new ListSection<>(Arrays.asList(value.split("\n"))));
                    break;
                case "EXPERIENCE":
                case "EDUCATION":
                    break;
                default:
                    throw new StorageException("Unknown SectionType name <" + type + ">");
            }
        }
    }

}