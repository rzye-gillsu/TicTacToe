import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);
        Setting setting = new Setting();
        ChartDesign.setValues();
        Menu menu = new Menu();

        int choice = 0;
        while (choice != 4) {

            ChartDesign.setValues();

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