package com.example.minesweeper;

import java.util.Random;

/* 💣をおくかどうか決めるだけのクラス*/

public class IsSetBomb {

    private final SetRandom setRandom = new SetRandom(100,new Random());
    private final BackGroundNumbersArrayClass backGroundNumbersArrayClass ;
    private final NearBombPanesBackNumbersArrayClass nearBombPanesBackNumbersArrayClass;

    public IsSetBomb(BackGroundNumbersArrayClass backGroundNumbersArrayClass) {
        this.backGroundNumbersArrayClass = backGroundNumbersArrayClass;
        nearBombPanesBackNumbersArrayClass = new NearBombPanesBackNumbersArrayClass(this.getBackGroundNumbersArrayClass());
    }

    public void isPutBomb(int line, int column){
        if(setRandom.getRandom() < 10){
            nearBombPanesBackNumbersArrayClass.nearBombPanesBackGroundNumbersLoop(line,column);
        }
    }

    public BackGroundNumbersArrayClass getBackGroundNumbersArrayClass() {
        return backGroundNumbersArrayClass;
    }

}
