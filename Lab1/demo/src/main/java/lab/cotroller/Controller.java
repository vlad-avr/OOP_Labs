package lab.cotroller;

import java.util.List;

import lab.flowers.Flower;
import lab.flowers.Tulip;
import lab.db_manager.DataBaseManager;
import lab.db_manager.DataBaseManager.FlowerSaver;
import lab.flowers.Bouquet;
import lab.flowers.Daisy;
import lab.flowers.Rose;

public class Controller {
    private DataBaseManager db_manager;
    private InputManager inputManager = new InputManager();

    public Controller(){
        db_manager = new DataBaseManager();
    }

    public Controller(DataBaseManager db_manager){
        this.db_manager = db_manager;
    }

    public void start() {
        db_manager.setup_database(false);
        main_loop();
    }

    private void main_loop() {
        System.out.println("\n\tWelcome to BoUwUquet Manager!\n");
        print_help();
        while (true) {
            try {
                String input = inputManager.get_string_input();
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
                        System.out.println("\n Enter ID of the flower you want to add to bouquet:\n");
                        Long id_input = inputManager.get_id_input();
                        add_flower_to_bouquet(id_input);
                        break;
                    case "b_remove":
                        System.out.println("\n Enter ID of the flower you want to remove from bouquet:\n");
                        id_input = inputManager.get_id_input();
                        add_flower_to_bouquet_impl(id_input, -1L);
                        break;
                    case "b_cost":
                        calculate_cost_of_bouquet();
                        break;
                    case "b_sort":
                        sort_flowers_by_freshness();
                        break;
                    case "f_find":
                        find_flowers_by_length();
                        break;
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

    private void sort_flowers_by_freshness() {
        System.out.println("\n Enter ID of the bunch tha you want to sort:\n");
        Long id = inputManager.get_id_input();
        sort_flowers_by_freshness_impl(id);
    }

    private void sort_flowers_by_freshness_impl(Long ID) {
        db_manager.print_all_bouquets(ID);
        Bouquet bouquet = db_manager.get_bouquet(ID, true);
        if (bouquet != null) {
            bouquet.sort();
            bouquet.print();
        }
    }

    private void find_flowers_by_length() {
        System.out.println("\n Enter ID of bouquet you want to search in:\n");
        Long ID = inputManager.get_id_input();
        float min;
        float max;
        System.out.println("\n Enter minimum length of stalk :\n");
        min = inputManager.get_float_input();
        System.out.println("\n Enter maximum length of stalk :\n");
        max = inputManager.get_float_input();

        if (ID != -1L) {
            List<Flower> flowers = find_flowers_by_length_impl(ID, min, max);
            for (int i = 0; i < flowers.size(); i++) {
                flowers.get(i).print();
            }
        }
    }

    private List<Flower> find_flowers_by_length_impl(Long Id, float min, float max) {
        return db_manager.find_flowers_by_length(Id, min, max);
    }

    private void calculate_cost_of_bouquet() {
        System.out.println("\n Enter ID of the bunch which cost you want to know:\n");
        Long id = inputManager.get_id_input();
        Bouquet bouquet = get_bouquet_impl(id, true);
        System.out.println("\n Cost of " + bouquet.get_name() + " : " + bouquet.calculate_cost() + " $");
    }

    private Bouquet get_bouquet_impl(Long id, boolean print) {
        if (print) {
            db_manager.print_all_bouquets(id);
        }
        Bouquet bouquet = db_manager.get_bouquet(id, true);
        return bouquet;
    }

    private void delete_tables() {
        db_manager.destroy_database();
    }

    private void delete_bouquet() {
        System.out.println("\n Enter ID of the bouquet you want to delete: \n");
        Long ID = inputManager.get_id_input();
        if (ID != -1L) {
            System.out.println("\n Do you want to delete flowers from this bouquet as well (+ / -)?\n");
            boolean check = inputManager.get_bool_input();
            delete_bouquet_impl(ID, check);
        }
    }

    private void delete_bouquet_impl(Long ID, boolean and_flowers) {
        db_manager.delete_bouquet(ID, and_flowers);
    }

    private void delete_flower() {
        System.out.println("\n Enter ID of the flower you want to delete: \n");
        Long ID = inputManager.get_id_input();
        if (ID != -1L) {
            delete_flower_impl(ID);
        }
    }

    private void delete_flower_impl(Long ID) {
        db_manager.delete_flower(ID);
    }

    private void print_flowers() {
        db_manager.print_all_flowers(-1L);
    }

    private void print_bouquets() {
        db_manager.print_all_bouquets(-1L);
    }

    private void update_bunch() {
        System.out.println("\n Enter ID of the bunch you want to update:\n");
        Long id = inputManager.get_id_input();
        db_manager.print_all_bouquets(id);
        Bouquet bouquet = db_manager.get_bouquet(id, false);
        System.out.println(
                "\n What property you would like to change (name / ):\n");
        while (true) {
            String input = inputManager.get_string_input();
            switch (input) {
                case "name":
                    System.out.println("\n Enter new name:\n");
                    input = inputManager.get_string_input();
                    bouquet.set_name(input);
                    break;
                default:
                    System.out.println("\nUnknown property!\n");
            }
            System.out.println("\n Anything else you want to change (+ / -)?\n");
            boolean eboolean = inputManager.get_bool_input();
            if (eboolean) {
                continue;
            } else {
                break;
            }
        }
        update_bunch_impl(id, bouquet);
    }

    private void update_bunch_impl(Long id, Bouquet new_bouquet){
        db_manager.update_bouquet(id, new_bouquet);
    }

    private void update_flower() {
        System.out.println("\n Enter ID of the flower you want to update:\n");
        Long id = inputManager.get_id_input();
        db_manager.print_all_flowers(id);

        FlowerSaver<Tulip> flower_loader = db_manager.new FlowerSaver<>();
        Tulip new_flower = new Tulip();
        flower_loader.load_flower(id, new_flower);

        System.out.println(
                "\n What property you would like to change (stalk_length / price / freshness / unique_property / bouquet_id):\n");
        while (true) {
            String input = inputManager.get_string_input();
            switch (input) {
                case "stalk_length":
                    System.out.println("\n Enter value:\n");
                    float new_val = inputManager.get_float_input();
                    new_flower.set_stalk_len(new_val);
                    break;
                case "price":
                    System.out.println("\n Enter value:\n");
                    new_val = inputManager.get_float_input();
                    new_flower.set_price(new_val);
                    break;
                case "freshness":
                    System.out.println("\n Enter value:\n");
                    new_val = inputManager.get_float_input();
                    new_flower.set_fresh(new_val);
                    break;
                case "unique_property":
                    System.out.println("\n Enter value:\n");
                    input = inputManager.get_string_input();
                    new_flower.set_unique_prop(input);
                    break;
                case "bouquet_id":
                    System.out.println("\n Enter value:\n");
                    Long new_id = inputManager.get_id_input();
                    if(new_id == -1L){
                        new_flower.set_in_bouquet(new_id);
                        break;
                    }
                    Bouquet bouquet = db_manager.get_bouquet(new_id, false);
                    if(bouquet != null){
                        new_flower.set_in_bouquet(new_id);
                    }
                    break;
                default:
                    System.out.println("\n Unknown property \n");
                    break;
            }
            System.out.println("\nAnything else you want to change (+ / -):\n");
            boolean eboolean = inputManager.get_bool_input();
            if (eboolean) {
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
        input = inputManager.get_string_input();
        add_bouquet_impl(input);
    }

    private void add_bouquet_impl(String name) {
        Bouquet bouquet = new Bouquet();
        bouquet.set_name(name);
        db_manager.add_bouquet(bouquet);
    }

    private void add_flower() {
        System.out.println("\n What type of flower you want to add (tulip / rose / daisy):\n");
        String input = inputManager.get_string_input();
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

    private void gen_flower(Flower flower, float stalk_length, float price, float freshness) {
        flower.set_stalk_len(stalk_length);
        flower.set_price(price);
        flower.set_fresh(freshness);
    }

    private void add_flower_to_bouquet(Long flower_id) {
        System.out.println("\n Enter ID of existing bouquet:\n");
        Long ID = inputManager.get_id_input();
        add_flower_to_bouquet_impl(flower_id, ID);
    }

    private void add_flower_to_bouquet_impl(Long flower_id, Long bouqet_id) {
        db_manager.add_flower_to_bouquet(flower_id, bouqet_id);
    }

    private void add_tulip() {
        Flower flower = new Tulip();
        gen_flower(flower, inputManager.get_float_input("\nEnter stalk length: "), inputManager.get_float_input("\nEnter price: "),
                inputManager.get_float_input("\nEnter freshness: "));
        Tulip tulip = (Tulip) flower;
        System.out.println("\nColor:\n");
        tulip.set_unique_prop(inputManager.get_string_input());
        System.out.println("\nAdd to existing bouquet? (+ / -)\n");
        boolean eboolean = inputManager.get_bool_input();
        if (eboolean) {
            Long b_id = inputManager.get_id_input();
            Bouquet bouquet = get_bouquet_impl(b_id, false);
            if (bouquet != null) {
                tulip.set_in_bouquet(b_id);
            } else {
                tulip.set_in_bouquet(-1L);
            }
        }else{
            tulip.set_in_bouquet(-1L);
        }
        FlowerSaver<Tulip> saver = db_manager.new FlowerSaver<Tulip>();
        saver.add_flower(tulip);
    }

    private void add_daisy() {
        Flower flower = new Daisy();
        gen_flower(flower, inputManager.get_float_input("\nEnter stalk length: "), inputManager.get_float_input("\nEnter price: "),
                inputManager.get_float_input("\nEnter freshness: "));
        Daisy daisy = (Daisy) flower;
        System.out.println("\nFlower diameter (big / small / medium etc.):\n");
        daisy.set_unique_prop(inputManager.get_string_input());
        System.out.println("\nAdd to existing bouquet? (+ / -)\n");
        boolean eboolean = inputManager.get_bool_input();
        if (eboolean) {
            Long b_id = inputManager.get_id_input();
            Bouquet bouquet = get_bouquet_impl(b_id, false);
            if (bouquet != null) {
                daisy.set_in_bouquet(b_id);
            } else {
                daisy.set_in_bouquet(-1L);
            }
        }else{
            daisy.set_in_bouquet(-1L);
        }
        FlowerSaver<Daisy> saver = db_manager.new FlowerSaver<Daisy>();
        saver.add_flower(daisy);
    }

    private void add_rose() {
        Flower flower = new Rose();
        gen_flower(flower, inputManager.get_float_input("\nEnter stalk length: "), inputManager.get_float_input("\nEnter price: "),
                inputManager.get_float_input("\nEnter freshness: "));
        Rose rose = (Rose) flower;
        System.out.println("\nSpikes Properties (large / small / absent etc.):\n");
        rose.set_unique_prop(inputManager.get_string_input());
        System.out.println("\nAdd to existing bouquet? (+ / -)\n");
        boolean eboolean = inputManager.get_bool_input();
        if (eboolean) {
            Long b_id = inputManager.get_id_input();
            Bouquet bouquet = get_bouquet_impl(b_id, false);
            if (bouquet != null) {
                rose.set_in_bouquet(b_id);
            } else {
                rose.set_in_bouquet(-1L);
            }
        }else{
            rose.set_in_bouquet(-1L);
        }
        FlowerSaver<Rose> saver = db_manager.new FlowerSaver<Rose>();
        saver.add_flower(rose);
    }

    private void print_help() {
        System.out.println(
                "\n List of commands:\n d_flower_add -> add flower to database\n d_flower_remove -> remove flower from database\n d_flower_update -> update flower data\n d_bunch_update -> update bouquet data\n d_bunch_add -> add bouquet to database\n d_bunch_remove -> remove bouquet from database\n show_flowers -> show all flowers in database\n show_bunches -> show all bouquets in database\n b_add -> add flower to bouquet\n b_remove -> remove flower from database\n b_cost -> calculate cost of bouquet\n b_sort -> sort flowers in bouquet\n f_find -> find flowers in given stalk length span\n help -> print help\n exit -> exit program");
    }
}
