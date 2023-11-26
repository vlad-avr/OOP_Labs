package com.example.lab3.logic;

import android.util.Log;

import com.example.lab3.mapping.MapHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class PathFinding {

    public static class Pair {
        public int first;
        public int second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Pair && this.first == ((Pair)obj).first && this.second == ((Pair)obj).second;
        }
    }
    public static class Cell{
        public double g;
        public double h;
        public double f;
        public Pair parent;

        public Cell(){
            this.g = -1;
            this.h = -1;
            this.parent = new Pair(-1, -1);
            this.f = -1;
        }
        public Cell(double g, double h, float f, Pair parent){
            this.g = g;
            this.h = h;
            this.parent = parent;
            this.f = f;
        }
    }

    public static class Details {
        double value;
        int i;
        int j;

        public Details(double value, int i, int j) {
            this.value = value;
            this.i = i;
            this.j = j;
        }
    }
    private static double calculateHValue(Pair p1, Pair p2)
    {
        return Math.sqrt(Math.pow((p1.first - p2.first), 2.0) + Math.pow((p1.second - p2.second), 2.0));
    }

    static Pair tracePath(Cell[][] cellDetails, int cols, int rows, Pair dest, Pair start, int speed)
    {
        int row = dest.first;
        int col = dest.second;
        List<Pair> path = new ArrayList<>();
        Pair nextNode = cellDetails[row][col].parent;
        do {
            path.add(nextNode);
            row = nextNode.first;
            col = nextNode.second;
            nextNode = cellDetails[row][col].parent;
        } while (!nextNode.equals(start)); // until src
        path.add(nextNode);
        int pointId = path.size() - speed - 1;
        if(pointId <= 0){
            pointId = path.size() - 2;
        }
        return path.get(pointId);
    }
    public static Pair aStarSearch(MapHolder mapHolder, Pair start, Pair dest, int speed)
    {

        if (!mapHolder.inBounds(start.first, start.second)) {
            Log.d("A*","Source is invalid...");
            return null;
        }


        if (!mapHolder.inBounds(dest.first, dest.second)) {
            Log.d("A*","Destination is invalid...");
            return null;
        }

/*
        if (!mapHolder.tileIsPassable(start.first, start.second)
                || !mapHolder.tileIsPassable(dest.first, dest.second)) {
            Log.d("A*","Source or destination is blocked...");
            return null;
        }*/


        if (start.equals(dest)) {
            Log.d("A*","We're already (t)here...");
            return null;
        }


        boolean[][] closedList = new boolean[MapHolder.WIDTH][MapHolder.HEIGHT];//our closed list

        Cell[][] cellDetails = new Cell[MapHolder.WIDTH][MapHolder.HEIGHT];

        int i, j;
        // Initialising of the starting cell
        i = start.first;
        j = start.second;
        cellDetails[i][j] = new Cell();
        cellDetails[i][j].f = 0.0;
        cellDetails[i][j].g = 0.0;
        cellDetails[i][j].h = 0.0;
        cellDetails[i][j].parent = new Pair( i, j );


        // Creating an open list


        PriorityQueue<Details> openList = new PriorityQueue<>((o1, o2) -> (int) Math.round(o1.value - o2.value));

        // Put the starting cell on the open list,   set f.startCell = 0

        openList.add(new Details(0.0, i, j));

        while (!openList.isEmpty()) {
            Details p = openList.peek();
            // Add to the closed list
            i = p.i; // second element of tuple
            j = p.j; // third element of tuple

            // Remove from the open list
            openList.poll();
            closedList[i][j] = true;

            for (int addX = -1; addX <= 1; addX++) {
                for (int addY = -1; addY <= 1; addY++) {
                    if(Math.abs(addX) == 1 && Math.abs(addY) == 1){
                        continue;
                    }
                    Pair neighbour = new Pair(i + addX, j + addY);
                    if (mapHolder.inBounds(neighbour.first, neighbour.second)) {
                        if(cellDetails[neighbour.first] == null){ cellDetails[neighbour.first] = new Cell[mapHolder.WIDTH]; }
                        if (cellDetails[neighbour.first][neighbour.second] == null) {
                            cellDetails[neighbour.first][neighbour.second] = new Cell();
                        }

                        if (neighbour.equals(dest)) {
                            cellDetails[neighbour.first][neighbour.second].parent = new Pair ( i, j );
                            return tracePath(cellDetails, mapHolder.HEIGHT, mapHolder.WIDTH, dest, start, speed);
                        }

                        else if (!closedList[neighbour.first][neighbour.second]
                                && mapHolder.tileIsPassable(neighbour.first, neighbour.second)) {
                            double gNew, hNew, fNew;
                            gNew = cellDetails[i][j].g + 1.0;
                            hNew = calculateHValue(neighbour, dest);
                            fNew = gNew + hNew;

                            if (cellDetails[neighbour.first][neighbour.second].f == -1
                                    || cellDetails[neighbour.first][neighbour.second].f > fNew) {

                                openList.add(new Details(fNew, neighbour.first, neighbour.second));

                                // Update the details of this
                                // cell
                                cellDetails[neighbour.first][neighbour.second].g = gNew;
//heuristic function                               cellDetails[neighbour.first][neighbour.second].h = hNew;
                                cellDetails[neighbour.first][neighbour.second].f = fNew;
                                cellDetails[neighbour.first][neighbour.second].parent = new Pair( i, j );
                            }
                        }
                    }
                }
            }
        }
        Log.d("A*", "Failed to find the Destination Cell");
        return null;
    }
}
