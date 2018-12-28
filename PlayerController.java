import java.util.ArrayList;
import java.util.Scanner;

public class PlayerController extends Controller 
{
	private MafiaGame currentGame;
	private ArrayList<String> allies;
	private Player player;
	public PlayerController(MafiaGame g, Player p)
	{
		this.currentGame = g;
		allies = new ArrayList<String>();
		this.player = p;
	}
	public void addAllies()
	{
		if(player.getRole().getRoleName().equals("Detective") || player.getRole().getRoleName().equals("Bodyguard") || 
		player.getRole().getRoleName().equals("Hooker") || player.getRole().getRoleName().equals("Vigilante"))
		{
			addAlliesForPRs();
		}
		else if(player.getRole().getRoleName().equals("Italian Mafia") || player.getRole().getRoleName().equals("Russian Mafia") || player.getRole().getRoleName().equals("Werewolf"))
		{
			addAlliesForMafia();
		}
	}
	
	public String getPlayerRoleName(int i)
	{
		return currentGame.getPlayerList().get(i).getRole().getRoleName();
	}
	
	public String getPlayerName(int i)
	{
		return currentGame.getPlayerList().get(i).getName();
	}
	
	public void addAlliesForPRs()
	{
		for(int i = 0; i< currentGame.getPlayerList().size(); i++)
		{
			if(getPlayerRoleName(i).equals("Detective") || getPlayerRoleName(i).equals("Bodyguard") || getPlayerRoleName(i).equals("Hooker") || getPlayerRoleName(i).equals("Vigilante"))
				allies.add(getPlayerName(i));
		}
	}
	public void addAlliesForMafia()
	{
		if(player.getRole().getRoleName().equals("Russian Mafia"))
		{
			for(int i = 0; i< currentGame.getPlayerList().size(); i++)
				if(getPlayerRoleName(i).equals("Russian Mafia"))
				{
					allies.add(getPlayerName(i));
				}
		}
		else if(player.getRole().getRoleName().equals("Italian Mafia"))
		{
			for(int i = 0; i< currentGame.getPlayerList().size(); i++)
			{
				if(getPlayerRoleName(i).equals("Italian Mafia"))
				{
					allies.add(getPlayerName(i));
				}
			}
		}
		else if(player.getRole().getRoleName().equals("Werewolf"))
		{
			for(int i = 0; i< currentGame.getPlayerList().size(); i++)
			{
				if(getPlayerRoleName(i).equals("Werewolf"))
				{
					allies.add(getPlayerName(i));
				}
			}
		}
	}
	
	//the return values are just to break out of the method faster.
	public int doNightBehavior()
	{
		Scanner scan = new Scanner(System.in);
		String target;
		// if player is inspector
		if(player.getRole().getRoleName().equals("Detective"))
		{
			System.out.println("Who would you like to inspect? If you don't want to select any target, type \"NONE\"");
			target = scan.nextLine();
			if(target.toUpperCase().equals("NONE"))
			{
				return 0;
			}
			Detective.inspect(currentGame.getMap().get(target));
		}
		// if player is bodyguard
		else if (player.getRole().getRoleName().equals("Bodyguard"))
		{
			System.out.println("Who would you like to protect? If you don't want to select any target, type \"NONE\"");
			target = scan.nextLine();
			if(target.toUpperCase().equals("NONE"))
			{
				return 0;
			}
			Bodyguard.Protect(currentGame.getMap().get(target));
		}
		// if player is hooker
		else if (player.getRole().getRoleName().equals("Hooker"))
		{
			System.out.println("Who would you like to distract? If you don't want to select any target, type \"NONE\"");
			target = scan.nextLine();
			if(target.toUpperCase().equals("NONE"))
			{
				return 0;
			}
			Hooker.Distract(currentGame.getMap().get(target));
		}
		// if player is werewolf, assassin, vigilante, or mafia
		else if (player.getRole().getRoleName().equals("Werewolf") || player.getRole().getRoleName().equals("Italian Mafia") 
				|| player.getRole().getRoleName().equals("Russian Mafia") || player.getRole().getRoleName().equals("Assassin") || player.getRole().getRoleName().equals("Vigilante"))
		{
			System.out.println("Who would you like to kill? If you don't want to select any target, type \"NONE\"");
			target = scan.nextLine();
			if(target.toUpperCase().equals("NONE"))
				return 0;
			else if(player.getRole().getRoleName().equals("Werewolf"))
				Werewolf.Kill(currentGame.getMap().get(target));
			
			else if(player.getRole().getRoleName().equals("Assassin"))
				Assassin.Kill(currentGame.getMap().get(target));
			
			else if(player.getRole().getRoleName().equals("Italian Mafia"))
				ItalianMafia.Kill(currentGame.getMap().get(target));
			
			else if(player.getRole().getRoleName().equals("Russian Mafia"))
				RussianMafia.Kill(currentGame.getMap().get(target));
			
			else if(player.getRole().getRoleName().equals("Vigilante"))
				Vigilante.Kill(currentGame.getMap().get(target));
		}
		
		//if player is godfather
		else if(player.getRole().getRoleName().equals("Godfather"))
		{
			String target1;
			String target2;
			String target3;
			
			System.out.println("Who would you like to kill? If you don't want to select any target, type \"NONE\"");
			target1 = scan.nextLine();
			
			if(target1.toUpperCase().equals("NONE"))
			{
				return 0;
			}
			System.out.println("Who else would you like to kill? If you don't want to select any target, type \"NONE\"");
			target2 = scan.nextLine();
			if(target2.toUpperCase().equals("NONE"))
			{
				Godfather.Kill(currentGame.getMap().get(target1));
				return 0;
			}
			System.out.println("Who else would you like to kill? If you don't want to select any target, type \"NONE\"");
			target3 = scan.nextLine();
			
			if(target3.toUpperCase().equals("NONE"))
			{
				Godfather.Kill(currentGame.getMap().get(target1), currentGame.getMap().get(target2));
				return 0;
			}
			Godfather.Kill(currentGame.getMap().get(target1), currentGame.getMap().get(target2), currentGame.getMap().get(target3));
		}
		return 0;
	}
	//returns int just to break out of the method if the player is dead
	public int doDawnBehavior()
	{
		//checks if players is dead
		if(!player.checkAlive())
		{
			System.out.println("Sorry, you died!");
			return -1;
		}
		
		//if player is inspector
		if(player.getRole().getRoleName().equals("Detective"))
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("Who would you like to lynch? If you don't want to select any target, type \"NONE\"");
			String target = scan.nextLine();
			if(target.toUpperCase().equals("NONE"))
			{
				return 0;
			}
			Detective.Accuse(currentGame.getMap().get(target));
			System.out.println("The Detective has accused " + target + " of being a bad guy!");
		}
		return 0;
	}
	public int doDayBehavior() 
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Who would you like to vote for? If you don't want to select any target, type \"NONE\"");
		String target = scan.nextLine();
		if(target.toUpperCase().equals("NONE"))
		{
			return 0;
		}
		Role.vote(currentGame.getMap().get(target));
		return 0;
	}
	public void printAllies()
	{
		System.out.print("Your Allies are: ");
		for(String s: allies)
		{
			System.out.print(currentGame.getMap().get(s).getName() + " (" + currentGame.getMap().get(s).getRole().getRoleName() + ") " + ", ");
		}
		System.out.println();
	}
	
}

