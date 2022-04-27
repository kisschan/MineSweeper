package com.example.minesweeper;

import java.util.Random;

/* 💣をおくかどうか決めるだけのクラス*/

public class IsSetBomb {

    private final SetRandom setRandom = new SetRandom(100,new Random());
    private final BackGroundNumbersArrayClass backGroundNumbersArrayClass ;
    private final SetBomb setbomb;

    public IsSetBomb(BackGroundNumbersArrayClass backGroundNumbersArrayClass) {
        this.backGroundNumbersArrayClass = backGroundNumbersArrayClass;
        setbomb = new SetBomb(this.getBackGroundNumbersArrayClass());
    }

    public void isPutBomb(int line, int column){
        if(setRandom.getRandom() < 10){
            this.nearBombPanesBackGroundNumbersLoop(line,column);
        }
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

    public BackGroundNumbersArrayClass getBackGroundNumbersArrayClass() {
        return backGroundNumbersArrayClass;
    }
}
