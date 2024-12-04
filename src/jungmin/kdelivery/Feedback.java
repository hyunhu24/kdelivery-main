package jungmin.kdelivery;

import java.util.Random;

public class Feedback {
  private final int orderIdx;
  private final String customerName;
  private final String shopName;
  private final String foodName;
  private final int grade;

  public Feedback(int orderIdx,String customerName, String shopName, String foodName, int grade) {
    this.orderIdx = orderIdx;
    this.customerName = customerName;
    this.shopName = shopName;
    this.foodName = foodName;
    this.grade = grade;
  }

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

  private String getGrade() {
    String star = "";  // 별점을 저장할 빈 문자열 초기화
    for (int i = 0; i < this.grade; i++) {  // 평점 수만큼 반복
      star += "★";  // 별 문자를 추가
    }
    return star;  // 완성된 별점 문자열을 반환
  }


  /**
   * @printInfo() : 출력
   */
  public void printInfo(){
    Random random = new Random();
    int randomNum = 100 + random.nextInt(900);

    System.out.println(this.customerName + " [고객님]");  // 고객 이름 출력
    System.out.println("[주문번호] 00" + randomNum + this.orderIdx);
    System.out.println("-------------------------");
    System.out.println("주문 매장 : " + this.shopName);
    System.out.println("주문 메뉴 : " + this.foodName);
    System.out.println("별점 : " + getGrade());
    System.out.println("");
  }


}
