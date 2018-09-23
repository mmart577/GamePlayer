//  PROGRAMMER:  Melissa Martinez  3327216
//
//  CLASS:       COP 3337          TH 10:50-12:35
//
//  INSTRUCTOR:  Norman Pestaina   ECS 135
//
//  ASSIGNMENT:  #4 Interfaces     DUE Sunday 7/06
//
// CERTIFICATION: I certify that this work is my own and that
//                 none of it is the work of any other person.
//
import javax.swing.JOptionPane;

interface InteractiveGame
{
    boolean isCompleted();
    boolean isPlayersTurn();
    boolean isValidMove(String move);
    void makePlayersMove(String move);
    void makeComputersMove();
    boolean playerHasWon();
    String toString();
    String inputPrompt();
}

public class GamePlayer
{
	//Play a series of two-person Interactive Games 
	//	between a human player and the computer
	public static void main(String[] args)
	{		
		boolean playAgain = true;
		do
		{
			InteractiveGame game = promptForGame();	
		
			if (game == null)
				playAgain = false;
			else
				play( game );
				
		} while ( playAgain );
	}

	//Prompt the (human) player for the name of the game
	//	and its parameters:
	//	   1) Computer plays smart		2) Human plays first
   //
   //Returns a new instance of the selected game
   // or null if the player wants to quit
	private static InteractiveGame  promptForGame()
	{
		Object[] gameOptions = {"QUIT", "GUESS", "NIM", "ROCK PAPER SCISSORS"};
		String gameName = (String)JOptionPane.showInputDialog(null,
		                              "Choose a game, or QUIT", "GAME MENU",
												JOptionPane.INFORMATION_MESSAGE, null,
												gameOptions, gameOptions[0] );
                                    
		if (gameName.equals(gameOptions[0]))
                    return null;
			
		boolean smartPlay = JOptionPane.showConfirmDialog(null, "Computer plays SMART?")
									           == JOptionPane.YES_OPTION;
											
		boolean playFirst = JOptionPane.showConfirmDialog(null, "Do you want to play FIRST?")
									           == JOptionPane.YES_OPTION;
                
      switch ( gameName )
      {
         case "GUESS"   : return new GuessingGame(smartPlay, !playFirst);
         
         case "NIM"     : return new Nim(smartPlay, !playFirst); 
             
         case "ROCK PAPER SCISSORS" : return new RockPaperScissors(smartPlay, !playFirst);

         default        : return null;
      }
	}
	
	//Play a selected 2-person game by alternating turns
	//	between the human PLAYER and the COMPUTER
	private static void play(InteractiveGame game)
	{
		while ( !game.isCompleted())
		{
			if (game.isPlayersTurn())
			{
				String nextMove = getPlayersMove(game);
				game.makePlayersMove( nextMove );
			}
			else
				game.makeComputersMove();
		}
			
		String message = game + "\n\n" + 
                      (game.playerHasWon() ? "Congratulations! You WON!"
                                           : "Better Luck Next Time!"   );
		JOptionPane.showMessageDialog(null, message);	
	}
	
	//Prompt the HUMAN PLAYER to enter their next move
	//The prompt is repeated if an invalid move is entered
	//	until the player enters a legitimate move
	public static String getPlayersMove(InteractiveGame game)
	{
		String playersMove;
		boolean invalidMove;
		do
		{
			playersMove = JOptionPane.showInputDialog(null, 
                                   game.inputPrompt() + " ?" );

			invalidMove = !game.isValidMove(playersMove);

			if ( invalidMove )
				JOptionPane.showMessageDialog(null, playersMove + " is an INVALID move");
		
		} while ( invalidMove );
		
		return playersMove;
	}	
}





























































































