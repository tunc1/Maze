package app.map;

import app.direction.Direction;
import app.gameObject.*;
import java.util.List;

public class MapController
{
	private Map map;
	private MapView view;
	private boolean gameFinished;
	private Direction[] directions={Direction.RIGHT,Direction.LEFT,Direction.UP,Direction.DOWN};
	public MapController(Map map,MapView view)
	{
		this.map=map;
		this.view=view;
		gameFinished=false;
		fillMap();
	}
	public Map getMap()
	{
		return map;
	}
	public void startGame()
	{
		while(!gameFinished)
		{
			view.show(map);
			view.message("Stars that must be collected:"+map.getStars());
			view.message("Right:d|Left:a|Up:w|Down:s");
			char input=view.getInput();
			Direction direction=null;
			switch(input)
            {
                case 'w':
                   direction=Direction.UP;
                break;
                case 's':
                   direction=Direction.DOWN;
                break;
                case 'a':
                   direction=Direction.LEFT;
                break;
                case 'd':
                   direction=Direction.RIGHT;
                break;
                default:
                    view.message("Invalid Input");
                break;
            }
			if(direction!=null)
			{
				movePlayer(direction);
				moveGuards();
			}
		}
		view.message("Game Finished");
	}
	private Direction getRandomDirection(GameObject gameObject)
	{
		int random=(int)(Math.random()*directions.length);
		Direction direction=directions[random];
		while(!isInBounds(gameObject,direction))
		{
			random=(int)(Math.random()*directions.length);
			direction=directions[random];
		}
		return direction;
	}
	private void moveGuards()
	{
		for(Guard guard:map.getGuards())
		{
			Direction direction=getRandomDirection(guard);
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
	private void movePlayer(Direction direction)
	{
		Player player=map.getPlayer();
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
	public void checkGame()
	{
		if(map.getStars()==0)
			finishGame();
	}
	private void fillMap()
	{
		GameObjectFactory gameObjectFactory=new GameObjectFactory();
		Player player=(Player)gameObjectFactory.create("player");
		player.setXY(0,0);
		map.setPlayer(player);
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
			GameObject star=gameObjectFactory.create("star");
			star.setXY(point[0],point[1]);
			map.setGameObject(star);
			map.setStars(map.getStars()+1);
		}
		int[][] guardLocations={{0,2},{5,9},{10,18}};
		for (int[] point:guardLocations)
		{
			Guard guard=(Guard)gameObjectFactory.create("guard");
			guard.setXY(point[0],point[1]);
			map.addGuard(guard);
			map.setGameObject(guard);
		}
	}
	public void remove(GameObject gameObject)
	{
		map.setGameObject(gameObject.getX(),gameObject.getY(),null);
	}
}