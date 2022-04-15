package com.example.minesweeper;

public class BackGroundNumbersArrayClass {
    private int [][] backGroundNumbersArrays ;

    public BackGroundNumbersArrayClass(int[][] backGroundNumbersArrays) {
        this.backGroundNumbersArrays = backGroundNumbersArrays;
    }

    public  void addBackGroundNumber(int line, int column){
        this.backGroundNumbersArrays[line][column]++;
    }

    public int getBackGroundNumber(int line, int column) {
        return backGroundNumbersArrays[line][column];
    }

    public void setBackGroundNumber(int line, int column,int numbers) {
        this.backGroundNumbersArrays[line][column] = numbers;
    }
}
