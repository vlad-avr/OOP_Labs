package com.aircompany.servlets;

import com.aircompany.db.dao.BrigadeDao;
import com.aircompany.db.dao.DaoManager;
import com.aircompany.db.dao.PlaneDao;
import com.aircompany.db.entity.Brigade;
import com.aircompany.db.entity.Entity;
import com.aircompany.db.entity.Plane;
import com.aircompany.parsers.JsonParser;
import com.aircompany.servlets.util.RequestPack;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/plane")
public class PlaneServlet extends HttpServlet {
    public Entity getEntity(String toParse){
        try {
            return JsonParser.parsePlane(toParse);
        } catch (Exception e) {
            return null;
        }
    }
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();
        String requestBodyString = RequestPack.processRequest(reader);
        Entity entity = getEntity(requestBodyString);
        DaoManager DBM = new DaoManager();
        Connection conn = DBM.getConnection("Aircompany", "postgres", "Vlad10092004");
        PlaneDao dao = new PlaneDao(conn);
        if(entity != null) {
            try {
                dao.update((Plane) entity);
                resp.getWriter().println(JsonParser.toJsonEntities(dao.readAll()));
            } catch (Exception e) {
                resp.getWriter().println("[]");
            }
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();
        String requestBodyString = RequestPack.processRequest(reader);
        Entity entity = getEntity(requestBodyString);
        if(entity == null){
            resp.getWriter().println("[]");
            return;
        }
        entity.setId(UUID.randomUUID().toString());
        DaoManager DBM = new DaoManager();
        Connection conn = DBM.getConnection("Aircompany", "postgres", "Vlad10092004");
        PlaneDao dao = new PlaneDao(conn);
        try {
            dao.create((Plane) entity);
        } catch (Exception e) {
            resp.getWriter().println("[]");
            return;
        }
        try {
            resp.getWriter().println(JsonParser.toJsonEntities(dao.readAll()));
        } catch (Exception e) {
            resp.getWriter().println("[]");
        }
    }
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
        PlaneDao dao = new PlaneDao(conn);
        List<Entity> entityList = new ArrayList<>();
        try {
            switch (field) {
                case "model":
                    entityList = dao.readByModel(value);
                    break;
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