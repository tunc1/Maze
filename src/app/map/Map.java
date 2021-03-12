package app.map;

import app.gameObject.GameObject;

public class Map
{
	private GameObject[][] map;
	public Map(int height,int width)
	{
		map=new GameObject[height][width];
	}
	public int getMapWidth()
	{
		return map[0].length;
	}
	public int getMapHeight()
	{
		return map.length;
	}
	public void setGameObject(GameObject gameObject)
	{
		setGameObject(gameObject.getX(),gameObject.getY(),gameObject);
	}
	public void setGameObject(int x,int y,GameObject gameObject)
	{
		map[y][x]=gameObject;
	}
	public GameObject getGameObject(int x,int y)
	{
		return map[y][x];
	}
	public boolean gameObjectExists(int x,int y)
	{
		return getGameObject(x,y)!=null;
	}
	public boolean isInBounds(int x,int y)
	{
		return x>-1&&x<getMapWidth()&&y>-1&&y<getMapHeight();
	}
	public String toString()
	{
		StringBuilder output=new StringBuilder();
		for (GameObject[] gameObjects:map)
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
		return output.toString();
	}
}