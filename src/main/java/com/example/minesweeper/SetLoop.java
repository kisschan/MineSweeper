package com.example.minesweeper;

public class SetLoop {

    private final IsSetBomb isSetBomb ;

    public SetLoop(IsSetBomb isSetBomb) {
        this.isSetBomb = isSetBomb;
    }

    public void backGroundNumbersArrayLoop(){
        for(int x = 0; x < 10; x++){
            for (int y = 0; y < 10; y++){
                isSetBomb.isPutBomb(x,y);
            }
        }
    }

}
