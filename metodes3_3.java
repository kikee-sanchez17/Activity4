package metodes3;

import java.util.Scanner;
/**
 * @author Enric Sanchez Montoya
 * @date:01/02/2023
 */
public class metodes3_3 {
	/*
	 * (*) Escriu un programa per jugar al tres en ratlla. Dos jugadors, per torns,
	 * posen sobre un tauler de 3x3 un dels seus símbols, O o X.
	 * 
	 * Quan un jugador ha col·locat una fitxa de manera que té tres fitxes alineades
	 * verticalment, horitzontal o diagonal guanya la partida.
	 * 
	 * Si s'acaben les caselles buides i no s'ha arribat a cap alineament la partida
	 * acaba en empat
	 * 
	 */

	public static void main(String[] args) {
		String array[][] = new String[3][3];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				array[i][j] = " ";
			}
		}
		Scanner read = new Scanner(System.in);
		System.out.println("Welcome to tic tac toe game! \nHere's the table ");
		board(array);
		byte empat = 0, contX = 0, contO = 0;
		boolean jugador = true;
		String player;
		while (true) {
			try {
				while (empat != 9) {
					if (jugador == true) {
						player = "O";
						contO++;
					} else {
						player = "X";
					}
					System.out.print("Write the row's position that you want (0,1,2) player " + player + ":  ");
					byte positionx = read.nextByte();
					System.out.print("Write the column's position that you want (0,1,2) player " + player + ": ");
					byte positiony = read.nextByte();
					while (!metodes.RangByte(positionx, 0, 2) || !metodes.RangByte(positiony, 0, 2)) {
						System.out.println("Out of the rang");
						System.out.print("Write the row that you want (0,1,2) player " + player + ":  ");
						positionx = read.nextByte();
						System.out.print("Write the column that you want (0,1,2) player " + player + ": ");
						positiony = read.nextByte();
					}
					fill(positionx, positiony, player, array, read, contX, contO);
					empat++;
					jugador = !jugador;
				}
				System.out.print("The game has finished with draw");
				System.exit(0);
			} catch (Exception ex) {
				System.out.println("Introdueix el format correcte");
				read.next();

			}

		}

	}

	public static void board(String array[][]) {
		System.out.println("-------------");
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				System.out.print("| " + array[i][j] + " ");
				if (j == 2) {
					System.out.println("|");
				}
			}
			System.out.println("-------------");
		}

	}

	/**
	 * This method is used to know if the position introduced is correct
	 * 
	 * @param array  The array used
	 * @param posx   The position of row
	 * @param posy   The position of column
	 * @param read   The scanner used to ask again the position
	 * @param player To know which player is playing
	 * @return
	 */
	public static boolean iscorrect(String array[][], byte posx, byte posy, Scanner read, String player) {
		boolean correcte = true;
		while (!metodes.RangByte(posx, 0, 2) || !metodes.RangByte(posy, 0, 2)) {
			System.out.println("Out of the rang");
			System.out.print("Write the row that you want (0,1,2) player " + player + ":  ");
			posx = read.nextByte();
			System.out.print("Write the column that you want (0,1,2) player " + player + ": ");
			posy = read.nextByte();
		}
		if (array[posx][posy] != " ") {
			correcte = false;

		} else if (array[posx][posy] == " ") {
			correcte = true;
		}
		return correcte;
	}

	/**
	 * This method is used to fill the board.
	 * 
	 * @param posx   To know row position
	 * @param posy   To know column position
	 * @param player To know which player is playing at that round
	 * @param array  The board
	 * @param read   If there's problem with the position introduced, we will need
	 *               the scanner to ask the user again
	 * @param contX  A counter to know how many plays the player X has needed to
	 *               win.
	 * @param contO  A counter to know how many plays the player O has needed to
	 *               win.
	 */
	public static void fill(byte posx, byte posy, String player, String array[][], Scanner read, byte contX,
			byte contO) {

		while (!iscorrect(array, posx, posy, read, player)) {
			System.out.println("Please choose the correct option");
			System.out.print("Write the row that you want (0,1,2) player " + player + ":  ");
			posx = read.nextByte();
			System.out.print("Write the column that you want (0,1,2) player " + player + ": ");
			posy = read.nextByte();
		}

		array[posx][posy] = player;
		board(array);

		if (winner(array) == true) {
			if (player == "X") {
				System.out.print("The player " + player + " have won using " + contX + " plays");
			} else {
				System.out.print("The player " + player + " have won using " + contO + " plays");
			}
			System.exit(0);
		}

	}

	/**
	 * This method is used to know when one of the player wins the game. This method
	 * also has all the conditions that must accomplish to win the game.
	 * 
	 * @param array The board to check if the positions are fill to win the game.
	 * @return
	 */
	public static boolean winner(String array[][]) {
		boolean guanya = false;
		int contX = 0;
		int contO = 0;
		for (int j = 0; j < 3; j++) {
			contX = 0;
			contO = 0;
			for (int i = 0; i < 3; i++) {
				if (array[j][i] == "X") {
					contX++;
				} else if (array[j][i] == "O") {
					contO++;
				}
			}
			if (contX == 3 || contO == 3) {
				guanya = true;
			}

		}
		for (int j = 0; j < 3; j++) {
			contX = 0;
			contO = 0;
			for (int i = 0; i < 3; i++) {
				if (array[i][j] == "X") {
					contX++;
				} else if (array[i][j] == "O") {
					contO++;
				}
			}
			if (contX == 3 || contO == 3) {
				guanya = true;
			}

		}
		if (array[0][0] == "X" && array[1][1] == "X" && array[2][2] == "X"
				|| array[0][0] == "O" && array[1][1] == "O" && array[2][2] == "O") {
			guanya = true;
		} else if (array[0][2] == "X" && array[1][1] == "X" && array[2][0] == "X"
				|| array[0][2] == "O" && array[1][1] == "O" && array[2][0] == "O") {

			guanya = true;
		}
		return guanya;
	}

}
