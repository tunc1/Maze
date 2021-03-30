package app.map;

import java.util.Scanner;
import app.gameObject.GameObject;

public class MapView
{
	private Scanner scanner;
    public MapView()
    {
		scanner=new Scanner(System.in);
    }
    public void message(String message)
    {
        System.out.println(message);
    }
    public void show(Map map)
    {
        StringBuilder output=new StringBuilder();
		for (GameObject[] gameObjects:map.getGameObjects())
		{
			for (GameObject gameObject:gameObjects)
			{
				if(gameObject!=null)
					output.append(gameObject);
				else
					output.append(" ");
			}
			output.append("\n");
		}
         System.out.println(output.toString());
    }
    public char getInput()
	{
		return scanner.nextLine().charAt(0);
	}
}