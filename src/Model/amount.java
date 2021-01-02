/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author immah
 */
public class amount {
          private final SimpleStringProperty amount;
        private final SimpleStringProperty date;
        private final SimpleStringProperty details;
        private final SimpleStringProperty total;
 
        public amount(String f, String l,String a,String mom)
        {
           amount = new SimpleStringProperty(f);
           date= new SimpleStringProperty(l);
            total= new SimpleStringProperty(a);
            details = new SimpleStringProperty(mom); 
        }
       
        public String getDetails() {
            return details.get();
        }
 
        public void setDetails(String a) {
            details.set(a);
        }
 
        
        public String getAmount() {
            return amount.get();
        }
 
        public void setAmount(String a) {
            amount.set(a);
        }
 
        public String getDate() {
            return date.get();
        }
 
        public void setDate(String fName) {
            date.set(fName);
        }
        
        public String getTotal() {
            return total.get();
        }
 
        public void setTotal(String fName) {
            total.set(fName);
        }
 
}
