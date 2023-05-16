package org.example.servlets;

import org.example.Hibernate.HibernateUtil;
import org.example.Hibernate.UsersEntity;
import org.hibernate.Query;
import org.hibernate.Session;

public class DAO {
    public void createUser(UsersEntity user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
    public static boolean checkUser(String username, String password){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from UsersEntity where userName=:username and password=:password");
        query.setString("username", username);
        query.setString("password", password);
        UsersEntity result = (UsersEntity) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result != null;
    }

}
