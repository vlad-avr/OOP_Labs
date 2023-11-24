package com.example.lab3.logic;

import android.util.Log;

import com.example.lab3.mapping.MapHolder;

public class PathFinding {

    public static class Pair {
        int first;
        int second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Pair && this.first == ((Pair)obj).first && this.second == ((Pair)obj).second;
        }
    }
    class Cell{
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
    private double calculateHValue(int posX, int posY, int destX, int destY)
    {
        return Math.sqrt(Math.pow((posX - destX), 2.0) + Math.pow((posY - destY), 2.0));
    }
/*
    void aStarSearch(MapHolder mapHolder, int startX, int startY, int destX, int destY)
    {

        if (!mapHolder.inBounds(startY, startX)) {
            Log.d("A*","Source is invalid...");
            return;
        }


        if (!mapHolder.inBounds(destY, destX)) {
            Log.d("A*","Destination is invalid...");
            return;
        }


        if (!mapHolder.tileIsPassable(startY, startX)
                || !mapHolder.tileIsPassable(destY, destX)) {
            Log.d("A*","Source or destination is blocked...");
            return;
        }


        if (startX == destX && startY == destY) {
            Log.d("A*","We're already (t)here...");
            return;
        }


        boolean[][] closedList = new boolean[MapHolder.WIDTH][MapHolder.HEIGHT];//our closed list

        Cell[][] cellDetails = new Cell[MapHolder.WIDTH][MapHolder.HEIGHT];

        int i, j;
        // Initialising of the starting cell
        i = startY;
        j = startX;
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

            // Generating all the 8 neighbors of the cell

            for (int addX = -1; addX <= 1; addX++) {
                for (int addY = -1; addY <= 1; addY++) {
                    Pair neighbour = new Pair(i + addX, j + addY);
                    if (isValid(grid, rows, cols, neighbour)) {
                        if(cellDetails[neighbour.first] == null){ cellDetails[neighbour.first] = new Cell[cols]; }
                        if (cellDetails[neighbour.first][neighbour.second] == null) {
                            cellDetails[neighbour.first][neighbour.second] = new Cell();
                        }

                        if (isDestination(neighbour, dest)) {
                            cellDetails[neighbour.first][neighbour.second].parent = new Pair ( i, j );
                            System.out.println("The destination cell is found");
                            tracePath(cellDetails, rows, cols, dest);
                            return;
                        }

                        else if (!closedList[neighbour.first][neighbour.second]
                                && isUnBlocked(grid, rows, cols, neighbour)) {
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

        System.out.println("Failed to find the Destination Cell");
    }
*/
}
