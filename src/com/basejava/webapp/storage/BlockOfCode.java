package com.basejava.webapp.storage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface BlockOfCode<T> {
    T execute(PreparedStatement pstm) throws SQLException;
}
