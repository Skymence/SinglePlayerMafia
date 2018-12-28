
public class ItalianMafia extends Role
{

	public static int actionSpeed = 200;
	
	public ItalianMafia()
	{
		super(true, "Italian Mafia");
	}
	//idk how i will implement this. we need to be able to return some object given a string. this will be done using a HashMap.
	public static void Kill(Player P)
	{
		if(!P.checkProtected())
			P.die();
	}
	public static void describeRole()
	{
		System.out.println("You are an Italian Mafia! You get to kill one player every night, and can vote during the day.");
	}
}
