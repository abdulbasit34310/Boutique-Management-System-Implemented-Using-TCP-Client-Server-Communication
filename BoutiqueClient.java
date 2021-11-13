package bms;

import java.io.*;
import java.net.Socket;
import java.util.*;
import javax.swing.JOptionPane;

public class BoutiqueClient {

    static Scanner input = new Scanner(System.in);
    static ObjectOutputStream os;
    static ObjectInputStream is;
    static PrintWriter pw;

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 147);
        pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());

        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
    }

    public static void addDress(String name, String quantity, String size, String color, String price) {

        pw.println(1);
        pw.flush();
        pw.println(1);
        pw.flush();

        Dress dressObject = new Dress(name, quantity, size, color, price);
        System.out.println(dressObject.toString());

        try {
            os.writeObject(dressObject);
            System.out.println(dressObject.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    public static void addCustomer(String name, int id, String contact) {

        pw.println(2);
        pw.flush();
        pw.println(1);
        pw.flush();

        Customer customerObject = new Customer(name, id, contact);
        System.out.println(customerObject.toString());
        try {
            os.writeObject(customerObject);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    public static void searchDress(String dressName) {
        pw.println(1);
        pw.flush();
        pw.println(2);
        pw.flush();
        try {
            os.writeObject(dressName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    public static void searchCustomer(String customerName) {
        pw.println(2);
        pw.flush();
        pw.println(2);
        pw.flush();
        try {
            os.writeObject(customerName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    public static void buyaDress(String customerName, String dressName) {
        pw.println(3);
        pw.flush();
        pw.println(1);
        pw.flush();

        try {
            os.writeObject(customerName);
            os.writeObject(dressName);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
}
