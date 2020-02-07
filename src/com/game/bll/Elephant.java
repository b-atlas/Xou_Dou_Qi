package com.game.bll;

public class Elephant extends Piece {

	public Elephant(Position pStanding, int pColor, int pPower, Movements pMovement, ChessBoard pBoard) {
		super(pStanding, pColor, pPower, pMovement, pBoard);
	}

	public int getNormalPower() {
		return 8;
	}

	/**
	 * overriding the method for the elephant so he can't take the rat
	 */
	public boolean isAbleToTake(Piece animal) {
		if (color == animal.color || animal.getClass() == Rat.class)
			return false;

		return true;
	}

}
