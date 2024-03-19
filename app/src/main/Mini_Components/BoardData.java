package Mini_Components;

import algorithms.CheckWin;

public class BoardData {
    private int fillCount;
    private Character[][] charArr;
    private CheckWin winChecker;

    private Integer recentMoveI = null;
    private Integer recentMoveJ = null;
    private Character recentMark = 'N';

    private int m,n;

    public BoardData(int m, int n, int targetCount) {
        this.m = m; 
        this.n = n;
        this.charArr = new Character[m][n];
        for (int i=0; i< m; i++) {
            for (int j=0; j< n; j++) {
                charArr[i][j] = 'N';
            }
        }
        this.fillCount = 0;
        winChecker = new CheckWin(m,n, targetCount);
        
    }


    public int getFillCount() { return fillCount;}
    public int getRecentMark() { return recentMark;}
    public Character[][] getArrayData() { return charArr; }


    // public void copy(Character [][] charArr, int fillCount) {
    //     this.charArr = charArr;
    //     this.fillCount = fillCount;
    // }

    /**
     * Mark down on the board
     * @param playerMark X or O
     * @param i i position
     * @param j j position
     * @return true if the move is legal
     */
    public boolean markPosition(char playerMark, int i, int j) {
        
        if (!checkBound(i, j) || 
            (playerMark != 'X' && playerMark != 'O') ||
            (charArr[i][j] != 'N')
        ) return false;
        // if (playerMark != 'X' && playerMark != 'O') return false;

        this.charArr[i][j] = playerMark;
        this.fillCount++;
        this.recentMoveI= i; this.recentMoveJ = j;
        this.recentMark = playerMark;
        return true;
    }

    public boolean removeMark(int i, int j) {
        
        Character playerMark = charArr[i][j];
        if (!checkBound(i, j)) return false;
        if (playerMark == 'X' || playerMark == 'O') {
            this.charArr[i][j] = 'N';
            this.fillCount +=-1;
            return true;
        }
        return false;
    }

    public boolean checkBound(Integer i, Integer j) {
        return (0<=i && i<m && 0<=j && j<n);
    }

    public Integer getRecentMoveI() {return recentMoveI;}
    public Integer getRecentMoveJ() { return recentMoveJ;}

    public void printCharArray() {
    
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(this.charArr[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
   
    }

    public Integer winStatus() {
        if (recentMark== 'N' || recentMoveI == null || recentMoveJ== null) { return null; }
        return winChecker.output(charArr, recentMark, recentMoveI, recentMoveJ, fillCount);
    }
}
