package classes;

import java.io.*;

public class Main {
    static BufferedReader sc_input = new BufferedReader(new InputStreamReader(System.in));
    static MainInit main_init = new MainInit();
    
    public static void main(String[] args) throws IOException{
        
        int option;

        do{
            System.out.println("1. ADD ITEM");
            System.out.println("2. VIEW ITEMS");
            System.out.println("3. UPDATE ITEM");
            System.out.println("4. DELETE ITEM");
            System.out.println("---------------");
            System.out.println("5. ADD ORDER");
            System.out.println("6. VIEW ORDERS");
            System.out.println("7. UPDATE ORDER");
            System.out.println("8. DELETE ORDER");
            System.out.println("---------------");
            System.out.println("9. VIEW ALL");
            System.out.println("10. EXIT\n");
            
            System.out.print("Enter option: ");
            option = Integer.parseInt(sc_input.readLine());
            
            switch(option){
                case 1:
                    main_init.addItem();
                    break;
                    
                case 2:
                    main_init.viewItems();
                    break;
                    
                case 3:
                    main_init.updateItem();
                    break;
                    
                case 4:
                    main_init.deleteItem();
                    break;
                    
                case 5:
                    main_init.addOrder();
                    break;
                    
                case 6:
                    main_init.viewOrders();
                    break;
                    
                case 7:
                    main_init.updateOrder();
                    break;
                    
                case 8:
                    main_init.deleteOrder();
                    break;
                    
                case 9:
                    main_init.viewAll();
                    break;
                    
                case 10:
                    System.out.println("Program exited.");
                    break;
                
                default:
                    System.out.println("Invalid option.");
            }
        }while(option != 10);
    }
}
