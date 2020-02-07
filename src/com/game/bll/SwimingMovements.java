package com.game.bll;

import java.util.ArrayList;
import java.util.List;

/**
 * class representing the movement of the rat
 * 
 * @author Group_cipher
 *
 */
public class SwimingMovements implements Movements {

	public List<Position> getPosibleMoves(ChessBoard pBoard, Piece animal) {
		List<Position> positions = new ArrayList<Position>();

		for (Directions dir : Directions.values()) {
			if (isPossibleMove(pBoard, animal, dir, 1))
				positions.add(animal.getPositonAfterMove(dir, 1));
		}

		return positions;
	}

	public boolean isPossibleMove(ChessBoard pBoard, Piece animal, Directions pDirection, int squaresNumber) {

		if (squaresNumber != 1)
			return false;

		Position p = animal.getPositonAfterMove(pDirection, squaresNumber);

		if (p == null || !pBoard.isPositionBelongsToChessBoard(p) || p.equals(pBoard.getMySanctuaryPosition()))
			return false;

		Piece piece = pBoard.getPieceAt(p);

		if (piece != null && !animal.isAbleToTake(piece))
			return false;

		return true;
	}
}
