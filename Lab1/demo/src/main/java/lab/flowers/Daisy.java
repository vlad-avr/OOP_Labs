package lab.flowers;



public class Daisy extends Flower{
    private String flower_diameter;

    public Daisy(){};

    public Daisy(float stalk_length, float price, float fresh_factor, Long bouquet_ID, String fd){
        super(stalk_length, price, fresh_factor, bouquet_ID);
        flower_diameter = fd;
    }

    @Override
    public void set_unique_prop(String D){
        this.flower_diameter = D;
    }

    @Override
    public String get_unique_prop(){
        return flower_diameter;
    }
}
