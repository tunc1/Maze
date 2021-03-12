package app.gameObject;

import app.map.MapController;
import app.direction.Direction;

public interface OnPlayerCollideWith
{
    void onPlayerCollideWith(MapController mapController,Direction direction);
}
