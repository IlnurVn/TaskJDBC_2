package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public void createUsersTable() {
        String createUsersTable = "CREATE TABLE Users ("
                + "id INT(15) NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(10),"
                + "lastname VARCHAR(10),"
                + "age INT(3),"
                + "PRIMARY KEY(id))";
        try {
            connection.createStatement().executeUpdate(createUsersTable);
            System.out.println("Таблица пользователей создана");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("При создании таблицы пользователей произошисключение\n" + e);
        }
    }

    public void dropUsersTable() {
        String createUsersTable = "DROP TABLE IF EXISTS users";
        try {
            connection.createStatement().execute(createUsersTable);
            System.out.println("Таблица пользователей удалена");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("При удалении таблицы пользователей произошло исключение\n" + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT users SET name='"
                + name + "', lastname='" + lastName + "', age=" + age;
        try {
            connection.createStatement().execute(saveUser);
            System.out.println("Новый пользователь добавлен в таблицу");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Во время тестирования сохранения пользователя произошло исключение\n" + e);
        }
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM users WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(removeUserById);
            ps.setInt(1, (int) id);
            ps.executeUpdate();
            System.out.println("Пользователь id = " + id + " удален из таблицы");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Во время тестирования удаления пользователя произошло исключение\n" + e);
        }
    }

    public List<User> getAllUsers() {

        String getAllUsers = "select * from users";
        List users = new ArrayList();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(getAllUsers);
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                System.out.println(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("При попытке достать всех пользователей из базы данных произошло исключение\n" + e);
        }
        return users;
    }

    public void cleanUsersTable() {

        String cleanUsersTable = "TRUNCATE TABLE users";
        try {
            connection.createStatement().execute(cleanUsersTable);
            System.out.println("Таблица пользователей очищена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("При  очистки таблицы пользователей произошло исключение\n" + e);
        }
    }
}