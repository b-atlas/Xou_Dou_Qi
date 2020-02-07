package com.game.bll;

public class Wolf extends Piece {

	public Wolf(Position pStanding, int pColor, int pPower, Movements pMovement, ChessBoard pBoard) {
		super(pStanding, pColor, pPower, pMovement, pBoard);
	}

	public int getNormalPower() {
		return 4;
	}
}
