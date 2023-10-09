package lab.flowers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Bouquet {
    private List<Flower> flowers = new ArrayList<>();
    private String name;
    private Long ID;

    public Bouquet() {
    };

    public Bouquet(Long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public String get_name() {
        return name;
    }

    public Long get_id() {
        return this.ID;
    }

    public void set_id(Long ID) {
        this.ID = ID;
    }

    public void add_flower(Flower flower) {
        flowers.add(flower);
    }

    public float calculate_cost() {
        float cost = 0.0f;
        for (int i = 0; i < flowers.size(); i++) {
            cost += flowers.get(i).get_price();
        }
        return cost;
    }

    public Flower get_flower(int i){
        if(i < flowers.size()){
            return flowers.get(i);
        }
        else{
            return null;
        }
    }

    public void sort() {
        Collections.sort(flowers, new Comparator<Flower>() {
            @Override
            public int compare(Flower f1, Flower f2) {
                if (f1.get_fresh() < f2.get_fresh()) {
                    return -1;
                } else if (f1.get_fresh() == f2.get_fresh()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    public void print() {
        System.out.println("\n ID : " + ID + "\n Name : " + name + "\n Flowers:");
        for (int i = 0; i < flowers.size(); i++) {
            String class_name = flowers.get(i).getClass().toString();
            int last_dot_ind = class_name.lastIndexOf(".") + 1;
            class_name = class_name.substring(last_dot_ind);
            System.out.println("\n\t id = " + flowers.get(i).get_id() + " " + class_name.toLowerCase() + " "
                    + flowers.get(i).get_price() + " $ freshness : " + flowers.get(i).get_fresh());
        }
    }

    //For testing
    public int flower_num(){
        return this.flowers.size();
    }
}
