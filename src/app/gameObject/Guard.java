package app.gameObject;

import app.map.MapController;
import app.direction.Direction;

public class Guard extends GameObject implements OnPlayerCollideWith
{
	public String toString()
	{
		return "G";
	}
	public void onPlayerCollideWith(MapController mapController,Direction direction)
	{
		mapController.finishGame();
	}
}