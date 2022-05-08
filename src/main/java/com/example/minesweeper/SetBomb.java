package com.example.minesweeper;

//💣をおくだけ

public class SetBomb {

    private final BackGroundNumbersArrayClass backGroundNumbersArrayClass;

    public SetBomb(BackGroundNumbersArrayClass backGroundNumbersArrayClass) {
        this.backGroundNumbersArrayClass = backGroundNumbersArrayClass;
    }

    public void setBomb(int line, int column, int currentLine, int currentColumn){
        if (line + currentLine == line && column + currentColumn == column) {
            backGroundNumbersArrayClass.setBackGroundNumber(line, column, 100);
        }
    }
}
