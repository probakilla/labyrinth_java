package model;

import model.Model.Directions;

public class Vertex {

	private int _x, _y, _nbr;

	public int getNbr() {
		return _nbr;
	}

	public void setNbr(int nbr) {
		_nbr = nbr;
	}

	public Vertex(int x, int y, int nbr) {
		_x = x;
		_y = y;
		_nbr = nbr;
	}

	public int getX() {
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

	public int compareTo(Vertex v) {
		if (v.getX() == _x && v.getY() == _y && _nbr == v.getNbr())
			return 0;
		return 1;
	}


	/**
	 * Vérifie que l'on reste bien dans les limites du labyrinthe si on se déplace
	 * @param dir direction du déplacement
	 * @param width largeur du labyrinthe
	 * @param height longueur du labyrinthe
	 * @return false si on va en dehors du labyrinthe sinon true
	 */
	public boolean inBorders(Directions dir, int width, int height) {
		switch(dir)
		{
		case NORTH: return (!((_x <= width && _x >= 0) && (_y - 1 <= height && _y - 1>= 0)));
		case SOUTH: return (!((_x <= width && _x >= 0) && (_y + 1<= height && _y + 1>= 0)));
		case EAST:  return (!((_x + 1<= width && _x + 1>= 0) && (_y <= height && _y >= 0)));
		case WEST:  return (!((_x - 1 <= width && _x - 1>= 0) && (_y <= height && _y >= 0)));
		}
		return false;
	}

}
