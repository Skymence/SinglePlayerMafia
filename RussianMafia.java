
public class RussianMafia extends Role 
{

	public static int actionSpeed = 100;

	public RussianMafia()
	{
		super(true, "Russian Mafia");
	}

	//idk how i will implement this. we need to be able to return some object given a string. this will be done using a HashMap.
	public static void Kill(Player P)
	{
		P.die();
	}
	public static void describeRole()
	{
		System.out.println("You are a Russian Mafia! You get to kill one player every night, and can vote during the day.");
	}

}
