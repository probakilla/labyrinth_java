package model;

public class Vertex {

	private int _x, _y, _nbr;

	public int getNbr()
	{
		return _nbr;
	}

	public void setNbr(int nbr) 
	{
		_nbr = nbr;
	}

	public Vertex (int x, int y, int nbr)
	{
		_x = x;
		_y = y;
		_nbr = nbr;
	}
	
	public int getX()
        {
		return _x;
	}

	public void setX(int x) {
		_x = x;
	}

	public int getY() {
		return _y;
	}

	public void setY(int y) {
		_y = y;
	}
	
	public int compareTo (Vertex v)
	{
		if (v.getX() == _x && v.getY() == _y && _nbr == v.getNbr())
			return 0;
		return 1;
	}
	
	public int inBorders (int width, int height)
	{
		if (_x <= width && _x >= 0)
			if (_y <= height && _y >= 0)
				return 0;
		return 1;
	}
}
