package app.gameObject;

import app.direction.Direction;
import app.map.MapController;

public class Star extends GameObject implements OnPlayerCollideWith
{
	public String toString()
	{
		return "*";
	}
	public void onPlayerCollideWith(MapController mapController,Direction direction)
	{
		mapController.remove(this);
		mapController.move(mapController.getMap().getPlayer(),direction);
		mapController.getMap().setStars(mapController.getMap().getStars()-1);
	}
}