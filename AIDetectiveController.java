import java.util.ArrayList;
public class AIDetectiveController extends Controller 
{
	private MafiaGame currentGame;
	private ArrayList<Player> notInnocentPeople;
	private ArrayList<Player> innocentPeople;
	private int lynchIndex;
	public AIDetectiveController(MafiaGame g)
	{
		this.currentGame = g;
		notInnocentPeople = new ArrayList<Player>();
		innocentPeople = new ArrayList<Player>();
		lynchIndex = -1;
		
	}
	public void addAllies()
	{
		for(int i = 0; i<currentGame.getPlayerList().size(); i++)
		{
			if(currentGame.getPlayerList().get(i).getRole().getRoleName().equals("Detective") ||
			   currentGame.getPlayerList().get(i).getRole().getRoleName().equals("Bodyguard") ||
			   currentGame.getPlayerList().get(i).getRole().getRoleName().equals("Hooker")	||
			   currentGame.getPlayerList().get(i).getRole().getRoleName().equals("Vigilante"))
			{
				innocentPeople.add(currentGame.getPlayerList().get(i));
			}
			else
			{
				notInnocentPeople.add(currentGame.getPlayerList().get(i));
			}
		}
	}
	@Override
	//if someone is guilty, vote for them. if less than 10 players, select randomly from list of not innocent people.
	public int doDayBehavior() 
	{
		boolean hasVoted = false;
		for(int i = 0; i < currentGame.getPlayerList().size(); i++)
		{
			if(currentGame.getPlayerList().get(i).checkGuilt())
				Role.vote(currentGame.getPlayerList().get(i));
				hasVoted = true;
				break;
		}
		if(!hasVoted && currentGame.getPlayerList().size() <= 10)
			Role.vote(notInnocentPeople.get((int)(Math.random()*notInnocentPeople.size())));
		return 0;
	}
	//Inspects a random person not already confirmed innocent.
	@Override
	public int doNightBehavior() 
	{
		int targetIndex = (int)(Math.random()*notInnocentPeople.size());
		
		if(Detective.inspect(notInnocentPeople.get(targetIndex)).checkIfBad())
			lynchIndex = targetIndex;
		else
			innocentPeople.add(notInnocentPeople.get(targetIndex));
			notInnocentPeople.remove(targetIndex);
		return 0;
	}

	@Override
	//For Lynching. Also, removes all dead people from Innocent and Non innocent lists.
	public int doDawnBehavior() 
	{
		if(lynchIndex != -1)
		{
			Detective.Accuse(notInnocentPeople.get(lynchIndex));
			lynchIndex = -1;
		}
			
		int i = 0;
		while (i < innocentPeople.size())
		{
			if(!innocentPeople.get(i).checkAlive())
			{
				innocentPeople.remove(i);
				i--;
			}
			i++;
		}
		i = 0;
		while (i < notInnocentPeople.size())
		{
			if(!notInnocentPeople.get(i).checkAlive())
			{
				notInnocentPeople.remove(i);
				i--;
			}
			i++;
		}
		return 0;
		
	}

	@Override
	public void printAllies() {
		// TODO Auto-generated method stub
		
	}


}
