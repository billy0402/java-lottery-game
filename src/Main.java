import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("樂透遊戲～～～");
        System.out.println("請輸入遊戲次數，請輸入純數字");
        Scanner input = new Scanner(System.in);
        int playNum = 0;

        while (playNum == 0) {
            String playStr = input.next();

            if (!playStr.equals("0")) {
                playNum = checkNum(playStr);
            } else {
                break;
            }
        }

        for (int i = 1; i <= playNum; i++) {
            List<Integer> winList = getRanList();
            List<Integer> yourList = new ArrayList<>();
            List<Integer> checkList = new ArrayList<>();
            System.out.println("請輸入遊戲模式，自動請輸入a，手動請輸入m");
            String modeStr = "";

            do {
                modeStr = input.next();

                if (modeStr.equals("a") || modeStr.equals("m")) {
                    if (modeStr.equals("a")) {
                        yourList = getRanList();
                    } else if (modeStr.equals("m")) {
                        yourList = getManList();
                    }

                    checkList = checkWinNum(winList, yourList);

                    System.out.println("你的號碼：" + yourList);
                    System.out.println("中獎號碼：" + winList);
                    if (checkList.size() != 0) {
                        System.out.println("有中號碼：" + checkList);
                    } else {
                        System.out.println("沒有中獎ˊ ˋ");
                    }
                    System.out.println("-----------------------------------");
                } else {
                    System.out.println("輸入錯誤，請重新輸入！");
                }
            } while (!(modeStr.equals("a") || modeStr.equals("m")));
        }
        System.out.println("遊戲結束");
    }

    // 檢查是否輸入數字
    static int checkNum(String manStr) {
        int manNum = 0;
        char[] strChar = manStr.toCharArray();

        for (int i = 0; i < strChar.length; i++) {
            if (Character.isDigit(strChar[i])) {
                try {
                    manNum = Integer.valueOf(manStr);
                } catch (Exception e) {
                    System.out.println("輸入非整數，請重新輸入！");
                    break; // 不符合直接結束
                }
            } else {
                System.out.println("輸入含非數字，請重新輸入！");
                break; // 不符合直接結束
            }
        }

        return manNum;
    }

    // 自動選號
    static List<Integer> getRanList() {
        List<Integer> arrayList = new ArrayList<>();
        Random random = new Random();

        while (arrayList.size() < 6) {
            int ramNum = random.nextInt(49) + 1;

            if (!arrayList.contains(ramNum)) {
                arrayList.add(ramNum);
            }
        }
        Collections.sort(arrayList);

        return arrayList;
    }

    // 檢查中獎號碼
    static List<Integer> checkWinNum(List<Integer> winList, List<Integer> yourList) {
        List<Integer> checkList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (winList.get(i) == yourList.get(j)) {
                    checkList.add(yourList.get(j));
                }
            }
        }

        return checkList;
    }

    // 手動選號
    static List<Integer> getManList() {
        List<Integer> manList = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        while (manList.size() < 6) {
            System.out.println("請輸入一個純數字，介於1~49之間且不能重複");
            String manStr = input.next();
            int manNum = checkNum(manStr);

            if (manNum > 0 && manNum <= 49) {
                if (!manList.contains(manNum)) {
                    manList.add(manNum);
                } else {
                    System.out.println("輸入重複，請重新輸入！");
                }
            } else {
                System.out.println("輸入超出範圍，請重新輸入！");
            }
        }

        Collections.sort(manList);

        return manList;
    }
}