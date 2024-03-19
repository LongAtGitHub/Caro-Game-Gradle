package algorithms;

import java.util.Random;

public class MiniMaxBot extends Robot {
    protected boolean isMaximizing;
    protected CheckWin winChecker;
    /**
     * 
     * @param nrow nrow of the board
     * @param ncol
     * @param markPlay 'X' or 'O'?
     * @param targetCount how many identical marks in a sequence to win the game?
     */
    public MiniMaxBot(int nrow, int ncol, Character markPlay, int targetCount) {
        super(nrow, ncol, markPlay);
        if (markPlay=='X') isMaximizing= true;
        else isMaximizing=false;
        // CheckWin winChecker = new CheckWin(nrow, ncol, targetCount);
    }

    
    @Override
    public int[] output( Character[][] charArr) {
        return new int[] {1,2};
    }

    public int miniMax(int depthLevel) {
        return -1;
    }

}