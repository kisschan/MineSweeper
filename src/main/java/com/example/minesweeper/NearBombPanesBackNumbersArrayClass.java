package com.example.minesweeper;

public class NearBombPanesBackNumbersArrayClass {

    private BackGroundNumbersArrayClass backGroundNumbersArrayClass;
    private final SetBomb setbomb;

    public NearBombPanesBackNumbersArrayClass(BackGroundNumbersArrayClass backGroundNumbersArrayClass) {
        this.backGroundNumbersArrayClass = backGroundNumbersArrayClass;
        setbomb = new SetBomb(this.backGroundNumbersArrayClass);
    }

    public void nearBombPanesBackGroundNumbersLoop(int line, int column){
        for(int k= -1 ; k < 2; k++){
            for (int l = -1 ; l < 2; l++){
                this.nearBombPanesIsInRange(line,column,k,l);
            }
        }
    }

    public void nearBombPanesIsInRange(int line , int column, int currentLine, int currentColumn) {
        if (line + currentLine > -1 && column + currentColumn > -1 && line + currentLine < 10 && column + currentColumn < 10) {
            backGroundNumbersArrayClass.addBackGroundNumber(line + currentLine, column + currentColumn);
            setbomb.setBomb(line,column,currentLine,currentColumn);
        }
    }

}
