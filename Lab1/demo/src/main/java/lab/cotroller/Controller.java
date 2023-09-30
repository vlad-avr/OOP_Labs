package lab.cotroller;

import java.util.ArrayList;
import java.util.List;
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
                        delete_flower();
                        break;
                    case "d_flower_update":
                        update_flower();
                        break;
                    case "d_bunch_update":
                        update_bunch();
                        break;
                    case "d_bunch_add":
                        add_bouquet();
                        break;
                    case "d_bunch_remove":
                        delete_bouquet();
                        break;
                    case "show_flowers":
                        print_flowers();
                        break;
                    case "show_bunches":
                        print_bouquets();
                        break;
                    case "b_add":
                        String id_input;
                        System.out.println("\n Enter ID of the flower you want to add to bouquet:\n");
                        id_input = get_input();
                        try {
                            Long id = Long.parseLong(id_input);
                            if (id < 0) {
                                NumberFormatException exception = new NumberFormatException("ID is a negative value");
                                throw exception;
                            }
                            add_flower_to_bouquet(id);
                        } catch (NumberFormatException e) {
                            System.out.println("\n Invalid ID format: " + e.getMessage());
                        }
                        break;
                    case "b_remove":
                        System.out.println("\n Enter ID of the flower you want to remove from bouquet:\n");
                        id_input = get_input();
                        try {
                            Long id = Long.parseLong(id_input);
                            if (id < 0) {
                                NumberFormatException exception = new NumberFormatException("ID is a negative value");
                                throw exception;
                            }
                            db_manager.add_flower_to_bouquet(id, -1L);
                        } catch (NumberFormatException e) {
                            System.out.println("\n Invalid ID format: " + e.getMessage());
                        }
                        break;
                    case "b_cost":
                        calculate_cost_of_bouquet();
                        break;
                    case "b_sort":
                        sort_flowers_by_freshness();
                        break;
                    case "f_find":
                        /* Find flowers */;
                    case "help":
                        print_help();
                        break;
                    case "exit":
                        delete_tables();
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

    private void sort_flowers_by_freshness(){
        String input;
        System.out.println("\n Enter ID of the bunch which cost you want to know:\n");
        input = get_input();
        Long id = 0L;
        Bouquet bouquet = null;
        try {
            id = Long.parseLong(input);
            if (id < 0) {
                NumberFormatException exception = new NumberFormatException("ID is a negative value");
                throw exception;
            }
            db_manager.print_all_bouquets(id);
            bouquet = db_manager.get_bouquet(id, true);
        } catch (NumberFormatException e) {
            System.out.println("\n Invalid ID format: " + e.getMessage());
            return;
        }
        if (bouquet != null){
            bouquet.sort();
            bouquet.print();
        }
    }

    private void calculate_cost_of_bouquet() {
        String input;
        System.out.println("\n Enter ID of the bunch which cost you want to know:\n");
        input = get_input();
        Long id = 0L;
        Bouquet bouquet = null;
        try {
            id = Long.parseLong(input);
            if (id < 0) {
                NumberFormatException exception = new NumberFormatException("ID is a negative value");
                throw exception;
            }
            db_manager.print_all_bouquets(id);
            bouquet = db_manager.get_bouquet(id, true);
        } catch (NumberFormatException e) {
            System.out.println("\n Invalid ID format: " + e.getMessage());
            return;
        }
        if (bouquet != null)
            System.out.println("\n Cost of " + bouquet.get_name() + " : " + bouquet.calculate_cost() + " $");
    }

    private void delete_tables() {
        db_manager.destroy_database();
    }

    private void delete_bouquet() {
        Long ID = -1L;
        System.out.println("\n Enter ID of the bouquet you want to delete: \n");
        String input;
        while (true) {
            input = get_input();
            try {
                ID = Long.parseLong(input);
                if (ID < 0) {
                    NumberFormatException exception = new NumberFormatException("ID is a negative value");
                    throw exception;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\n Invalid ID format: " + e.getMessage());
                break;
            }
        }
        if (ID != -1L) {
            System.out.println("\n Do you want to delete flowers from this bouquet as well (+ / -)?\n");
            while (true) {
                input = get_input();
                if (input.equals("+")) {
                    db_manager.delete_bouquet(ID, true);
                    break;
                } else if (input.equals("-")) {
                    db_manager.delete_bouquet(ID, false);
                    break;
                } else {
                    System.out.println("\n Enter + if you want to delete flowers and - if you don`t!\n");
                }
            }
        }
    }

    private void delete_flower() {
        Long ID = -1L;
        System.out.println("\n Enter ID of the flower you want to delete: \n");
        String input;
        while (true) {
            input = get_input();
            try {
                ID = Long.parseLong(input);
                if (ID < 0) {
                    NumberFormatException exception = new NumberFormatException("ID is a negative value");
                    throw exception;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\n Invalid ID format: " + e.getMessage());
                break;
            }
        }
        if (ID != -1L) {
            db_manager.delete_flower(ID);
        }
    }

    private void print_flowers() {
        db_manager.print_all_flowers(-1L);
    }

    private void print_bouquets() {
        db_manager.print_all_bouquets(-1L);
    }

    private void update_bunch() {
        String input;
        System.out.println("\n Enter ID of the bunch you want to update:\n");
        input = get_input();
        Long id = 0L;
        try {
            id = Long.parseLong(input);
            if (id < 0) {
                NumberFormatException exception = new NumberFormatException("ID is a negative value");
                throw exception;
            }
            db_manager.print_all_bouquets(id);
        } catch (NumberFormatException e) {
            System.out.println("\n Invalid ID format: " + e.getMessage());
            return;
        }
        Bouquet bouquet = db_manager.get_bouquet(id, false);
        System.out.println(
                "\n What property you would like to change (name / ):\n");
        while (true) {
            input = get_input();
            switch (input) {
                case "name":
                    System.out.println("\n Enter new name:\n");
                    input = get_input();
                    bouquet.set_name(input);
                    break;
                default:
                    System.out.println("\nUnknown property!\n");
            }
            System.out.println("\n Anything else you want to change (+ / -)?\n");
            input = get_input();
            if (input.equals("+")) {
                continue;
            } else {
                break;
            }
        }
        db_manager.update_bouquet(id, bouquet);
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

    private void add_flower_to_bouquet(Long flower_id) {
        Long ID;
        String input;
        System.out.println("\n Enter ID of existing bouquet:\n");
        while (true) {
            input = get_input();
            try {
                ID = Long.parseLong(input);
                if (ID < 0) {
                    NumberFormatException exception = new NumberFormatException("ID is a negative value");
                    throw exception;
                }
                break;
            } catch (NumberFormatException exception) {
                System.out.println("\n Invalid ID format: " + exception.getMessage());
            }
        }
        db_manager.add_flower_to_bouquet(flower_id, ID);
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
                Long b_id;
                System.out.println("\n Enter ID of existing bouquet:\n0");
                while (true) {
                    input = get_input();
                    try {
                        b_id = Long.parseLong(input);
                        Bouquet bouquet = db_manager.get_bouquet(b_id, false);
                        if (bouquet != null) {
                            tulip.set_in_bouquet(b_id);
                        } else {
                            NumberFormatException exception = new NumberFormatException();
                            throw exception;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\n Bouquet with this ID does not exist!\n");
                    }
                }
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
                Long b_id;
                System.out.println("\n Enter ID of existing bouquet:\n0");
                while (true) {
                    input = get_input();
                    try {
                        b_id = Long.parseLong(input);
                        Bouquet bouquet = db_manager.get_bouquet(b_id, false);
                        if (bouquet != null) {
                            daisy.set_in_bouquet(b_id);
                        } else {
                            NumberFormatException exception = new NumberFormatException();
                            throw exception;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\n Bouquet with this ID does not exist!\n");
                    }
                }
                break;
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
                Long b_id;
                System.out.println("\n Enter ID of existing bouquet:\n0");
                while (true) {
                    input = get_input();
                    try {
                        b_id = Long.parseLong(input);
                        Bouquet bouquet = db_manager.get_bouquet(b_id, false);
                        if (bouquet != null) {
                            rose.set_in_bouquet(b_id);
                        } else {
                            NumberFormatException exception = new NumberFormatException();
                            throw exception;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\n Bouquet with this ID does not exist!\n");
                    }
                }
                break;
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
                "\n List of commands:\n d_flower_add -> add flower to database\n d_flower_remove -> remove flower from database\n d_flower_update -> update flower data\n d_bunch_update -> update bouquet data\n d_bunch_add -> add bouquet to database\n d_bunch_remove -> remove bouquet from database\n show_flowers -> show all flowers in database\n show_bunches -> show all bouquets in database\n b_add -> add flower to bouquet\n b_remove -> remove flower from database\n b_cost -> calculate cost of bouquet\n b_sort -> sort flowers in bouquet\n f_find -> find flowers in given stalk length span\n help -> print help\n exit -> exit program");
    }
}
