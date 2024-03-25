package com.aircompany.servlets;

import com.aircompany.db.DataBaseManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/connect")
public class RequestServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DataBaseManager DBM = new DataBaseManager();
        String res;
        Connection conn = DBM.getConnection("Aircompany", "postgres", "Vlad10092004");
        if(conn == null){
            res = "Nuh uh";
        }else{
            res = "Bring'em on!";
        }
        resp.getWriter().println(res);
    }
}
