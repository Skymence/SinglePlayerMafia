
public class Villager extends Role
{
	
	public Villager()
	{
		super(false, "Villager");
	}
	public static void describeRole()
	{
		System.out.println("You are a villager! You have no powers at night, but can vote to lynch people during the day.");
	}

	
}
