package jungmin.kdelivery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {
  //가게이름
  private String shopName;
  //메뉴이름, 가격
  private Map<String, Integer> menus = new HashMap<>();

  //매장이름 생성자
  public Shop(String shopName) {
    this.shopName = shopName;
  }

  public String getShopName() {
    return shopName;
  }

  public boolean addFood(String foodName, int price) {
    if (menus.containsKey(foodName)) { // 메뉴에 이미 해당 음식이 있는지 확인
      return false;  // 이미 있으면 false 반환
    } else {
      menus.put(foodName, price);  // 없으면 메뉴에 추가
      return true;  // 성공적으로 추가되면 true 반환
    }
  }

  //해당하는 메뉴가 있는가?
  public boolean hasMenu(String foodName){
    return menus.containsKey(foodName);
  }
}
