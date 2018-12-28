import java.util.ArrayList;
import java.util.Collections;

public class AIGodfatherController extends Controller
{
	private MafiaGame currentGame;
	private ArrayList<Player> allies;
	private ArrayList<Player> enemies;
	public AIGodfatherController (MafiaGame g)
	{
		this.allies = new ArrayList<Player>();
		this.enemies = new ArrayList<Player>();
		this.currentGame = g;
		
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
	//Tries to kill a random person in the Enemies ArrayList. Behaves differently depending on how many enemies are left.
	@Override
	public int doNightBehavior() 
	{
		if(enemies.size() > 2)
		{
			Collections.shuffle(enemies);
			Godfather.Kill(enemies.get(0), enemies.get(1), enemies.get(2));
		}
		else if(enemies.size() == 2)
		{
			Godfather.Kill(enemies.get(0), enemies.get(1));
		}
		else if(enemies.size()==1)
		{
			Godfather.Kill(enemies.get(0));
		}
		return 0;
	}
	@Override
	public void printAllies() {
		// TODO Auto-generated method stub
		
	}

}
