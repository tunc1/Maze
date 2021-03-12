package app.gameObject;

import app.map.MapController;
import app.direction.Direction;

public interface OnGuardCollideWith
{
    void onGuardCollideWith(MapController mapController,Guard guard,Direction direction);
}
