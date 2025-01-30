package com.gopal;

public class PlayerMove {
    private final int rowNum;
    private final int colNum;

    public PlayerMove(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    @Override
    public String toString() {
        return "PlayerMove{" +
                "rowNum=" + rowNum +
                ", colNum=" + colNum +
                '}';
    }
}
