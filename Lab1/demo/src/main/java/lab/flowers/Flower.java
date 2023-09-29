package lab.flowers;


public abstract class Flower {

    private Long ID;
    private Float stalk_length;
    private Float price;
    private Float fresh_factor; //0 -> not fresh | 1 -> very fresh

    private Integer bouquet_ID = -1;

    public void set_stalk_len(float stalk_length){
        this.stalk_length = stalk_length;
    }

    public void set_price(float price){
        this.price = price;
    }

    public void set_fresh(float fresh_factor){
        this.fresh_factor = fresh_factor;
    }

    public void set_in_bouquet(int bouquet_ID){
        this.bouquet_ID = bouquet_ID;
    }

    public float get_stalk_len(){
        return stalk_length;
    }

    public float get_price(){
        return price;
    }

    public float get_fresh(){
        return fresh_factor;
    }

    public int get_bouquet_id(){
        return bouquet_ID;
    }

    public abstract String get_unique_prop();

}
