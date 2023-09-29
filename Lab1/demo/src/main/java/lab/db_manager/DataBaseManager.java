package lab.db_manager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lab.flowers.Bouquet;
import lab.flowers.Daisy;
import lab.flowers.Flower;
import lab.flowers.Rose;
import lab.flowers.Tulip;

public abstract class DataBaseManager {
    private final String db_url = "jdbc:sqlite:lab.db";
    private final String flowers_table = "flowers";
    private final String bouquets_table = "bouquets";

    public void setup_database(){
        create_database();
        create_flowers_table();
        create_bouquets_table();
    }

    private Connection connect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(db_url);
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return con;
    }

    private void create_database(){
        try (Connection conn = DriverManager.getConnection(db_url)) {
            if (conn == null) {
                System.out.println("\n UNABLE TO ESTABLISH CONNECTION!\n");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void create_flowers_table(){
        String sql = "CREATE TABLE IF NOT EXISTS flowers (\n"
                    + "     id integer PRIMARY KEY, \n"
                    + "     type text NOT NULL, \n"
                    + "     stalk_length real NOT NULL, \n"
                    + "     price real NOT NULL, \n"
                    + "     fresh_factor real NOT NULL, \n"
                    + "     unique_param text NOT NULL, \n"
                    + "     bouquet_id integer NOT NULL";

        try(Connection con = DriverManager.getConnection(db_url);
         Statement st = con.createStatement();){
            st.execute(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void create_bouquets_table(){
        String sql = "CREATE TABLE IF NOT EXISTS bouquets (\n"
                    + "     id integer PRIMARY KEY, \n"
                    + "     name text NOT NULL";

        try(Connection con = DriverManager.getConnection(db_url);
         Statement st = con.createStatement();){
            st.execute(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void add_bouquet(Bouquet bouquet){
        String save_str = "INSERT INTO bouquets (name) VALUES (?)";
        try(Connection con = connect(); PreparedStatement statement = con.prepareStatement(save_str)){
            statement.setString(1, bouquet.get_name());
            statement.executeUpdate();
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void print_all_flowers(){
        String str = "SELECT *\n"
                    + "FROM flowers";
        try(Connection con = connect(); PreparedStatement statement = con.prepareStatement(str)){
            ResultSet res = statement.executeQuery();
            while(res.next()){
                String type = res.getString("type");
                System.out.println("\n ID : " + res.getInt("id") + "\n Flower type : " + type + "\n Stalk length : " + res.getFloat("stalk_length") + "\n Price : "
                                    + res.getFloat("price") + " $ \n Freshness : " + res.getFloat("fresh_factor"));
                if(type == "tulip"){
                    System.out.println(" Color : " + res.getString("unique_param"));
                }else if(type == "rose"){
                    System.out.println(" Spikes : " + res.getString("unique_param"));
                }else if(type == "daisy"){
                    System.out.println(" Flower diameter : " + res.getString("unique_param"));
                }else{
                    System.out.println(" Unknown property : " + res.getString("unique_param"));
                }
                int b_id = res.getInt("bouquet_id");
                if(b_id == -1){
                    System.out.println(" Not in bouquet");
                }else{
                    System.out.println(" In bouquet with id " + b_id);
                }
            }
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void print_all_bouquets(){
        String b_str = "SELECT *\nFROM bouquets";
        try(Connection con = connect(); PreparedStatement statement = con.prepareStatement(b_str)){
            ResultSet b_res = statement.executeQuery();
            while(b_res.next()){
                System.out.println("\n ID : " + b_res.getString("id") + "\n Name : " + b_res.getString("name") + "\n Flowers:");
                String f_str = "SELECT id, type, price\nFROM bouquets";
                try(Connection f_con = connect(); PreparedStatement f_statement = con.prepareStatement(f_str)){
                    ResultSet f_res = f_statement.executeQuery();
                    while(f_res.next()){
                        System.out.println("\n\t (id = " + f_res.getInt("id") + " " + f_res.getString("type") + " " + f_res.getFloat("price") + " $\n");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("\n");
            }
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public class FlowerSaver<T extends Flower>{
        public void add_flower(T flower){
            String save_str = "INSERT INTO flowers (type, stalk_length, price, fresh_factor, unique_param, bouquet_id) VALUES (?, ?, ?, ?, ?, ?)";
            try(Connection con = connect(); PreparedStatement statement = con.prepareStatement(save_str)){
                if(flower instanceof Tulip){
                    statement.setString(1, "tulip");
                }
                else if(flower instanceof Rose){
                    statement.setString(1, "rose");
                }
                else if(flower instanceof Daisy){
                    statement.setString(1, "daisy");
                }else{
                    statement.setString(1, "flower");
                }
                statement.setDouble(2, flower.get_stalk_len());
                statement.setDouble(3, flower.get_price());
                statement.setDouble(4, flower.get_fresh());
                statement.setString(5, flower.get_unique_prop());
                statement.setInt(6, flower.get_bouquet_id());
                statement.executeUpdate();
            }catch(SQLException exception){
                System.out.println(exception.getMessage());
            }
        }
    }
}
