package com.basejava.webapp.storage;

import com.basejava.webapp.Config;

import java.sql.SQLException;

public class MainSqlHelperTest {
    public static void main(String[] args) throws SQLException {
        /*SqlHelper helper = new SqlHelper(Config.get().getDbUrl(), Config.get().getDbUser(),
                Config.get().getDbPassword());
        Connection conn = helper.getConnection();*/

        /*System.out.println("row count: " + JDBCWrapper.executeUpdate(conn,
                "insert into resume (uuid, full_name) VALUES (?, ?)",
                new Object[]{"uuid4", "Сидоров Иван"}
        ));
*/
        /*System.out.println("row count: " + JDBCWrapper.executeUpdate(conn,
                "update resume set full_name = ? where uuid = ?",
                "Сидоров Иван Update", "uuid4"
        ));*/

        /*System.out.println("row count: " + JDBCWrapper.executeUpdate(conn,
                "CREATE TABLE users (id SERIAL PRIMARY KEY NOT NULL, name TEXT NOT NULL)"

        ));*/

        /*Map<String, List<Object>> map = JDBCWrapper.executeQueryWithMapResult(conn,
                "select count(*) from resume where uuid='uuid2'");
*/
        //"select uuid as id, full_name as name, count(*) as count from resume group by uuid
        /*map.entrySet().forEach(el -> System.out.println(el.getKey() + " " + el.getValue()));*/

        SqlStorage sqlStorage = new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUser(),
                Config.get().getDbPassword());

        sqlStorage.clear();


    }





}
