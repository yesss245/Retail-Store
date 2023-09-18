import java.io.*;
import java.net.FileNameMap;
import java.util.*;

public class fileIO {
    public static void writeUsers(String fileName, ArrayList<RegisteredUsers> regUsers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(regUsers);
            // System.out.println("user information written to file successfully!");
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            System.out.println("An error occurred while writing user information to file: ");
            ex.printStackTrace();
        }
    }

    public static ArrayList<RegisteredUsers> readUsers(String fileName) {
        try {
            File newFile = new File(fileName);
            if (newFile.length() == 0) {
                return null;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            ArrayList<RegisteredUsers> regUsers = (ArrayList<RegisteredUsers>) ois.readObject();
            // System.out.println("user information read from file successfully!");
            ois.close();
            return regUsers;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("An error occurred while reading user information from file: " + ex.getMessage());
        }
        return null;
    }

    public static void writeProduct(String fileName, ArrayList<Product> regUsers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(regUsers);
            // System.out.println("Product information written to file successfully!");
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            System.out.println("An error occurred while writing product information to file: ");
            ex.printStackTrace();
        }
    }

    public static ArrayList<Product> readProduct(String fileName) {
        try {
            File newFile = new File(fileName);
            if (newFile.length() == 0) {
                return null;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            ArrayList<Product> products = (ArrayList<Product>) ois.readObject();
            // System.out.println("Product information read from file successfully!");
            ois.close();
            return products;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("An error occurred while reading product information from file: " + ex.getMessage());
        }
        return null;
    }
}
