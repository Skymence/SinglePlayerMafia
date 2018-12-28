
public class Role 
{
	private String roleName;
	private boolean isBad;

	public Role(boolean isBad, String roleName)
	{
		this.isBad = isBad;
		this.roleName = roleName;
	}
	public boolean checkIfBad()
	{
		return isBad;
	}
	//This is why we need a HashMap mapping strings to players.
	public static void vote(Player p)
	{
		p.increaseVoteCounter();
	}
	public String getRoleName()
	{
		return roleName;
	}
}
