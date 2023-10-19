import static java.lang.System.out;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        printMenu();

        StepTracker stepTracker = new StepTracker();
        int userInput = 1;
        while (userInput != 0) {
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                if (userInput > 3 || userInput < 0){
                    out.println("Такого действия нет!");
                }
            } else {
                out.println("Неправильный ввод!");
                scanner.nextLine();
            }

            switch (userInput){
                case 1:{
                    stepTracker.enterTheCompletedSteps();
                    break;
                }
                case 2:{
                    stepTracker.getStat();
                    break;
                }
                case 3:{
                    stepTracker.changePurpose();
                    break;
                }
                default:
                    break;
            }
            printMenu();
        }

        out.println("Программа завершена!");
    }

    private static void printMenu() {
        out.println("""
                     Вас приветствует программа 'CalorieCounter'. Что вы хотите сделать?
                     
                     1 - Ввести количество шагов за определённый день.
                     2 - Напечатать статистику за определённый месяц.
                     3 - Изменить цель по количеству шагов в день.
                     0 - Выйти из приложения.
                    """);

    }
}