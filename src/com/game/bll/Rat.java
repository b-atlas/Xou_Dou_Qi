package com.game.bll;

public class Rat extends Piece {

	public Rat(Position pStanding, int pColor, int pPower, Movements pMovement, ChessBoard pBoard) {
		super(pStanding, pColor, pPower, pMovement, pBoard);
	}

	public int getNormalPower() {
		return 1;
	}

	public boolean isOnTheJumpWay(Piece bigCat, Directions pDirection, int squaresNumber) {

		if (!board.isPositionBelongsToRiver(standing))
			return false;

		if (squaresNumber == 4 && bigCat.getPosition().getX() == getPosition().getX()) {
			return true;
		}

		if (squaresNumber == 3 && bigCat.getPosition().getY() == getPosition().getY()) {
			return true;
		}
		return false;

	}

	public boolean isAbleToTake(Piece animal) {
		if (color == animal.color) {
			return false;
		}

		if (animal.getClass() != Elephant.class && animal.getClass() != Rat.class) {
			return false;
		}

		if (board.isPositionBelongsToRiver(standing)) {
			return false;
		}

		return true;
	}
}
