package Pages;

import java.util.List;

import algorithms.RandomBot;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.MouseButtonEvent;

public class GameCaroAI extends GameCaro{
    public static void main(String[] args) {
        GameCaroAI game = new GameCaroAI(12,20, false);
        game.gameComplete();
    }


    protected RandomBot randoBot;

    private Boolean humanTurn;
    private Boolean humanPlayFirst;


    public GameCaroAI(int numGridM, int numGridN, Boolean humanPlayFirst) {
        super(numGridM, numGridN);
        this.humanTurn = humanPlayFirst;
        this.humanPlayFirst = humanPlayFirst;
        if (humanPlayFirst) this.randoBot = new RandomBot(numGridM, numGridN,'O');
        else this.randoBot = new RandomBot(numGridM, numGridN,'X');
        
    }

    @Override
    protected void gameProgress(CanvasWindow canva) {
        canva.onClick((event -> {
            System.out.println("human turn:" + humanTurn);
            // end game
            changeGameStatusUIVal();
            if (gameState!=null && 
                (gameState == 1 || gameState== -1 || gameState==0)
            )  return;
            if (humanTurn) { 
                Boolean hasHumanCompletedTurn = humanPlay(event); 
                System.out.println();
                if (hasHumanCompletedTurn) humanTurn = !humanTurn;
            }

            canva.draw();
            delay(500);

            changeGameStatusUIVal();
            if (gameState!=null && 
                (gameState == 1 || gameState== -1 || gameState==0)
            )  return;
            if (!humanTurn)  {
                botPlay();
                humanTurn = !humanTurn;
            } 
            canva.draw();
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
        GameCaroAI newGame = new GameCaroAI(numGridM, numGridN, this.humanPlayFirst); 
        newGame.gameComplete();
    }
    

    /**
     * 
     * @param event mouse event click
     * @return true if human makes a correct move
     */
    protected Boolean humanPlay(MouseButtonEvent event) {
        List<Integer> indices = translatePointToGrid(event.getPosition());
        if (indices == null) { return false;}
        
        Integer i = indices.get(0); Integer j = indices.get(1);
        Boolean markCharacterSuccess = gridArray[i][j].setCharValue(currentTurn);
        if (markCharacterSuccess) 
        {
            boardData.markPosition(currentTurn, i, j);
            setXOImagePath();
            gameState = boardData.winStatus();
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
        int[] botOuput = randoBot.output(boardData.getArrayData());
        int i = botOuput[0]; int j = botOuput[1];
        Boolean markCharacterSuccess = gridArray[i][j].setCharValue(currentTurn);
        if (markCharacterSuccess) 
        { 
            Boolean play = boardData.markPosition(currentTurn, i, j);
            System.out.println(play);
            setXOImagePath();
            gameState = boardData.winStatus();
            setNextTurnChar();
            return true;
        }
        return false;
    }

    
}

