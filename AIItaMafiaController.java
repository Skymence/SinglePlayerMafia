import java.util.ArrayList;
public class AIItaMafiaController extends Controller {
	private MafiaGame currentGame;
	private ArrayList<Player> allies;
	private ArrayList<Player> enemies;
	public AIItaMafiaController (MafiaGame g)
	{
		this.allies = new ArrayList<Player>();
		this.enemies = new ArrayList<Player>();
		this.currentGame = g;
		
	}
	public void addAllies()
	{
		for (int i = 0; i<currentGame.getPlayerList().size(); i++)
		{
			if(currentGame.getPlayerList().get(i).getRole().getRoleName().equals("Italian Mafia"))
			{
				allies.add(currentGame.getPlayerList().get(i));
			}
			else
			{
				enemies.add(currentGame.getPlayerList().get(i));
			}
		}
	}
	//votes some random enemy to be lynched if there are less than ten players left
	@Override
	public int doDayBehavior() 
	{
		if(currentGame.getPlayerList().size() <= 10)
		{
			Role.vote(enemies.get((int)(Math.random()*enemies.size())));
		}
		return 0;

	}

	//removes all dead people from allies or enemies
	@Override
	public int doDawnBehavior() 
	{
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
		while (i < enemies.size())
		{
			if(!enemies.get(i).checkAlive())
			{
				enemies.remove(i);
				i--;
			}
			i++;
		}
		return 0;

	}
	//Tries to kill a random person in the Enemies ArrayList.
	@Override
	public int doNightBehavior() 
	{
		ItalianMafia.Kill(enemies.get((int)(Math.random()*enemies.size())));
		return 0;
	}
	@Override
	public void printAllies() {
		// TODO Auto-generated method stub
		
	}

}
