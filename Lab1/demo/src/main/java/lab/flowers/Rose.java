package lab.flowers;


public class Rose extends Flower{
    private String spike_prop;

    public Rose(){};

    public Rose(float stalk_length, float price, float fresh_factor, Long bouquet_ID, String sp){
        super(stalk_length, price, fresh_factor, bouquet_ID);
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
        return "\n ID : " + this.ID + "\n Flower type : Daisy \n Stalk length : "
        + this.stalk_length + "\n Price : "
        + this.price + " $ \n Freshness : " + this.fresh_factor + " \n Spikes : " + this.spike_prop;
    }
}
