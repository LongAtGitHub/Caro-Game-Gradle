package Pages;

import java.util.List;

import algorithms.RandomBot;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.MouseButtonEvent;

public class GameCaroMiniMaxAI extends GameCaro{
    public static void main(String[] args) {
        GameCaroMiniMaxAI game = new GameCaroMiniMaxAI(12,20, false);
        game.gameComplete();
    }


    protected RandomBot randoBot;

    private Boolean humanTurn;
    private Boolean humanPlayFirst;
    private Character robotMark;
    private Integer recentI = null, recentJ = null;

    public GameCaroMiniMaxAI(int numGridM, int numGridN, Boolean humanPlayFirst) {
        super(numGridM, numGridN);
        this.humanTurn = humanPlayFirst;
        this.humanPlayFirst = humanPlayFirst;
        if (humanPlayFirst) this.randoBot = new RandomBot(numGridM, numGridN,'O');
        else this.randoBot = new RandomBot(numGridM, numGridN,'X');
        
    }

    @Override
    protected void gameProgress(CanvasWindow canva) {
        canva.onClick((event -> {
            // end game
            changeGameStatusUIVal();
            if (gameState == 1 || gameState== -1 || fillUpNum >= numGridM*numGridN) { return;}
            if (humanTurn) { 
                Boolean hasHumanCompletedTurn = humanPlay(event); 
                if (hasHumanCompletedTurn) humanTurn = !humanTurn;
            }

            canva.draw();
            delay(500);

            changeGameStatusUIVal();
            if (gameState == 1 || gameState== -1 || fillUpNum >= numGridM*numGridN) { return;}
            if (!humanTurn)  {
                botPlay();
                humanTurn = !humanTurn;
            } 
        }));   
    }

    // Define a method to handle the sleep with InterruptedException
    public void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void resetGame() {
        this.canva.closeWindow();
        GameCaroMiniMaxAI newGame = new GameCaroMiniMaxAI(numGridM, numGridN, this.humanPlayFirst); 
        newGame.gameComplete();
    }
    

    /**
     * 
     * @param event mouse event click
     * @return true if human makes a correct move
     */
    protected Boolean humanPlay(MouseButtonEvent event) {
        List<Integer> indices = translatePointToGrid(event.getPosition());
        if (indices == null) { 
            System.out.println("False move");
            return false; 
            }
        
        Integer i = indices.get(0); Integer j = indices.get(1);
        Boolean markCharacterSuccess = gridArray[i][j].setCharValue(currentTurn);
        if (markCharacterSuccess) 
        { 
            charArray[i][j] = currentTurn;
            fillUpNum+=1;
            setXOImagePath();
            gameState = winChecker.output(charArray, currentTurn, i, j);
            setNextTurnChar();
            return true;
        }
        return false;
    }

    /**
     * 
     * @return true if bot plays a legal move
     */
    protected Boolean botPlay() {
        int[] botOuput = randoBot.output(charArray);
        int i = botOuput[0]; int j = botOuput[1];
        Boolean markCharacterSuccess = gridArray[i][j].setCharValue(currentTurn);
        if (markCharacterSuccess) 
        { 
            charArray[i][j] = currentTurn;
            fillUpNum+=1;
            setXOImagePath();
            gameState = winChecker.output(charArray, currentTurn, i, j);
            setNextTurnChar();
            return true;
        }
        return false;
    }  
}

