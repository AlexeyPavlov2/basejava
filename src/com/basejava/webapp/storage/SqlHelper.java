package com.basejava.webapp.storage;

import com.basejava.webapp.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String statement) {
        execute(statement, (ps) -> ps.execute());
    }

    public <T> T execute(String sql, BlockOfCode<T> executor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql/*, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY*/)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}