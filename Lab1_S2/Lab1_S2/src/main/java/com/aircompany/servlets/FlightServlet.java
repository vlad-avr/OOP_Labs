package com.aircompany.servlets;

import com.aircompany.db.dao.DaoManager;
import com.aircompany.db.dao.FlightDao;
import com.aircompany.db.entity.Entity;
import com.aircompany.parsers.JsonParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/flight")
public class FlightServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String field = req.getParameter("field");
        String value = req.getParameter("value");
        if(field == null || value == null){
            resp.getWriter().println("[]");
            return;
        }
        DaoManager mgr = new DaoManager();
        Connection conn = mgr.getConnection("Aircompany", "postgres", "Vlad10092004");
        if(conn == null){
            resp.getWriter().println("[]");
            return;
        }
        FlightDao dao = new FlightDao(conn);
        List<Entity> entityList = new ArrayList<>();
        try {
            switch (field) {
                case "id":
                    entityList.add(dao.read(value));
                    break;
                case "all":
                    entityList = dao.readAll();
                    break;
                case "delete":
                    dao.delete(value);
                    entityList = dao.readAll();
                    break;
                case "ids":
                    List<String> ids = dao.getIds(value);
                    resp.getWriter().println(JsonParser.toJsonIds(ids));
                    return;
                default:
                    resp.getWriter().println("[]");
                    return;
            }
        }catch (Exception e){
            resp.getWriter().println("[]");
            return;
        }
        try {
            String res = JsonParser.toJsonEntities(entityList);
            resp.getWriter().println(res);
        } catch (Exception e) {
            resp.getWriter().println("[]");
            return;
        }
    }
}