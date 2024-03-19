package algorithms;

import java.util.ArrayList;
import java.util.Random;

public class RandomBot extends Robot {
    // private int nrow ;
    // private int ncol ;
    // private ArrayList<int[]> possibleMove;

    // private int outputI, outputJ;

    public RandomBot(int nrow, int ncol, Character markPlay) {
        super(nrow, ncol, markPlay);
    }


    @Override
    public int[] output( Character[][] charArr) {
        setPosssibleMove(charArr);
        Random random = new Random();
        int randomIndex = random.nextInt(possibleMove.size());
        int[] randomElement = this.possibleMove.get(randomIndex);
        // int indexI = randomElement[0];
        // int indexJ = randomElement[1];
        // return new int[]{indexI, indexJ};
        return randomElement;
    }

    private void setPosssibleMove(Character[][] charArr) {
        possibleMove = new ArrayList<int[]>();
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                if (charArr[i][j] == 'N' ){
                    int[] indices = new int[] {i,j};
                    possibleMove.add(indices);
                } 
            }
        } 
    }


    
}
