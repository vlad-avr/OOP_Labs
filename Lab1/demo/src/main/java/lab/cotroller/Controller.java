package lab.cotroller;

import java.util.Scanner;

public class Controller {
    

    public void start(){

    }

    private void main_loop(){
        System.out.println("Welcome to BoUwUquet Manager!\n");
        print_help();
        while(true){
            try{
                String input = get_input();
                switch(input){
                    case "d_flower_add" : /*Add flower*/;
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
                    case "help" : print_help();
                    case "exit" : return;
                    default : System.out.println("\nInvalid input command!\n");
                }
            }catch(Exception exception){
                exception.printStackTrace();
            }
        }
    }

    private String get_input(){
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();
        return input;
    }

    private void print_help(){
        System.out.println("\n List of commands:\n d_flower_add -> add flower to database\n d_flower_remove -> remove flower from database\n d_bunch_add -> add bouquet to database\n d_bunch_remove -> remove bouquet from database\n show_flowers -> show all flowers in database\n show_bunches -> show all bouquets in database\n b_add -> add flower to bouquet\n b_remove -> remove flower from database\n b_cost -> calculate cost of bouquet\n b_sort -> sort flowers in bouquet\n f_find -> find flowers in given stalk length span\n help -> print help\n exit -> exit program");
    }
}
