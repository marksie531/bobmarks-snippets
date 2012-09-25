package org.bobmarks.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class UserService {

    public UserService () {}        

    public User getUser(long id) {
        SqlSession session = ConnectionFactory.getSession().openSession();
        try {
            UserDAO dao = session.getMapper(UserDAO.class);
            User user = dao.selectUserById(id);
            return user;
        }
        finally {
            session.close();
        }       
    }

    public List<User> getUsers () {
        SqlSession session = ConnectionFactory.getSession().openSession();
        try {
            UserDAO dao = session.getMapper(UserDAO.class);
            List<User> users = dao.selectAllUsers();
            return users;
        }
        finally {
            session.close();
        }
    }

    public void addUser(User user) {
        SqlSession session = ConnectionFactory.getSession().openSession();
        try {
            UserDAO dao = session.getMapper(UserDAO.class);
            dao.insertUser(user);
            session.commit();
        }
        finally {
            session.close();
        }
    }

    public void modifyUser(User user) {
        SqlSession session = ConnectionFactory.getSession().openSession();
        try {
            UserDAO dao = session.getMapper(UserDAO.class);
            dao.updateUser(user);
            session.commit();
        }
        finally {
            session.close();
        }       
    }

    public void deleteUser(long id) {
        SqlSession session = ConnectionFactory.getSession().openSession();
        try {
            UserDAO dao = session.getMapper(UserDAO.class);
            dao.deleteUser(id);
            session.commit();
        }
        finally {
            session.close();
        }           
    }

    public void deleteAllUsers() {
        SqlSession session = ConnectionFactory.getSession().openSession();
        try {
            UserDAO dao = session.getMapper(UserDAO.class);
            dao.deleteAllUsers();
            session.commit();
        }
        finally {
            session.close();
        }   
    }
}