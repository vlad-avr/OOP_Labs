package lab.flowers_test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lab.flowers.Bouquet;
import lab.flowers.Daisy;
import lab.flowers.Rose;
import lab.flowers.Tulip;

public class BouquetTest {
    Bouquet bouquet1;
    Bouquet bouquet2;
    @Test
    public void test_getter_setter(){
        String name = "test";
        Long ID = 1L;
        bouquet1 = new Bouquet(ID, name);
        bouquet2 = new Bouquet();
        bouquet2.set_id(ID);
        bouquet2.set_name(name);
        assertTrue(bouquet1.get_id() == ID && bouquet2.get_id() == ID);
        assertTrue(bouquet1.get_name().equals(name) && bouquet2.get_name().equals(name));
    }

    @Test
    public void test_flowers_list(){
        String name = "test";
        Long ID = 1L;
        bouquet1 = new Bouquet(ID, name);
        Tulip tulip = new Tulip(0, 0, 0, ID, name);
        Daisy daisy = new Daisy(0, 0, 0, ID, name);
        Rose rose = new Rose(0, 0, 0, ID, name);
        bouquet1.add_flower(rose);
        bouquet1.add_flower(tulip);
        bouquet1.add_flower(daisy);
        assertTrue(bouquet1.get_flower(0).get_bouquet_id() == ID);
        assertTrue(bouquet1.get_flower(1).get_bouquet_id() == ID);
        assertTrue(bouquet1.get_flower(2).get_bouquet_id() == ID);
    }

    @Test
    public void test_sort_and_sum_functions(){
        String name = "test";
        Long ID = 1L;
        bouquet1 = new Bouquet(ID, name);
        Tulip tulip = new Tulip(0, 30, 0.5f, ID, name);
        Daisy daisy = new Daisy(0, 20, 0.8f, ID, name);
        Rose rose = new Rose(0, 10, 0.3f, ID, name);
        bouquet1.add_flower(rose);
        bouquet1.add_flower(tulip);
        bouquet1.add_flower(daisy);
        bouquet1.sort();
        assertTrue(bouquet1.get_flower(0).get_fresh() == 0.3f);
        assertTrue(bouquet1.get_flower(1).get_fresh() == 0.5f);
        assertTrue(bouquet1.get_flower(2).get_fresh() == 0.8f);
        assertTrue(bouquet1.calculate_cost() == 60.0f);
    }
}
