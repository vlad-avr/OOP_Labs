package lab.db_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import lab.flowers.Bouquet;
import lab.flowers.Daisy;
import lab.flowers.Flower;
import lab.flowers.Rose;
import lab.flowers.Tulip;

public class DataBaseManager {
    private final String db_url = "jdbc:sqlite:lab.db";

    public void setup_database(boolean is_test) {
        destroy_database();
        create_database();
        create_flowers_table();
        create_bouquets_table();
        if (!is_test) {
            make_entry_data();
        }
    }

    public void destroy_database() {
        String sql1 = "DROP TABLE IF EXISTS flowers";
        String sql2 = "DROP TABLE IF EXISTS bouquets";
        try (Connection con = DriverManager.getConnection(db_url); Statement st = con.createStatement()) {
            st.execute(sql1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try (Connection con = DriverManager.getConnection(db_url); Statement st = con.createStatement()) {
            st.execute(sql2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void make_entry_data() {
        Bouquet bouquet1 = new Bouquet(1L, "bouquet 1");
        Bouquet bouquet2 = new Bouquet(2L, "bouquet 2");
        Tulip tulip1 = new Tulip(12.0f, 20.0f, "2023-10-01", 30L, 1L, "red");
        Tulip tulip2 = new Tulip(12.0f, 40.0f, "2023-10-02", 10L, 2L, "violet");
        Tulip tulip3 = new Tulip(14.0f, 10.0f, "2023-10-03", 6L, 1L, "blue");
        Daisy daisy1 = new Daisy(10.0f, 25.0f, "2023-10-04", 8L, 1L, "small");
        Rose rose1 = new Rose(9.0f, 35.0f, "2023-10-05", 20L, 1L, "absent");
        Daisy daisy2 = new Daisy(12.0f, 40.0f, "2023-10-06", 2L, 1L, "big");
        Rose rose2 = new Rose(14.0f, 10.0f, "2023-10-07", 12L, 1L, "sharp");
        FlowerSaver<Tulip> st = new FlowerSaver<>();
        st.add_flower(tulip1);
        st.add_flower(tulip2);
        st.add_flower(tulip3);
        FlowerSaver<Daisy> dt = new FlowerSaver<>();
        dt.add_flower(daisy1);
        dt.add_flower(daisy2);
        FlowerSaver<Rose> rt = new FlowerSaver<>();
        rt.add_flower(rose1);
        rt.add_flower(rose2);
        add_bouquet(bouquet1);
        add_bouquet(bouquet2);
    }

    private Connection connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(db_url);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return con;
    }

    private void create_database() {
        try (Connection conn = DriverManager.getConnection(db_url)) {
            if (conn == null) {
                System.out.println("\n UNABLE TO ESTABLISH CONNECTION!\n");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void create_flowers_table() {
        String sql = "CREATE TABLE IF NOT EXISTS flowers (\n"
                + "     id integer PRIMARY KEY, \n"
                + "     type text NOT NULL, \n"
                + "     stalk_length real NOT NULL, \n"
                + "     price real NOT NULL, \n"
                + "     date text NOT NULL, \n"
                + "     maxDays integer NOT NULL, \n"
                + "     unique_param text NOT NULL, \n"
                + "     bouquet_id integer NOT NULL"
                + ");";

        try (Connection con = DriverManager.getConnection(db_url); Statement st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void create_bouquets_table() {
        String sql = "CREATE TABLE IF NOT EXISTS bouquets (\n"
                + "     id integer PRIMARY KEY, \n"
                + "     name text NOT NULL"
                + ");";

        try (Connection con = DriverManager.getConnection(db_url); Statement st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add_bouquet(Bouquet bouquet) {
        String save_str = "INSERT INTO bouquets (name) VALUES (?)";
        try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(save_str)) {
            statement.setString(1, bouquet.get_name());
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void print_all_flowers(Long id) {
        String str = "SELECT *\nFROM flowers";
        if (id != -1) {
            str += " WHERE id = " + String.valueOf(id);
        }
        try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(str)) {
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String type = res.getString("type");
                if (type.equals("tulip")) {
                    Tulip tulip = new Tulip(res.getFloat("stalk_length"), res.getFloat("price"),
                            res.getString("date"), res.getLong("maxDays"), res.getLong("bouquet_id"), res.getString("unique_param"));
                    tulip.set_id(res.getLong("id"));
                    tulip.print();
                } else if (type.equals("rose")) {
                    Rose rose = new Rose(res.getFloat("stalk_length"), res.getFloat("price"),
                            res.getString("date"), res.getLong("maxDays"), res.getLong("bouquet_id"), res.getString("unique_param"));
                    rose.set_id(res.getLong("id"));
                    rose.print();
                } else if (type.equals("daisy")) {
                    Daisy daisy = new Daisy(res.getFloat("stalk_length"), res.getFloat("price"),
                            res.getString("date"), res.getLong("maxDays"), res.getLong("bouquet_id"), res.getString("unique_param"));
                    daisy.set_id(res.getLong("id"));
                    daisy.print();
                } else {
                    System.out.println("\nUnknown type of flower!\n");
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void print_all_bouquets(Long id) {
        String b_str = "SELECT *\nFROM bouquets";
        Long i = 1L;
        if (id > 0) {
            b_str += " WHERE id = " + String.valueOf(id);
            i = id;
        }
        try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(b_str)) {
            ResultSet b_res = statement.executeQuery();
            while (b_res.next()) {
                Bouquet bouquet = new Bouquet();
                bouquet.set_id(b_res.getLong("id"));
                bouquet.set_name(b_res.getString("name"));
                List<Flower> f_list = find_flowers_by_length(i, Float.MIN_VALUE, Float.MAX_VALUE);
                bouquet.set_flowers_list(f_list);
                bouquet.print();
                if(id <= 0){
                    i++;
                }
                System.out.println("\n");
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public List<Flower> find_flowers_by_length(Long ID, float min_length, float max_length) {
        String str = "SELECT *\nFROM flowers WHERE bouquet_id = " + String.valueOf(ID) + " AND stalk_length >= "
                + String.valueOf(min_length) + " AND stalk_length <= " + String.valueOf(max_length);
        List<Flower> flowers = new ArrayList<>();
        try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(str)) {
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String type = res.getString("type");
                if (type.equals("tulip")) {
                    Tulip new_tulip = new Tulip(res.getFloat("stalk_length"), res.getFloat("price"),
                            res.getString("date"), res.getLong("maxDays"), res.getLong("bouquet_id"), res.getString("unique_param"));
                    new_tulip.set_id(res.getLong("id"));
                    flowers.add(new_tulip);
                } else if (type.equals("rose")) {
                    Rose new_rose = new Rose(res.getFloat("stalk_length"), res.getFloat("price"),
                            res.getString("date"), res.getLong("maxDays"), res.getLong("bouquet_id"), res.getString("unique_param"));
                    new_rose.set_id(res.getLong("id"));
                    flowers.add(new_rose);
                } else if (type.equals("daisy")) {
                    Daisy new_daisy = new Daisy(res.getFloat("stalk_length"), res.getFloat("price"),
                            res.getString("date"), res.getLong("maxDays"), res.getLong("bouquet_id"), res.getString("unique_param"));
                    new_daisy.set_id(res.getLong("id"));
                    flowers.add(new_daisy);
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return flowers;
    }

    public void add_flower_to_bouquet(Long ID, Long new_bouquet_ID) {
        String str = "UPDATE flowers SET bouquet_id = " + String.valueOf(new_bouquet_ID) + " WHERE id = "
                + String.valueOf(ID);
        try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(str)) {
            if (new_bouquet_ID != -1L) {
                Bouquet bouquet = get_bouquet(new_bouquet_ID, false);
                if (bouquet != null) {
                    statement.executeUpdate();
                }
            } else {
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Bouquet get_bouquet(Long id, boolean load_flowers) {
        String str = "SELECT *\nFROM bouquets WHERE id = " + String.valueOf(id);
        try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(str)) {
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                Bouquet bouquet = new Bouquet(res.getLong("id"), res.getString("name"));
                if (load_flowers) {
                    String f_str = "SELECT *\nFROM flowers WHERE bouquet_id = " + String.valueOf(id);
                    try (Connection con2 = connect(); PreparedStatement statement2 = con.prepareStatement(f_str)) {
                        ResultSet f_res = statement2.executeQuery();
                        while (f_res.next()) {
                            String type = f_res.getString("type");
                            Flower flower = null;
                            if (type.equals("tulip")) {
                                flower = new Tulip(f_res.getFloat("stalk_length"), f_res.getFloat("price"),
                                        f_res.getString("date"), f_res.getLong("maxDays"), f_res.getLong("bouquet_id"),
                                        f_res.getString("unique_param"));
                                flower.set_id(f_res.getLong("id"));
                            } else if (type.equals("rose")) {
                                flower = new Rose(f_res.getFloat("stalk_length"), f_res.getFloat("price"),
                                         f_res.getString("date"), f_res.getLong("maxDays"), f_res.getLong("bouquet_id"),
                                        f_res.getString("unique_param"));
                                flower.set_id(f_res.getLong("id"));
                            } else if (type.equals("daisy")) {
                                flower = new Daisy(f_res.getFloat("stalk_length"), f_res.getFloat("price"),
                                         f_res.getString("date"), f_res.getLong("maxDays"), f_res.getLong("bouquet_id"),
                                        f_res.getString("unique_param"));
                                flower.set_id(f_res.getLong("id"));
                            }
                            if (flower != null) {
                                bouquet.add_flower(flower);
                            }
                        }
                    } catch (SQLException exception) {
                        System.out.println(exception.getMessage());
                    }
                }
                return bouquet;
            } else {
                SQLException exception = new SQLException("Bouquet with ID " + String.valueOf(id) + " does not exist!");
                throw exception;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public void delete_flower(Long ID) {
        String str = "DELETE FROM flowers WHERE id = " + String.valueOf(ID);
        try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(str)) {
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void delete_bouquet(Long ID, boolean and_flowers) {
        String str = "DELETE FROM bouquets WHERE id = " + String.valueOf(ID);
        try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(str)) {
            statement.executeUpdate();
            if (and_flowers) {
                String f_str = "DELETE FROM flowers WHERE bouquet_id = " + String.valueOf(ID);
                try (Connection f_con = connect(); PreparedStatement f_statement = f_con.prepareStatement(f_str)) {
                    f_statement.executeUpdate();
                } catch (SQLException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void update_bouquet(Long ID, Bouquet bouquet) {
        String str = "UPDATE bouquets SET name = ? WHERE id = " + String.valueOf(ID);
        try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(str)) {
            statement.setString(1, bouquet.get_name());
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public class FlowerSaver<T extends Flower> {
        public void add_flower(T flower) {
            String save_str = "INSERT INTO flowers (type, stalk_length, price, date, maxDays, unique_param, bouquet_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(save_str)) {
                if (flower instanceof Tulip) {
                    statement.setString(1, "tulip");
                } else if (flower instanceof Rose) {
                    statement.setString(1, "rose");
                } else if (flower instanceof Daisy) {
                    statement.setString(1, "daisy");
                } else {
                    statement.setString(1, "flower");
                }
                statement.setDouble(2, flower.get_stalk_len());
                statement.setDouble(3, flower.get_price());
                statement.setString(4, flower.get_date().toString());
                statement.setLong(5, flower.getDaysCount());
                statement.setString(6, flower.get_unique_prop());
                statement.setLong(7, flower.get_bouquet_id());
                statement.executeUpdate();
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }

        public T load_flower(Long id, T flower) {
            T loaded_flower = flower;
            String str = "SELECT *\nFROM flowers WHERE id = " + String.valueOf(id);
            try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(str)) {
                ResultSet res = statement.executeQuery();
                if (res.next()) {
                    flower.set_stalk_len(res.getFloat("stalk_length"));
                    flower.set_price(res.getFloat("price"));
                    flower.set_date(LocalDate.parse(res.getString("date")));
                    flower.setDaysCount(res.getLong("maxDays"));
                    flower.set_unique_prop(res.getString("unique_param"));
                    flower.set_in_bouquet(res.getLong("bouquet_id"));
                    return loaded_flower;
                } else {
                    return null;
                }
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
            return null;
        }

        public void update_flower(Long id, T flower) {
            String str = "UPDATE flowers SET stalk_length = ? , price = ? , date = ? , maxDays = ? , unique_param = ? , bouquet_id = ? WHERE id = "
                    + String.valueOf(id);
            try (Connection con = connect(); PreparedStatement statement = con.prepareStatement(str)) {
                statement.setFloat(1, flower.get_stalk_len());
                statement.setFloat(2, flower.get_price());
                statement.setString(3, flower.get_date().toString());
                statement.setLong(4, flower.getDaysCount());
                statement.setString(5, flower.get_unique_prop());
                statement.setLong(6, flower.get_bouquet_id());
                statement.executeUpdate();
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
