package lab.cotroller;

import java.util.Scanner;
import lab.flowers.Flower;
import lab.flowers.Tulip;
import lab.db_manager.DataBaseManager;
import lab.db_manager.DataBaseManager.FlowerSaver;
import lab.flowers.Bouquet;
import lab.flowers.Daisy;
import lab.flowers.Rose;

public class Controller {
    private DataBaseManager db_manager = new DataBaseManager();

    public void start() {
        db_manager.setup_database();
        main_loop();
    }

    private void main_loop() {
        System.out.println("Welcome to BoUwUquet Manager!\n");
        print_help();
        while (true) {
            try {
                String input = get_input();
                switch (input) {
                    case "d_flower_add":
                        add_flower();
                        break;
                    case "d_flower_remove":
                        /* Remove flower */;
                    case "d_flower_update":
                        update_flower();
                        break;
                    case "d_bunch_update":
                        update_flower();
                        break;
                    case "d_bunch_add":
                        add_bouquet();
                        break;
                    case "d_bunch_remove":
                        /* Remove bouquet */;
                    case "show_flowers":
                        print_flowers();
                        break;
                    case "show_bunches":
                        print_bouquets();
                        break;
                    case "b_add":
                        /* Add flower to bouquet */;
                    case "b_remove":
                        /* Remove flower from bouquet */;
                    case "b_cost":
                        /* Cost of bouquet */;
                    case "b_sort":
                        /* Sort flowers in bouquet */;
                    case "f_find":
                        /* Find flowers */;
                    case "help":
                        print_help();
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("\nInvalid input command!\n");
                        break;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void print_flowers() {
        db_manager.print_all_flowers(-1L);
    }

    private void print_bouquets() {
        db_manager.print_all_bouquets(-1L);
    }

    private void update_flower() {
        String input;
        System.out.println("\n Enter ID of the flower you want to update:\n");
        input = get_input();
        Long id = 0L;
        try {
            id = Long.parseLong(input);
            if (id < 0) {
                NumberFormatException exception = new NumberFormatException("ID is a negative value");
                throw exception;
            }
            db_manager.print_all_flowers(id);
        } catch (NumberFormatException e) {
            System.out.println("\n Invalid ID format: " + e.getMessage());
            return;
        }

        // String type = db_manager.get_flower_type(id);

        FlowerSaver<Tulip> flower_loader = db_manager.new FlowerSaver<>();
        Tulip new_flower = new Tulip();
        flower_loader.load_flower(id, new_flower);

        System.out.println(
                "\n What property you would like to change (stalk_length / price / freshness / unique_property / bouquet_id):\n");
        while (true) {
            input = get_input();
            switch (input) {
                case "stalk_length":
                    System.out.println("\n Enter value:\n");
                    input = get_input();
                    float new_val = 0f;
                    try {
                        new_val = Float.parseFloat(input);
                        if (new_val < 0) {
                            NumberFormatException exception = new NumberFormatException();
                            throw exception;
                        }
                        new_flower.set_stalk_len(new_val);
                        break;
                    } catch (NumberFormatException exception) {
                        System.out.println(
                                "\nStalk length must be a numeric value greater than 0 (example: 10 or 0.5)\n");
                    }
                    break;
                case "price":
                    System.out.println("\n Enter value:\n");
                    input = get_input();
                    new_val = 0f;
                    try {
                        new_val = Float.parseFloat(input);
                        if (new_val < 0) {
                            NumberFormatException exception = new NumberFormatException();
                            throw exception;
                        }
                        new_flower.set_price(new_val);
                        break;
                    } catch (NumberFormatException exception) {
                        System.out.println(
                                "\nPrice must be a numeric value greater than 0 (example: 10 or 0.5)\n");
                    }
                    break;
                case "freshness":
                    System.out.println("\n Enter value:\n");
                    input = get_input();
                    new_val = 0f;
                    try {
                        new_val = Float.parseFloat(input);
                        if (new_val < 0 || new_val > 1) {
                            NumberFormatException exception = new NumberFormatException();
                            throw exception;
                        }
                        new_flower.set_stalk_len(new_val);
                        break;
                    } catch (NumberFormatException exception) {
                        System.out.println(
                                "\nFreshness must be a numeric value in range [0.0 , 1.0] (example: 0.4)\n");
                    }
                    break;
                case "unique_property":
                    System.out.println("\n Enter value:\n");
                    input = get_input();
                    new_flower.set_unique_prop(input);
                    break;
                case "bouquet_id":
                    System.out.println("\n Enter value:\n");
                    input = get_input();
                    Long new_id = 0L;
                    try {
                        new_id = Long.parseLong(input);
                        if (new_id < 0) {
                            NumberFormatException exception = new NumberFormatException();
                            throw exception;
                        }
                        Bouquet bouquet = db_manager.get_bouquet(new_id, false);
                        if (bouquet != null) {
                            new_flower.set_in_bouquet(new_id);
                        }
                        break;
                    } catch (NumberFormatException exception) {
                        System.out.println(
                                "\nPrice must be a numeric value greater than 0 (example: 10 or 0.5)\n");
                    }
                    break;
                default:
                    System.out.println("\n Unknown property \n");
                    break;
            }
            System.out.println("\nAnything else you want to change (+ / -):\n");
            input = get_input();
            if (input.equals("+")) {
                continue;
            } else {
                break;
            }
        }
        flower_loader.update_flower(id, new_flower);
    }

    private void add_bouquet() {
        String input;
        System.out.println("\nName of bouquet :\n");
        input = get_input();
        Bouquet bouquet = new Bouquet();
        bouquet.set_name(input);
        db_manager.add_bouquet(bouquet);
    }

    private void add_flower() {
        System.out.println("\n What type of flower you want to add (tulip / rose / daisy):\n");
        String input = get_input();
        switch (input) {
            case "tulip":
                add_tulip();
                break;
            case "rose":
                add_rose();
                break;
            case "daisy":
                add_daisy();
                break;
            default:
                System.out.println("We don't have this kind of flowers in stock!\n");
        }
    }

    private void gen_flower(Flower flower) {
        String input;
        float val;
        while (true) {
            System.out.println("\nStalk length:\n");
            input = get_input();
            try {
                val = Float.parseFloat(input);
                if (val < 0) {
                    NumberFormatException exception = new NumberFormatException();
                    throw exception;
                }
                break;
            } catch (NumberFormatException exception) {
                System.out.println("\nStalk length must be a numeric value greater than 0 (example: 10 or 0.5)\n");
            }
        }
        flower.set_stalk_len(val);
        while (true) {
            System.out.println("\nPrice:\n");
            input = get_input();
            try {
                val = Float.parseFloat(input);
                if (val < 0) {
                    NumberFormatException exception = new NumberFormatException();
                    throw exception;
                }
                break;
            } catch (NumberFormatException exception) {
                System.out.println("\nPrice must be a numeric value greater than 0 (example: 10 or 0.5)\n");
            }
        }
        flower.set_price(val);
        while (true) {
            System.out.println("\nFreshness (from 0 to 1, where 0 - not fresh at all and 1 - very fresh):\n");
            input = get_input();
            try {
                val = Float.parseFloat(input);
                if (val < 0 || val > 1) {
                    NumberFormatException exception = new NumberFormatException();
                    throw exception;
                }
                break;
            } catch (NumberFormatException exception) {
                System.out.println("\nFreshness must be a numeric value in range [0.0 , 1.0] (example: 0.4)\n");
            }
        }
        flower.set_fresh(val);
    }

    private void add_tulip() {
        Flower flower = new Tulip();
        gen_flower(flower);
        Tulip tulip = (Tulip) flower;
        System.out.println("\nColor:\n");
        tulip.set_unique_prop(get_input());
        System.out.println("\nAdd to existing bouquet? (+ / -)\n");
        String input;
        while (true) {
            input = get_input();
            if (input.equals("+")) {
                // Add to existing bouquet
                break;
            } else if (input.equals("-")) {
                break;
            } else {
                System.out.println(
                        "\nEnter '+' if you want to add this flower to existing bouquet or '-' if you don`t!\n");
            }
        }
        FlowerSaver<Tulip> saver = db_manager.new FlowerSaver<Tulip>();
        saver.add_flower(tulip);
    }

    private void add_daisy() {
        Flower flower = new Daisy();
        gen_flower(flower);
        Daisy daisy = (Daisy) flower;
        System.out.println("\nFlower diameter (big / small / medium etc.):\n");
        daisy.set_unique_prop(get_input());
        System.out.println("\nAdd to existing bouquet? (+ / -)\n");
        String input;
        while (true) {
            input = get_input();
            if (input.equals("+")) {
                // Add to existing bouquet
            } else if (input.equals("-")) {
                break;
            } else {
                System.out.println(
                        "\nEnter '+' if you want to add this flower to existing bouquet or '-' if you don`t!\n");
            }
        }
        FlowerSaver<Daisy> saver = db_manager.new FlowerSaver<Daisy>();
        saver.add_flower(daisy);
    }

    private void add_rose() {
        Flower flower = new Rose();
        gen_flower(flower);
        Rose rose = (Rose) flower;
        System.out.println("\nSpikes Properties (large / small / absent etc.):\n");
        rose.set_unique_prop(get_input());
        System.out.println("\nAdd to existing bouquet? (+ / -)\n");
        String input;
        while (true) {
            input = get_input();
            if (input.equals("+")) {
                // Add to existing bouquet
            } else if (input.equals("-")) {
                break;
            } else {
                System.out.println(
                        "\nEnter '+' if you want to add this flower to existing bouquet or '-' if you don`t!\n");
            }
        }
        FlowerSaver<Rose> saver = db_manager.new FlowerSaver<Rose>();
        saver.add_flower(rose);
    }

    private String get_input() {
        Scanner scan = new Scanner(System.in);
        String input;
        do {
            input = scan.nextLine();
        } while (input == null || input.isEmpty());
        // dsdscan.close();
        return input;
    }

    private void print_help() {
        System.out.println(
                "\n List of commands:\n d_flower_add -> add flower to database\n d_flower_remove -> remove flower from database\n d_bunch_add -> add bouquet to database\n d_bunch_remove -> remove bouquet from database\n show_flowers -> show all flowers in database\n show_bunches -> show all bouquets in database\n b_add -> add flower to bouquet\n b_remove -> remove flower from database\n b_cost -> calculate cost of bouquet\n b_sort -> sort flowers in bouquet\n f_find -> find flowers in given stalk length span\n help -> print help\n exit -> exit program");
    }
}
