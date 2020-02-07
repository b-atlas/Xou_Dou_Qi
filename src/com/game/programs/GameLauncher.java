package com.game.programs;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.game.bll.ChessBoard;
import com.game.bll.Directions;
import com.game.bll.Piece;
import com.game.exceptions.GameRulesNotRespectedException;

/**
 * class responsible for starting the game with different options
 * 
 * @author Group_cipher
 *
 */
public class GameLauncher {

	private static ChessBoard board = ChessBoard.getChessBoard();

	/**
	 * start a random game between the machine and itself
	 * 
	 * @throws InterruptedException
	 */
	public static void randomGame() throws InterruptedException {
		board.showChessBoard();

		while (!board.isGameOver()) {

			Thread.sleep(2000);
			board.randomDeplacement();
			board.showChessBoard();
		}
		System.out.println("Game Over!");
	}

	/**
	 * start a game between two humans
	 */
	public static void humanAgainstHuman() {
		Scanner sc = new Scanner(System.in);
		board.showChessBoard();

		while (!board.isGameOver()) {

			String commande = sc.nextLine();

			Pattern p = Pattern.compile("^(E|L|T|P|W|D|C|R)(f|b|r|l)(\\d)$");
			Matcher m = p.matcher(commande);

			if (m.matches()) {
				String pieceToMove = m.group(1);
				String direction = m.group(2);
				int squaresNumber = Integer.parseInt(m.group(3));
				Directions dir = null;

				if ("f".equals(direction))
					dir = Directions.FORWARD;
				else if ("b".equals(direction))
					dir = Directions.BACKWARD;
				else if ("r".equals(direction))
					dir = Directions.RIGHT;
				else if ("l".equals(direction))
					dir = Directions.LEFT;

				Piece chosenPiece = board.getPieceWithColor(board.getTurn(), pieceToMove);

				try {
					if (chosenPiece != null) {
						chosenPiece.move(dir, squaresNumber);
						board.showChessBoard();
					} else
						System.out.println("La piece n'existe pas!");
				} catch (GameRulesNotRespectedException e) {
					displayGameRules();
				}

			}

			else if ("exit".equals(commande))
				System.exit(0);

			else
				System.out.println("Commande incorrecte!");
		}

		System.out.println("Game Over!");
	}

	/**
	 * start a game between a human and the machine who is playing using a naive
	 * algorithm
	 * 
	 * @throws InterruptedException
	 */
	public static void machineVsHuman() throws InterruptedException {

		Scanner sc = new Scanner(System.in);
		board.showChessBoard();

		while (!board.isGameOver()) {

			String commande = sc.nextLine();

			Pattern p = Pattern.compile("^(E|L|T|P|W|D|C|R)(f|b|r|l)(\\d)$");
			Matcher m = p.matcher(commande);

			if (m.matches()) {
				String pieceToMove = m.group(1);
				String direction = m.group(2);
				int squaresNumber = Integer.parseInt(m.group(3));
				Directions dir = null;

				if ("f".equals(direction))
					dir = Directions.FORWARD;
				else if ("b".equals(direction))
					dir = Directions.BACKWARD;
				else if ("r".equals(direction))
					dir = Directions.RIGHT;
				else if ("l".equals(direction))
					dir = Directions.LEFT;

				Piece chosenPiece = board.getPieceWithColor(board.getTurn(), pieceToMove);

				try {
					if (chosenPiece != null) {
						chosenPiece.move(dir, squaresNumber);
						board.showChessBoard();

						if (board.isGameOver())
							break;

						Thread.sleep(1500);
						board.makeTheBestMove();
						board.showChessBoard();
					} else
						System.out.println("La piece n'existe pas!");
				} catch (GameRulesNotRespectedException e) {
					displayGameRules();
				}

			}

			else if ("exit".equals(commande))
				System.exit(0);

			else
				System.out.println("Commande incorrecte!");
		}

		System.out.println("Game Over!");
	}

	/**
	 * start a game between the machine and itself using the naive algorithm
	 */
	public static void machineVsItself() {

		board.showChessBoard();

		while (!board.isGameOver()) {

			try {

				Thread.sleep(2000);
				board.makeTheBestMove();
				board.showChessBoard();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Game Over!");

	}

	/**
	 * start a game between this machine and other machine through a network
	 */
	public static void machineVsOtherMachine() {

		// TODO ....

	}

	public static void displayGameRules() {
		System.out.println("Game rules!");
	}

	public static int showMenu() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("What do you want to try?");
		System.out.println("1- Human vs Machine");
		System.out.println("2- This machine vs other machine");
		System.out.println("3- machine vs itself");
		System.out.println("4- Random game");
		System.out.println("5- Human vs Human");
		do {
			System.out.println("Enter 1,2,3, 4 or 5:");
			choice = sc.nextInt();
		} while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5);

		return choice;
	}

	public static void main(String[] args) throws InterruptedException {

		int choice = showMenu();
		switch (choice) {
		case 1:
			machineVsHuman();
			break;
		case 2:
			machineVsOtherMachine();
			break;
		case 3:
			machineVsItself();
			break;
		case 4:
			randomGame();
			break;
		case 5:
			humanAgainstHuman();
			break;
		}
	}
}
