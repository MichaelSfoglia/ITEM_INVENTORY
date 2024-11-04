package classes;

import java.io.*;

public class Main {
    static BufferedReader sc_input = new BufferedReader(new InputStreamReader(System.in));
    static Main main_init = new Main();
    static Config config_init = new Config();
    
    public static void main(String[] args) throws IOException{
        // INVENTORY SYSTEM
        
        int option;
        
        
        
        do{
            System.out.println("1. ADD ITEM");
            System.out.println("2. VIEW ITEMS");
            System.out.println("3. UPDATE ITEM");
            System.out.println("4. DELETE ITEM");
            System.out.println("5. EXIT\n");
            
            System.out.print("Enter option: ");
            option = Integer.parseInt(sc_input.readLine());
            
            switch(option){
                case 1 -> {
                    main_init.addItem();
                }
                
                case 2 -> {
                    main_init.viewItems();
                }
                
                case 3 -> {
                    main_init.updateItem();
                }
                
                case 4 -> {
                    main_init.deleteItem();
                }
                
                case 5 -> {
                    System.out.println("Program exited.");
                }
                default -> {
                    System.out.println("Invalid option.");
                }
            }
        }while(option != 5);
    }
    
    public void addItem() throws IOException{
        System.out.print("Enter name: ");
        String name = sc_input.readLine();
        
        System.out.print("Enter date: ");
        String date = sc_input.readLine();
        
        System.out.print("Enter grams: ");
        Double grams = Double.valueOf(sc_input.readLine());
        
        System.out.print("Enter temperature: ");
        Double temperature = Double.valueOf(sc_input.readLine());
        
        System.out.print("Enter price: ");
        Double price = Double.valueOf(sc_input.readLine());
        
        String sql = "INSERT INTO inventory_tbl (item_name, item_date, item_grams, item_temperature, item_price) VALUES (?, ?, ?, ?, ?)";
        
        config_init.addRecord(sql, name, date, grams, temperature, price);
    }
    
    public void viewItems(){
        String inventoryQuery = "SELECT * FROM inventory_tbl";
        String[] inventoryHeaders = {"ID", "Name", "Date", "Grams", "Celsius", "Price"};
        String[] inventoryColumns = {"item_id", "item_name", "item_date", "item_grams", "item_temperature", "item_price"};

        config_init.viewRecords(inventoryQuery, inventoryHeaders, inventoryColumns);
    }
    
    public void updateItem() throws IOException{
        System.out.print("Enter ID to update: ");
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
}
