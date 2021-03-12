package app.gameObject;

import app.map.MapController;
import app.direction.Direction;

public class Player extends GameObject implements OnGuardCollideWith
{
	public String toString()
	{
		return "P";
	}
	public void onGuardCollideWith(MapController mapController,Guard guard,Direction direction)
	{
		mapController.finishGame();
	}
}