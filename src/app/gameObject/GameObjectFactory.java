package app.gameObject;

public class GameObjectFactory
{
	public GameObject create(String type)
	{
		GameObject gameObject=null;
		if(type.equals("player"))
			gameObject=new Player();
		else if(type.equals("wall"))
			gameObject=new Wall();
		else if(type.equals("box"))
			gameObject=new Box();
		else if(type.equals("star"))
			gameObject=new Star();
		else if(type.equals("guard"))
			gameObject=new Guard();
		else if(type.contentEquals("exit"))
			gameObject=new Exit();
		return gameObject;
	}
}