import java.sql.SQLOutput;
import java.util.*;

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
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static String X = ANSI_CYAN + "X    " + ANSI_RESET;
        public static String O = ANSI_YELLOW + "O    " + ANSI_RESET;
        public static String sharp = ANSI_RED + "#    " + ANSI_RESET;
        private Random random = new Random();
        public String player;
        private int moveCount;
        private String[] lock = new String[lockCells];
        private int row, col;
        private int countFills = 0;
        private String[][] chart = new String[n][n];

        public ChartDesign() {

            int count = 1;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    chart[i][j] = String.valueOf(count++);

//            int[] lockInt = new int[lockCells];
//            int i = 0;
//            Set<Integer> set = new HashSet<Integer>();
//            while (set.size() < lockCells) {
//                int num = random.nextInt(nPow);
//                lockInt[i++] = num + 1;
//            }
//            for (int i1 = 0; i1 < lockCells; i1++) {
//                System.out.print(lockInt[i] + " ");
//            }


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
                System.out.print(lockInt[i] + " ");
            }

//            int[] lockInt = new int[lockCells];
//            lockInt[0] = random.nextInt(1, nPow); // ye doonaro ba hamash moghayese kon hey
//            for (int i = 1; i < lockCells; i++) {
//                int lockCheck = random.nextInt(1, nPow);
//                for (int j = 0; j < i + 1; j++) {
//                    if (lockCheck == lockInt[i - 1]) {
//                        i--;
//                        break;
//                    } else {
//                        lockInt[i] = lockCheck;
//                        lock[i] = String.valueOf(lockInt[i]);
//                    }
//                }
//            }
            for (int i = 0; i < lockCells; i++) {
                rowColMaking(lockInt[i]);
                chart[row][col] = sharp;
            }

        }

        public void setPlayer(int moveCount) {
            this.moveCount = moveCount;
            setPlayerOption();
        }

        private void setPlayerOption() {
            if (moveCount % 2 == 0) {
                player = X;
            } else {
                player = O;
            }
        }

        public int computer() {
            int computerChoice = 0;
            for (int i = 0; i < n; i++) {
                for (int i1 = 0; i1 < n; i1++) { // do we need an if with the condition (moveCount == 12)?
                    //if (chart[i][i1].equals(X) || chart[i][i1].equals(O) || chart[i][i1].equals(sharp))
                    if (!isInteger(chart[i][i1]))
                        countFills++;
                    else
                        computerChoice = Integer.valueOf(chart[i][i1]);
                }
            }
            if (countFills == (nPow - 1))
                return computerChoice;

            while (true) {
                computerChoice = random.nextInt(1, nPow);
                rowColMaking(computerChoice);
                if (!(chart[row][col].equals(sharp)) && !(chart[row][col].equals(O)) && !(chart[row][col].equals(X)))
                    break;
            }
            return computerChoice;
        }

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
//            if (option % 4 == 1)
//                col = 0;
//            else if (option % 4 == 2)
//                col = 1;
//            else if (option % 4 == 3)
//                col = 2;
//            else if (option % 4 == 0)
//                col = 3;
        }

        private boolean setChart(int option, int howToPlay) {
            if ((option == 0) && (howToPlay == 1)) {
                option = computer();
            }
            rowColMaking(option);
            if (chart[row][col].equals(sharp) || chart[row][col].equals(O) || chart[row][col].equals(X))
                return false;
            else
                chart[row][col] = player;
            return true;
        }


        public int checkWin() {
            if (moveCount > 4) {
                // tie
                if (moveCount == (nPow - lockCells) && (countFills == nPow)) // maybe there's no need to the (countFills == 16).
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

        public int gatheredUpThingsTogether(int option, int howToPlay) {
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

        public void display() {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println();
            for (int i = 0; i < n; i++) {
                for (int i1 = 0; i1 < n; i1++) {
                    System.out.printf("%-5s", chart[i][i1]);;
                }
                System.out.println("\n" /*+ ANSI_PURPLE + "-----------------" + ANSI_RESET*/);
            }

        }

        private boolean isInteger(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }