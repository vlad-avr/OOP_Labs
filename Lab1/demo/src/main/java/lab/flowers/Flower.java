package lab.flowers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Flower {

    protected Long ID;
    protected Float stalk_length;
    protected Float price;
    // protected Float fresh_factor; // 0 -> not fresh | 1 -> very fresh
    protected LocalDate date;
    protected Long daysCount;
    protected Long bouquet_ID = -1L;

    public Flower() {
    };

    public Flower(float stalk_length, float price, String date, Long daysCount, Long bouquet_ID) {
        this.stalk_length = stalk_length;
        this.price = price;
        // this.fresh_factor = fresh_factor;
        this.date = LocalDate.parse(date);
        this.daysCount = daysCount;
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

    public void set_date(LocalDate date) {
        this.date = date;
    }

    public void setDaysCount(Long daysCount){
        this.daysCount = daysCount;
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

    // public float get_fresh() {
    //     return fresh_factor;
    // }

    public float get_fresh(){
        LocalDate curDate = LocalDate.now();
        Long curDays = Math.abs(ChronoUnit.DAYS.between(curDate, date));
        if(curDays > daysCount){
            return 0.0f;
        }else{
            return 1.0f-((float)curDays/(float)daysCount);
        }
    }

    public LocalDate get_date(){
        return this.date;
    }

    public Long getDaysCount(){
        return this.daysCount;
    }

    public Long get_bouquet_id() {
        return bouquet_ID;
    }

    public abstract String get_unique_prop();

    public abstract void set_unique_prop(String prop);

    public void print() {
        System.out.println("\n ID : " + ID);
        if(bouquet_ID == -1L){
            System.out.println(" Not in bouquet");
        }else{
            System.out.println(" In bouquet with ID : " + bouquet_ID);
        }
        System.out.println(" Stalk length : "
                + stalk_length + "\n Price : "
                + price + " $ \n Date : " + date.toString() + "\n Storage longevity : " + daysCount + "\n Freshness : " + get_fresh());
    }
    
    public abstract String toString();

}
