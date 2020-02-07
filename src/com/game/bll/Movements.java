package com.game.bll;

import java.util.List;

/**
 * interface used to implement the strategy design pattern with the Piece class
 * 
 * @author Group_cipher
 *
 */
public interface Movements {

	/**
	 * return all the possible moves for a given piece
	 * 
	 * @param chess  board
	 * @param animal
	 * @return List<Position>
	 */
	public List<Position> getPosibleMoves(ChessBoard pBoard, Piece animal);

	/**
	 * check if a given movement is possible for a given piece
	 * 
	 * @param chess     board
	 * @param animal
	 * @param Direction
	 * @param number    of squares
	 * @return boolean
	 */
	public boolean isPossibleMove(ChessBoard pBoard, Piece animal, Directions pDirection, int squaresNumber);

}
