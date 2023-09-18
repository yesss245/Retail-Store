import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ListIterator;
import java.util.Scanner;

class RegisteredUsers extends User {

  public RegisteredUsers(
      String uName,
      String uPwd,
      String fullName,
      boolean isMember) {
    super(uName, uPwd, fullName, isMember);
  }

  public void ShowProfile() {
    String ismemb;
    if (getMember())
      ismemb = "Yes";
    else
      ismemb = "No";

    // System.out.println(getFullName() + " " + getuName() + " " + ismemb);
    System.out.println("---------------------------------- User Profile -----------------------------------");
    System.out.println("Full Name : " + getFullName());
    System.out.println("User Name : " + getuName());
    System.out.println("Member    : " + ismemb);
    System.out.println("Fine : " + getFine());
    System.out.println();

    ListIterator<Product> iterate = boughtProducts.listIterator();
    System.out.println("---------------------------------- Products Bought --------------------------------");
    System.out.println();
    System.out.printf("%-30s %-30s %-30s %-30s %-30s%n", "Product Name", "Product Id", "Price", "Issue Date",
        "Return Date");
    System.out.println("-----------------------------------------------------------------------------------");
    while (iterate.hasNext()) {
      Product products = iterate.next();
      System.out.printf("%-30s %-30d %-30d %-30tF %-30tF %n", products.getpName(), products.getpId(),
          (getMember() ? products.getDiscoutPrice() : products.getSellPrice()),
          products.getIssueDate(), products.getReturnDate());
      System.out.println();
    }
    System.out.println();
  }

  public void PurchaseProduct(Product p) throws Exception {

    p.setIssueDate(LocalDate.now());

    boughtProducts.add(p);
    temp.add(p);
  }

  public Product ReturnProduct(int id) throws Exception {

    ListIterator<Product> iterate = boughtProducts.listIterator();
    while (iterate.hasNext()) {
      Product p = iterate.next();
      if (p.getpId() == id && p.getReturnDate() == null) {
        p.setReturnDate(LocalDate.now());
        // boughtProducts.remove(p);
        return p;
      } else {
        System.out.println("Invalid product ID");
      }
    }
    return null;
  }
}
