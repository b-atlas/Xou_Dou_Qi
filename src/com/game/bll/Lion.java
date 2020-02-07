package com.game.bll;

public class Lion extends Piece {

	public Lion(Position pStanding, int pColor, int pPower, Movements pMovement, ChessBoard pBoard) {
		super(pStanding, pColor, pPower, pMovement, pBoard);
	}

	public int getNormalPower() {
		return 7;
	}
}
