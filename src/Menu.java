import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private ChartDesign cd;
    public void menu ()
    {
        System.out.print("\n" +
                "  _______ _        _______           _______         \n" +
                " |__   __(_)      |__   __|         |__   __|        \n" +
                "    | |   _  ___     | | __ _  ___     | | ___   ___ \n" +
                "    | |  | |/ __|    | |/ _` |/ __|    | |/ _ \\ / _ \\\n" +
                "    | |  | | (__     | | (_| | (__     | | (_) |  __/\n" +
                "    |_|  |_|\\___|    |_|\\__,_|\\___|    |_|\\___/ \\___|\n" +
                "                                                     \n" +
                "                                                     \n");

        System.out.println("1. Play with the Computer\n" +
                           "2. Play with your Friend\n" +
                           "3. Settings\n" +
                           "4. Exit");
    }

    public void option1 (int choice) throws InterruptedException {
         cd = new ChartDesign();
        int moveCount = 0, option;
//        while (R == 'r' || R == 'R') {
            for (int i = 0; i < ((int) (Math.pow(ChartDesign.n, 2) - ChartDesign.lockCells)); i++) {

                cd.setPlayer(moveCount + 1);
                System.out.println("\nTurn: " + cd.player);

                if (cd.player.equals(ChartDesign.O))
                    System.out.print("Computer...");

                cd.display();
                if (cd.player.equals(ChartDesign.X))
                    while (true) {
                        option = getChoice(input);
                        if (option >= 1 && option <= Math.pow(ChartDesign.n, 2))
                            break;
                        else
                            System.out.printf("choose a number between 1 to %d: ", Math.pow(ChartDesign.n, 2));
                    }
                else {
                    Thread.sleep(1000);
                    option = 0;
                }
                moveCount++;
                cd.setPlayer(moveCount);
                int result = cd.gatheredUpThingsTogether(option, choice);
                if (result == 1) {
                    System.out.println("\nWrong input! Try another one...");
                    i--;
                    moveCount--;
                } else if (result == 0) {
                    cd.display();
                    System.out.println("\nPlayer   " + cd.player + "Won!\n");
                    break;
                } else if (result == 2) {
                    cd.display();
                    System.out.println("\nYou made a tie!\n"); // didn't show this one
                    break;
                }
            }
//            System.out.println("Wanna play it again? (r for Return/ Any other key to Exit)");
//            R = input.next().charAt(0);
//            cd = new ChartDesign();
//            moveCount = 0;
//        }
    }

    public void option2(int choice) {
        cd = new ChartDesign();
        int moveCount = 0, option;
//        while (R == 'r' || R == 'R') {
            for (int i = 0; i < (int) (Math.pow(ChartDesign.n, 2) - ChartDesign.lockCells); i++) {

                cd.setPlayer(moveCount + 1);
                System.out.println("\nTurn: " + cd.player);

                cd.display();
                option = getChoice(input);
                moveCount++;
                cd.setPlayer(moveCount);
                int result = cd.gatheredUpThingsTogether(option, choice);
                if (result == 1) {
                    System.out.println("\nWrong input! Try another one...");
                    i--;
                    moveCount--;
                } else if (result == 0) {
                    cd.display();
                    System.out.println("\nPlayer   " + cd.player + "Won!\n");
                    break;
                } else if (result == 2) {
                    cd.display();
                    System.out.println("\nYou made a tie!\n");
                    break;
                }
            }
//            System.out.println("Wanna play it again? (r for Return/ Any other key to Exit)");
//            R = input.next().charAt(0);
//            cd = new ChartDesign();
//            moveCount = 0;
//        }
    }
    public static int getChoice(Scanner input) {
        String Choice;
        while (true) {
            Choice = input.nextLine();
            if (isInteger(Choice))
                break;
            else
                System.out.print("Try another input: ");
        }
        int choice = Integer.parseInt(Choice);

        return choice;
    }

    public static boolean isInteger(String str) {
        if (str == null)
            return false;
        int length = str.length();
        if (length == 0)
            return false;
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1)
                return false;
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }
}

/**
 * import java.io.BufferedReader;
 * import java.io.FileReader;
 * import java.io.IOException;
 * import java.util.ArrayList;
 * import java.util.List;
 * import java.util.stream.Collectors;
 *
 * class Refactorings {
 *     public static void main(String[] args) throws IOException {
 *         List<String> array = readStrings();
 *         List<String> filtered = array.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
 *         for (String s : filtered) {
 *             System.out.println(s);
 *         }
 *     }
 *
 *     private static List<String> readStrings() throws IOException {
 *         try(BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
 *             ArrayList<String> lines = new ArrayList<>();
 *             String line;
 *             while ((line = reader.readLine()) != null) {
 *                 lines.add(line);
 *             }
 *             return lines;
 *         }
 *     }
 * }
 */