package app.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import app.direction.Direction;
import app.gameObject.*;

public class MapController
{
	private Map map;
	private Player player;
	private Scanner scanner;
	private int starCount;
	private boolean gameFinished;
	private List<Guard> guards;
	private Direction[] directions={Direction.RIGHT,Direction.LEFT,Direction.UP,Direction.DOWN};
	public MapController()
	{
		scanner=new Scanner(System.in);
		map=new Map(20,20);
		starCount=0;
		gameFinished=false;
		guards=new ArrayList<>();
		fillMap();
	}
	public void startGame()
	{
		while(!gameFinished)
		{
			System.out.println(map);
			System.out.println("Star Count That Has to be collected:"+starCount);
			Direction direction=getDirectionFromUser();
			movePlayer(player,direction);
			moveGuards();
		}
		System.out.println("Game Finished");
	}
	private void moveGuards()
	{
		for(Guard guard:guards)
		{
			int random=(int)(Math.random()*directions.length);
			Direction direction=directions[random];
			while(!isInBounds(guard,direction))
			{
				random=(int)(Math.random()*directions.length);
				direction=directions[random];
			}
			moveGuard(guard,direction);
		}
	}
	private void moveGuard(Guard guard,Direction direction)
	{
		if(canMove(guard,direction))
			move(guard,direction);
		else
		{
			int[] point=getPoint(guard,direction);
			int x=point[0];
			int y=point[1];
			GameObject gameObject=map.getGameObject(x,y);
			if(gameObject instanceof OnGuardCollideWith)
				((OnGuardCollideWith)gameObject).onGuardCollideWith(this,guard,direction);
		}
	}
	private Direction getDirectionFromUser()
	{
		System.out.println("Right:0|Left:1|Up:2|Down:3");
		String s=scanner.nextLine();
		int i=Integer.parseInt(s);
		return directions[i];
	}
	private int[] getPoint(GameObject gameObject,Direction direction)
	{
		int x=gameObject.getX();
		int y=gameObject.getY();
		switch (direction)
		{
			case DOWN:
				y++;
			break;
			case UP:
				y--;
			break;
			case LEFT:
				x--;
			break;
			case RIGHT:
				x++;
			break;
		}
		return new int[]{x,y};
	}
	private boolean isInBounds(GameObject gameObject,Direction direction)
	{
		int[] point=getPoint(gameObject,direction);
		int x=point[0];
		int y=point[1];
		return map.isInBounds(x,y);
	}
	public boolean canMove(GameObject gameObject,Direction direction)
	{
		int[] point=getPoint(gameObject,direction);
		int x=point[0];
		int y=point[1];
		return !map.gameObjectExists(x,y);
	}
	private void movePlayer(Player player,Direction direction)
	{
		if(isInBounds(player,direction))
		{
			if(canMove(player,direction))
				move(player,direction);
			else
			{
				int[] point=getPoint(player,direction);
				int x=point[0];
				int y=point[1];
				GameObject gameObject=map.getGameObject(x,y);
				if(gameObject instanceof OnPlayerCollideWith)
					((OnPlayerCollideWith)gameObject).onPlayerCollideWith(this,direction);
			}
		}
	}
	public void move(GameObject gameObject,Direction direction)
	{
		if(canMove(gameObject,direction))
		{
			int[] point=getPoint(gameObject,direction);
			int x=point[0];
			int y=point[1];
			map.setGameObject(x,y,gameObject);
			map.setGameObject(gameObject.getX(),gameObject.getY(),null);
			gameObject.setXY(x,y);
		}
	}
	public void finishGame()
	{
		gameFinished=true;
	}
	public Player getPlayer()
	{
		return player;
	}
	public int getStarCount()
	{
		return starCount;
	}
	private void fillMap()
	{
		GameObjectFactory gameObjectFactory=new GameObjectFactory();
		player=(Player)gameObjectFactory.create("player");
		player.setXY(0,0);
		map.setGameObject(player);
		GameObject box=gameObjectFactory.create("box");
		box.setXY(1,2);
		map.setGameObject(box);
		GameObject exit=gameObjectFactory.create("exit");
		exit.setXY(9,9);
		map.setGameObject(exit);
		int[][] walls={{1,1},{2,1},{3,1}};
		for (int[] point:walls)
		{
			GameObject wall=gameObjectFactory.create("wall");
			wall.setXY(point[0],point[1]);
			map.setGameObject(wall);
		}
		int[][] stars={{3,3},{7,4},{8,2},{0,1}};
		for (int[] point:stars)
		{
			starCount++;
			GameObject star=gameObjectFactory.create("star");
			star.setXY(point[0],point[1]);
			map.setGameObject(star);
		}
		int[][] guardLocations={{0,2},{5,9},{10,18}};
		for (int[] point:guardLocations)
		{
			Guard guard=(Guard)gameObjectFactory.create("guard");
			guard.setXY(point[0],point[1]);
			guards.add(guard);
			map.setGameObject(guard);
		}
	}
	public void decreaseStarCount(int i)
	{
		starCount-=i;
	}
	public void remove(GameObject gameObject)
	{
		map.setGameObject(gameObject.getX(),gameObject.getY(),null);
	}
}