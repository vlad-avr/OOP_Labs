package lab.cotroller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        return get_float_input(0.0f, Float.MAX_VALUE);
    }

    public float get_float_input(String ptompt, float lower_bound, float upper_bound){
        System.out.println(ptompt);
        return get_float_input(lower_bound, upper_bound);
    }

    public float get_float_input(float lower_bound, float upper_bound) {
        float res = -1.0f;
        while (true) {
            String input = get_line();
            try {
                res = Float.parseFloat(input);
                if (res < lower_bound || res > upper_bound) {
                    NumberFormatException exception = new NumberFormatException("\n Number must be in range of [" + lower_bound + " , " + upper_bound + "]");
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

    public String getDateInput(String prompt){
        System.out.println(prompt);
        return getDateInput();
    }

    public String getDateInput(){
        while(true){
            String toDate = get_string_input();
            try {
                LocalDate date = LocalDate.parse(toDate);
                if(LocalDate.now().isBefore(date)){
                    DateTimeParseException e = new DateTimeParseException("Impossible date : entered date goes after current date!", toDate, 0, null);
                    throw e;
                }
                return toDate;
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Long getLongInput(String prompt){
        System.out.println(prompt);
        return getLongInput();
    }

    public Long getLongInput(){
        Long id = 0L;
        while (true) {
            String input = get_line();
            try {
                id = Long.parseLong(input);
                if (id < 0L) {
                    NumberFormatException exception = new NumberFormatException("This value can`t be a negative value");
                    throw exception;
                }
                return id;
            } catch (NumberFormatException e) {
                System.out.println("\n Invalid Long format: " + e.getMessage());
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
