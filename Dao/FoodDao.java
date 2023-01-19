package Dao;

import CommonData.Constants;
import Entity.FoodOrder;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FoodDao {
    public static void save(FoodOrder foodOrder) {

        try {
            FileWriter fw = new FileWriter(Constants.ORDER, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(Constants.ORDER_LIST_HEADER);
            bw.newLine();

            String food = foodOrder.getItemno() + "," + foodOrder.getQuantity() + "," + foodOrder.getPrice();
            System.out.println( "\nItem no :"+foodOrder.getItemno() + "\nItem quentity :" + foodOrder.getQuantity() + "\nTotal amount :" + foodOrder.getPrice());
            bw.write(food);
            bw.newLine();

            bw.close();

        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
}
