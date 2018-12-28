
public class Detective extends Role
{
	public static int actionSpeed = 50;
	public Detective()
	{
		super(false, "Detective");
	}
	// night command
	public static Role inspect(Player p)
	{
		return p.getRole();
	}
	// day command
	public static void Accuse(Player p)
	{
		System.out.println("The Detective Accuses " + p.getName() + "!");
		p.toggleGuilt();
	}
	public static void describeRole()
	{
		System.out.println("You are the detective! You can inspect people to find their role during the night, and accuse someone during the day to get all the villages to vote for them, which removes them from the game. You can also vote.");
	}
}
