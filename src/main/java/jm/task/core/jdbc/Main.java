package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();


        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 25);
        userService.saveUser("Петр", "Петров", (byte) 30);
        userService.saveUser("Сидор", "Сидоров", (byte) 35);
        userService.saveUser("Андрей", "Андреев", (byte) 40);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}