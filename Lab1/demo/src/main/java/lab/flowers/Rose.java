package lab.flowers;


public class Rose extends Flower{
    private String spike_prop;

    public Rose(){};

    public Rose(float stalk_length, float price, String date, Long daysCount, Long bouquet_ID, String sp){
        super(stalk_length, price, date, daysCount, bouquet_ID);
        spike_prop = sp;
    }

    @Override
    public void set_unique_prop(String prop){
        this.spike_prop = prop;
    }

    @Override
    public String get_unique_prop(){
        return spike_prop;
    }

    @Override
    public void print(){
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        return "\n ID : " + this.ID + "\n Flower type : Rose \n Stalk length : "
        + this.stalk_length + "\n Price : "
        + this.price + " $ \n Date : " + date.toString() + "\n Storage longevity : " + daysCount + "\n Freshness : " + get_fresh() + " \n Spikes : " + this.spike_prop;
    }
}
