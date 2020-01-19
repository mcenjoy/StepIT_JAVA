package Task_1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.*;

public class MultiplicationTable {

    public static void main(String[] args) {
        boolean flag = true;
        int number1, number2; //сомножители
        int result;         //произведение
        int otv;         //ответ испытуемого
        byte kol = 0;  //кол-во правильных ответов
        byte z = 0; //счётчик вопросов
        final int MIN = 6;
        final int MAX = 10; // 10 примеров
        int qa = genRandom(MIN, MAX);
        int[][] mas = new int[qa][2];
        Scanner input = new Scanner(System.in);
        String str_input;
        System.out.println("***Проверка знания таблицы умножения***");
        System.out.println("\tОтветьте на " + qa + " вопросов на оценку");
        System.out.println("После вопроса введите ответ и <Enter>");
        for (int i = 0; i < mas.length; ++i) {
            mas[i][0] = genRandom(2, 10);//1 убрали из-за простоты
            number1 = mas[i][0];
            mas[i][1] = genRandom(2, 10);//1 убрали из-за простоты
            number2 = mas[i][1];
            if (i != 0) {
                do for (int j = 0; j < i; j++) {
                    if ((mas[i][0] == mas[j][0] && mas[i][1] == mas[j][1]) || (mas[i][0] == mas[j][1] && mas[i][1] == mas[j][0])) {
                        mas[i][0] = genRandom(2, 10);
                        number1 = mas[i][0];
                        mas[i][1] = genRandom(2, 10);
                        number2 = mas[i][1];
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                } while (!flag);
            }
            result = number1 * number2;
            System.out.print(++z + ". " + number1 + "*" + number2 + "=");
            while (true) {
                str_input = input.nextLine();
                if (reg(str_input)) {
                    otv = Integer.parseInt(str_input);
                    if (otv == result) kol++;
                    else {
                        System.out.print("Вы ошиблись!");
                        System.out.println(number1 + "x" + number2 + "=" + result);
                        System.out.println("Продолжим...");
                    }
                    break;
                } else {
                    System.out.println("Попробуйте ещё раз..." + "\nВведите число и <Enter>");
                    System.out.print(z + ". " + number1 + "*" + number2 + "=");
                }
            }
        }
        displayMark(qa, kol);
        input.close();
    }

    private static int genRandom(int min, int max) {
        /*Random r = new Random();
        int i = r.nextInt((max - min) + 1) + min;*/
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private static boolean reg(String str) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    private static void displayMark(int all, int rqa) {
        System.out.println("Правильных ответов: " + rqa + " из " + all);
        System.out.print("Ваша оценка: ");
        switch (all - rqa) {
            case 0:
                System.out.print("5");
                break;
            case 1:
            case 2:
                System.out.println("4");
                break;
            case 3:
                System.out.println("3");
                break;
            default:
                System.out.println("2");
                break;
        }

    }
}