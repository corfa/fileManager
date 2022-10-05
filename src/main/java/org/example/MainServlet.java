package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getParameter("path");
        if (path == null) {
            path = "/";
        }


        File currentPath = new File(path);
        File testPath = new File(path);

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


}