package jungmin.kdelivery;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Kiosk {
    private ArrayList<Shop> shops = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Feedback> feedbacks = new ArrayList<>();
    // 이미 리뷰가 작성된 주문이 중복되지 않게
    Set<Integer> reviewedOrderIds = new HashSet<>();

    private Scanner s;
    public Kiosk() {
        this.s = new Scanner(System.in);
    }

    //처음에세지
    public void welcomeMessage(){
        System.out.println("[치킨의 민족 프로그램 V1]");
        System.out.println("-".repeat(30));
    }

    //메뉴 선택!
    public int selectMainMenu(){
        int menuNum;
        do {
            selectMainMenuMessage();
            menuNum = selectMenuNumberInput();

            if(menuNum <= 0 || menuNum > 5){
                System.out.println("메뉴에 없는 번호를 입력하셨습니다.");
            }
        }while(menuNum <= 0 || menuNum > 5);

        return menuNum;
    }

    //선택 창 메세지 출력
    private void selectMainMenuMessage(){
        System.out.println("1) [사장님 용] 음식점 등록하기");
        System.out.println("2) [고객님과 사장님 용] 음식점 별점 조회하기");
        System.out.println("3) [고객님 용] 음식 주문하기");
        System.out.println("4) [고객님 용] 별점 등록하기");
        System.out.println("5) 프로그램 종료하기");

        System.out.println("-".repeat(30));
        System.out.println("[시스템] 무엇을 도와드릴까요?");
    }

    //번호 입력
    private int selectMenuNumberInput(){
        int num = 0;
        String input = s.nextLine();

        if(isValidNumber(input)){
            num = Integer.parseInt(input);
        }else{
            System.out.println("숫자가 아닌 수를 입력했습니다.");
        }
        return num;
    }

    // 1. 음식점 등록
    public void selectAddShopMenu() {
        System.out.println("[안내] 반갑습니다. 가맹주님!");
        System.out.println("[안내] 음식점 상호는 무엇인가요?");
        System.out.print(">>>");

        String shopName = s.nextLine();

        System.out.println("[안내] 대표 메뉴 이름은 무엇인가요?");
        System.out.print(">>>");

        String foodName = s.nextLine();

        String priceStr;
        int price;
        do {
            System.out.println("[안내] 해당 메뉴 가격은 얼마인가요?");
            System.out.print(">>>");
            priceStr = s.nextLine();

            if (!isValidNumber(priceStr)) {
//                price = Integer.parseInt(priceStr);
                System.out.println("[시스템] 숫자만 입력해야 합니다.");
            }

        } while (!isValidNumber(priceStr));

        price = Integer.parseInt(priceStr);
        //숫자를 주고 비교하기
        int shopIndex = getShopIndex(shops, shopName);

        if (shopIndex != -1) {
//            boolean isValid = shops.get(shopIndex).addFood(foodName, price);
//            if(!isValid) {
            System.out.println("[시스템] 동일한 이름의 메뉴를 추가할 수 없습니다.");
            return;
//            }
        } else {
            Shop shop = new Shop(shopName);
            shop.addFood(foodName, price);
            shops.add(shop);
        }
        System.out.printf("[안내] %s에 음식(%s, %d) 추가되었습니다.\n", shopName, foodName, price);
        System.out.println("[시스템] 가게 등록이 정상 처리되었습니다.");
    }

    //1-1 음식점 이름 idx 부여
    //shopList 에서 shop 이름 일치하지 않으면 idx 부여해줌
    private int getShopIndex(ArrayList<Shop> shopList, String shopName) {
        int currentIdx = -1;
        for(int i = 0; i < shopList.size(); i++) {
            if(shopList.get(i).getShopName().equals(shopName)) {
                return i;
            }
        }
        return currentIdx;
    }

//    2. 별점조회
    public void selectDashboardMenu(){
        if(feedbacks.size() == 0){
            System.out.println("등록된 별점이 없습니다!!!!!");
        }else{
            for(Feedback feedback : feedbacks)
                feedback.printInfo();
        }
    }

    //3. 주문하기
    public void selectOrderMenu(){
        System.out.println("[안내] 고객님! 메뉴 주문을 진행하겠습니다!");
        System.out.println("[안내] 주문자 이름을 알려주세요!");
        System.out.print(">>>");

        String customerName = s.nextLine();

        System.out.println("[안내] 주문할 음식점 상호는 무엇인가요?");
        System.out.print(">>>");

        String shopName = s.nextLine();

        System.out.println("[안내] 주문할 메뉴 이름을 알려주세요!");

        String menuName = s.nextLine();

        int count = orders.size();
        int shopIndex = getShopIndex(shops, shopName);
        //1. shop 이 있는지 확인
        //2. 있다면 해당하는 메뉴가 있는지 확인
        if(shopIndex != -1){
            if(!shops.get(shopIndex).hasMenu(menuName)){
                System.out.printf("해당하는 메뉴가 %s 가게에 존재하지 않습니다.\n",shopName);
                return;
            }else{
//                count++;
                //                order.setOrderIdx();
                LocalDateTime creat_at = LocalDateTime.now();
                Order order = new Order(count, customerName, shopName, menuName, creat_at);
                orders.add(order);
                System.out.printf("[안내]%d %s님!\n", count , customerName);
                System.out.printf("[안내] %s 매장에 %s 주문이 완료되었습니다.\n", shopName, menuName);
            }
        }else{
            System.out.printf("%s님 해당하는 가게가 존재하지 않습니다!\n",customerName);
        }
    }

    //4. 별점 작성하기
    public void selectFeedbackMenu() {
        if (orders.size() != 0) {
            System.out.println("[안내] 고객님! 별점 등록을 진행합니다.");
            System.out.println("[안내] 주문자 이름은 무엇인가요?");
            System.out.print(">>>");
            String customerName = s.nextLine();
            System.out.println("[안내] 음식점 상호는 무엇인가요?");
            System.out.print(">>>");
            String shopName = s.nextLine();
            System.out.println("[안내] 주문하신 음식 이름은 무엇인가요?");
            System.out.print(">>>");
            String menuName = s.nextLine();

            //이렇게 하면 내용이 중복되면 아예 안됨!
            //            boolean isTrue = false;
            //            for(Order order : orders){
            //                if(order.hasOrder(customerName, shopName, menuName)){
            //                    isTrue = true;
            //                }
            //            }
            //            if(!isTrue){
            //               System.out.println("[안내] 주문내역이 확인이 되지 않았습니다!");
            //               return;
            //           }

            // 1. 주문자의 주문 내역이 있는가?
            List<Order> orderCheck = orders.stream()
                    .filter(order ->
                            order.getCustomerName().equals(customerName) &&
                                    order.getShopName().equals(shopName) &&
                                    order.getFoodName().equals(menuName))
                    .sorted(Comparator.comparing(Order::getCreate_at).reversed())
                    .collect(Collectors.toList());

            //비어있으면 주문내역없음 출력!
            if (orderCheck.isEmpty()) {
                System.out.println("주문내역 없어!");
                return;
            }

            // 2. 리뷰가 작성되지 않은 주문 걸러내기!
            Order orderToReview = orderCheck.stream()
                    .filter(order -> !reviewedOrderIds.contains(order.getOrderIdx()))
                    .findFirst()
                    .orElse(null);

            //작성이 된게있어?? 그럼 이미 리뷰 작성했다고 출력
            if (orderToReview == null) {
                System.out.println("이미 리뷰 작성완료!");
                return;
            }

            // 3. 위에 다 통과면 리뷰 작성 시작!
            System.out.println("[안내] 음식 맛은 어떠셨나요? (1점 ~ 5점)");
            System.out.print(">>>");
            String gradeNum = s.nextLine();
            int grade = 0;

            if (isValidNumber(gradeNum)) {
                grade = Integer.parseInt(gradeNum);
                if (grade < 1 || grade > 5) {
                    System.out.println("1에서 5까지의 숫자를 입력해야 합니다.");
                } else {
                    Feedback feedback = new Feedback(orderToReview.getOrderIdx(), customerName, shopName, menuName, grade);
                    feedbacks.add(feedback);
                    reviewedOrderIds.add(orderToReview.getOrderIdx()); // 리뷰 완료된 주문 ID 추가
                    System.out.println("[안내] 리뷰 등록이 완료되었습니다.");
                }
            } else {
                System.out.println("숫자가 아닙니다!");
            }
        } else {
            System.out.println("주문내역이 없습니다.");
        }
    }

    //숫자 예외처리
    private boolean isValidNumber(String formula){
        String digits = "0123456789";
        for(char c : formula.toCharArray()){
            if(digits.indexOf(c) != -1){
                return true;
            }
        }
        return false;
    }
}
