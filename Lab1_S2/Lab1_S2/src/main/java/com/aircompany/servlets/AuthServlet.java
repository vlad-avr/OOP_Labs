package com.aircompany.servlets;

import com.aircompany.db.entity.User;
import com.aircompany.parsers.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        String requestBodyString = requestBody.toString();
        System.out.println(requestBodyString);
        User user = JsonParser.parseUser(requestBodyString);
        String responseData = "NULL";
        if(user != null) {
            responseData = "Hello " + user.getLogin() + " (" + user.getPassword() + ") from Servlet!";
        }
//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        resp.setContentType("text/plain");
//        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(responseData);
    }
}
