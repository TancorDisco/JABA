import static java.lang.System.out;
import java.util.HashMap;
import java.util.Scanner;
public class StepTracker {

    int purpose;

    HashMap<Integer, MonthData> monthToData = new HashMap<>();

    StepTracker() {
        purpose = 10000;

        for (int i = 1; i < 13; i++){
            monthToData.put(i, new MonthData());
        }
    }
    static class MonthData {
        static int counter = 0;
        String name;
        String[] nameArr = new String[]{
                "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
        };

        int[] dayArr = new int[30];

        MonthData(){
            name = nameArr[counter++];
            for (int i = 0; i < dayArr.length; i++)     {
                dayArr[i] = (i+1)*100;
            }
        }

    }

    void enterTheCompletedSteps() {
        out.println("Шаги за какой месяц вы хотите ввести? Введите номер месяца, как в календаре.");
        var scanner = new Scanner(System.in);

        var userInput = -1;
        while (userInput <= 0 || userInput > 12) {
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                if (userInput <= 0 || userInput > 12){
                    out.println("Такового месяца нет, повторите ввод!");
                }
            } else {
                out.println("Неправильный ввод!");
                scanner.nextLine();
            }
        }
        int numberOfMonth = userInput;

        out.println("Какой день?");
        userInput = -1;
        while (userInput <= 0 || userInput > 30) {
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                if (userInput <= 0 || userInput > 30){
                    out.println("Такового дня нет, повторите ввод!");
                }
            } else {
                out.println("Неправильный ввод!");
                scanner.nextLine();
            }
        }
        int numberOfDay = userInput;

        out.println("Сколько вы прошли?");
        userInput = -1;
        while (userInput < 0) {
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                if (userInput < 0){
                    out.println("Такового дня нет, повторите ввод!");
                }
            } else {
                out.println("Неправильный ввод!");
                scanner.nextLine();
            }
        }
        monthToData.get(numberOfMonth).dayArr[numberOfDay] = userInput;
        out.println(monthToData.get(numberOfMonth).dayArr[numberOfDay]);
    }
    void getStat() {
        out.println("За какой месяц вы хотите увидеть статистику? Введите номер месяца, как в календаре.");

        var scanner = new Scanner(System.in);
        var userInput = -1;
        while (userInput <= 0 || userInput > 12) {
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                if (userInput <= 0 || userInput > 12){
                    out.println("Нет такого месяца!");
                }
            } else {
                out.println("Неправильный ввод!");
                scanner.nextLine();
            }
        }
        out.println(monthToData.get(userInput).name);
        for (int i = 0; i < monthToData.get(userInput).dayArr.length; i++){
            out.println(i+1 + " - " + monthToData.get(userInput).dayArr[i]);
        }
        additionalFuncs(monthToData.get(userInput));
    }
    void changePurpose(){
        out.println("Сейчас ваша цель шагов за день: " + purpose + ".\n На какое значение вы хотите изменить её?");

        var scanner = new Scanner(System.in);

        var userInput = -1;
        while (userInput <= 0) {
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
            } else {
                out.println("Неправильный ввод!");
                scanner.nextLine();
            }
        }
        purpose = userInput;
    }

    void additionalFuncs (MonthData month) {
        int summa = 0;
        int max = 0;
        int indicatorCombo = 0;
        int combo = 0;
        int bestCombo = 0;
        for (int i = 0; i < month.dayArr.length; i++) {
            summa += month.dayArr[i];

            max = (max < month.dayArr[i]) ? month.dayArr[i] : max;

            if (indicatorCombo < month.dayArr[i]) {
                indicatorCombo = month.dayArr[i];
                combo++;
            }
            else {
                indicatorCombo = 0;
                if (bestCombo < combo) {
                    bestCombo = combo;
                }
                combo = 0;
            }
        }
        if (bestCombo < combo) {
            bestCombo = combo;
        }

        int average = summa/month.dayArr.length;
        out.println("Всего пройденно в этом месяце: " + summa + " / " + Converter.convertToKm(summa) + " км / " + Converter.convertToKcal(summa) + " ккал");
        out.println("Самое большое количество шагов: " + max + " / " + Converter.convertToKm(max) + " км / " + Converter.convertToKcal(max) + " ккал");
        out.println("Среднее количество пройденных шагов за месяц: " + average + " / " + Converter.convertToKm(average) + " км / " + Converter.convertToKcal(average) + " ккал");
        out.println("Лучшее комбо: " + bestCombo);
    }

}
