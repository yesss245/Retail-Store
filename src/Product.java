import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {

  private int pId;
  private String pName;
  private int pQty;
  private int basePrice;
  private int sellPrice;
  private int discoutPrice;
  private int limit;

  private LocalDate issueDate;
  private LocalDate returnDate;
  
  public Product(
    int pId,
    String pName,
    int pQty,
    int basePrice,
    int sellPrice,
    int discoutPrice,
    int limit
  ) {
    this.pId = pId;
    this.pName = pName;
    this.pQty = pQty;
    this.basePrice = basePrice;
    this.sellPrice = sellPrice;
    this.discoutPrice = discoutPrice;
    this.limit = limit;
  }

  public int getpId() {
    return pId;
  }

  public String getpName() {
    return pName;
  }

  public int getpQty() {
    return pQty;
  }


  public void increaseQtyby(int x) {
    pQty = pQty + 1;
    return;
  }

  public void decreaseQtyby(int x) {
    pQty = pQty - 1;
  }

  public int getBasePrice() {
    return basePrice;
  }

  public int getSellPrice() {
    return sellPrice;
  }

  public int getDiscoutPrice() {
    return discoutPrice;
  }

  public int getLimit() {
    return limit;
  }

  public LocalDate getIssueDate() {
    return issueDate;
  }

  public LocalDate getReturnDate() {
    return returnDate;
  }

  public void setIssueDate(LocalDate issueDate) {
    this.issueDate = issueDate;
  }

  public void setReturnDate(LocalDate returnDate) {
    this.returnDate = returnDate;
  }
}
