
public class Godfather extends Role 
{
	public static int actionSpeed = 80;
	public Godfather()
	{
		super(true, "Godfather");
	}
	
	public static void Kill(Player p)
	{
		if(!p.checkProtected())
			p.die();
	}
	
	public static void Kill(Player p1, Player p2)
	{
		if(!p1.checkProtected())
			p1.die();
		if(!p2.checkProtected())
			p2.die();
	}
	
	public static void Kill(Player p1, Player p2, Player p3)
	{
		if(!p1.checkProtected())
			p1.die();
		if(!p2.checkProtected())
			p2.die();
		if(!p3.checkProtected())
			p3.die();
	}
	public static void describeRole()
	{
		System.out.println("You are the godfather! You may kill up to three people each night and can vote during the day.");
	}
}
