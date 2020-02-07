package com.game.bll;

public class Cat extends Piece {

	public Cat(Position pStanding, int pColor, int pPower, Movements pMovement, ChessBoard pBoard) {
		super(pStanding, pColor, pPower, pMovement, pBoard);
	}

	public int getNormalPower() {
		return 2;
	}
}
