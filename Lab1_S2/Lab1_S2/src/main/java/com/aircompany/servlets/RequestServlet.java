package com.aircompany.servlets;

import com.aircompany.db.dao.*;
import com.aircompany.db.entity.*;
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

@WebServlet("/request")
public class RequestServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        if(conn == null){
//            res = "Nuh uh";
//        }else{
//            res = "Bring'em on!";
//            PlaneDao planeDao = new PlaneDao(conn);
//            CrewDao crewDao = new CrewDao(conn);
//            BrigadeDao brigadeDao = new BrigadeDao(conn);
//            RaceDao raceDao = new RaceDao(conn);
//            try {
//                crewDao.create(new Crewmate(UUID.randomUUID(), "NAME", Crewmate.Qualification.PILOT, ""));
//                System.out.println("BALL");
//                crewDao.create(new Crewmate(UUID.randomUUID(), "NAME_1", Crewmate.Qualification.PILOT, ""));
//                System.out.println("BALL");
//                crewDao.create(new Crewmate(UUID.randomUUID(), "NAME_2", Crewmate.Qualification.STUART, ""));
//                System.out.println("BALL");
//                crewDao.create(new Crewmate(UUID.randomUUID(), "NAME_3", Crewmate.Qualification.STUART, ""));
//                System.out.println("BALL");
//                crewDao.create(new Crewmate(UUID.randomUUID(), "NAME_4", Crewmate.Qualification.RADIO_OFFICER, ""));
//                planeDao.create(new Plane(UUID.randomUUID(), "MODEL_0", 1234.5, 600, 500));
//                System.out.println("BALL");
//                planeDao.create(new Plane(UUID.randomUUID(), "MODEL_1", 2234.5, 800, 100));
//                System.out.println("BALL");
//                planeDao.create(new Plane(UUID.randomUUID(), "MODEL_2", 3234.5, 700, 300));
//                System.out.println("BALL");
//                brigadeDao.create(new Brigade(UUID.randomUUID(), "BRIGADE_1", false));
//                System.out.println("BALL");
//                brigadeDao.create(new Brigade(UUID.randomUUID(), "BRIGADE_2", false));
//                System.out.println("BALL");
//                raceDao.create(new Race(UUID.randomUUID(), "DEPART_1", "ARRIVE_1", Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), 450, 2000));
//                System.out.println("BALL");
//                raceDao.create(new Race(UUID.randomUUID(), "DEPART_2", "ARRIVE_2", Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), 500, 1500));
//                System.out.println("BALL");
//            }catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
