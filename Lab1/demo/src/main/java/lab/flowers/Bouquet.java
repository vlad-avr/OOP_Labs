package lab.flowers;

import java.util.List;


public class Bouquet {
    private List<Flower> flowers;
    private String name;
    private Long ID;

    public Bouquet(){};

    public Bouquet(Long ID, String name){
        this.ID = ID;
        this.name = name;
    }

    public void set_name(String name){
        this.name = name;
    }

    public String get_name(){
        return name;
    }

    public Long get_id(){
        return this.ID;
    }

    public void set_id(Long ID){
        this.ID = ID;
    }

    public void add_flower(Flower flower){
        flowers.add(flower);
    }
}
