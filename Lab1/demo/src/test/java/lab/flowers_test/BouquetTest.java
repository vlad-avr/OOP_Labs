package lab.flowers_test;

import static org.junit.Assert.assertEquals;
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
        Tulip tulip = new Tulip(0, 0, "2023-10-01", 100L, ID, name);
        Daisy daisy = new Daisy(0, 0, "2023-10-01", 100L, ID, name);
        Rose rose = new Rose(0, 0, "2023-10-01", 100L, ID, name);
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
        Tulip tulip = new Tulip(0, 30, "2023-10-05", 100L, ID, name);
        Daisy daisy = new Daisy(0, 20, "2023-10-03", 100L, ID, name);
        Rose rose = new Rose(0, 10, "2023-10-01", 100L, ID, name);
        bouquet1.add_flower(rose);
        bouquet1.add_flower(tulip);
        bouquet1.add_flower(daisy);
        bouquet1.sort();
        assertEquals("2023-10-01", bouquet1.get_flower(0).get_date().toString());
        assertEquals("2023-10-03", bouquet1.get_flower(1).get_date().toString());
        assertEquals("2023-10-05", bouquet1.get_flower(2).get_date().toString());
        assertTrue(bouquet1.calculate_cost() == 60.0f);
    }
}
