package app.gameObject;

import app.direction.Direction;
import app.map.MapController;

public class Box extends GameObject implements OnPlayerCollideWith,OnGuardCollideWith
{
	public String toString()
	{
		return "B";
	}
	public void onPlayerCollideWith(MapController mapController,Direction direction)
	{
		if(mapController.canMove(this,direction))
		{
			mapController.move(this,direction);
			mapController.move(mapController.getMap().getPlayer(),direction);
		}
	}
	public void onGuardCollideWith(MapController mapController,Guard guard,Direction direction)
	{
		if(mapController.canMove(this,direction))
		{
			mapController.move(this,direction);
			mapController.move(guard,direction);
		}
	}
}