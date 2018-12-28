import java.util.ArrayList;

public class AIHookerController extends Controller {

	private MafiaGame currentGame;
	private ArrayList<Player> allies; 
	private ArrayList<Player> notAllies;
	
	//adds power roles to allies list, also makes it so he can auto-protect detective
	public AIHookerController(MafiaGame g)
	{
		this.currentGame = g;
		this.allies = new ArrayList<Player>();
		this.notAllies = new ArrayList<Player>();
		
	}
	public void addAllies()
	{
		for (int i = 0; i<currentGame.getPlayerList().size(); i++)
		{
			if(currentGame.getPlayerList().get(i).getRole().getRoleName().equals("Detective") ||
			   currentGame.getPlayerList().get(i).getRole().getRoleName().equals("Bodyguard") ||
			   currentGame.getPlayerList().get(i).getRole().getRoleName().equals("Hooker")	||
			   currentGame.getPlayerList().get(i).getRole().getRoleName().equals("Vigilante"))
			{
				allies.add(currentGame.getPlayerList().get(i));
			}
			else
			{
				notAllies.add(currentGame.getPlayerList().get(i));
			}
		}
	}
	@Override
	public int doDayBehavior() // same as bodyguard/detective
	{
		// TODO Auto-generated method stub
		boolean hasVoted = false;
		for(int i = 0; i < currentGame.getPlayerList().size(); i++)
		{
			if(currentGame.getPlayerList().get(i).checkGuilt())
			{
				Role.vote(currentGame.getPlayerList().get(i));
				hasVoted = true;
				break;
			}
		}
		if(!hasVoted && currentGame.getPlayerList().size() <= 10)
		{
			Role.vote(notAllies.get((int)(Math.random()*notAllies.size())));
		}
		return 0;
	}

	@Override
	public int doDawnBehavior() // removes dead people from allies and notallies
	{
		// TODO Auto-generated method stub
		int i = 0;
		while (i < allies.size())
		{
			if(!allies.get(i).checkAlive())
			{
				allies.remove(i);
				i--;
			}
			i++;
		}
		i = 0;
		while(i<notAllies.size())
		{
			if(!notAllies.get(i).checkAlive())
			{
				notAllies.remove(i);
				i--;
			}
			i++;
		}
		return 0;

	}

	@Override
	public int doNightBehavior() // distracts random people not on the allies list
	{
		
		Hooker.Distract(notAllies.get((int)(Math.random()*notAllies.size())));
		return 0;
	}
	@Override
	public void printAllies() {
		// TODO Auto-generated method stub
		
	}

}
