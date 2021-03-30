package app;

import app.map.*;

public class Main
{
	public static void main(String[] args)
	{
		new MapController(new Map(20,20),new MapView()).startGame();
	}
}