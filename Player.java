
public class Player 
{
	private Role role;
	private boolean isAlive;
	private int votes;
	private boolean isProtected;
	private boolean isDistracted;
	private String playerName;
	private boolean guilty;
	private Controller control;
	
	public Player(Role r, String s) 
	{

		this.role = r;
		this.isAlive = true;
		this.votes = 0;
		this.isProtected = false;
		this.isDistracted = false;
		this.playerName = s;
		this.guilty = false;
		
	}
	public Player(Role r, String s, Controller c)
	{
		this.role = r;
		this.isAlive = true;
		this.votes = 0;
		this.isProtected = false;
		this.isDistracted = false;
		this.playerName = s;
		this.guilty = false;
		this.control = c;
	}
	public void increaseVoteCounter() 
	{
		votes++;
	}

	public int getNumVotes() 
	{		
		return votes;
	}
	public boolean checkProtected()
	{
		return isProtected;
	}
	public boolean checkDistracted()
	{
		return isDistracted;
	}
	public void resetSpecialStatus() 
	{
		votes = 0;
		isProtected = false;
		isDistracted = false;
		guilty = false;
	}
	public void toggleProtected()
	{
		isProtected = true;
	}
	public void toggleDistracted()
	{
		isDistracted = true;
	}
	public Role getRole()
	{
		return role;
	}
	public void die()
	{
		isAlive = false;
	}
	public boolean checkAlive()
	{
		return isAlive;
	}
	public void toggleGuilt()
	{
		guilty = true;
	}
	public boolean checkGuilt()
	{
		return guilty;
	}
	public String getName()
	{
		return playerName;
	}
	public Controller getControl()
	{
		return control;
	}
}
