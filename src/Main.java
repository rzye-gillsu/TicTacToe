import java.util.Scanner;
import java.util.Set;


/**
 * Don't forget to ADD COMMENTS
 */


public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);

        int choice = 0;

        Setting setting = new Setting();
        ChartDesign.setValues();


//        setting.readFile();
//        ChartDesign.n = Setting.set[0];
//        ChartDesign.lockCells = Setting.set[1];
//        ChartDesign.winCells = Setting.set[2];
//        System.out.println("Setting.set[]\t" + Setting.set[0] + " " + Setting.set[1] + " " + Setting.set[2]);
//        System.out.println("ChartDesign.-\t" + ChartDesign.n + " " + ChartDesign.lockCells + " " + ChartDesign.winCells);

        Menu menu = new Menu();
//        menu.menu();
//        System.out.print("your choice: ");
//        choice = Menu.getChoice(input);


        while (choice != 4) {

            ChartDesign.setValues();
            System.out.println("Setting.set[]\t" + Setting.set[0] + " " + Setting.set[1] + " " + Setting.set[2]);
            System.out.println("ChartDesign.-\t" + ChartDesign.n + " " + ChartDesign.lockCells + " " + ChartDesign.winCells);

            menu.menu();
            System.out.print("your choice: ");
            choice = Menu.getChoice(input);

            switch (choice) {
                case 1:
                    ChartDesign.setValues();
                    menu.option1(1);
                    break;
                case 2:
                    ChartDesign.setValues();
                    menu.option2(2);
                    break;
                case 3:
                    setting.explain();
                    setting.writeFile();
                    setting.readFile();
                    ChartDesign.setValues();
                    break;
            }
        }
        if (choice == 4)
            System.out.println("See you soon!");
    }
}