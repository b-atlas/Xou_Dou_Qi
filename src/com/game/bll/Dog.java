package com.game.bll;

public class Dog extends Piece {

	public Dog(Position pStanding, int pColor, int pPower, Movements pMovement, ChessBoard pBoard) {
		super(pStanding, pColor, pPower, pMovement, pBoard);
	}

	public int getNormalPower() {
		return 3;
	}
}
