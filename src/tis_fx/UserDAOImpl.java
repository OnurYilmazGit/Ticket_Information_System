/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tis_fx;

import DAOs.UserDAO;
import Models.User;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author HP
 */
public class UserDAOImpl implements UserDAO {

    Session session = HibernateUtility.getHibernateSession();

    @Override
    public User insertUser(User u) {
        try {
            System.out.println(u.getName());
            session.beginTransaction();
            session.save(u);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            session.beginTransaction();
            users = session.createQuery("FROM User").getResultList();

        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
             session.getTransaction().commit();
        }
        

        return users;
    }

    public List<String> checkPassword(String username) {
        List<String> password = null;
        
        try {
            session.beginTransaction();
            password = session.createSQLQuery("SELECT user_password FROM users where user_name = '" + username + "'").getResultList();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
           session.getTransaction().commit();
        }

        return password;
    }

    @Override
    public void updateUser(int id, String name) {
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            user.setName(name);
            session.update(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(int id) {
        try {
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public User findUser(int id) {
        User user = session.get(User.class, id);
        System.out.println("Username : " + user.getName());
        return user;
    }

}
