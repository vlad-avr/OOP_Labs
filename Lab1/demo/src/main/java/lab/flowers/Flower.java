package lab.flowers;

public abstract class Flower {

    private Long ID;
    private Float stalk_length;
    private Float price;
    private Float fresh_factor; // 0 -> not fresh | 1 -> very fresh

    private Long bouquet_ID = -1L;

    public Flower() {
    };

    public Flower(float stalk_length, float price, float fresh_factor, Long bouquet_ID) {
        this.stalk_length = stalk_length;
        this.price = price;
        this.fresh_factor = fresh_factor;
        this.bouquet_ID = bouquet_ID;
    }

    public void set_id(Long id) {
        this.ID = id;
    }

    public Long get_id() {
        return ID;
    }

    public void set_stalk_len(float stalk_length) {
        this.stalk_length = stalk_length;
    }

    public void set_price(float price) {
        this.price = price;
    }

    public void set_fresh(float fresh_factor) {
        this.fresh_factor = fresh_factor;
    }

    public void set_in_bouquet(Long bouquet_ID) {
        this.bouquet_ID = bouquet_ID;
    }

    public float get_stalk_len() {
        return stalk_length;
    }

    public float get_price() {
        return price;
    }

    public float get_fresh() {
        return fresh_factor;
    }

    public Long get_bouquet_id() {
        return bouquet_ID;
    }

    public abstract String get_unique_prop();

    public abstract void set_unique_prop(String prop);

    public void print() {
        System.out.println("\n ID : " + ID + "\n Stalk length : "
                + stalk_length + "\n Price : "
                + price + " $ \n Freshness : " + fresh_factor);
    }

}
