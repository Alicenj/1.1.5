package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {


    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mytest";
    private static final String DB_USERNAME = "roott";
    private static final String DB_PASSWORD = "root";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Соединение с базой данных установлено");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось загрузить класс драйвера БД");
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение с БД");
        }
        return connection;
    }

}

