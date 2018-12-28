
public class AIVillagerController extends Controller 
{

	private MafiaGame currentGame;
	
	public AIVillagerController(MafiaGame g)
	{
		this.currentGame = g;
	}
	
	@Override
	// Votes for anyone who is accused by the detective. If there are ten or fewer players remaining, votes for a random person.
	public int doDayBehavior() 
	{
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
			Role.vote(currentGame.getPlayerList().get((int)(Math.random()*currentGame.getPlayerList().size())));
		}
		return 0;
	}

	@Override
	// Can't do anything.
	public int doNightBehavior() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	// can't do anything
	public int doDawnBehavior() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printAllies() {
		// TODO Auto-generated method stub
		
	}

}
