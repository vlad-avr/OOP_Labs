package com.aircompany.servlets;

import com.aircompany.db.dao.CrewDao;
import com.aircompany.db.dao.DaoManager;
import com.aircompany.db.entity.Entity;
import com.aircompany.parsers.JsonParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/crew")
public class CrewServlet  extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String field = req.getParameter("field");
        String value = req.getParameter("value");
        if(field == null || value == null){
            resp.getWriter().println("");
            return;
        }
        DaoManager mgr = new DaoManager();
        Connection conn = mgr.getConnection("Aircompany", "postgres", "Vlad10092004");
        if(conn == null){
            resp.getWriter().println();
            return;
        }
        CrewDao crewDao = new CrewDao(conn);
        List<Entity> entityList = new ArrayList<>();
        try {
            switch (field) {
                case "name":
                    entityList = crewDao.readByName(value);
                    break;
                case "qualification":
                    entityList = crewDao.readByQualification(value);
                    break;
                case "id":
                    entityList.add(crewDao.read(value));
                    break;
                case "all":
                    entityList = crewDao.readAll();
                    break;
                default:
                    resp.getWriter().println();
                    return;
            }
        }catch (Exception e){
            resp.getWriter().println();
            return;
        }
        try {
            String res = JsonParser.toJsonEntities(entityList);
            resp.getWriter().println(res);
        } catch (Exception e) {
            resp.getWriter().println();
            return;
        }
    }
}
