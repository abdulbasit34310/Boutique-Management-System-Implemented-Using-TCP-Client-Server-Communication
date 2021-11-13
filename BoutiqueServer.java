package bms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class BoutiqueServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Server Started");
        ServerSocket welcomeSocket = new ServerSocket(147);
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("Connection established");
        BufferedReader br = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        ObjectOutputStream os = new ObjectOutputStream(connectionSocket.getOutputStream());
        ObjectInputStream is = new ObjectInputStream(connectionSocket.getInputStream());

        while (true) {
            String option = br.readLine();
            System.out.println("Choice: " + option);
            switch (option) {
//// Dress Menu /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                case "1": {
                    String choice1 = br.readLine();
                    System.out.println("Dress Menu Choice: " + choice1);
                    switch (choice1) {

                        case "1": {
                            Dress dressObject = (Dress) is.readObject();
                            System.out.println(dressObject.toString());

                            dressObject.writeToDressFile(dressObject);
                        }
                        break;

                        case "2": {
                            String recievedName = (String) is.readObject();
                            System.out.println("Dress name to be found is: " + recievedName);
                            ArrayList<Dress> foundDressList = Dress.search(recievedName);
                            if (foundDressList.isEmpty() == true) {
                                JOptionPane.showMessageDialog(null, "Dress Not Found");
                            } else {
                                JOptionPane.showMessageDialog(null, foundDressList.toString());
                            }
                        }
                        break;

                        default:
                            System.out.println("You pressed the wrong key");
                    }
                }
                break;
////// Customer Menu ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                case "2": {
                    String choice2 = br.readLine();
                    System.out.println("Customer Menu: " + choice2);
                    switch (choice2) {
                        case "1": {
                            Customer customerObject = (Customer) is.readObject();
                            System.out.println(customerObject.toString());
                            customerObject.writeToCustomerFile(customerObject);
                        }
                        break;

                        case "2": {
                            String recievedName = (String) is.readObject();
                            System.out.println("Customer to be found: " + recievedName);

                            ArrayList<Customer> foundCustomerList = Customer.search(recievedName);
                            if (foundCustomerList.isEmpty() == true) {
                                JOptionPane.showMessageDialog(null, "Customer Not Found");

                            } else {
                                JOptionPane.showMessageDialog(null, foundCustomerList.toString());
                            }
                        }
                        break;

                        default:
                            System.out.println("You sent me the wrong option from Client. I am in Server Customer");
                    }
                }
                break;
//// Order Menu /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                case "3": {
                    String choice3 = br.readLine();
                    System.out.println("Order Menu: " + choice3);
                    switch (choice3) {
                        case "1": {
                            String customerName = (String) is.readObject();
                            System.out.println("Validate Customer Name" + customerName);
                            String dressName = (String) is.readObject();
                            System.out.println("Validate Dress Name" + dressName);

                            Customer c2 = null;
                            Dress d2 = null;
                            Order o1;

                            ArrayList<Customer> foundCustomerList = Customer.search(customerName);

                            if (foundCustomerList.isEmpty() == true) {
                                JOptionPane.showMessageDialog(null, "Customer Not Found");
                            } else {
                                for (int i = 0; i < foundCustomerList.size(); i++) {

                                    if (customerName.equalsIgnoreCase(foundCustomerList.get(i).getName())) {
                                        c2 = new Customer(foundCustomerList.get(i).getName(), foundCustomerList.get(i).getId(), foundCustomerList.get(i).getContactNo());
                                        JOptionPane.showMessageDialog(null, foundCustomerList.toString());

                                        ArrayList<Dress> foundDressList = Dress.search(dressName);
                                        if (foundDressList.isEmpty() == true) {
                                            JOptionPane.showMessageDialog(null, "Dress Not Found");
                                        } else {

                                            for (int y = 0; y < foundDressList.size(); y++) {
                                                if (foundDressList.get(y).getName().equalsIgnoreCase(dressName)) {
                                                    d2 = new Dress(foundDressList.get(y).getName(), foundDressList.get(y).getQuantity(), foundDressList.get(y).getSize(), foundDressList.get(y).getColor(), foundDressList.get(y).getPrice());
                                                }
                                            }
                                            JOptionPane.showMessageDialog(null, foundDressList.toString());
                                        }
                                    }

                                    Date date = Calendar.getInstance().getTime();
                                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                                    String strDate = dateFormat.format(date);

                                    o1 = new Order(c2, d2, strDate);
                                    Order.writeToOrderFile(o1);
                                    JOptionPane.showMessageDialog(null, o1.toString());
                                }
                                break;
                            }
                        }
                        break;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        case "4": {
                            System.out.println("Exiting...");
                            System.exit(0);
                        }
                        break;
                    }
                }
            }
        }
    }
}
