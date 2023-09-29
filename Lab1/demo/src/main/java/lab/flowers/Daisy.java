package lab.flowers;



public class Daisy extends Flower{
    private String flower_diameter;

    public void set_flower_diameter(String D){
        this.flower_diameter = D;
    }

    @Override
    public String get_unique_prop(){
        return flower_diameter;
    }
}
