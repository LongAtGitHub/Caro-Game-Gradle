package algorithms;

import java.util.ArrayList;

public abstract class Robot {
    protected int nrow;
    protected int ncol;
    protected ArrayList<int[]> possibleMove;
    protected Character markPlay;

    public Robot(int nrow, int ncol, Character markPlay ) {
        this.nrow = nrow;
        this.ncol = ncol;
        this.markPlay = markPlay;
    }

    // Define an abstract method to output the next move
    public abstract int[] output(Character[][] charArr);
}
