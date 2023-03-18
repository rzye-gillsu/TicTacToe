import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Setting {

    private File file;
    public static int[] set = new int[3];
    private String setting;

    /**
     *  The constructor of the Setting Class, makes a txt file if it doesn't exist
     * and reads its content.
     */
    public Setting () {
        file = new File("Settings.txt");
        readFile();
    }

    /**
     *  It gets the player's options about their favorable chart options.
     */
    public void explain () { // how to avoid importing many libraries
        System.out.println("->You can set your favorable setting here.");

        Scanner input = new Scanner(System.in);
        System.out.print("\n\tSize of the chart(n>2): ");
        set[0] = input.nextInt();

        System.out.print("\n\tNumber of Locked cells: ");
        set[1] = input.nextInt();

        System.out.print("\n\tNumber of blocks needed to win: ");
        set[2] = input.nextInt();
    }

    /**
     * It writes the user inputs to the file.
     */
    public void writeFile () {
        try {
            FileWriter writer = new FileWriter("Settings.txt");
            setting = String.valueOf(set[0]) + " " + String.valueOf(set[1]) + " " + String.valueOf(set[2]);
            writer.write(setting);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            throw new RuntimeException(e);
        }
    }

    /**
     * It reads the content of the file and sets it to class's variables.
     */
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
