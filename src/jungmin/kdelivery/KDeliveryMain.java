package jungmin.kdelivery;

public class KDeliveryMain {
  public static void main(String[] args) {
    Kiosk kiosk = new Kiosk();

    kiosk.welcomeMessage();

    int count = 5;
    do{
      count = kiosk.selectMainMenu();
      switch (count){
        case 1:
          kiosk.selectAddShopMenu();
          break;
        case 2:
          kiosk.selectDashboardMenu();
          break;
        case 3:
          kiosk.selectOrderMenu();
          break;
        case 4:
          kiosk.selectFeedbackMenu();
          break;
      }
    }while(count != 5);

    System.out.println("[안내] 이용해주셔서 감사합니다.");
  }
}