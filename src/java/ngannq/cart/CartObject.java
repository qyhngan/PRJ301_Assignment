/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class CartObject implements Serializable{
    private Map<String, Integer> items;
    
    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addProductToCart(String id) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }//items are not existed
        //items has existed
        int quantity = 1;
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id) + 1;
        }
        //update quantity of product
        this.items.put(id, quantity);
    }
    
    public void removeProductFromCart(String id) {
        if (this.items == null) {
            return;
        }
        //item have existed
        if (this.items.containsKey(id)) {
            this.items.remove(id);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }// item existed in items
    }
}
