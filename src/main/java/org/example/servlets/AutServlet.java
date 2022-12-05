package org.example.servlets;


import org.example.accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.example.servlets.RegServlet.accountService;

@WebServlet("/aut")
public class AutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("aut.jsp").forward(req, resp);


    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //System.out.println(accountService.getUserByLogin(req.getParameter("username")));
        if(accountService.getUserByLogin(req.getParameter("username"))!=null){
            System.out.println(accountService.getUserByLogin(req.getParameter("username")));
            resp.sendRedirect("http://localhost:8080/untitled3_war/");
            //req.getRequestDispatcher("/");
        }
        else{
            System.out.println("this data not found");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }


    }
}
