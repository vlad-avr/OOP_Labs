package lab.db_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


import lab.cotroller.*;

import org.junit.Test;

import lab.db_manager.DataBaseManager;
import lab.db_manager.DataBaseManager.FlowerSaver;
import lab.flowers.Bouquet;
import lab.flowers.Daisy;
import lab.flowers.Rose;
import lab.flowers.Tulip;

public class DataBaseTest {
    DataBaseManager db_manager = new DataBaseManager();
    Controller controller = new Controller(db_manager);

    @Test
    public void test_database(){
        db_manager.destroy_database();
        db_manager.setup_database(true);
        Bouquet bouquet = new Bouquet();
        bouquet.set_name("Bouquet");
        db_manager.add_bouquet(bouquet);
        FlowerSaver<Tulip> tulip_saver = db_manager.new FlowerSaver<>(); 
        tulip_saver.add_flower(new Tulip(1.0f, 10.0f, 0.5f, 1L, "red"));
        FlowerSaver<Rose> rose_saver = db_manager.new FlowerSaver<>(); 
        rose_saver.add_flower(new Rose(2.0f, 20.0f, 0.2f, 1L, "absent"));
        FlowerSaver<Daisy> daisy_saver = db_manager.new FlowerSaver<>(); 
        daisy_saver.add_flower(new Daisy(3.0f, 30.0f, 1.0f, 1L, "big"));
        
        FlowerSaver<Tulip> tulip_loader = db_manager.new FlowerSaver<>();
        Tulip tulip = new Tulip();
        tulip_loader.load_flower(1L, tulip);
        FlowerSaver<Daisy> daisy_loader = db_manager.new FlowerSaver<>();
        Daisy daisy = new Daisy();
        daisy_loader.load_flower(3L, daisy);
        FlowerSaver<Rose> rose_loader = db_manager.new FlowerSaver<>();
        Rose rose = new Rose();
        rose_loader.load_flower(2L, rose);
        float eps = 0.000001f;
        //Testing db loading 
        assertTrue(tulip.get_bouquet_id() == 1L);
        assertEquals(0.5f, tulip.get_fresh(), eps);
        assertEquals(10.0f, tulip.get_price(), eps); 
        assertEquals(1.0f, tulip.get_stalk_len(), eps); 
        assertEquals(tulip.get_unique_prop(),"red");
        assertTrue(rose.get_bouquet_id() == 1L);
        assertEquals(0.2f, rose.get_fresh(), eps);
        assertEquals(20.0f, rose.get_price(), eps);
        assertEquals(2.0f, rose.get_stalk_len(), eps);
        assertEquals(rose.get_unique_prop(),"absent");
        assertTrue(daisy.get_bouquet_id() == 1L);
        assertEquals(1.0f, daisy.get_fresh(), eps);
        assertEquals(30.0f, daisy.get_price(), eps);
        assertEquals(3.0f, daisy.get_stalk_len(), eps);
        assertEquals(daisy.get_unique_prop(),"big");
        bouquet = db_manager.get_bouquet(1L, true);
        assertEquals(bouquet.get_name(),"Bouquet");
        assertEquals(0.5f, bouquet.get_flower(0).get_fresh(), eps);
        assertEquals(0.2f, bouquet.get_flower(1).get_fresh(), eps);
        assertEquals(1.0f, bouquet.get_flower(2).get_fresh(), eps);

        //Testing db adding/updating
        Bouquet bouquet1 = new Bouquet();
        bouquet1.set_name("new");
        db_manager.add_bouquet(bouquet1);
        bouquet.set_name("updated");
        db_manager.update_bouquet(1L, bouquet);
        tulip.set_unique_prop("yellow");
        tulip_saver.update_flower(1L, tulip);
        daisy.set_price(100.0f);
        daisy_saver.update_flower(3L, daisy);
        rose.set_stalk_len(100.0f);
        rose_saver.update_flower(2L, rose);
        db_manager.add_flower_to_bouquet(1L, 2L);
        db_manager.add_flower_to_bouquet(2L, 2L);
        db_manager.add_flower_to_bouquet(3L, 2L);
        bouquet = db_manager.get_bouquet(1L, true);
        assertEquals(bouquet.get_name(),"updated");
        //bouquet.print();
        assertEquals(bouquet.flower_num(), 0);
        bouquet = db_manager.get_bouquet(2L, true);
        assertEquals(bouquet.get_name(), "new");
        assertEquals(bouquet.flower_num(), 3);
        assertEquals(bouquet.get_flower(0).get_unique_prop(), "yellow");
        assertEquals(100.0f, bouquet.get_flower(1).get_stalk_len(), eps);
        assertEquals(100.0f, bouquet.get_flower(2).get_price(), eps);

        //Testing deleting entries from db
        db_manager.add_flower_to_bouquet(1L, 1L);
        db_manager.add_flower_to_bouquet(2L, 1L);
        db_manager.add_flower_to_bouquet(3L, 1L);
        db_manager.delete_bouquet(2L, true);
        bouquet = db_manager.get_bouquet(2L, true);
        assertEquals(bouquet, null);
        db_manager.delete_flower(1L);
        bouquet = db_manager.get_bouquet(1L, true);
        assertEquals(bouquet.flower_num(), 2);
        assertNotEquals(bouquet.get_flower(0).get_unique_prop(), "yellow");
        db_manager.delete_bouquet(1L, true);
        bouquet = db_manager.get_bouquet(1L, true);
        assertEquals(bouquet, null);
        daisy_loader.load_flower(2L, daisy);
        assertEquals(daisy, null);
        rose_loader.load_flower(1L, rose);
        assertEquals(rose, null);

        db_manager.destroy_database();
    }
}
