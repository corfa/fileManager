package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cookie[] cookie = req.getCookies();
        String userName=checkCookie(cookie);
        if (userName.length()==0){
            req.getRequestDispatcher("/aut").forward(req, resp);
        }
        String path = req.getParameter("path");

        if (path == null) {
            checkDir(userName);
            path = "C:/Users/ivanw/Desktop/filemanager/" + userName;
        }


        if (!checkOwner(path, userName)){
            path = "C:/Users/ivanw/Desktop/filemanager/" + userName;
            req.getRequestDispatcher("mypage.jsp").forward(req, resp);
            System.out.println("not owner");
        }

        File currentPath = new File(path);


        if (currentPath.isDirectory()) {
            showFiles(req, currentPath);
            req.setAttribute("date", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
            req.setAttribute("currentPath", path);

            req.getRequestDispatcher("mypage.jsp").forward(req, resp);

        } else {
            downloadFile(resp, currentPath);
        }



    }
    private void showFiles(HttpServletRequest req, File currentPath) {
        File[] allFiles = currentPath.listFiles();

        if (allFiles == null) {
            return;
        }
        List<File> directories = new ArrayList<>();

        List<File> files = new ArrayList<>();
        for (File file : allFiles) {
            (file.isDirectory() ? directories : files).add(file);
        }
        req.setAttribute("files", files);
        req.setAttribute("directories", directories);
    }
    private void downloadFile(HttpServletResponse resp, File file) throws IOException {
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=" + file.getName());

        try (InputStream in = Files.newInputStream(file.toPath()); OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[1048];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }

    private String checkCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("UserName".equals(c.getName())) {

                    return c.getValue();

                }
            }
        }
        return "";
    }
    private void checkDir(String userName){
        Path path = Paths.get("C:/Users/ivanw/Desktop/filemanager/"+userName);
        if(!Files.exists(path)){
        File directory = new File("C:/Users/ivanw/Desktop/filemanager/"+userName);
        directory.mkdir();
    }
    }
    private boolean checkOwner(String path,String userName){


        if (path.contains("C:/Users/ivanw/Desktop/filemanager/"+userName)){
            return true;
        }
        else{
            return false;
        }

    }


}