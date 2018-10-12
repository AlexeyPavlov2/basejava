package com.basejava.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface BlockOfCode<T> {
    T execute(PreparedStatement pstm) throws SQLException;
}
