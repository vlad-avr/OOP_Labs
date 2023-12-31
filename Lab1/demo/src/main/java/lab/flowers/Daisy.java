package lab.flowers;

public class Daisy extends Flower{
    private String flower_diameter;

    public Daisy(){};

    public Daisy(float stalk_length, float price, String date, Long daysCount, Long bouquet_ID, String fd){
        super(stalk_length, price, date, daysCount, bouquet_ID);
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

    @Override
    public void print(){
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        return "\n ID : " + this.ID + "\n Flower type : Daisy \n Stalk length : "
        + this.stalk_length + "\n Price : "
        + this.price + " $ \n Date : " + date.toString() + "\n Storage longevity : " + daysCount + "\n Freshness : " + get_fresh() + " \n Flower diameter : " + this.flower_diameter;
    }
}
