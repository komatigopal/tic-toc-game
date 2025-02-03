package com.gopal;

import java.util.Arrays;
import java.util.Scanner;

public class TicTocApplication {
    private static final String[][] board = new String[3][3];
    private static final String player1Coin = "X", player2Coin = "O";
    private static final int min = 0, max = 2;
    private static final String initialCharForBoard = " ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the 1st player name - ");
        String player1Name = sc.nextLine();
        Player player1 = new Player(player1Name, player1Coin);
        System.out.println("Enter the 2nd player name - ");

        String player2Name = sc.nextLine();
        Player player2 = new Player(player2Name, player2Coin);

        createBoard();

        boolean player1Turn = true;
        while (checkAnyCellNull() && !(checkWon(player1) || checkWon(player2))) {
            System.out.println("Enter the " + (player1Turn ? player1Name : player2Name) + "'s move - ");
            String[] input = sc.nextLine().split(" ");
            int rowNum = Integer.parseInt(input[0]) - 1;
            int colNum = Integer.parseInt(input[1]) - 1;
            if (checkRowColNumValid(rowNum) && checkRowColNumValid(colNum) && board[rowNum][colNum].equals(initialCharForBoard)) {
                PlayerMove playerMove = new PlayerMove(rowNum, colNum);
                if (player1Turn) {
                    board[rowNum][colNum] = player1Coin;
                    player1.getPlayerMoves().add(playerMove);
                    player1Turn = false;
                } else {
                    board[rowNum][colNum] = player2Coin;
                    player2.getPlayerMoves().add(playerMove);
                    player1Turn = true;
                }
            } else {
                System.out.println("Invalid PlayerMove, " + (player1Turn ? player1Name : player2Name) + " need to move again");
            }
        }
        System.out.println("End of game");
        sc.close();
    }

    private static void createBoard() {
        for (String[] rows : board) {
            Arrays.fill(rows, initialCharForBoard);
        }
    }

    private static boolean checkRowColNumValid(int rowOrColNum) {
        return rowOrColNum >= min && rowOrColNum <= max;
    }

    private static boolean checkWon(Player player) {
        String playerCoin = player.getPlayerCoin();
        int count = 0;
        //checking row wise
        for (String[] strings : board) {
            count = 0;
            for (int colIndex = 0; colIndex < board.length; colIndex++) {
                String currentCoin = strings[colIndex];
                if (currentCoin != null && currentCoin.equals(playerCoin)) {
                    count++;
                }
                if (count == board.length) {
                    System.out.println(player.getPlayerName() + " Won the game");
                    return true;
                }
            }
        }

        //checking col wise
        for (int colIndex = 0; colIndex < board.length; colIndex++) {
            count = 0;
            for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
                String currentCoin = board[rowIndex][colIndex];
                if (currentCoin != null && currentCoin.equals(playerCoin)) {
                    count++;
                }
                if (count == board.length) {
                    System.out.println(player.getPlayerName() + " Won the game");
                    return true;
                }
            }
        }

        //checking \ wise
        count = 0;
        for (int index = 0; index < board.length; index++) {
            String currentCoin = board[index][index];
            if (currentCoin != null && currentCoin.equals(playerCoin)) {
                count++;
            }
            if (count == board.length) {
                System.out.println(player.getPlayerName() + " Won the game");
                return true;
            }
        }

        //checking / wise
        count = 0;
        for (int index = 0; index < board.length; index++) {
            String currentCoin = board[index][board.length - 1 - index];
            if (currentCoin != null && currentCoin.equals(playerCoin)) {
                count++;
            }
            if (count == board.length) {
                System.out.println(player.getPlayerName() + " Won the game");
                return true;
            }
        }
        return false;
    }

    private static boolean checkAnyCellNull() {
        for (String[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        return Arrays.stream(board).anyMatch(row -> Arrays.asList(row).contains(initialCharForBoard));
    }
}
