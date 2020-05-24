package ru.geekbrains.java;

import com.sun.deploy.security.SelectableSecurityManager;

import java.util.Scanner;
import java.util.Random;

public class XO
{
    public static char map[][];
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = '0';

    public static void main(String[] args)
    {
        initMap();
        printMap();

        while (true)
        {
            humanTurn();
            printMap();
            if (isWinner(DOT_X))
            {
                System.out.println("Вы победили!!");
                System.out.println("Игра закончена.");
                break;
            }
            if (isMapFull())
            {
                System.out.println("НИЧЬЯ!");
            }
            aiTurn();
            printMap();
            if (isWinner(DOT_O))
            {
                System.out.println("Победил компьютер!");
                System.out.println("Игра закончена.");
                break;
            }
        }
    }

    static void initMap()
    {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    static void printMap()
    {
        for(int i = 0; i <= SIZE; i++)
        {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++)
        {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void humanTurn()
    {
        int x;
        int y;
        System.out.println("Сделайте свой ход. ");
        Scanner scanner = new Scanner(System.in);

        do
        {
            System.out.println("Введите координаты клетки X и затем координаты клетки Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;

            if (x <= 0 || x > SIZE || y <= 0 || y > SIZE)
            {
                System.out.println("Координаты вне игрового поля. Повторите ввод.");
            }

        }
        while (!isCellValid(x, y));
        {
            map[y][x] = DOT_X;
        }

    }

    static void aiTurn()
    {
        int x;
        int y;
        Random random = new Random();

        do
        {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        }

        while (!isCellValid(x,y));
        {
            System.out.println("Компьютер cделал ход по координатам Х Y: " + (x + 1) + " " + (y + 1));
            map[y][x] = DOT_O;
        }
    }

        public static boolean isMapFull()
        {
            for (int i = 0; i < SIZE; i++)
            {
                for (int j = 0; j < SIZE; j++)
                {
                    if (map[i][j] == DOT_EMPTY)
                    return false;
                }

            }
            return true;
        }


//    static boolean isWinner(char symbol)
//    {

//        if (map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol)
//        {
//            return true;
//        }
//            else if (map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol)
//        {
//            return true;
//        }
//            else if (map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol)
//        {
//            return true;
//        }
//            else if (map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol)
//        {
//            return true;
//        }
//            else if (map[0][1] == symbol && map[1][1] == symbol && map[1][2] == symbol)
//        {
//            return true;
//        }
//            else if (map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol)
//        {
//            return true;
//        }
//            else if (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol)
//        {
//            return true;
//        }
//            else if (map[0][2] == symbol && map[1][1] == symbol && map[2][1] == symbol)
//        {
//            return true;
//        }
//            else
//            {
//            return false;
//            }
//    }

    static boolean isCellValid(int x, int y)
    {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE)
        {
            return false;
        }

        if ((map[y][x]) == DOT_EMPTY)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    static boolean checkDiagonal(char symbol, int offsetX, int offsetY)
    {
        boolean toright = true, toleft = true;
        for (int i = 0; i < DOTS_TO_WIN; i++)
        {
            toright = toright & (map[i + offsetX][i + offsetY] == symbol);
            toleft = toleft & (map[DOTS_TO_WIN - i - 1 + offsetX][i + offsetY] == symbol);
        }
        if (toleft || toleft) return true;
        return false;
    }
    static boolean checkLines(char symbol, int offsetX, int offsetY)
    {
        boolean cols = true, rows = true;
        for (int i = offsetX; i < DOTS_TO_WIN + offsetX; i++)
        {
            cols = true;
            rows = true;
            for (int j = offsetY; j < DOTS_TO_WIN + offsetY; j++)
            {
                cols = cols & (map[i][j]) == symbol;
                rows = rows & (map[j][i]) == symbol;
            }
            if (cols || rows) return true;
        }
        return false;
    }

    static boolean isWinner (char symbol)
    {
        for (int i = 0; i < SIZE - DOTS_TO_WIN + 1; i++)
            for (int j = 0; j < SIZE - DOTS_TO_WIN + 1; j++)
            {
                if (checkDiagonal(symbol, i, j) || checkLines(symbol, i, j)) return true;
            }
         return false;
    }

}






