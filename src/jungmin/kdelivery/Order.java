package jungmin.kdelivery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Order {
  private int orderIdx;
  private String customerName;
  private String shopName;
  private String foodName;
  private LocalDateTime create_at;
//  private LocalTime create_at1 = LocalTime.now();

  public Order(int orderIdx ,String customerName, String shopName, String foodName, LocalDateTime create_at) {
    this.orderIdx = orderIdx;
    this.customerName = customerName;
    this.shopName = shopName;
    this.foodName = foodName;
    this.create_at = create_at;
  }

  //고유 번호
  public int getOrderIdx() {
    return orderIdx;
  }

  public String getCustomerName() {
    return customerName;
  }

  public String getShopName() {
    return shopName;
  }

  public String getFoodName() {
    return foodName;
  }

  public LocalDateTime getCreate_at() {
    return create_at;
  }

  public int setOrderIdx() {
      return orderIdx++;
  }

//  //주문 날짜
//  public Date getCreate_at() {
//    return create_at;
//  }

  public boolean hasOrder(String customerName, String shopName, String foodName) {
    boolean cName = this.customerName.equals(customerName);
    boolean sName = this.shopName.equals(shopName);
    boolean fName = this.foodName.equals(foodName);
    return cName && sName && fName;
  }
}
