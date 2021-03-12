package app.gameObject;

import app.direction.Direction;
import app.map.MapController;

public class Exit extends GameObject implements OnPlayerCollideWith
{
	public String toString()
	{
		return "E";
	}
	public void onPlayerCollideWith(MapController mapController,Direction direction)
	{
		if(mapController.getStarCount()==0)
			mapController.finishGame();
	}
}