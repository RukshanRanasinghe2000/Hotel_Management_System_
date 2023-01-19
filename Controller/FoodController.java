package Controller;

import Dao.FoodDao;
import Entity.FoodOrder;

public class FoodController {
    public static void saveFoodOrder(FoodOrder food) {
        int itemno = food.getItemno();
        int quantity = food.getQuantity();
        float price = 0.0f;

        switch (itemno) {
            case 1: {
                price = quantity * 50;
                System.out.println("Total amount:" +price+"/=");
                break;
            }
            case 2: {
                price = quantity * 60;
                System.out.println("Total amount:" +price+"/=");
                break;
            }
            case 3: {
                price = quantity * 70;
                System.out.println("Total amount:" +price+"/=");
                break;
            }
            case 4: {
                price = quantity * 30;
                System.out.println("Total amount:" +price+"/=");
                break;
            }
            default:
                System.out.println("Total amount:" +price+"/=");
        }

        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setItemno(itemno);
        foodOrder.setPrice(price);
        foodOrder.setQuantity(quantity);

        FoodDao.save(foodOrder);

    }
}
