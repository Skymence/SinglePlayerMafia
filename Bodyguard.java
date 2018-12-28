
public class Bodyguard extends Role
{
	public static int actionSpeed = 500;
	public Bodyguard() 
	{
		super(false, "Bodyguard");
	}
	public static void Protect (Player p)
	{
		p.toggleProtected();
	}
	public static void describeRole()
	{
		System.out.println("You are the bodyguard! You can protect someone during the night to prevent them from getting killed. You can vote during the day.");
	}
}
