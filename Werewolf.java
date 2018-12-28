
public class Werewolf extends Role 
{
	public static int actionSpeed = 1000;
	public Werewolf()
	{
		super(true, "Werewolf");
	}
	public static void Kill(Player p)
	{
		p.die();
	}
	public static void describeRole()
	{
		System.out.println("You are a werewolf! You may not be distracted. You can kill one person each night through the bodyguard's protection.");
	}
}
