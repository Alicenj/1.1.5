package jm.task.core.jdbc.service;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDaoHibernate = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        try {
            userDaoHibernate.createUsersTable();
        } catch (Exception e) {
            System.err.println("Ошибка при создании таблицы пользователей:");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            userDaoHibernate.dropUsersTable();
        } catch (Exception e) {
            System.err.println("Ошибка при удалении таблицы пользователей:");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            userDaoHibernate.saveUser(name, lastName, age);
            System.out.printf("Пользователь с именем – %s добавлен в базу данных\n", name);
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении пользователя:");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            userDaoHibernate.removeUserById(id);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении пользователя:");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userDaoHibernate.getAllUsers();
        } catch (Exception e) {
            System.err.println("Ошибка при получении списка пользователей:");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try {
            userDaoHibernate.cleanUsersTable();
        } catch (Exception e) {
            System.err.println("Ошибка при очистке таблицы пользователей:");
            e.printStackTrace();
        }
    }
}
