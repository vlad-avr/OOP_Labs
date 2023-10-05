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
        System.out.println("\n Flower type : Rose");
        super.print();
        System.out.println("\n Spikes : " + spike_prop);
    }
}
