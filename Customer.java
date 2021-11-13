package bms;

import java.io.*;
import java.util.ArrayList;

public class Customer implements Serializable {

    private String name;
    private int id;
    private String contactNo;

    public Customer() {
    }

    public Customer(String name, int id, String contactNo) {
        this.name = name;
        this.id = id;
        this.contactNo = contactNo;
    }
// Getter Methods

    public int getId() {
        return id;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getName() {
        return name;
    }

// Setter Methods
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer Name: " + name + " | Customer ID: " + id + " | Contact: " + contactNo;
    }
////////////////////////////////////////////////////////////////////////////////

    public static ArrayList<Customer> search(String name) {

        ArrayList<Customer> resultList = new ArrayList<>();
        ArrayList<Customer> customerList = readFromCustomerFile();

        for (int i = 0; i < customerList.size(); i++) {
            if (name.equalsIgnoreCase(customerList.get(i).getName())) {
                resultList.add(customerList.get(i));
            }
        }
        return resultList;
    }
////////////////////////////////////////////////////////////////////////////////

    public static ArrayList<Customer> readFromCustomerFile() {

        ArrayList<Customer> customerList = new ArrayList<>(0);
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream("Customer.ser");
            ois = new ObjectInputStream(fis);

            boolean bol = false;
            while (!bol) {
                try {
                    Customer customerObject = (Customer) ois.readObject();
                    customerList.add(customerObject);

                } catch (ClassNotFoundException e) {

                } catch (EOFException end) {
                    bol = true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return customerList;
    }
////////////////////////////////////////////////////////////////////////////////

    public void writeToCustomerFile(Customer s) {

        ObjectOutputStream oos = null;
        try {
            ArrayList<Customer> customerList = readFromCustomerFile();
            customerList.add(s);
            FileOutputStream fos = new FileOutputStream("Customer.ser");
            oos = new ObjectOutputStream(fos);

            for (int i = 0; i < customerList.size(); i++) {
                oos.writeObject(customerList.get(i));
            }
        } catch (IOException exp) {
            System.out.println(exp.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
