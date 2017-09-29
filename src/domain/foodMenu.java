/*
Author : Ong Boon Fong
Class  : DC02 D2
*/
package domain;
import java.io.Serializable;
import java.util.Objects;

public class foodMenu implements Serializable {
    private String id;
    private String name;
    private String type;
    private double price;
    
    public foodMenu(){
        
    }
    
    public foodMenu(String id){
        this.id = id;
    }
    
    public foodMenu(String id, String name,String type,double price){
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.id = type;
    }
    
    public double getPrice(){
        return price;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
     @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final foodMenu other = (foodMenu) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public String toString(){
        return String.format("%-15s %-20s %-30s %-5.2f",id,name,type,price);
    }
}
