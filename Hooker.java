
public class Hooker extends Role 
{
	public static int actionSpeed = 9999;
	public Hooker()
	{
		super(false, "Hooker");
	}
	public static void Distract(Player p)
	{
		p.toggleDistracted();
	}
	public static void describeRole()
	{
		System.out.println("You are the Hooker! You may distract one person each night to prevent them from taking any actions. You may vote during the day.");
	}
}	
