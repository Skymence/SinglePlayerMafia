
public class Vigilante extends Role 
{
	public static int actionSpeed = 90;
	public Vigilante()
	{
		super(false, "Vigilante");
	}
	public static void Kill(Player p)
	{
		p.die();
	}
	public static void describeRole()
	{
		System.out.println("You are the vigilante! You can kill one person each night, but you side with the good people. You can vote during the day.");
	}
}
