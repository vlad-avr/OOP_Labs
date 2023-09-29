package lab.flowers;


public class Rose extends Flower{
    private String spike_prop;

    public Rose(){};

    public Rose(float stalk_length, float price, float fresh_factor, int bouquet_ID, String sp){
        super(stalk_length, price, fresh_factor, bouquet_ID);
        spike_prop = sp;
    }

    public void set_spike_prop(String prop){
        this.spike_prop = prop;
    }

    @Override
    public String get_unique_prop(){
        return spike_prop;
    }
}
