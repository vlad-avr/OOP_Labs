package lab.flowers;

import java.util.List;


public class Bouquet {
    private List<Flower> flowers;
    private String name;
    private Long ID;

    public void set_name(String name){
        this.name = name;
    }

    public String get_name(){
        return name;
    }
}
