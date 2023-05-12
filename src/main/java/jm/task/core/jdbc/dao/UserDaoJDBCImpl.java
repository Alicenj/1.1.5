package jm.task.core.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    @Override
    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (" + "id BIGINT NOT NULL AUTO_INCREMENT," + "name VARCHAR(45) NOT NULL," + "last_name VARCHAR(45) NOT NULL," + "age TINYINT NOT NULL," + "PRIMARY KEY (id));";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении пользователя");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User с id – " + id + " удален из базы данных");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT id, name, last_name, age FROM users";
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка пользователей");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы");
            e.printStackTrace();
        }
    }
}
