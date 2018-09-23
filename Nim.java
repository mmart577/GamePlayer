import java.util.Random;

public class Nim implements InteractiveGame
{
    private int pileSize;          //The number of marbles currently in the pile
    private boolean playSmart;     //true iff the computer plays smart
    private boolean playersTurn;   //true iff it is the player’s turn to move
    private String gameRecord;     //Complete history of the game’s progression
    
    public Nim(boolean smartComputer, boolean computerFirst)
    {
        this.playSmart = smartComputer;
        this.playersTurn = !computerFirst;
        this.pileSize = randomInt(10, 100);     //generates a random number (10 ... 100) for the initial pile size
        this.gameRecord = "NIM GAME \nInitial Pile Size: " + pileSize;
    }
    
    public boolean isCompleted()
    {
        return this.pileSize == 0;      //Game is completed if the pile is empty
    }
    public boolean isPlayersTurn()
    {
        return this.playersTurn;
    }
    public boolean isValidMove(String move)
    {
        int number = Integer.parseInt(move);
        
        if(number == 1)
            return true;
        
        else if(number == 0)
            return false;
            
        else if(number <= pileSize/2)
            return true;        //Valid only if between 1 and half the pile size
        
        else
            return false;
    }
    public void makePlayersMove(String move)
    {
        int removeAmt = Integer.parseInt(move);
      
        updateHistory("Player", removeAmt);
    }
    public void makeComputersMove()
    {
        int removeAmt = 0;
                                              //Smart mode leaves the pile size as a power of 2 minus 1
        if(this.playSmart && pileSize != 0)
        {
            if(pileSize > 63)
                removeAmt = pileSize - 63;

            else if(pileSize > 31)
                removeAmt = pileSize - 31;

            else if(pileSize > 15)
                removeAmt = pileSize - 15;

            else if(pileSize > 7)
                removeAmt = pileSize - 7;
            
            else if(pileSize > 3)
                removeAmt = pileSize - 3;

            else if(pileSize == 2)
                removeAmt = pileSize - 1;

            else
                removeAmt = randomInt(1, pileSize/2);
        }
        if(!this.playSmart && pileSize > 1)    //In normal mode, a number between 1 and half the pile size is removed
        {
            removeAmt = randomInt(1, pileSize/2);
        }
        if(!this.playSmart && pileSize == 1) 
        {
            removeAmt = 1;
        }
        
        updateHistory("Computer", removeAmt);
    }
    public boolean playerHasWon()
    {
        if(this.pileSize == 0 && this.playersTurn)     //If the pile is empty when it's the player's turn
            return true;
            
        return false;        
    }
    public String toString()
    {
        return this.gameRecord;
    }
    public String inputPrompt()
    {
        return this.gameRecord + "\nPile Size: " + pileSize + " marbles \nRemove marbles (1..." + pileSize/2 + ")";
    }
    
   private void updateHistory(String player, int removeAmt)     //updates current record
   { 
       this.pileSize -= removeAmt;                   //Removes amount from pile size 

       this.gameRecord += "\n" + player + " removed " + removeAmt + " marbles. \nPile: " + pileSize;
              
       this.playersTurn = !this.playersTurn;         //Next player's turn
   }
   
   //Accessors:
   public int getPileSize()
   {
       return this.pileSize;
   }
   public boolean getPlaySmart()
   {
       return this.playSmart;
   }
    
   private static Random generator = new Random();
   private static int randomInt(int lowest, int highest)
   {
      return generator.nextInt(highest - lowest + 1) + lowest;
   }
}
