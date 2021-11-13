package bms;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dress implements Serializable {

    private String name;
    private String quantity;
    private String size;
    private String color;
    private String price;

    public Dress() {
    }

    public Dress(String name, String quantity, String size, String color, String price) {
        this.name = name;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.price = price;

    }
// Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(String price) {
        this.price = price;
    }
// Getter Methods

    public String getName() {
        return this.name;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Dress Name: " + name + " | Quantity: " + quantity + " | Size: " + size + " | Color: " + color + " | Price: " + price;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static ArrayList<Dress> search(String name) {

        ArrayList<Dress> dressList = readFromDressFile();
        ArrayList<Dress> resultList = new ArrayList<>();

        for (int i = 0; i < dressList.size(); i++) {
            if (name.equalsIgnoreCase(dressList.get(i).getName())) {
                resultList.add(dressList.get(i));
            }
        }
        return resultList;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static ArrayList<Dress> readFromDressFile() {

        ArrayList<Dress> dressList = new ArrayList<>(0);
        ObjectInputStream ois = null;

        try {
            FileInputStream fis = new FileInputStream("Dresses.ser");
            ois = new ObjectInputStream(fis);

            boolean bol = false;
            while (!bol) {
                try {
                    Dress dressObject = (Dress) ois.readObject();
                    dressList.add(dressObject);
                } catch (EOFException eof) {
                    bol = true;

                    System.out.println(eof.getMessage());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return dressList;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void writeToDressFile(Dress s) {

        ObjectOutputStream oos = null;
        try {
            ArrayList<Dress> dressList = readFromDressFile();
            dressList.add(s);
            FileOutputStream fos = new FileOutputStream("Dresses.ser");
            oos = new ObjectOutputStream(fos);

            for (int i = 0; i < dressList.size(); i++) {
                oos.writeObject(dressList.get(i));
            }
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
