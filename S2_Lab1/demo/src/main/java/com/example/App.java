package com.example;

import com.example.db.DBFuntionsTest;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        DBFuntionsTest test = new DBFuntionsTest();
        test.getConnection("Aircompany", "postgres", "Vlad10092004");
    }
}
