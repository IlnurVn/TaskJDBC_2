package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;

import static jm.task.core.jdbc.util.Util.*;

public class Main {


    public static void main(String[] args) {

//        run_exp1();
        run_exp2();
    }

//    public static void run_exp1() {
//        UserDaoJDBCImpl user;
//        Util util;
//        util = new Util();
//        Connection connection = util.getConnection();
//        if (connection != null) {
//            user = new UserDaoJDBCImpl(connection);
//            user.createUsersTable();
//            user.saveUser("Pavel", "Pavlov", (byte) 43);
//            user.saveUser("Sergei", "Ivanov", (byte) 21);
//            user.saveUser("Boris", "Andreev", (byte) 19);
//            user.saveUser("Ilya", "Savinov", (byte) 39);
//            user.getAllUsers();
//            user.removeUserById(2);
//            user.cleanUsersTable();
//            user.dropUsersTable();
//        }
//    }

    private static void run_exp2() {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Pavel", "Pavlov", (byte) 43);
        userService.saveUser("Sergei", "Ivanov", (byte) 21);
        userService.saveUser("Boris", "Andreev", (byte) 19);
        userService.saveUser("Ilya", "Savinov", (byte) 39);

        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}



