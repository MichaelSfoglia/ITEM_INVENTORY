package classes;

import java.io.*;

public class MainInit {
    static BufferedReader sc_input = new BufferedReader(new InputStreamReader(System.in));
    static Config config_init = new Config();
    static MainInit internal_init = new MainInit();
    // INVENTORY INIT
    
    public void addItem() throws IOException{
        System.out.print("Enter name for item: ");
        String inv_name = sc_input.readLine();
        
        System.out.print("Enter date for: ");
        String inv_date = sc_input.readLine();
        
        System.out.print("Enter grams for item: ");
        Double inv_grams = Double.valueOf(sc_input.readLine());
        
        System.out.print("Enter temperature for item: ");
        Double inv_temperature = Double.valueOf(sc_input.readLine());
        
        System.out.print("Enter price for item: ");
        Double inv_price = Double.valueOf(sc_input.readLine());
        
        System.out.print("Enter quantity for item: ");
        Integer inv_quantity = Integer.valueOf(sc_input.readLine());
        
        String sql = "INSERT INTO inventory_tbl (item_name, item_date, item_grams, item_temperature, item_price, item_quantity) VALUES (?, ?, ?, ?, ?, ?)";
        
        config_init.addRecord(sql, inv_name, inv_date, inv_grams, inv_temperature, inv_price, inv_quantity);
    }
    public void viewItems(){
        String inventoryQuery = "SELECT * FROM inventory_tbl";
        String[] inventoryHeaders = {"ID", "Name", "Date", "Grams", "Celsius", "Price", "Quantity"};
        String[] inventoryColumns = {"item_id", "item_name", "item_date", "item_grams", "item_temperature", "item_price", "item_quantity"};

        config_init.viewRecords(inventoryQuery, inventoryHeaders, inventoryColumns);
    }
    public void updateItem() throws IOException{
        System.out.print("Enter Item ID to update: ");
        int update_id = Integer.parseInt(sc_input.readLine());
        
        System.out.print("Enter new name: ");
        String new_name = sc_input.readLine();

        String sqlUpdate = "UPDATE inventory_tbl SET item_name = ? WHERE item_id = ?";
        config_init.updateRecord(sqlUpdate, new_name, update_id);
    }
    public void deleteItem() throws IOException{
        System.out.print("Enter ID delete: ");
        int delete_id = Integer.parseInt(sc_input.readLine());
        
        String sqlDelete = "DELETE FROM inventory_tbl WHERE item_id = ?";
        config_init.deleteRecord(sqlDelete, delete_id);
    }
    
    // -------------------------------------------------------------------------------------------------------------------------------
    // ORDER INIT
    // -------------------------------------------------------------------------------------------------------------------------------
    
    public void addOrder() throws IOException{
        System.out.print("Enter customer name: ");
        String order_customer_name = sc_input.readLine();
        viewItems();
        
        System.out.print("Enter item id: ");
        int order_item_id = Integer.parseInt(sc_input.readLine());
        
        System.out.print("Enter quantity: ");
        int order_quantity = Integer.parseInt(sc_input.readLine());
        
        System.out.print("Enter date: ");
        String order_date = sc_input.readLine();
        
        String sql = "SELECT item_price FROM inventory_tbl WHERE item_id = ?";
        
        double order_item_price = config_init.getSingleValue(sql, order_item_id);
        double new_order_total = order_item_price * order_quantity;
        
        sql = "INSERT INTO orders_tbl (item_id, order_customer_name, order_quantity, order_total, order_date) VALUES (?, ?, ?, ?, ?)";
        
        config_init.addRecord(sql, order_item_id, order_customer_name, order_quantity, new_order_total, order_date);
    }
    
    public void viewOrders(){
        String orderQuery = "SELECT * FROM orders_tbl";
        String[] orderHeaders = {"Order ID", "Item ID", "Customer Name", "Quantity", "Total", "Date"};
        String[] orderColumns = {"order_id", "item_id", "order_customer_name", "order_quantity", "order_total", "order_date"};

        config_init.viewRecords(orderQuery, orderHeaders, orderColumns);
    }
    
    public void updateOrder() throws IOException{
        System.out.print("Enter Order ID to update: ");
        int order_update_id = Integer.parseInt(sc_input.readLine());
        
        System.out.print("Enter customer name: ");
        String new_customer_name = sc_input.readLine();
        
        System.out.print("Enter quantity: ");
        int new_order_quantity = Integer.parseInt(sc_input.readLine());
        
        System.out.print("Enter date: ");
        String new_order_date = sc_input.readLine();
        
        String sql = "SELECT item_price FROM inventory_tbl WHERE item_id = ?";
        
        double new_order_item_price = config_init.getSingleValue(sql, order_update_id);
        
        double new_order_total = new_order_item_price * new_order_quantity;

        String sqlUpdate = "UPDATE orders_tbl SET order_customer_name = ?, order_quantity = ?, order_total = ?, order_date = ? WHERE order_id = ?";
        config_init.updateRecord(sqlUpdate, new_customer_name, new_order_quantity, new_order_total, new_order_date, order_update_id);
    }
    
    public void deleteOrder() throws IOException{
        System.out.print("Enter ID delete: ");
        int delete_id = Integer.parseInt(sc_input.readLine());
        
        String sqlDelete = "DELETE FROM orders_tbl WHERE order_id = ?";
        config_init.deleteRecord(sqlDelete, delete_id);
    }
    
    public void viewAll(){
        System.out.println("INVENTORY");
        internal_init.viewItems();
        
        System.out.println("ORDERS");
        internal_init.viewOrders();
    }
}
