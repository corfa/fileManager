package org.example.servlets;

import org.example.Hibernate.UsersEntity;
import org.example.accounts.AccountService;
import org.example.accounts.UserProfile;
import org.example.db.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reg")
public class RegServlet extends HttpServlet {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 public static AccountService accountService = new AccountService();
    //JDBC
    DataBase db = new DataBase();
//Hibernate
    DAO dao = new DAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("reg.jsp").forward(req, resp);


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        UsersEntity user_db = new UsersEntity();
        user_db.setUserName(req.getParameter("username"));
        user_db.setEmail(req.getParameter("email"));
        user_db.setPassword(req.getParameter("password"));
        dao.createUser(user_db);

//        try {
//            db.createUser(req.getParameter("username"),req.getParameter("password"), req.getParameter("email"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        UserProfile user = new UserProfile(req.getParameter("username"), req.getParameter("password"), req.getParameter("email"));
        accountService.addSession(req.getParameter("username"), user);
        accountService.addNewUser(user);
        req.getRequestDispatcher("aut.jsp").forward(req, resp);

        }


    }



