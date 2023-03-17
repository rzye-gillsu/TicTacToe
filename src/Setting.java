import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Setting {

    private File file;
    public static int[] set = new int[3];
    private String setting;
    public Setting () {
        file = new File("Settings.txt");
        readFile();
    }

    public void explain () { // how to avoid importing many libraries
        System.out.println("->You can set your favorable setting here.");

        Scanner input = new Scanner(System.in);
        System.out.print("\tSize of the chart(n>2): ");
        set[0] = input.nextInt();

        System.out.print("\n\tNumber of Locked cells: ");
        set[1] = input.nextInt();

        System.out.print("\n\tNumber of blocks needed to win: ");
        set[2] = input.nextInt();
    }

    public void writeFile () {
        try {
//            if (file.createNewFile()) {}
            FileWriter writer = new FileWriter("Settings.txt");
            setting = String.valueOf(set[0]) + " " + String.valueOf(set[1]) + " " + String.valueOf(set[2]);
            writer.write(setting);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            throw new RuntimeException(e);
        }
    }

    public void readFile() {
        String[] splitString = new String[3];
        if (file.exists()) {
            try {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    splitString = reader.nextLine().split(" ");
                }
                for (int i = 0; i < 3; i++) {
                    set[i] = Integer.valueOf(splitString[i]);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Set the game settings first, or continue with the default values.");
        }
    }

}
