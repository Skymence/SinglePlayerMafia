import java.util.*;
public class RunMafia {

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your name");
		String playerName = scan.nextLine();
		if(playerName.toUpperCase().equals("NONE"))
		{
			throw new IllegalArgumentException("NONE is not a valid name.");
		}
		//Start of Game. 
		MafiaGame game = new MafiaGame(playerName);
		
		game.printRemainingPlayers();
		game.printRemainingRoles();
		System.out.println("You are the " + game.getMap().get(playerName).getRole().getRoleName() + "! \n");
		for(int i = 0; i < game.getPlayerList().size(); i++)
		{
			game.getPlayerList().get(i).getControl().addAllies();
		}
		game.getMap().get(playerName).getControl().printAllies();
		while(!game.checkIfGameOver())
		{
			//night
			for(int i = 0; i<game.getPlayerList().size(); i++)
			{
				game.getPlayerList().get(i).getControl().doNightBehavior();
			}
			//dawn (aka, day but pre-vote. check if game is over.)
			game.removeDeadPlayers();
			if(game.checkIfGameOver())
			{
				break;
			}
			for(int i = 0; i<game.getPlayerList().size(); i++)
			{
				game.getPlayerList().get(i).getControl().doDawnBehavior();
			}
			System.out.println();
			game.printRemainingPlayers();
			game.printRemainingRoles();
			
			//Voting Day
			for(int i = 0; i<game.getPlayerList().size(); i++)
			{
				game.getPlayerList().get(i).getControl().doDayBehavior();
			}
			game.killPlayerWithMostVotes();
			game.resetAllSpecialStates();
			game.printRemainingPlayers();
			game.printRemainingRoles();
		}
		System.out.println("Game Over!");
	}

}
