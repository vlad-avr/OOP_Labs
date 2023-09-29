package lab.flowers;


public class Tulip extends Flower{
    private String color;

    public void set_color(String color){
        this.color = color;
    }

    @Override
    public String get_unique_prop(){
        return color;
    }
}
