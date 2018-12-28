
public class Assassin extends Role
{
	public static int actionSpeed = 2000;
	public Assassin()
	{
		super(true, "Assassin");
	}
	public static void Kill(Player p)
	{
		p.die();
	}
	public static void describeRole()
	{
		System.out.println("You are the Assassin! You can kill 1 person per night through the bodyguard's protection. You may not be killed during the night. You can vote during the day.");
	}
	
}
