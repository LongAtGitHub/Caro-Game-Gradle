package algorithms;

public class CheckWin {
    /**
     * 
     * @param arr character array
     * @param recentChar recent turn played char value
     * @param recentI the index I of the recent turn
     * @param recentJ the index J of the recent turn
     */
    private Character[][] arr;
    private Integer m,n;
    private Integer recentI, recentJ, targetCount;
    private Character charVal;
    /**
     * 
     * @param m nrow of the board
     * @param n ncol of the board
     * @param typeOfGame  If the game is 3x3, fill in typeOfGame = 3. If the game is at least size 5x5, fill in targetCount=5
     * 
     */
    public CheckWin(Integer m, Integer n, Integer targetCount) {
        this.m = m; this.n = n; 
        this.targetCount = targetCount;
    }

    public CheckWin(Integer m, Integer n) {
        this.m = m; this.n = n; 
        this.targetCount = 3;
    }

    /**
     * @param arr pass in array char
     * @param recentChar pass in recent char
     * @param recentI
     * @param recentJ
     * @return 0 (tie, the game can still resume), 1 (X wins), -1 (O wins)
     */
    public int output(
        Character[][] arr, 
        Character recentChar, 
        Integer recentI,
        Integer recentJ ) 
    
    {
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
        return 0;
    }

    /**
     * @param deltaI orientation of i that counts up matching char val
     * @param deltaJ orientation of j that counts up matching char val
     * @return
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

    public void printCharArray() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }


    private boolean checkBound(Integer i, Integer j) {
        return (0<=i && i<m && 0<=j && j<n);
    }
}
