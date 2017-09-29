/*
Author : Ong Boon Fong
Class  : DC02 D2
*/

package control;

import domain.foodMenu;
import da.FoodMenuDA;


public class FoodMenuControl {
    private FoodMenuDA menuDA;
    
    public FoodMenuControl(){
        menuDA = new FoodMenuDA();
    }
    
    public foodMenu selectRecord(String id){
        return menuDA.getRecord(id);
    }
    
      public void createRecord(String id,String name,String type,double price) {
       menuDA.createRecord(id, name, type, price);
    }
      
    public void updateRecord(String id,String name,String type,double price) {
        menuDA.updateRecord(id,name,type,price);
    }
    
    public void deleteRecord(String id) {
        menuDA.deleteRecord(id);
    }
    
    public void shutdown(){
        menuDA.shutDown();
    }
}
