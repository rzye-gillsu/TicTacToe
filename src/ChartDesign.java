import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class ChartDesign {
    public static int n;
    public static int lockCells;
    public static int winCells;

    public static void setValues() {
        n = Setting.set[0];
        lockCells = Setting.set[1];
        winCells = Setting.set[2];
    }
    private int nPow = (int)(Math.pow(n, 2));

        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static String X = ANSI_CYAN + "X    " + ANSI_RESET;
        public static String O = ANSI_YELLOW + "O    " + ANSI_RESET;
        public static String sharp = ANSI_RED + "#    " + ANSI_RESET;
        private Random random = new Random();
        public String player;
        private int moveCount;
        private String[] lock = new String[lockCells];
        private int row, col;
        private int countFills = lockCells;
        private String[][] chart = new String[n][n];

    /**
     *  In this constructor of the ChartDesign Class, the game chart is set.
     * Then the random lock numbers are produced based on the user input or
     * default settings.
     */
    public ChartDesign() {

            int count = 1;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    chart[i][j] = String.valueOf(count++);

            ArrayList<Integer> list = new ArrayList<Integer>(n);
            for (int i = 0; i < nPow; i++) {
                list.add(i + 1);
            }
            int[] lockInt = new int[lockCells];
            Collections.shuffle(list);
            for (int i = 0; i < lockCells; i++) {
                lockInt[i] = list.get(i);
                lock[i] = String.valueOf(lockInt[i]);
            }

            for (int i = 0; i < lockCells; i++) {
                rowColMaking(lockInt[i]);
                chart[row][col] = sharp;
            }

        }

    /**
     *  This method calls the private method setPlayerOption which determines the player
     * X or O based on the move counts.
     * @param moveCount If the moveCount is even, it's X's turn, otherwise O has to play.
     */
    public void setPlayer(int moveCount) {
            this.moveCount = moveCount;
            setPlayerOption();
    }

    /**
     *  Supports the public setPlayer method. If the moveCount is even, it's X's turn,
     * otherwise O has to play.
     */
    private void setPlayerOption() {
            if (moveCount % 2 == 0) {
                player = X;
            } else {
                player = O;
            }
    }

    /**
     *  This method produces a random integer as the computer choice while
     * the player is playing with the computer.
     * @return int
     */
    public int computer() {
            int computerChoice = 0;

            if (countFills == (nPow - 1)) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (isInteger(chart[i][j]))
                            return (Integer.parseInt(chart[i][j]));
                    }
                }
            }

            while (true) {
                computerChoice = random.nextInt(1, nPow);
                rowColMaking(computerChoice);
                if (!(chart[row][col].equals(sharp)) && !(chart[row][col].equals(O)) && !(chart[row][col].equals(X)))
                    break;
            }
            return computerChoice;
    }

    /**
     *  This method has the user's or computer's option(i.e. number of the cell in the chart)
     * and declares its row and column in the chart.
     * @param option
     */
    private void rowColMaking(int option) {
            for (int i = 0; i < n; i++) {
                if (option >= ((n * i) + 1) && option <= n * (i + 1)) {
                    row = i;
                }
            }
            if (option % n == 0)
                col = n - 1;
            for (int i = 1; i < n; i++) {
                if(option % n == i)
                    col = i - 1;
            }
    }

    /**
     *  This method place the user's or computer's option in the chart, using the row and column
     * which rowColMaking method has produced.
     * @param option
     * @param howToPlay
     * @return
     */
    private boolean setChart(int option, int howToPlay) {
            if ((option == 0) && (howToPlay == 1)) {
                option = computer();
            }
            rowColMaking(option);
            if (chart[row][col].equals(sharp) || chart[row][col].equals(O) || chart[row][col].equals(X))
                return false;
            else {
                chart[row][col] = player;
                countFills++;
            }
            return true;
    }

    /**
     *  This method does all the needed calculations to determine the winner
     * in the rows, cols, diags and antidiags of the chart.
     * @return
     */
    public int checkWin() {
            if (moveCount > 4) {
                // tie
                if ((countFills == nPow))
                    return 2;

                // row wins
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    if (chart[row][i].equals(player))
                        sum++;
                    else {
                        sum = 1;
                        player = chart[row][i];
                    }
                    if (sum == winCells)
                        return 0;
                }

                // col wins
                sum = 0;
                for (int i = 0; i < n; i++) {
                    if (chart[i][col].equals(player))
                        sum++;
                    else {
                        sum = 1;
                        player = chart[i][col];
                    }
                    if (sum == winCells)
                        return 0;
                }

                // diag wins
                sum = 0;
                if (row == col) {
                    for (int i = 0; i < n; i++) {
                        if (chart[i][i].equals(player))
                            sum++;
                        else {
                            sum = 1;
                            player = chart[i][i];
                        }
                        if (sum == winCells)
                            return 0;
                    }
                } else {
                    for (int i = 1; i < (n - 2); i++) {
                        if (row == (col - i)) {
                            for (int j = 0; j < (n - 1); j++) {
                                if (chart[i][j + 1].equals(player))
                                    sum++;
                                else {
                                    sum = 1;
                                    player = chart[j][j + 1];
                                }
                                if (sum == winCells)
                                    return 0;
                            }
                        } else if (row == (col + i)) {
                            for (int j = 1; j < n; j++) {
                                if (chart[j][j - 1].equals(player))
                                    sum++;
                                else {
                                    sum = 1;
                                    player = chart[j][j - 1];
                                }
                                if (sum == winCells)
                                    return 0;
                            }
                        }
                    }
                }

                // antiDiag wins
                sum = 0;
                int minusN = n - 1;
                if (row + col == minusN) {
                    for (int i = 0; i < n; i++) {
                        if (chart[i][Math.abs(minusN - i)].equals(player))
                            sum++;
                        else {
                            sum = 1;
                            player = chart[i][Math.abs(minusN - i)];
                        }
                        if (sum == winCells)
                            return 0;
                    }
                } else {
                    for (int i = 1; i < (n - 2); i++) {
                        if (row + col == (minusN - i)) {
                            for (int j = 0; j < (n - 1); j++) {
                                if (chart[j][Math.abs(2 - j)].equals(player))
                                    sum++;
                                else {
                                    sum = 1;
                                    player = chart[j][Math.abs(2 - j)];
                                }
                                if (sum == winCells)
                                    return 0;
                            }
                        } else if (row + col == (minusN + i)) {
                            for (int j = 1; j < 4; j++) {
                                if (chart[j][Math.abs(4 - j)].equals(player))
                                    sum++;
                                else {
                                    sum = 1;
                                    player = chart[j][Math.abs(4 - j)];
                                }
                                if (sum == winCells)
                                    return 0;
                            }
                        }
                    }
                }
            }
            return 1;
    }

    /**
     *  This method gathers all the private methods and gives their outputs to
     * external classes.
     * @param option
     * @param howToPlay
     * @return
     */
    public int gatheredThingsTogether(int option, int howToPlay) {
            if (!setChart(option, howToPlay)) {
                return 1;
            }

            if (checkWin() == 0)
                return 0;
            else if (checkWin() == 2)
                return 2;
            else
                return -1;
    }

    /**
     * It prints the table of the game.
     */
    public void display() {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println();
            for (int i = 0; i < n; i++) {
                for (int i1 = 0; i1 < n; i1++) {
                    System.out.printf("%-5s", chart[i][i1]);;
                }
                System.out.println("\n");
            }

    }

    /**
     *  It's used in the computer method where we need to know if the String input parameter
     * is Integer or not.
     * @param str
     * @return
     */
    private boolean isInteger(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }