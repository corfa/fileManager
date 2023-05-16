package org.example.servlets;


import org.example.accounts.AccountService;
import org.example.db.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.example.servlets.RegServlet.accountService;

@WebServlet("/aut")
public class AutServlet extends HttpServlet {
    //JDBC
    DataBase db = new DataBase();
    //Hibernate
    DAO dao = new DAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("aut.jsp").forward(req, resp);


    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


//        try {

       boolean res = DAO.checkUser(req.getParameter("username"),req.getParameter("password"));

//        res = db.checkUser(req.getParameter("username"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        if(res){
            String username = req.getParameter("username");
            Cookie cookie = new Cookie("UserName", username);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
            resp.sendRedirect("http://localhost:8080/com_devcolibri_servlet_war/");

        }
        else{
            System.out.println("this data not found");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }


    }
}
