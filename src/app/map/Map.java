package app.map;

import app.gameObject.*;
import java.util.ArrayList;
import java.util.List;

public class Map
{
	private GameObject[][] map;
	private List<Guard> guards;
	private Player player;
	private int stars;
	public Map(int height,int width)
	{
		stars=0;
		map=new GameObject[height][width];
		guards=new ArrayList<>();
	}
	public int getStars()
	{
		return stars;
	}
	public void setStars(int stars)
	{
		this.stars=stars;
	}
	public Player getPlayer()
	{
		return player;
	}
	public void setPlayer(Player player)
	{
		this.player=player;
	}
	public List<Guard> getGuards()
	{
		return new ArrayList<>(guards);
	}
	public void addGuard(Guard guard)
	{
		guards.add(guard);
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
    public GameObject[][] getGameObjects()
    {
        GameObject[][] tmp=new GameObject[getMapHeight()][getMapWidth()];
        for(int y=0;y<getMapHeight();y++)
            tmp[y]=map[y].clone();
        return tmp;
    }
}