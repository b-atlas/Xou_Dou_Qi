package com.game.bll;

public class Tiger extends Piece {

	public Tiger(Position pStanding, int pColor, int pPower, Movements pMovement, ChessBoard pBoard) {
		super(pStanding, pColor, pPower, pMovement, pBoard);
	}

	public int getNormalPower() {
		return 6;
	}

}
