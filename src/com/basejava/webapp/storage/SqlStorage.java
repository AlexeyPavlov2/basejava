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

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
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
        List<Resume> resumes = new LinkedList<>();
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT r.uuid,r.full_name, c.type, c.value FROM resume r\n" +
                    "LEFT JOIN contact c on r.uuid = c.resume_uuid\n" +
                    "ORDER BY full_name,uuid")) {
                ResultSet rs = ps.executeQuery();
                // Create ResultSet List
                List<List<Object>> list = new LinkedList<>();
                while (rs.next()) {
                    List<Object> objects = new LinkedList<>();
                    int count = rs.getMetaData().getColumnCount();
                    for (int i = 0; i < count; i++) {
                        objects.add(rs.getObject(i + 1));
                    }
                    list.add(objects);
                }
                // Make Map with uuid as key
                Map<String, List<List<Object>>> map = new LinkedHashMap<>();
                list.forEach(el -> {
                    map.merge((String) el.get(0), new LinkedList<List<Object>>() {{
                                add(el);
                            }},
                            (a, b) -> new LinkedList<List<Object>>(a) {{
                                addAll((b));
                            }});
                });
                // Create resumes and add to result list
                map.forEach((key, value) -> {
                    Resume resume = new Resume(key, (String) value.get(0).get(1));
                    resumes.add(resume);
                    value.forEach(el -> {
                            if (el != null && !el.isEmpty()) {
                              if (el.get(2) != null && el.get(3) != null) {
                        resume.addContact(ContactType.valueOf((String) el.get(2)), (String) el.get(3));
                            }
                        }
                    });
                });
                return resumes;
            }
        });
        return resumes;
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
}