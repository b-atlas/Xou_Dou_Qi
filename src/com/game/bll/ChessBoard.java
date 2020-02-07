package com.game.bll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A class representing the chess board, implemented with pattern design
 * singleton
 * 
 * @author Group_cipher
 *
 */
public class ChessBoard {

	private static ChessBoard uniqueChessBoard;

	private int turn = Color.WHITE;
	private boolean gameOver = false;
	private List<Piece> pieces = new ArrayList<Piece>();

	/**
	 * Initialise the chess board with the pieces
	 */
	private ChessBoard() {
		Elephant e1 = new Elephant(new Position(0, 2), Color.WHITE, 8, new SimpleMovements(), this);
		Elephant e2 = new Elephant(new Position(6, 6), Color.BLACK, 8, new SimpleMovements(), this);

		Lion l1 = new Lion(new Position(6, 0), Color.WHITE, 7, new JumpingMovements(), this);
		Lion l2 = new Lion(new Position(0, 8), Color.BLACK, 7, new JumpingMovements(), this);

		Tiger t1 = new Tiger(new Position(0, 0), Color.WHITE, 6, new JumpingMovements(), this);
		Tiger t2 = new Tiger(new Position(6, 8), Color.BLACK, 6, new JumpingMovements(), this);

		Panther p1 = new Panther(new Position(4, 2), Color.WHITE, 5, new SimpleMovements(), this);
		Panther p2 = new Panther(new Position(2, 6), Color.BLACK, 5, new SimpleMovements(), this);

		Wolf w1 = new Wolf(new Position(2, 2), Color.WHITE, 4, new SimpleMovements(), this);
		Wolf w2 = new Wolf(new Position(4, 6), Color.BLACK, 4, new SimpleMovements(), this);

		Dog d1 = new Dog(new Position(5, 1), Color.WHITE, 3, new SimpleMovements(), this);
		Dog d2 = new Dog(new Position(1, 7), Color.BLACK, 3, new SimpleMovements(), this);

		Cat c1 = new Cat(new Position(1, 1), Color.WHITE, 2, new SimpleMovements(), this);
		Cat c2 = new Cat(new Position(5, 7), Color.BLACK, 2, new SimpleMovements(), this);

		Rat r1 = new Rat(new Position(6, 2), Color.WHITE, 1, new SwimingMovements(), this);
		Rat r2 = new Rat(new Position(0, 6), Color.BLACK, 1, new SwimingMovements(), this);

		pieces.add(e1);
		pieces.add(l1);
		pieces.add(t1);
		pieces.add(p1);
		pieces.add(w1);
		pieces.add(d1);
		pieces.add(c1);
		pieces.add(r1);

		pieces.add(e2);
		pieces.add(l2);
		pieces.add(t2);
		pieces.add(p2);
		pieces.add(w2);
		pieces.add(d2);
		pieces.add(c2);
		pieces.add(r2);
	}

	/**
	 * return the unique instance of the chess board
	 * 
	 * @return ChessBoard
	 */
	public static ChessBoard getChessBoard() {

		if (uniqueChessBoard == null) {

			uniqueChessBoard = new ChessBoard();

		}

		return uniqueChessBoard;
	}

	/**
	 * check if the position belongs to the chess board
	 * 
	 * @param Position
	 * @return boolean
	 */
	public boolean isPositionBelongsToChessBoard(Position p) {
		return p.getX() >= 0 && p.getX() <= 6 && p.getY() >= 0 && p.getY() <= 8;
	}

	/**
	 * check if the position belongs to the river
	 * 
	 * @param Position
	 * @return boolean
	 */
	public boolean isPositionBelongsToRiver(Position p) {
		return (p.getX() > 0 && p.getX() < 3 || p.getX() > 3 && p.getX() < 6) && p.getY() > 2 && p.getY() < 6;
	}

	/**
	 * check if the river is on the way of the player movement
	 * 
	 * @param animal
	 * @param direction
	 * @param number    of squares
	 * @return boolean
	 */
	public boolean isTheRiverOnTheWay(Piece animal, Directions pDirection, int squaresNumber) {

		Position p = animal.getPosition();
		if (animal.color == Color.WHITE) {
			if (pDirection == Directions.FORWARD && squaresNumber == 4) {
				if (p.getY() > 3)
					return false;
				if (p.getX() == 0 || p.getX() == 3 || p.getX() == 6)
					return false;

				return true;
			}

			else if (pDirection == Directions.BACKWARD && squaresNumber == 4) {
				if (p.getY() < 6)
					return false;
				if (p.getX() == 0 || p.getX() == 3 || p.getX() == 6)
					return false;

				return true;
			}

			else if (pDirection == Directions.RIGHT && squaresNumber == 3) {
				if (p.getX() != 0 && p.getX() != 3)
					return false;
				if (p.getY() < 3 || p.getY() > 5)
					return false;
				return true;
			}

			else if (pDirection == Directions.LEFT && squaresNumber == 3) {
				if (p.getX() != 3 && p.getX() != 6)
					return false;
				if (p.getY() < 3 || p.getY() > 5)
					return false;
				return true;
			}

			else
				return false;
		}

		else {
			if (pDirection == Directions.FORWARD && squaresNumber == 4) {
				if (p.getY() < 6)
					return false;
				if (p.getX() == 0 || p.getX() == 3 || p.getX() == 6)
					return false;

				return true;
			}

			else if (pDirection == Directions.BACKWARD && squaresNumber == 4) {
				if (p.getY() > 2)
					return false;
				if (p.getX() == 0 || p.getX() == 3 || p.getX() == 6)
					return false;

				return true;
			}

			else if (pDirection == Directions.RIGHT && squaresNumber == 3) {
				if (p.getX() != 3 && p.getX() != 6)
					return false;
				if (p.getY() < 3 || p.getY() > 5)
					return false;
				return true;
			}

			else if (pDirection == Directions.LEFT && squaresNumber == 3) {
				if (p.getX() != 0 && p.getX() != 3)
					return false;
			}

			if (p.getY() < 3 || p.getY() > 5) {
				return false;
			}
			return true;
		}
	}

	public Position getMySanctuaryPosition() {
		if (turn == Color.WHITE)
			return new Position(3, 0);
		else
			return new Position(3, 8);
	}

	public Position getEnemySanctuaryPosition() {
		if (turn == Color.WHITE)
			return new Position(3, 8);
		else
			return new Position(3, 0);
	}

	/**
	 * delete piece at a given position from the chess board
	 * 
	 * @param Position
	 */
	public void removePieceAt(Position p) {
		Iterator itr = pieces.iterator();
		Piece piece = null;

		while (itr.hasNext()) {
			piece = (Piece) itr.next();
			if (piece.getPosition().equals(p)) {
				itr.remove();
			}
		}
	}

	/**
	 * get the existing pieces of the player of the on going turn
	 * 
	 * @return List<Piece>
	 */
	public List<Piece> getPiecesWithMyColor() {
		List<Piece> piecesWithColor = new ArrayList<Piece>();
		for (Piece itr : pieces) {
			if (itr.getColor() == turn)
				piecesWithColor.add(itr);
		}
		return piecesWithColor;
	}

	/**
	 * get the existing pieces of the enemy
	 * 
	 * @return List<Piece>
	 */
	public List<Piece> getPiecesWithEnemyColor() {
		List<Piece> piecesWithEnemyColor = new ArrayList<Piece>();
		for (Piece itr : pieces) {
			if (itr.getColor() != turn)
				piecesWithEnemyColor.add(itr);
		}
		return piecesWithEnemyColor;
	}

	/**
	 * make random movement for the player with turn
	 */
	public void randomDeplacement() {
		List<Piece> lPieces = getPiecesWithMyColor();
		Piece piece = null;
		while (true) {
			piece = lPieces.get(new Random().nextInt(lPieces.size()));
			if (piece.isAbleToMove()) {
				piece.randomMovement();
				return;
			}
		}

	}

	/**
	 * return a piece if exists at a given position
	 * 
	 * @param Position
	 * @return Piece
	 */
	public Piece getPieceAt(Position p) {

		for (Piece itr : pieces) {
			if (itr.getPosition().equals(p))
				return itr;
		}

		return null;
	}

	/**
	 * inverse the turn
	 */
	public void inverseTurn() {
		turn = (turn == Color.BLACK) ? Color.WHITE : Color.BLACK;
	}

	/**
	 * check if the game is over or not
	 * 
	 * @return boolean
	 */
	public boolean isGameOver() {
		Piece p1 = getPieceAt(getMySanctuaryPosition());
		Piece p2 = getPieceAt(getEnemySanctuaryPosition());
		List<Piece> mine = getPiecesWithMyColor();
		List<Piece> enemys = getPiecesWithEnemyColor();

		return p1 != null || p2 != null || mine.size() == 0 || enemys.size() == 0;
	}

	/**
	 * check if the given position is a trap of the player with turn
	 * 
	 * @param Position
	 * @return boolean
	 */
	public boolean isMyTrap(Position p) {
		Position p1, p2, p3;
		if (turn == Color.WHITE) {
			p1 = new Position(2, 0);
			p2 = new Position(4, 0);
			p3 = new Position(3, 1);
		} else {
			p1 = new Position(2, 8);
			p2 = new Position(4, 8);
			p3 = new Position(3, 7);
		}

		return p.equals(p1) || p.equals(p2) || p.equals(p3);
	}

	/**
	 * check if the given position is a trap of the enemy player
	 * 
	 * @param Position
	 * @return boolean
	 */
	public boolean isEnemyTrap(Position p) {
		Position p1, p2, p3;
		if (turn == Color.BLACK) {
			p1 = new Position(2, 0);
			p2 = new Position(4, 0);
			p3 = new Position(3, 1);
		} else {
			p1 = new Position(2, 8);
			p2 = new Position(4, 8);
			p3 = new Position(3, 7);
		}

		return p.equals(p1) || p.equals(p2) || p.equals(p3);
	}

	/**
	 * display the chess board
	 */
	public void showChessBoard() {

		String str;
		String spaces = "                 ";

		for (int i = 8; i >= 0; i--) {

			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------");

			for (int j = 0; j < 7; j++) {

				str = " ";

				Position p = new Position(j, i);
				Piece piece = getPieceAt(p);

				if (piece != null) {
					str = piece.getStringRepresentation();
					str += spaces;
				} else if (isPositionBelongsToRiver(p)) {
					str += "River";
					str += spaces;
				} else if (isMyTrap(p) || isEnemyTrap(p)) {
					str += "Trap";
					str += spaces;
				} else if (p.equals(getMySanctuaryPosition()) || p.equals(getEnemySanctuaryPosition())) {
					str += "Sanctuary";
					str += spaces;
				} else {
					str += spaces;
				}

				str = str.substring(0, 16);

				System.out.print("|| " + str);
			}
			System.out.print("||");
			System.out.println();

		}

		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------");

	}

	/**
	 * return a given piece of a player
	 * 
	 * @param color
	 * @param commande
	 * @return Piece
	 */
	public Piece getPieceWithColor(int pColor, String commande) {
		List<Piece> pieces;
		if (turn == pColor)
			pieces = getPiecesWithMyColor();
		else
			pieces = getPiecesWithEnemyColor();

		for (Piece itr : pieces) {

			if (itr instanceof Elephant && "E".equals(commande))
				return itr;

			if (itr instanceof Lion && "L".equals(commande))
				return itr;

			if (itr instanceof Tiger && "T".equals(commande))
				return itr;

			if (itr instanceof Panther && "P".equals(commande))
				return itr;

			if (itr instanceof Wolf && "W".equals(commande))
				return itr;

			if (itr instanceof Dog && "D".equals(commande))
				return itr;

			if (itr instanceof Cat && "C".equals(commande))
				return itr;

			if (itr instanceof Rat && "R".equals(commande))
				return itr;
		}

		return null;
	}

	/**
	 * make the best possible movement for the player with turn
	 */
	public void makeTheBestMove() {

		List<Piece> picesWithThisTurn = getPiecesWithMyColor();

		int pieceToMoveIndex = 0;

		for (int i = 0; i < picesWithThisTurn.size(); i++) {

			if (!picesWithThisTurn.get(pieceToMoveIndex).isAbleToMove()) {
				continue;
			}

			else if (picesWithThisTurn.get(pieceToMoveIndex).getTheBestMoveEvaluation() < picesWithThisTurn.get(i)
					.getTheBestMoveEvaluation()) {
				pieceToMoveIndex = i;
			}

			// Just to randomise the movement
			else if (picesWithThisTurn.get(pieceToMoveIndex).getTheBestMoveEvaluation() == picesWithThisTurn.get(i)
					.getTheBestMoveEvaluation()) {

				int rand = new Random().nextInt(2);

				if (rand == 1)
					pieceToMoveIndex = i;
			}

		}
		
		picesWithThisTurn.get(pieceToMoveIndex).makeTheBestMove();
	}

	/**
	 * return all the remaining pieces in the chess board
	 * 
	 * @return
	 */
	public List<Piece> getAllPieces() {
		return pieces;
	}

	public int getTurn() {
		return turn;
	}
}
