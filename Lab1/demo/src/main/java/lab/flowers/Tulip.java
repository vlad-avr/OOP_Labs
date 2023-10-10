package lab.flowers;


public class Tulip extends Flower{
    private String color;

    public Tulip(){};

    public Tulip(float stalk_length, float price, String date, Long daysCount, Long bouquet_ID, String col){
        super(stalk_length, price, date, daysCount, bouquet_ID);
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

    @Override
    public void print(){
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        return "\n ID : " + this.ID + "\n Flower type : Tulip \n Stalk length : "
        + this.stalk_length + "\n Price : "
        + this.price + " $ \n Date : " + date.toString() + "\n Storage longevity : " + daysCount + "\n Freshness : " + get_fresh() + " \n Color : " + this.color;
    }
}
