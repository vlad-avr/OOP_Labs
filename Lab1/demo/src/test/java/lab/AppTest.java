package lab;

import org.junit.Test;

import lab.db_test.DataBaseTest;
import lab.flowers_test.BouquetTest;
import lab.flowers_test.FlowerTest;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testAll()
    {
        FlowerTest fTest = new FlowerTest();
        fTest.test_getters_setters();
        BouquetTest bTest = new BouquetTest();
        bTest.test_getter_setter();
        bTest.test_flowers_list();
        bTest.test_sort_and_sum_functions();
        DataBaseTest dbTest = new DataBaseTest();
        dbTest.test_database();
    }
}
