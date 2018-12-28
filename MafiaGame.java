import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
public class MafiaGame 
{
	private boolean isNight;
	private ArrayList<Player> playersRemaining;
	private HashMap<String, Player> namesToPlayers;
	private String playerName;
	// players: 30
	public static Role[] roleOrder = {new Villager(), new Villager(), new Villager(), new Detective(), new Bodyguard(), new Hooker(), 
										new Villager(), new RussianMafia(), new RussianMafia(), new ItalianMafia(), new ItalianMafia(), 
										new Werewolf(),new Villager(), new Villager(), new Villager(), new RussianMafia(), new Villager(), 
										new ItalianMafia(), new Villager(), new Vigilante(), new Villager(), new Assassin(), new Villager(), 
										new Godfather(), new Villager(),new Werewolf(), new Villager(), new ItalianMafia(), new RussianMafia(),new Villager()};
	
	//30 BotNames. Player gets to choose his own name.
	public static String[] botNameOrder = {"Albert", "Bob", "Charlie", "Dan", "Ethan", "Fiona", "George", "Henry", "Irene", "Jenny", "Kelly", "Liam",
											"Mary", "Nelson", "Owen", "Preston", "Quinn", "Rachel", "Sally", "Tokita", "Umi", "Vanessa", "William",
											"Xavier", "Yuki", "Zach", "Andrew", "Belle", "Chanel", "Daniel"};
	
	//Starts the game. Gives the player a random role, and the game starts at night.
	public MafiaGame(String playerName)
	{
		this.isNight = true;
		this.playerName=playerName;
		this.playersRemaining = new ArrayList<Player>();
		this.namesToPlayers = new HashMap<String, Player>();
		
		int playerPosition = (int)(30*Math.random());
		for(int i = 0; i < 30; i++)
		{
			if(i == playerPosition)
			{
				Player p = new Player(roleOrder[i], playerName);
				p = new Player(roleOrder[i], playerName, new PlayerController(this, p));
				playersRemaining.add(p);
				namesToPlayers.put(playerName, p);
			}
			else
			{	
				Player p1 = new Player(new Villager(), "");
				if(roleOrder[i].getRoleName().equals("Villager"))
				{
					p1 = new Player(roleOrder[i], botNameOrder[i], new AIVillagerController(this));
				}
				else if(roleOrder[i].getRoleName().equals("Detective"))
				{
					p1 = new Player(roleOrder[i], botNameOrder[i], new AIDetectiveController(this));
				}
				else if(roleOrder[i].getRoleName().equals("Hooker"))
				{
					p1 = new Player(roleOrder[i], botNameOrder[i], new AIHookerController(this));
				}
				else if(roleOrder[i].getRoleName().equals("Bodyguard"))
				{
					p1 = new Player(roleOrder[i], botNameOrder[i], new AIBodyguardController(this));
				}
				else if(roleOrder[i].getRoleName().equals("Vigilante"))
				{
					p1 = new Player(roleOrder[i], botNameOrder[i], new AIVigilanteController(this));
				}
				else if(roleOrder[i].getRoleName().equals("Italian Mafia"))
				{
					p1 = new Player(roleOrder[i], botNameOrder[i], new AIItaMafiaController(this));
				}
				else if(roleOrder[i].getRoleName().equals("Russian Mafia"))
				{
					p1 = new Player(roleOrder[i], botNameOrder[i], new AIRussianMafiaController(this));
				}
				else if(roleOrder[i].getRoleName().equals("Werewolf"))
				{
					p1 = new Player(roleOrder[i], botNameOrder[i], new AIWerewolfController(this));
				}
				else if(roleOrder[i].getRoleName().equals("Assassin"))
				{
					p1 = new Player(roleOrder[i], botNameOrder[i], new AIAssassinController(this));
				}
				else if(roleOrder[i].getRoleName().equals("Godfather"))
				{
					p1 = new Player(roleOrder[i], botNameOrder[i], new AIGodfatherController(this));
				}
				playersRemaining.add(p1);
				namesToPlayers.put(botNameOrder[i], p1);
			}	
		}
	}
	
	public void removeDeadPlayers()
	{
		int i = 0;
		while(i < playersRemaining.size())
		{
			if(!playersRemaining.get(i).checkAlive())
			{
				System.out.println(playersRemaining.get(i).getName() + " (" + playersRemaining.get(i).getRole().getRoleName() + ") " + "was killed!");
				playersRemaining.remove(playersRemaining.get(i));
				i--;
			}
			i++;
		}
	}
	
	public void killPlayerWithMostVotes()
	{
		int maxVotes = 0;
		int maxVoteIndex = 0;
		for(int i = 0; i<playersRemaining.size(); i++)
		{
			if(playersRemaining.get(i).getNumVotes() > maxVotes)
			{
				maxVotes = playersRemaining.get(i).getNumVotes();
				maxVoteIndex = i;
			}
		}
		
		if(maxVotes > 0)
		{
			System.out.println(playersRemaining.get(maxVoteIndex).getName() + " (" + playersRemaining.get(maxVoteIndex).getRole().getRoleName() + ") " + "was removed from the game!");
			playersRemaining.remove(playersRemaining.get(maxVoteIndex));
		}
	}
	public void resetAllSpecialStates()
	{
		for (Player p: playersRemaining)
		{
			p.resetSpecialStatus();
		}
			
	}
	public void toggleTime()
	{
		if(isNight)
		{
			isNight = false;
		}
		else
		{
			isNight = true;
		}
	}
	
	public void printRemainingPlayers()
	{
		Collections.shuffle(playersRemaining);
		System.out.println("Remaining Players: ");
		int fiveCounter = 0;
		for(int i = 0; i<playersRemaining.size(); i++ )
		{
			fiveCounter++;
			System.out.print(playersRemaining.get(i).getName() +", ");
			if(fiveCounter == 5)
			{
				System.out.println();
				fiveCounter = 0;
			}
		}
		System.out.println("\n");
	}
	public void printRemainingRoles()
	{
		Collections.shuffle(playersRemaining);
		System.out.println("Remaining Roles: ");
		int fiveCounter = 0;
		for(int i = 0; i<playersRemaining.size(); i++ )
		{
			fiveCounter++;
			System.out.print(playersRemaining.get(i).getRole().getRoleName() +", ");
			if(fiveCounter == 5)
			{
				System.out.println();
				fiveCounter = 0;
			}
		}
		System.out.println("\n");
	}
	public ArrayList<Player> getPlayerList()
	{
		return playersRemaining;
	}
	public HashMap<String, Player> getMap()
	{
		return namesToPlayers;
	}
	public boolean checkIfGameOver()
	{
		//first checks if everyone is dead
		if(playersRemaining.size() == 0)
		{
			return true;
		}
		//first checks if there are only good people left
		int goodPeople = 0;
		for(Player p: playersRemaining)
		{
			if(!p.getRole().checkIfBad())
			{
				goodPeople++;
			}
		}
		if(goodPeople == playersRemaining.size())
		{
			return true;
		}
		//then checks if all remaining roles are the same.
		int counter = 0;
		String s = playersRemaining.get(0).getRole().getRoleName();
		for(Player p: playersRemaining)
		{
			if(p.getRole().getRoleName().equals(s))
			{
				counter++;
			}
		}
		if(counter == playersRemaining.size())
		{
			return true;
		}
		return false;
	}
}
