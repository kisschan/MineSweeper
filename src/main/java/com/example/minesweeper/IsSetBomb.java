package com.example.minesweeper;

import java.util.Random;

/* 💣をおくかどうか決めるだけのクラス*/

public class IsSetBomb {

    private final SetRandom setRandom = new SetRandom(100,new Random());
    private final BackGroundNumbersArrayClass backGroundNumbersArrayClass ;
    private final NearBombPanesIsInRange nearBombPanesIsInRange;

    public IsSetBomb(BackGroundNumbersArrayClass backGroundNumbersArrayClass) {
        this.backGroundNumbersArrayClass = backGroundNumbersArrayClass;
        nearBombPanesIsInRange = new NearBombPanesIsInRange(this.getBackGroundNumbersArrayClass());
    }

    public void isPutBomb(int line, int column){
        if(setRandom.getRandom() < 10){
            nearBombPanesIsInRange.nearBombPanesBackGroundNumbersLoop(line,column);
        }
    }

    public BackGroundNumbersArrayClass getBackGroundNumbersArrayClass() {
        return backGroundNumbersArrayClass;
    }

}
