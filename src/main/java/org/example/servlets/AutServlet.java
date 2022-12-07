package org.example.servlets;


import org.example.accounts.AccountService;
import org.example.db.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.example.servlets.RegServlet.accountService;

@WebServlet("/aut")
public class AutServlet extends HttpServlet {
    DataBase db = new DataBase();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("aut.jsp").forward(req, resp);


    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //System.out.println(accountService.getUserByLogin(req.getParameter("username")));
        boolean res=false;
        try {
            res = db.checkUser(req.getParameter("username"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(res!=false){
            System.out.println(accountService.getUserByLogin(req.getParameter("username")));
            resp.sendRedirect("http://localhost:8080/com_devcolibri_servlet_war/");
            //req.getRequestDispatcher("/");
        }
        else{
            System.out.println("this data not found");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }


    }
}
