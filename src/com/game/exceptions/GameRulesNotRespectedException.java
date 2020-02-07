package com.game.exceptions;

/**
 * Exception thrown if the rules of the game are violated
 * 
 * @author Group_cipher
 *
 */
public class GameRulesNotRespectedException extends Exception {

	public GameRulesNotRespectedException(String message) {
		super(message);
	}

}
