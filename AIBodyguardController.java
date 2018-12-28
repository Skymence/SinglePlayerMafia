import java.util.ArrayList;
public class AIBodyguardController extends Controller
{
	private MafiaGame currentGame;
	private ArrayList<Player> allies;
	private ArrayList<Player> notAllies;
	
	//adds power roles to allies list, also makes it so he can auto-protect detective
	public AIBodyguardController(MafiaGame g)
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
	public int doDayBehavior() //same voting behavior as villager
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
	public int doDawnBehavior() // cleans dead people off of allies
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
	// Protects Detective, Vigilante, Hooker, and randoms in that priority
	@Override
	public int doNightBehavior() 
	{
		// TODO Auto-generated method stub
		boolean hasProtected = false;
		for(Player p: currentGame.getPlayerList())
		{
			if(p.getRole().getRoleName().equals("Detective"))
			{
				Bodyguard.Protect(p);
				hasProtected = true;
				break;
			}
		}
		for(Player p: currentGame.getPlayerList())
		{
			if(hasProtected)
				break;
			if(p.getRole().getRoleName().equals("Vigilante") && !hasProtected)
			{
				Bodyguard.Protect(p);
				hasProtected = true;
				break;
			}
		}
		for(Player p: currentGame.getPlayerList())
		{
			if(hasProtected)
				break;
			if(p.getRole().getRoleName().equals("Hooker") && !hasProtected)
			{
				Bodyguard.Protect(p);
				hasProtected = true;
				break;
			}
		}
		
		if(!hasProtected)
		{
			Bodyguard.Protect(currentGame.getPlayerList().get((int)(Math.random()*currentGame.getPlayerList().size())));
		}
		return 0;
		
	}

	@Override
	public void printAllies() {
		// TODO Auto-generated method stub
		
	}

}
