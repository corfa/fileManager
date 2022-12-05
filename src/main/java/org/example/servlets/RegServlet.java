package org.example.servlets;

import org.example.accounts.AccountService;
import org.example.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/reg")
public class RegServlet extends HttpServlet {
 public static AccountService accountService = new AccountService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("reg.jsp").forward(req, resp);


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.addCookie(new Cookie("UserName",req.getParameter("username")));
        UserProfile user = new UserProfile(req.getParameter("username"), req.getParameter("password"), req.getParameter("email"));
        accountService.addSession(req.getParameter("username"), user);
        accountService.addNewUser(user);
        req.getRequestDispatcher("aut.jsp").forward(req, resp);

        }


    }



