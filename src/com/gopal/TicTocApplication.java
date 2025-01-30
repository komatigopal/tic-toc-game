package com.gopal;

import java.util.*;

public class TicTocApplication {
    private static final String[][] board = new String[3][3];
    private static final String player1S = "X", player2S = "O";
    private static final int min = 0, max = 2;
    private static final String initialCharForBoard = " ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the 1st player name - ");
        String player1Name = sc.nextLine();
        Player player1 = new Player(player1Name, player1S);
        System.out.println("Enter the 2nd player name - ");

        String player2Name = sc.nextLine();
        Player player2 = new Player(player2Name, player2S);

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
                    board[rowNum][colNum] = player1S;
                    player1.getPlayerMoves().add(playerMove);
                    player1Turn = false;
                } else {
                    board[rowNum][colNum] = player2S;
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
        List<PlayerMove> playerMoves = player.getPlayerMoves();
        System.out.println(player.getPlayerName() + " playerMoves - " + playerMoves);
        Set<PlayerMove> completedMoves = new HashSet<>();
        String[] directions = "row col rightDown rightUp".split(" ");
        for (String direction : directions) {
            for (PlayerMove currentMove : playerMoves) {
                completedMoves = new HashSet<>();
                if (playerMoves.size() >= 3 && recursiveCheckWon(playerMoves, currentMove, completedMoves, direction)) {
                    System.out.println(player.getPlayerName() + " Won the game");
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean recursiveCheckWon(List<PlayerMove> playerMoves, PlayerMove currentMove, Set<PlayerMove> completedMoves, String direction) {
        int rowNum = currentMove.getRowNum();
        int colNum = currentMove.getColNum();
        for (PlayerMove playerMove : playerMoves) {
            if (direction.equals("row") && !completedMoves.contains(playerMove) && playerMove.getRowNum() == rowNum && (playerMove.getColNum() == colNum + 1 || playerMove.getColNum() == colNum - 1)) {
                completedMoves.add(playerMove);
                completedMoves.add(currentMove);
                return recursiveCheckWon(playerMoves, playerMove, completedMoves, direction);
            } else if (direction.equals("col") && !completedMoves.contains(playerMove) && (playerMove.getRowNum() == rowNum + 1 || playerMove.getRowNum() == rowNum - 1) && playerMove.getColNum() == colNum) {
                completedMoves.add(playerMove);
                completedMoves.add(currentMove);
                return recursiveCheckWon(playerMoves, playerMove, completedMoves, direction);
            } else if (direction.equals("rightDown") && !completedMoves.contains(playerMove) && playerMove.getRowNum() == rowNum + 1 && playerMove.getColNum() == colNum + 1) {
                completedMoves.add(playerMove);
                completedMoves.add(currentMove);
                return recursiveCheckWon(playerMoves, playerMove, completedMoves, direction);
            } else if (direction.equals("rightUp") && !completedMoves.contains(playerMove) && playerMove.getRowNum() == rowNum - 1 && playerMove.getColNum() == colNum + 1) {
                completedMoves.add(playerMove);
                completedMoves.add(currentMove);
                return recursiveCheckWon(playerMoves, playerMove, completedMoves, direction);
            }
        }
        return completedMoves.size() == board.length;
    }

    private static boolean checkAnyCellNull() {
        for (String[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        return Arrays.stream(board).anyMatch(row -> Arrays.asList(row).contains(initialCharForBoard));
    }
}
