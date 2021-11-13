package bms;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Order implements Serializable {

    Customer customer;
    Dress DressesPurchased;
    String dateOfPurchase;

    public Order(Customer customer, Dress DressesPurchased, String dateOfPurchase) {
        this.customer = customer;
        this.dateOfPurchase = dateOfPurchase;
        this.DressesPurchased = DressesPurchased;
    }

    public Order() {
    }
// Getter Methods
    public Customer getCustomer() {
        return customer;
    }
    
    public Dress getDressesPurchased() {
        return DressesPurchased;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }
// Setter Methods

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setDressesPurchased(Dress DressesPurchased) {
        this.DressesPurchased = DressesPurchased;
    }

    @Override
    public String toString() {
        return "\nCUSTOMER:-\n " + getCustomer() + "\nDRESS:-\n " + getDressesPurchased() + "\nDATE:-\n " + dateOfPurchase;
    }
////////////////////////////////////////////////////////////////////////////////

    public static ArrayList<Order> readFromOrderFile() {

        ArrayList<Order> orderList = new ArrayList<>(0);
        ObjectInputStream ois = null;

        try {
            FileInputStream fis = new FileInputStream("Order.ser");
            ois = new ObjectInputStream(fis);

            orderList = (ArrayList<Order>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return orderList;
    }
////////////////////////////////////////////////////////////////////////////////

    public static void writeToOrderFile(Order s) {

        ObjectOutputStream oos = null;
        try {
            ArrayList<Order> orderList = readFromOrderFile();
            orderList.add(s);
            FileOutputStream fos = new FileOutputStream("Order.ser");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(orderList);

        } catch (IOException exp) {
            JOptionPane.showMessageDialog(null, exp.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException exp) {
                JOptionPane.showMessageDialog(null, exp.getMessage());
            }
        }
    }
}