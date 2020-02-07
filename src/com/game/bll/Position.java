package com.game.bll;

/**
 * class representing the position of a given piece
 * 
 * @author Groupe_cipher
 *
 */
public class Position {

	private int x;
	private int y;

	public Position(int pX, int pY) {
		x = pX;
		y = pY;
	}

	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (obj == this)
			return true;

		if (obj.getClass() != getClass())
			return false;

		Position p = (Position) obj;

		return p.x == x && p.y == y;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

}
