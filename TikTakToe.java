import java.util.Scanner;

public class TikTakToe {
    static Scanner scanner;
    static char [][] map;

    static final int MAP_SIZE = 3;

    static final char EMPTY_FIELD = '*';
    static final char X_FIELD = 'X';
    static final char O_FIELD = 'O';

    static int TURN_COUNT;

    public static void main(String[] args) {
        init();
        printMap();
        do {
            if (fullField()) break;
            humanTurn();
            printMap();
            if (isWinner(X_FIELD)) {
                System.out.println("Ты выиграл!");
                break;
            }

            if (fullField()) break;
            aiTurn();
            printMap();
            if (isWinner(O_FIELD)) {
                System.out.println("Ты проиграл!");
                break;
            }
        } while (true);
    }

    public static boolean isWinner(char playerField) {
        int horizontalWinner, verticallyWinner;

        for (int i = 0; i < MAP_SIZE; i++) {
            horizontalWinner = 0;
            for (int j = 0; j < MAP_SIZE; j++) {
                if(map[i][j] == playerField) horizontalWinner++;
            }
            if (horizontalWinner==3) return true;
        }

        for (int i = 0; i < MAP_SIZE; i++) {
            verticallyWinner = 0;
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[j][i] == playerField) verticallyWinner++;
            }
            if (verticallyWinner==3) return true;
        }

        //check diagonal
        if (map[1][1] == playerField && map[0][0] == playerField && map[2][2] == playerField ||
                map[1][1] == playerField && map[2][0] == playerField && map[0][2] == playerField) {
            return true;
        }
        return false;
    }

    public static boolean fullField() {
        if(TURN_COUNT == 9) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }
    public static boolean isCellValid(int x, int y) {
        if (y<0 || x<0 || y>=MAP_SIZE || x>=MAP_SIZE) {
            return false;
        }
        if (map[y][x] != EMPTY_FIELD) {
            return false;
        }
        return true;
    }

    public static void aiTurn() {
        int x, y;

        do {
            y = (int)(Math.random() * MAP_SIZE);
            x = (int)(Math.random() * MAP_SIZE);
        } while(!isCellValid(x, y) || fullField());

        map[y][x] = O_FIELD;
        TURN_COUNT++;
    }

    public static void humanTurn() {
        int x, y;

        do {
            System.out.println("Ваш ход!");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while(!isCellValid(x, y));

        map[y][x] = X_FIELD;
        TURN_COUNT++;
    }

    public static void printMap() {
        for (int i = 0; i <= MAP_SIZE; i++) {
            System.out.print(i + " ");
        }
        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.println();
            System.out.print(i + 1 + " ");
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static void init() {
        map = new char [MAP_SIZE][MAP_SIZE];

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = EMPTY_FIELD;
            }
        }
        scanner = new Scanner(System.in);
    }
}