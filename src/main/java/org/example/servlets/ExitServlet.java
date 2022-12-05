package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exit")
public class ExitServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        eraseCookie(req,resp);
        req.getRequestDispatcher("aut.jsp").forward(req, resp);


    }

    private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("UserName".equals(c.getName())) {
                    System.out.println(c.getValue());
                    c.setValue("");
                    System.out.println(c.getValue());
                    System.out.println("cooki drop");


                }
            }
        }


    }
//    private String checkCookie(Cookie[] cookies) {
//        if (cookies != null) {
//            for (Cookie c : cookies) {
//                if ("UserName".equals(c.getName())) {
//
//                    return c.setValue("");
//
//
//                }
//            }
//        }
//        return "";
//    }
}