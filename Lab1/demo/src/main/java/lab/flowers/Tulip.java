package lab.flowers;


public class Tulip extends Flower{
    private String color;

    public Tulip(){};

    public Tulip(float stalk_length, float price, float fresh_factor, Long bouquet_ID, String col){
        super(stalk_length, price, fresh_factor, bouquet_ID);
        color = col;
    }

    @Override
    public void set_unique_prop(String color){
        this.color = color;
    }

    @Override
    public String get_unique_prop(){
        return color;
    }
}
