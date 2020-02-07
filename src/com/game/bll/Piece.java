package com.game.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.game.exceptions.GameRulesNotRespectedException;

/**
 * class responsible for making all the operations of pieces
 * 
 * @author Group_cipher
 *
 */
public abstract class Piece {

	protected Position standing;

	protected int color;

	protected int power;

	/**
	 * This variable is responsible of the implementation of the strategy design
	 * pattern
	 */
	protected Movements movement;

	protected ChessBoard board;

	public Piece(Position pStanding, int pColor, int pPower, Movements pMovement, ChessBoard pBoard) {
		standing = pStanding;
		color = pColor;
		power = pPower;
		movement = pMovement;
		board = pBoard;
	}

	/**
	 * return all the possible moves of a piece using the strategy design pattern
	 * 
	 * @return List<Position>
	 */
	public List<Position> getPosibleMoves() {
		return movement.getPosibleMoves(board, this);
	}

	/**
	 * check if the command entered by the user correspond to a possible move for
	 * that piece
	 * 
	 * @param direction
	 * @param number    of squares
	 * @return boolean
	 */
	public boolean isPossibleMove(Directions pDirection, int squaresNumber) {
		return movement.isPossibleMove(board, this, pDirection, squaresNumber);
	}

	/**
	 * return a list of possible movements evaluation, every evaluation of index i
	 * correspond to the movement index i returned by the method {@getPosibleMoves}
	 * 
	 * @return List<Integer>
	 */
	public List<Integer> getMovesEvaluations() {

		List<Position> possibleMoves = movement.getPosibleMoves(board, this);
		List<Integer> movesEvaluation = new ArrayList<Integer>();

		int evaluation = 0;

		List<Piece> boardPieces = board.getAllPieces();

		for (Piece itr : boardPieces) {

			if (color == itr.color) {
				evaluation += itr.power;
			} else {
				evaluation -= itr.power;
			}
		}

		for (Position itr : possibleMoves) {

			Piece p = board.getPieceAt(itr);

			if (p == null) {
				movesEvaluation.add(evaluation);
			} else {
				movesEvaluation.add(evaluation + p.power);
			}
		}

		return movesEvaluation;

	}

	/**
	 * get the position of the best move for that piece
	 * 
	 * @return Position
	 */
	public Position getTheBestMove() {

		if (!isAbleToMove())
			return null;

		List<Position> possiblesMoves = getPosibleMoves();

		// if movement to the enemy sanctuary is possible
		for (Position itr : possiblesMoves) {

			if (itr.equals(board.getEnemySanctuaryPosition()))
				return board.getEnemySanctuaryPosition();
		}

		List<Integer> movesEvaluations = getMovesEvaluations();

		int bestMoveIndex = 0;

		for (int i = 0; i < movesEvaluations.size(); i++) {

			if (movesEvaluations.get(i) > movesEvaluations.get(bestMoveIndex))
				bestMoveIndex = i;

			// Just to randomise the movement
			else if (movesEvaluations.get(i) == movesEvaluations.get(bestMoveIndex)) {

				int rand = new Random().nextInt(2);

				if (rand == 1)
					bestMoveIndex = i;
			}
		}

		return possiblesMoves.get(bestMoveIndex);
	}

	/**
	 * get the evaluation of the best possible move
	 * 
	 * @return integer
	 */
	public int getTheBestMoveEvaluation() {

		if (!isAbleToMove())
			return -1;

		List<Integer> movesEvaluations = getMovesEvaluations();

		int bestEvaluation = movesEvaluations.get(0);

		for (int i = 0; i < movesEvaluations.size(); i++) {

			if (bestEvaluation < movesEvaluations.get(i))
				bestEvaluation = movesEvaluations.get(i);

		}

		return bestEvaluation;

	}

	/**
	 * make the best possible move
	 */
	public void makeTheBestMove() {

		if (!isAbleToMove())
			return;

		Position p = getTheBestMove();

		if (board.isEnemyTrap(p))
			power = 0;
		else
			power = getNormalPower();

		Piece lPiece = board.getPieceAt(p);

		if (getColor() == Color.WHITE)
			System.out.println(getClass().getSimpleName() + " of color white" + " To " + p + " from " + standing);
		else
			System.out.println(getClass().getSimpleName() + " of color black" + " To " + p + " from " + standing);

		if (lPiece != null)
			board.removePieceAt(p);

		standing = p;

		board.inverseTurn();
	}

	/**
	 * get the position correspond of the move entered by the player
	 * 
	 * @param direction
	 * @param number    of squares
	 * @return Position
	 */
	public Position getPositonAfterMove(Directions pDirection, int squaresNumber) {
		Position p = null;
		if (pDirection == Directions.FORWARD) {
			if (getColor() == Color.WHITE)
				p = new Position(getPosition().getX(), getPosition().getY() + squaresNumber);
			else
				p = new Position(getPosition().getX(), getPosition().getY() - squaresNumber);
		}

		else if (pDirection == Directions.BACKWARD) {
			if (getColor() == Color.WHITE)
				p = new Position(getPosition().getX(), getPosition().getY() - squaresNumber);
			else
				p = new Position(getPosition().getX(), getPosition().getY() + squaresNumber);
		}

		else if (pDirection == Directions.RIGHT) {
			if (getColor() == Color.WHITE)
				p = new Position(getPosition().getX() + squaresNumber, getPosition().getY());
			else
				p = new Position(getPosition().getX() - squaresNumber, getPosition().getY());
		}

		else if (pDirection == Directions.LEFT) {
			if (getColor() == Color.WHITE)
				p = new Position(getPosition().getX() - squaresNumber, getPosition().getY());
			else
				p = new Position(getPosition().getX() + squaresNumber, getPosition().getY());
		}

		return p;
	}

	/**
	 * check if a piece is able to take another or not
	 * 
	 * @param animal
	 * @return boolean
	 */
	public boolean isAbleToTake(Piece animal) {

		if (color == animal.color)
			return false;

		if (power >= animal.power)
			return true;

		return false;
	}

	/**
	 * make the move entered by the player if possible
	 * 
	 * @param direction
	 * @param number    of squares
	 * @throws GameRulesNotRespectedException
	 */
	public void move(Directions pDirection, int squaresNumber) throws GameRulesNotRespectedException {

		if (!movement.isPossibleMove(board, this, pDirection, squaresNumber))
			throw new GameRulesNotRespectedException("Player wished movement is impossible!");

		Position p = getPositonAfterMove(pDirection, squaresNumber);

		if (board.isEnemyTrap(p))
			power = 0;
		else
			power = getNormalPower();

		Piece lPiece = board.getPieceAt(p);

		if (lPiece != null)
			board.removePieceAt(p);

		standing = p;

		board.inverseTurn();
	}

	/**
	 * make a random movement of the list of the possible moves
	 */
	public void randomMovement() {
		List<Position> positions = movement.getPosibleMoves(board, this);

		Position p = positions.get(new Random().nextInt(positions.size()));

		if (board.isEnemyTrap(p))
			power = 0;
		else
			power = getNormalPower();

		if (getColor() == Color.WHITE)
			System.out.println(getClass().getSimpleName() + " of color white" + " To " + p + " from " + standing);
		else
			System.out.println(getClass().getSimpleName() + " of color black" + " To " + p + " from " + standing);

		Piece lPiece = board.getPieceAt(p);

		if (lPiece != null)
			board.removePieceAt(p);

		standing = p;

		board.inverseTurn();
	}

	/**
	 * check if the piece is able to move
	 * 
	 * @return boolean
	 */
	public boolean isAbleToMove() {
		List<Position> l = getPosibleMoves();
		if (l.isEmpty())
			return false;
		return true;
	}

	/**
	 * get the piece class and the her owning player
	 * 
	 * @return string
	 */
	public String getStringRepresentation() {
		String str;
		str = getClass().getSimpleName();
		if (color == Color.WHITE)
			str += "_white";
		else
			str += "_black";
		return str;
	}

	/**
	 * get the piece of the power in the normal situation (not being at the
	 * sanctuary)
	 * 
	 * @return integer
	 */
	abstract public int getNormalPower();

	public Position getPosition() {
		return standing;
	}

	public int getColor() {
		return color;
	}
}
