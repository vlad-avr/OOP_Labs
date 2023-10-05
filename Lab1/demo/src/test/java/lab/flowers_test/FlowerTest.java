package lab.flowers_test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lab.flowers.Daisy;
import lab.flowers.Rose;
import lab.flowers.Tulip;

public class FlowerTest {
    @Test
    public void test_getters_setters(){
        Tulip tulip;
        Daisy daisy;
        Rose rose;
        //Test values set
        Float len = 10.5f;
        Float price = 34.6f;
        Float fresh = 0.8f;
        Long bouquet_id = -1L;
        String unique = "test";
        tulip = new Tulip(len, price, fresh, bouquet_id, unique);
        Tulip tulip2 = new Tulip();
        tulip2.set_stalk_len(len);
        tulip2.set_price(price);
        tulip2.set_fresh(fresh);
        tulip2.set_in_bouquet(bouquet_id);
        tulip2.set_unique_prop(unique);
        assertTrue(tulip.get_stalk_len() == len && tulip2.get_stalk_len() == len);
        assertTrue(tulip.get_price() == price && tulip2.get_price() == price);
        assertTrue(tulip.get_fresh() == fresh && tulip2.get_fresh() == fresh);
        assertTrue(tulip.get_bouquet_id() == bouquet_id && tulip2.get_bouquet_id() == bouquet_id);
        assertTrue(tulip.get_unique_prop().equals(unique) && tulip2.get_unique_prop().equals(unique));
        daisy = new Daisy(len, price, fresh, bouquet_id, unique);
        Daisy daisy2 = new Daisy();
        daisy2.set_stalk_len(len);
        daisy2.set_price(price);
        daisy2.set_fresh(fresh);
        daisy2.set_in_bouquet(bouquet_id);
        daisy2.set_unique_prop(unique);
        assertTrue(daisy.get_stalk_len() == len && daisy2.get_stalk_len() == len);
        assertTrue(daisy.get_price() == price && daisy2.get_price() == price);
        assertTrue(daisy.get_fresh() == fresh && daisy2.get_fresh() == fresh);
        assertTrue(daisy.get_bouquet_id() == bouquet_id && daisy2.get_bouquet_id() == bouquet_id);
        assertTrue(daisy.get_unique_prop().equals(unique) && daisy2.get_unique_prop().equals(unique));
        rose = new Rose(len, price, fresh, bouquet_id, unique);
        Rose rose2 = new Rose();
        rose2.set_stalk_len(len);
        rose2.set_price(price);
        rose2.set_fresh(fresh);
        rose2.set_in_bouquet(bouquet_id);
        rose2.set_unique_prop(unique);
        assertTrue(rose.get_stalk_len() == len && rose2.get_stalk_len() == len);
        assertTrue(rose.get_price() == price && rose2.get_price() == price);
        assertTrue(rose.get_fresh() == fresh && rose2.get_fresh() == fresh);
        assertTrue(rose.get_bouquet_id() == bouquet_id && rose2.get_bouquet_id() == bouquet_id);
        assertTrue(rose.get_unique_prop().equals(unique) && rose2.get_unique_prop().equals(unique));
    }
}
