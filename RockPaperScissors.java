import java.util.Random;

public class RockPaperScissors implements InteractiveGame
{
    private String gameRecord;
    private boolean playersTurn;
    private String compOption;
    private String playerOption;
    private boolean playSmart;
    private int rounds;
    private int playerScore;
    private int compScore;
    private String[] options = new String[3];
    
    public RockPaperScissors(boolean smartComputer, boolean computerFirst)
    {
        this.playSmart = smartComputer;
        this.playersTurn = !computerFirst;
        this.gameRecord = "ROCK PAPER SCISSORS GAME";
        
        options[0] = "ROCK";
        options[1] = "PAPER";
        options[2] = "SCISSORS";

        this.compOption = "";
        this.playerOption = "";
        this.rounds = 0;            //Total of player turns completed
        this.playerScore = 0;
        this.compScore = 0;
    }
    
    public boolean isCompleted()
    {
        if(this.rounds == 6)        //Game will last 6 player turns
            return true;
        
        return false;
    }
    public boolean isPlayersTurn()
    {
        return this.playersTurn;
    }
    public boolean isValidMove(String move)
    {                                         //Only 3 options are valid
        if(move.equalsIgnoreCase("rock") || move.equalsIgnoreCase("paper") || move.equalsIgnoreCase("scissors"))
            return true;

        else
            return false;
    }
    public void makePlayersMove(String move)
    {
        playerOption = move;
        updateHistory("Player");
    }
    public void makeComputersMove()
    {                           //Computer selects a random option from the option's list
        compOption = options[randomInt(0, 2)];
        updateHistory("Computer");
    }
    public boolean playerHasWon()
    {
        if(playerScore > compScore)     //Player with highest overall score wins
            return true;
        
        return false;
    }
    public String toString()
    {
        return this.gameRecord;
    }
    public String inputPrompt()
    {                                     //Asks user for input
        return this.gameRecord + "\nSelect an option: ("+options[0]+","+options[1]+","+options[2]+")";
    }
    
    private void updateHistory(String player)     //updates gameRecord
    {
        if(this.playersTurn)
            this.gameRecord += "\n" + player + " chose " + playerOption.toUpperCase();
        else
            this.gameRecord += "\n" + player + " chose " + compOption.toUpperCase();
        
        //Updates scores:
        if(!playersTurn && this.playerOption.equalsIgnoreCase("ROCK") && this.compOption.equalsIgnoreCase("PAPER"))
            compScore++;
        if(!playersTurn && this.playerOption.equalsIgnoreCase("PAPER") && this.compOption.equalsIgnoreCase("ROCK"))
            playerScore++;
        if(!playersTurn && this.playerOption.equalsIgnoreCase("PAPER") && this.compOption.equalsIgnoreCase("SCISSORS"))
            compScore++;
        if(!playersTurn && this.playerOption.equalsIgnoreCase("SCISSORS") && this.compOption.equalsIgnoreCase("PAPER"))
            playerScore++;
        if(!playersTurn && this.playerOption.equalsIgnoreCase("ROCK") && this.compOption.equalsIgnoreCase("SCISSORS"))
            playerScore++;
        if(!playersTurn && this.playerOption.equalsIgnoreCase("SCISSORS") && this.compOption.equalsIgnoreCase("ROCK"))
            compScore++;
        
        if(rounds == 5)
        {                      //Displays players' scores at the end of the game
            this.gameRecord += "\n";
            this.gameRecord += "\n Player Score: " + this.playerScore;
            this.gameRecord += "\n Computer Score: " + this.compScore;
        }
        
        this.playersTurn = !this.playersTurn;               //Next player's turn
        this.rounds++;                               //Next round is initialized
    }
    //Accessor methods:
    public String getCompMove()
    {
        return this.compOption;
    }
    public String getPlayersMove()
    {
        return this.playerOption;
    }
    public int getRound()
    {
        return this.rounds;
    }
    public int getPlayerScore()
    {
        return this.playerScore;
    }
    public int getCompScore()
    {
        return this.compScore;
    }
    public String[] getOptions()
    {
        return this.options;
    }
    
    private static Random generator = new Random();
    private static int randomInt(int lowest, int highest)
    {
        return generator.nextInt(highest - lowest + 1) + lowest;
    }
}
