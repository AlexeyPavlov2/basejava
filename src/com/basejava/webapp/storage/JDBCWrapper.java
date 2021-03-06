package com.basejava.webapp.storage;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JDBCWrapper {
    private JDBCWrapper() {
    }

    // for  INSERT, UPDATE or DELETE
    public static int executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        setParam(ps, params);
        return ps.executeUpdate();
    }

    public static Map<String, List<Object>> executeQueryWithMapResult(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        setParam(ps, params);
        return resultSetToMap(ps.executeQuery());
    }

    public static Object executeQuery(Connection connection, String sql, Class clazz) {
        return null;
    }

    private static void setParam(PreparedStatement ps, Object[] params) throws SQLException {
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

    private static Map<String, List<Object>> resultSetToMap(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        Map<String, List<Object>> map = new LinkedHashMap<>(columns);
        for (int i = 1; i <= columns; ++i) {
            map.put(md.getColumnName(i), new LinkedList<>());
        }
        while (rs.next()) {
            for (int i = 1; i <= columns; ++i) {
                map.get(md.getColumnName(i)).add(rs.getObject(i));
            }
        }
        return map;
    }
}
