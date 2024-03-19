package algorithms;

import org.checkerframework.checker.units.qual.mol;

public class CheckWin {
    private Character[][] arr;
    private Integer m,n;
    private Integer recentI, recentJ, targetCount;
    private Character charVal;
    private int fillCount;
   

    /**
     * @param arr pass in array char
     * @param recentChar pass in recent char
     * @param recentI
     * @param recentJ
     * @return null (Ongoing), 1 (X wins), -1 (O wins), 0 (absolute tie)
     */

    public CheckWin(Integer m, Integer n, Integer targetCount) {
        this.m= m;
        this.n = n;
        this.targetCount = targetCount;
    }
    public Integer output(
        Character[][] arr, 
        Character recentChar, 
        Integer recentI,
        Integer recentJ,
        Integer fillCount ) 
    
    {
        if (fillCount == m*n) {return 0;}
        this.arr=  arr; this.recentI = recentI; this.recentJ = recentJ; this.charVal = recentChar;
        // printCharArray();
        if (countCharSeq(0, 1) >= targetCount || 
            countCharSeq(1, 0) >=  targetCount || 
            countCharSeq(1, 1) >=  targetCount || 
            countCharSeq(1, -1) >= targetCount) 
        {
            if (charVal=='X') {return 1;}
            else { return -1;}
        } 
        return null;
    }

    /**
     * @param deltaI orientation of i that counts up matching char val
     * @param deltaJ orientation of j that counts up matching char val
     * @return how many matching mark in the direction of orientation I and J
     */
    private int countCharSeq(int deltaI, int deltaJ) {
        Integer i= recentI; Integer j = recentJ;
        int count = 1;
        // forward
        for (int t=0; t<5; t++) {
            i+= deltaI; j+=deltaJ;
            if (checkBound(i, j) && this.arr[i][j] == this.charVal ) {
                count+=1;
                // System.out.println(i+" "+ j);
            }
            else break;
        }
        
        // backward
        i= recentI; j = recentJ;
        deltaI*=-1; deltaJ*=-1;
        for (int t=0; t<5; t++) {
            i+= deltaI; j+=deltaJ;
            if (checkBound(i, j) && this.arr[i][j] == this.charVal ) {
                count+=1;
            }
            else break;
        }
        return count;
    }


    private boolean checkBound(Integer i, Integer j) {
        return (0<=i && i<m && 0<=j && j<n);
    }
}
