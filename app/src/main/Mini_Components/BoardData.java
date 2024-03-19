package Mini_Components;

import algorithms.CheckWin;

public class BoardData {
    private int fillCount = 0;
    private Character[][] charArr;
    private CheckWin winChecker;

    private Integer recentMoveI = null;
    private Integer recentMoveJ = null;
    private Character recentMark = null;

    private int m,n;

    public BoardData(int m, int n, int targetCount) {
        this.charArr = new Character[m][n];
        for (int i=0; i< m; i++) {
            for (int j=0; j< n; j++) {
                charArr[i][j] = null;
            }
        }
        winChecker = new CheckWin(m, n, targetCount);
    }


    public int getFillCount() { return fillCount;}
    public int getRecentMark() { return recentMark;}


    // public void copy(Character [][] charArr, int fillCount) {
    //     this.charArr = charArr;
    //     this.fillCount = fillCount;
    // }

    public boolean markPosition(char playerMark, int i, int j) {
        this.fillCount++;
        if (!checkBound(i, j)) return false;
        if (playerMark != 'X' && playerMark != 'O') return false;
        this.charArr[i][j] = playerMark;
        
        this.recentMoveI= i; this.recentMoveJ = j;
        return true;
    }

    public boolean removeMark(int i, int j) {
        this.fillCount +=-1;
        Character playerMark = charArr[i][j];
        if (!checkBound(i, j)) return false;
        if (playerMark == 'X' || playerMark == 'O') {
            this.charArr[i][j] = null;
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
        if (recentMark== null || recentMoveI == null || recentMoveJ== null) { return null; }
        return winChecker.output(charArr, recentMark, recentMoveI, recentMoveJ);
    }
}
