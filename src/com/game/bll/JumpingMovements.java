package com.game.bll;

import java.util.ArrayList;
import java.util.List;

/**
 * class representing the movement of the lion and the tiger
 * 
 * @author Group_cipher
 *
 */
public class JumpingMovements implements Movements {

	public List<Position> getPosibleMoves(ChessBoard pBoard, Piece animal) {

		List<Position> positions = new ArrayList<Position>();
		int tab[] = { 1, 3, 4 };

		for (int i = 0; i < 3; i++) {
			for (Directions dir : Directions.values()) {
				if (isPossibleMove(pBoard, animal, dir, tab[i]))
					positions.add(animal.getPositonAfterMove(dir, tab[i]));
			}
		}

		return positions;
	}

	public boolean isPossibleMove(ChessBoard pBoard, Piece animal, Directions pDirection, int squaresNumber) {
		if (squaresNumber != 1 && squaresNumber != 3 && squaresNumber != 4)
			return false;

		Position p = animal.getPositonAfterMove(pDirection, squaresNumber);

		if (p == null || !pBoard.isPositionBelongsToChessBoard(p) || pBoard.isPositionBelongsToRiver(p)
				|| p.equals(pBoard.getMySanctuaryPosition()))
			return false;

		// check that the tiger/lion can jump if the rat is not on the way
		if (squaresNumber == 3 || squaresNumber == 4) {

			if (!pBoard.isTheRiverOnTheWay(animal, pDirection, squaresNumber))
				return false;

			List<Piece> pieces = pBoard.getAllPieces();
			Rat rat = null;

			for (Piece itr : pieces) {
				if (itr.getClass() == Rat.class) {
					rat = (Rat) itr;
					if (rat.isOnTheJumpWay(animal, pDirection, squaresNumber))
						return false;
				}
			}

		}

		Piece piece = pBoard.getPieceAt(p);

		if (piece != null && !animal.isAbleToTake(piece))
			return false;

		return true;
	}
}
