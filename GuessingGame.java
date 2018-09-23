
import java.util.Random;

//A simple 2-player number guessing game
public class GuessingGame implements InteractiveGame
{
   //Initial range of possible numbers
   public static final int MINIMUM = 1;
   public static final int MAXIMUM = 100;
   
   //Instance Varuables
   private int secretNumber;     //The secret number the players try to guess
	private boolean guessedRight; //false until a player guesses the secret number
   private boolean playersTurn;  //true when it's the human player's turn
   
   private int rangeMin;         //Current lowest possible smart guess 
   private int rangeMax;         //Current highest possible smart guess
	private String record;        //Record of the game as it progresses
   
   private boolean smartMode;    //true if the computer plays smart, false otherwise
	
   //Constructor
	public GuessingGame(boolean smartComputer, boolean computerFirst)
	{
      this.secretNumber = randomInt(MINIMUM, MAXIMUM);
 		this.guessedRight = false;
      this.playersTurn = !computerFirst;
      
      this.rangeMin = MINIMUM;
      this.rangeMax = MAXIMUM;
 		this.record = "NUMBER GUESSING GAME";
      
      this.smartMode = smartComputer;
	}

	public boolean isCompleted()
	{
      return this.guessedRight;
	}
	
	public boolean isPlayersTurn()
	{
      return this.playersTurn;
  	}
	
	public boolean isValidMove(String move)
	{
      try
      {
		   int number = Integer.parseInt(move);
         return number >= MINIMUM && number <= MAXIMUM;
      }
      catch (NumberFormatException nfex)
      {
         return false;
      }

	}

	public void makePlayersMove(String move)
	{
      int guess = Integer.parseInt(move);
      
      updateHistory("PLAYER", guess);
	}

	public void makeComputersMove()
	{
      int guess = (this.smartMode ? 
                   this.rangeMin + (this.rangeMax - this.rangeMin)/2 :
                   randomInt(MINIMUM, MAXIMUM)  );

      updateHistory("COMPUTER", guess);
	}
	
   private void updateHistory(String player, int guess)
   {  
      this.record += "\n" + player + " guessed " + guess;
      
      if (guess > this.secretNumber)
      {
         this.record += " Too High";
         this.rangeMax = guess - 1;
      }
      else if (guess < this.secretNumber)
      {
         this.record += " Too Low";
         this.rangeMin = guess + 1;
      }
      else
         this.guessedRight = true;
         
      this.playersTurn = !this.playersTurn;
   }
   
	public boolean playerHasWon()	
	{
		return this.guessedRight && !this.playersTurn;
	}
	
	public String toString()
	{
		return this.record;
	}
	
	public String inputPrompt()
	{
		return this.record + "\nGuess a number " + MINIMUM + " .. " + MAXIMUM;
	}
   
   private static Random generator = new Random();
   private static int randomInt(int lowest, int highest)
   {
      return generator.nextInt(highest - lowest + 1) + lowest;
   }
}


































































