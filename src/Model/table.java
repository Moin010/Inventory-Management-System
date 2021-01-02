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
public class table {
  
        private final SimpleStringProperty ID;
        private final SimpleStringProperty name;
        private final SimpleStringProperty address;
        private final SimpleStringProperty phone;
 
        public table(String f, String l,String a,String b)
        {
            this.ID = new SimpleStringProperty(f);
            this.name = new SimpleStringProperty(l);
            this.address = new SimpleStringProperty(a);
            this.phone = new SimpleStringProperty(b);
        }
 
        public String getID() {
            return ID.get();
        }
 
        public void setID(String fName) {
            ID.set(fName);
        }
 
        public String getName() {
            return name.get();
        }
 
        public void setName(String fName) {
            name.set(fName);
        }
        
        public String getAddress() {
            return address.get();
        }
 
        public void setAddress(String fName) {
            address.set(fName);
        }
 
        public String getPhone() {
            return phone.get();
        }
 
        public void setPhone(String fName) {
            phone.set(fName);
        }
    }


