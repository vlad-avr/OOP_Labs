package lab.cotroller;

import java.util.Scanner;

public class InputManager {

    public String get_string_input(String prompt){
        System.out.println(prompt);
        return get_string_input();
    }

    public String get_string_input() {
        return get_line();
    }

    public Long get_id_input(String prompt) {
        System.out.println(prompt);
        return get_id_input();
    }

    public Long get_id_input() {
        Long id = -1L;
        while (true) {
            String input = get_line();
            try {
                id = Long.parseLong(input);
                if (id < -1L) {
                    NumberFormatException exception = new NumberFormatException("ID is a negative value");
                    throw exception;
                }
                return id;
            } catch (NumberFormatException e) {
                System.out.println("\n Invalid ID format: " + e.getMessage());
            }
        }
    }

    public float get_float_input(String prompt) {
        System.out.println(prompt);
        return get_float_input();
    }

    public float get_float_input() {
        float res = -1.0f;
        while (true) {
            String input = get_line();
            try {
                res = Float.parseFloat(input);
                if (res < 0) {
                    NumberFormatException exception = new NumberFormatException("\n Must be a positive number");
                    throw exception;
                }
                return res;
            } catch (NumberFormatException exception) {
                System.out
                        .println(exception.getMessage());
            }
        }

    }

    public boolean get_bool_input(String prompt) {
        System.out.println(prompt);
        return get_bool_input();
    }

    public boolean get_bool_input() {
        while (true) {
            String input = get_line();
            if (input.equals("+")) {
                return true;
            } else if (input.equals("-")) {
                return false;
            } else {
                System.out.println("\n Enter + if you want to delete flowers and - if you don`t!\n");
            }
        }
    }

    private String get_line() {
        Scanner scan = new Scanner(System.in);
        String input;
        do {
            input = scan.nextLine();
        } while (input == null || input.isEmpty());
        return input;
    }
}
