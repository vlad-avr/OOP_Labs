package com.aircompany.servlets;
import com.aircompany.db.dao.BrigadeDao;
import com.aircompany.db.dao.CrewDao;
import com.aircompany.db.dao.DaoManager;
import com.aircompany.db.entity.Brigade;
import com.aircompany.db.entity.Crewmate;
import com.aircompany.db.entity.Entity;
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

@WebServlet("/brigade")
public class BrigadeServlet extends HttpServlet {

    public Entity getEntity(String toParse){
        try {
            return JsonParser.parseBrigade(toParse);
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
        BrigadeDao dao = new BrigadeDao(conn);
        if(entity != null) {
            try {
                dao.update((Brigade) entity);
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
        }
        entity.setId(UUID.randomUUID().toString());
        DaoManager DBM = new DaoManager();
        Connection conn = DBM.getConnection("Aircompany", "postgres", "Vlad10092004");
        BrigadeDao dao = new BrigadeDao(conn);
        try {
            dao.create((Brigade) entity);
        } catch (Exception e) {
            resp.getWriter().println("[]");
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
        BrigadeDao brigadeDao = new BrigadeDao(conn);
        List<Entity> entityList = new ArrayList<>();
        try {
            switch (field) {
                case "name":
                    entityList = brigadeDao.readByName(value);
                    break;
                case "id":
                    entityList.add(brigadeDao.read(value));
                    break;
                case "all":
                    entityList = brigadeDao.readAll();
                    break;
                case "delete":
                    brigadeDao.delete(value);
                    entityList = brigadeDao.readAll();
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