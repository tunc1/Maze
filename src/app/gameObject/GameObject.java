package app.gameObject;

public abstract class GameObject
{
	private int x,y;
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setXY(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
}