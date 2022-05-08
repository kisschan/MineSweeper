package com.example.minesweeper;

import java.util.Random;

//ランダムを返すだけのクラス

public class SetRandom {

    private final int randomNumber;
    private final Random random;

    public SetRandom(int randomising, Random random) {
        this.randomNumber = randomising;
        this.random = random;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public int getRandom() {
        return random.nextInt(this.getRandomNumber());
    }
}
