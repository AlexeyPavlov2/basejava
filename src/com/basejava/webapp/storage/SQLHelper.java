package com.basejava.webapp.storage;

import com.basejava.webapp.sql.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.*;

public class SQLHelper {
    private ConnectionFactory connectionFactory;

    public enum QueryMode {
        WITH_PARAMS_WITH_RESULT,
        WITH_PARAMS_WITHOUT_RESULT,
        WITHOUT_PARAMS_WITH_RESULT,
        WITHOUT_PARAMS_WITHOUT_RESULT
    }

    public SQLHelper(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public ResultSet doQuery(QueryMode mode, String statement, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = connectionFactory.getConnection();
        ps = conn.prepareStatement(statement);

        switch (mode) {
            case WITH_PARAMS_WITH_RESULT:
                setParam(ps, params);
                return ps.executeQuery();
            case WITH_PARAMS_WITHOUT_RESULT:
                setParam(ps, params);
                ps.execute();
                return null;
            case WITHOUT_PARAMS_WITHOUT_RESULT:
                ps.execute();
                return null;
            case WITHOUT_PARAMS_WITH_RESULT:
                return ps.executeQuery();
        }

        return null;
    }

    private void setParam(PreparedStatement ps, Object[] params) throws SQLException {
        int pos = 1;
        for (Object el : params) {
            if (el instanceof String) {
                ps.setString(pos, (String) el);
            } else if (el instanceof Integer) {
                ps.setInt(pos, (Integer) el);
            } else if (el instanceof Double) {
                ps.setDouble(pos, (Double) el);
            } else if (el instanceof Boolean) {
                ps.setBoolean(pos, (Boolean) el);
            } else if (el instanceof BigDecimal) {
                ps.setBigDecimal(pos, (BigDecimal) el);
            } else if (el instanceof Date) {
                ps.setDate(pos, (Date) el);
            } else if (el instanceof Float) {
                ps.setFloat(pos, (Float) el);
            } else if (el instanceof byte[]) {
                ps.setBytes(pos, (byte[]) el);
            }
            pos++;
        }
    }

}
