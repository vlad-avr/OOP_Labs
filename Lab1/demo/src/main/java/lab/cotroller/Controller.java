package lab.cotroller;

import java.util.Scanner;
import lab.flowers.Flower;
import lab.flowers.Tulip;
import lab.flowers.Daisy;
import lab.flowers.Rose;

public class Controller {
    

    public void start(){
        main_loop();
    }

    private void main_loop(){
        System.out.println("Welcome to BoUwUquet Manager!\n");
        print_help();
        while(true){
            try{
                String input = get_input();
                switch(input){
                    case "d_flower_add" : add_flower();break;
                    case "d_flower_remove" : /*Remove flower*/;
                    case "d_bunch_add" : /*Add bouquet*/;
                    case "d_bunch_remove" : /*Remove bouquet*/;
                    case "show_flowers" : /*Show flowers */;
                    case "show_bunches" : /*Show bouquets */;
                    case "b_add" : /*Add flower to bouquet */;
                    case "b_remove" : /*Remove flower from bouquet */;
                    case "b_cost" : /*Cost of bouquet */;
                    case "b_sort" : /*Sort flowers in bouquet */;
                    case "f_find" : /*Find flowers */;
                    case "help" : print_help(); break;
                    case "exit" : return;
                    default : System.out.println("\nInvalid input command!\n"); break;
                }
            }catch(Exception exception){
                exception.printStackTrace();
            }
        }
    }

    private void add_flower(){
        System.out.println("\n What type of flower you want to add (tulip / rose / daisy):\n");
        String input = get_input();
        switch(input){
            case "tulip" : add_tulip();
            case "rose" : add_rose();
            case "daisy" : add_daisy();
            default : System.out.println("We don't have this kind of flowers in stock!\n");
        }
    }

    private void gen_flower(Flower flower){
        String input;
        float val;
        while(true){
            System.out.println("\nStalk length:\n");
            input = get_input();
            try{
                val = Float.parseFloat(input);
                if(val < 0){
                    NumberFormatException exception = new NumberFormatException();
                    throw exception;
                }
                break;
            }catch(NumberFormatException exception){
                System.out.println("\nStalk length must be a numeric value greater than 0 (example: 10 or 0.5)\n");
            }
        }
        flower.set_stalk_len(val);
        while(true){
            System.out.println("\nPrice:\n");
            input = get_input();
            try{
                val = Float.parseFloat(input);
                if(val < 0){
                    NumberFormatException exception = new NumberFormatException();
                    throw exception;
                }
                break;
            }catch(NumberFormatException exception){
                System.out.println("\nPrice must be a numeric value greater than 0 (example: 10 or 0.5)\n");
            }
        }
        flower.set_price(val);
        while(true){
            System.out.println("\nFreshness (from 0 to 1, where 0 - not fresh at all and 1 - very fresh):\n");
            input = get_input();
            try{
                val = Float.parseFloat(input);
                if(val < 0 || val > 1){
                    NumberFormatException exception = new NumberFormatException();
                    throw exception;
                }
                break;
            }catch(NumberFormatException exception){
                System.out.println("\nPrice must be a numeric value in range [0.0 , 1.0] (example: 0.4)\n");
            }
        }
    }

    private void add_tulip(){
        Flower flower = new Tulip();
        gen_flower(flower);
        Tulip tulip = (Tulip)flower;
        System.out.println("\nColor:\n");
        tulip.set_color(get_input());
        System.out.println("\nAdd to existing bouquet? (+ / -)\n");
        String input;
        while(true){
            input = get_input();
            if(input == "+"){
                //Add to existing bouquet
                break;
            }
            else if(input == "-"){
                break;
            }
            else{
                System.out.println("\nEnter '+' if you want to add this flower to existing bouquet or '-' if you don`t!\n");
            }
        }
        //Save flower to DB
    }

    private void add_daisy(){
        Flower flower = new Daisy();
        gen_flower(flower);
        Daisy tulip = (Daisy)flower;
        System.out.println("\nFlower diameter (big / small / medium etc.):\n");
        tulip.set_flower_diameter(get_input());
        System.out.println("\nAdd to existing bouquet? (+ / -)\n");
        String input;
        while(true){
            input = get_input();
            if(input == "+"){
                //Add to existing bouquet
            }
            else if(input == "-"){
                break;
            }
            else{
                System.out.println("\nEnter '+' if you want to add this flower to existing bouquet or '-' if you don`t!\n");
            }
        }
        //Save flower to DB
    }

    private void add_rose(){
        Flower flower = new Rose();
        gen_flower(flower);
        Rose tulip = (Rose)flower;
        System.out.println("\nSpikes Properties (large / small / absent etc.):\n");
        tulip.set_spike_prop(get_input());
        System.out.println("\nAdd to existing bouquet? (+ / -)\n");
        String input;
        while(true){
            input = get_input();
            if(input == "+"){
                //Add to existing bouquet
            }
            else if(input == "-"){
                break;
            }
            else{
                System.out.println("\nEnter '+' if you want to add this flower to existing bouquet or '-' if you don`t!\n");
            }
        }
        //Save flower to DB
    }

    private String get_input(){
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            input = scan.nextLine();
        }while (input == null || input.isEmpty());
        //dsdscan.close();
        return input;
    }

    private void print_help(){
        System.out.println("\n List of commands:\n d_flower_add -> add flower to database\n d_flower_remove -> remove flower from database\n d_bunch_add -> add bouquet to database\n d_bunch_remove -> remove bouquet from database\n show_flowers -> show all flowers in database\n show_bunches -> show all bouquets in database\n b_add -> add flower to bouquet\n b_remove -> remove flower from database\n b_cost -> calculate cost of bouquet\n b_sort -> sort flowers in bouquet\n f_find -> find flowers in given stalk length span\n help -> print help\n exit -> exit program");
    }
}
