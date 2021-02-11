package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session = null;
    private Transaction tx = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE Users (id BIGINT NOT NULL," +
                    "name VARCHAR(45) NOT NULL," +
                    "lastName VARCHAR(45) NOT NULL," +
                    "age INT NOT NULL," +
                    "PRIMARY KEY (id))");
            tx.commit();
            System.out.println("Таблица успешно создана");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("При создании таблицы пользователей произошло исключение\n" + e);
            tx.rollback();
        } finally {
            session.close();
        }
    }


    @Override
    public void dropUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(session.createSQLQuery("DROP TABLE Users").executeUpdate());
            tx.commit();
            System.out.println("Таблица была успешно удалена");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("При создании таблицы пользователей произошло исключение\n" + e);
            tx.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("При добавлении пользователей произошло исключение\n" + e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(session.get(User.class, id));
            tx.commit();
            System.out.println("Пользователь был успешно удален");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("При удалении пользователей произошло исключение\n" + e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            allUsers = session.createQuery("From " + User.class.getSimpleName()).list();
            System.out.println("Все пользователи были успешно получены");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("При получении всех пользователей произошло исключение\n" + e);
            tx.rollback();
        } finally {
            session.close();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {

        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(session.createSQLQuery("DELETE FROM Users"));
            tx.commit();
            System.out.println("Таблица успешно очищена");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("При очистке таблицы  произошло исключение\n" + e);
            tx.rollback();
        } finally {
            session.close();
        }
    }
}

