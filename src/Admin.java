import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

interface adminInterface {
  void addProduct(ArrayList<Product> productArrayList) throws IOException;

  int totalProduct(ArrayList<Product> productArrayList);

  int maxProfit(ArrayList<Product> productArrayList);

  void fineCalculate(ArrayList<RegisteredUsers> regUsers);
}

class Admin implements adminInterface {

  private String aName;
  private String aPwd;

  Admin(String aName, String aPwd) {
    this.aName = aName;
    this.aPwd = aPwd;
  }

  static int idcnt = 1;

  public void addProduct(ArrayList<Product> productArrayList)
      throws IOException {
    Scanner sc = new Scanner(System.in);
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    System.out.println("Welcome, admin!");
    System.out.println("Please enter the product information:");

    System.out.print("Product Name: ");
    String productName = br.readLine();

    System.out.print("Total quantity: ");
    int qty = sc.nextInt();

    System.out.print("Base price: ");
    int baseprice = sc.nextInt();

    System.out.print("Sell price: ");
    int sellprice = sc.nextInt();

    System.out.print("Discount price: ");
    int discountprice = sc.nextInt();

    System.out.println("Limit for return product(in Days): ");
    int lmt = sc.nextInt();

    if (productArrayList.size() == 0) {
      idcnt = 1;
    } else {
      idcnt = productArrayList.get(productArrayList.size() - 1).getpId() + 1;
    }

    Product newProduct = new Product(
        idcnt,
        productName,
        qty,
        baseprice,
        sellprice,
        discountprice,
        lmt);
    // System.out.println(newProduct.getpId() + " " + newProduct.getBasePrice() + "
    // " + newProduct.getDiscoutPrice() + " " + newProduct.getpName() + " " +
    // newProduct.getpQty() + " " + newProduct.getSellPrice() + " " +
    // newProduct.getLimit());
    productArrayList.add(newProduct);
    fileIO.writeProduct("products.txt", productArrayList);
  }

  public int totalProduct(ArrayList<Product> productArrayList) {
    int totalProd = 0;
    System.out.println("Total Product count in retail store:");
    for (Product p : productArrayList) {
      totalProd += p.getpQty();
    }
    return totalProd;
  }

  public int maxProfit(ArrayList<Product> productArrayList) {
    int maxProf = 0;
    System.out.println("Maximum profit can make is :");
    for (Product p : productArrayList) {
      maxProf += (p.getSellPrice() - p.getBasePrice()) * p.getpQty();
    }
    return maxProf;
  }

  public void fineCalculate(ArrayList<RegisteredUsers> regUsers) {

    ListIterator<RegisteredUsers> iterate = regUsers.listIterator();
    while (iterate.hasNext()) {
      int fine = 0;
      RegisteredUsers regusr = iterate.next();
      ListIterator<Product> pr = regusr.temp.listIterator();
      while (pr.hasNext()) {
        Product p = pr.next();
        int maxReturnLimit = p.getLimit();
        int finePerDay = 50;
        LocalDate issueDt = p.getIssueDate();
        LocalDate returnDt = p.getReturnDate();
        if(returnDt!=null){
          long lateDays = ChronoUnit.DAYS.between(issueDt, returnDt);
          if ((lateDays-maxReturnLimit)>0) {
            fine = (int)(lateDays - maxReturnLimit) * finePerDay;
            regusr.setFine(fine);
          }
        }
      }
      System.out.println(regusr.getuName() + "  Total fine: " + regusr.getFine());
    }
  }

  void DisplayRegUsers(ArrayList<RegisteredUsers> regUsers) {
    System.out.println(
        "---------------------------------- Users Profile ----------------------------------");
    //System.out.println("Full Name  User Name   Password  isMember? ");
    System.out.println(
        "-----------------------------------------------------------------------------------");
    for (RegisteredUsers u : regUsers) {
      u.ShowProfile();
      System.out.println("");
    }
    System.out.println(
        "-----------------------------------------------------------------------------------");
  }

  public static void AdmindisplayProductCatalog(
      ArrayList<Product> productArrayList) {
    System.out.println(
        "--------------------------------- Product Catalog ---------------------------------");
    System.out.println();
    System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s%n", "Product ID","Product Name", "Product Qty", "Availability","Base price","Sell price","Discount price","Return limit");

    String avail;
    if (productArrayList.size() != 0) {

      for (Product p : productArrayList) {
        if (p.getpQty() > 0)
          avail = "In Stock";
        else
          avail = "Out of Stock";
        System.out.printf("%-20d %-20s %-20d %-20s %-20d %-20d %-20d%n",p.getpId(),p.getpName(),p.getpQty(),avail,p.getBasePrice(),p.getSellPrice(),p.getDiscoutPrice(),p.getLimit());
      }
    } else {
      System.out.println("No product available");
    }
    System.out.println("-----------------------------------------------------------------------------------");
  }

  public static void AdminLogin(
      Admin admin) {
    Scanner scan = new Scanner(System.in);
    while (admin != null) {
      System.out.println(
          "------------------------------------ Admin Panel ----------------------------------");
      System.out.println("1--->Registered Users");
      System.out.println("2--->Add Product");
      System.out.println("3--->Display Products");
      System.out.println("4--->Total Products");
      System.out.println("5--->Maximum Profit");
      System.out.println("6--->Calculate Fine");
      System.out.println("7--->LogOut");
      System.out.println(
          "-----------------------------------------------------------------------------------");

      try {
        System.out.println("Enter your choice : ");
        int ch = scan.nextInt();

        ArrayList<Product> productArrayList = new ArrayList<>();
        ArrayList<RegisteredUsers> regUsers = new ArrayList<>();

        switch (ch) {
          case 1:
            if (fileIO.readUsers("users.txt") != null) {
              regUsers = fileIO.readUsers("users.txt");
            }
            admin.DisplayRegUsers(regUsers);
            break;
          case 2:
            if (fileIO.readProduct("products.txt") != null) {
              productArrayList = fileIO.readProduct("products.txt");
              admin.addProduct(productArrayList);
            } else {
              productArrayList = new ArrayList<>();
              admin.addProduct(productArrayList);
            }
            break;
          case 3:
            if (fileIO.readProduct("products.txt") != null) {
              productArrayList = fileIO.readProduct("products.txt");
              // AdmindisplayProductCatalog(productArrayList);
            } else {
              System.out.println("No products To Display");
            }
            AdmindisplayProductCatalog(productArrayList);
            break;
          case 4:
            if (fileIO.readProduct("products.txt") != null) {
              productArrayList = fileIO.readProduct("products.txt");
              int total = admin.totalProduct(productArrayList);
              System.out.println("Total Availble Products : " + total);
            } else {
              System.out.println("No products Available");
            }
            break;
          case 5:
            if (fileIO.readProduct("products.txt") != null) {
              productArrayList = fileIO.readProduct("products.txt");
              int mProfit = admin.maxProfit(productArrayList);
              System.out.println("Maximum profit :  " + mProfit);
            } else {
              System.out.println("No products Available");
            }
            break;
          case 6:
            if (fileIO.readUsers("users.txt") != null) {
              regUsers = fileIO.readUsers("users.txt");
              admin.fineCalculate(regUsers);
            } else {
              System.out.println("No users registered!!");
            }
            // if (regUsers.size() == 0) {
            // System.out.println("No users registered!!");
            // } else {
            // admin.fineCalculate(regUsers);
            // }
            break;
          case 7:
            admin = null;
            System.out.println("Logged out successfully!!");
            break;
          default:
            System.out.println("Invalid choice!!");
        }
      } catch (Exception e) {
        System.out.println("Exception caught--->");
        e.printStackTrace();
      }
    }
  }
}
