package tris;

import java.util.Scanner;

public class Main {

	static int numeroTurni = 0;

	public static void main(String[] args) {

		String[][] grid = new String[3][3];

		boolean gameActive = true;
		boolean continuePlaying = false;
		int maxTurni = 9;

		do {
			do {
				for (int x = 0; x < grid.length; x++) {
					for (int y = 0; y < grid.length; y++) {
						grid[x][y] = " ";
					}
				}
//			Scanner scan = new Scanner(System.in);
//			System.out.println("Benvenuto nel gioco del tris !");
//			System.out.println("Giocatore 1 inserisci il tuo nome:");
//			String player1 = scan.nextLine();
//			System.out.println("Giocatore 2 inserisci il tuo nome:");
//			String player2 = scan.nextLine();	

				for (int i = 0; i < maxTurni || gameActive == false; i++) {
					if (i % 2 == 0) {
						playerChoice(grid, "Giocatore 1", "X");
					} else if (i % 2 != 0) {
						playerChoice(grid, "Giocatore 2", "O");
					}
//					if (isPlayerWin(grid, "X")) {
//						System.out.println(" di X, Giocatore 1 ha vinto");
//						printGrid(grid);
//						gameActive = false;
//						break;
//					}
//					if (isPlayerWin(grid, "O")) {
//						System.out.println(" di O, Giocatore 2 ha vinto");
//						printGrid(grid);
//						gameActive = false;
//						break;
//					}
					if (rowFull(grid, "X")) {
						System.out.println("Giocatore 1 ha vinto");
						printGrid(grid);
						gameActive = false;
						break;
					}

					if (columnFull(grid, "X")) {
						System.out.println("Giocatore 1 ha vinto");
						printGrid(grid);
						gameActive = false;
						break;
					}
					
					if (firstDiagonal(grid, "X")) {
						System.out.println("Diagonale Giocatore 1 ha vinto");
						printGrid(grid);
						gameActive = false;
						break;
					}
					
					if (secondDiagonal(grid, "X")) {
						System.out.println("Diagonale Giocatore 1 ha vinto");
						printGrid(grid);
						gameActive = false;
						break;
					}

				}

			} while (gameActive);

			System.out.print("\n\nPartita conclusa !\n");

			Scanner scan = new Scanner(System.in);
			System.out.print("Vuoi giocare un'altra partita?\n" + "[1] Si\n" + "[2] No\n");
			System.out.println();

			int playAgain = scan.nextInt();
			if (playAgain == 1) {
				continuePlaying = true;
			} else {
				continuePlaying = false;
			}

		} while (continuePlaying);

	}

	public static String drawSymbol(String symbol) {
		if (symbol == null) {
			return "_";
		}

		return symbol;
	}

	public static void printGrid(String[][] grid) {
		for (int x = 0; x < grid.length; x++) {
			if (x >= 1) {
				System.out.println();
				System.out.println("--------------");
			}
			for (int y = 0; y < grid.length; y++) {
				if (y < grid.length - 1) {
					System.out.print(drawSymbol(grid[x][y]) + "  |  ");
				} else {
					System.out.print(drawSymbol(grid[x][y]) + " ");
				}
//				System.out.print(x + "," + y + "|");
			}
		}
	}

	public static void playerChoice(String[][] grid, String player, String symbol) {
		// Check se una cella è già stata riempita
		boolean isCellEmpty = true;
		do {
			Scanner scan = new Scanner(System.in);
			System.out.println("********************");
			System.out.print(player + ", fai la tua mossa!\n");
			printGrid(grid);
			System.out.println();

			System.out.print("Inserisci coordinate (" + symbol + "):");
			String inputs = scan.nextLine();
			String[] coordinates = inputs.split(",");
			int x = Integer.parseInt(coordinates[0]);
			int y = Integer.parseInt(coordinates[1]);

			// Verifica che la cella non sia già occupata, prima di scrivere il valore
			if (grid[x][y] != " ") {
				isCellEmpty = false;
				System.out.print("La cella contiene già un simbolo, ripetere l'inserimento");
				continue;
			} else {
				grid[x][y] = symbol;
				numeroTurni++;
				break;
			}
		} while (isCellEmpty);

		System.out.println();
	}

	public static void checkWinner(String[][] grid, String symbol) {

//		for (int x = 0; x < grid.length; x++) {
//			for (int y = 0; y < grid.length; y++) {
//				// Caso righe contententi lo stesso simbolo
//				if ((grid[x][0]).equals(symbol) && (grid[x][1]).equals(symbol) && (grid[x][2]).equals(symbol)) {
//					System.out.print("Linea");
//					return true;
//				}
//				// Caso colonne contenetni lo stesso simbolo
//				if ((grid[0][y]).equals(symbol) && (grid[1][y]).equals(symbol) && (grid[2][y]).equals(symbol)) {
//					System.out.print("Colonna");
//					return true;
//				}
//			}
//		}
//
//		if ((grid[0][0]).equals(symbol) && (grid[1][1]).equals(symbol) && (grid[2][2]).equals(symbol)) {
//			System.out.print("Diagonale");
//			return true;
//		}
//
//		if ((grid[0][2]).equals(symbol) && (grid[1][0]).equals(symbol) && (grid[2][0]).equals(symbol)) {
//			System.out.print("Diagonale");
//			return true;
//		}
//		return false;
	}

	public static boolean rowFull(String[][] grid, String symbol) {
		int riga = 0;
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				if ((grid[x][y]).equals(symbol)) {
//					System.out.print("Si");
					riga++;
					if (riga == 3) {
						return true;
					}
					continue;
				} else {
//					System.out.print("No");
//					System.out.println();
					riga = 0;
					break;
				}
			}
		}
		return false;
	}

	public static boolean columnFull(String[][] grid, String symbol) {
		int column = 0;
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				if ((grid[y][x]).equals(symbol)) {
//						System.out.print("Si");
					column++;
					if (column == 3) {
						return true;
					}
					continue;
				} else {
//						System.out.print("No");
//						System.out.println();
					column = 0;
					break;
				}
			}
		}
		return false;
	}

	public static boolean firstDiagonal(String[][] grid, String symbol) {
		int diagonal = 0;
		for (int x = 0; x < grid.length; x++) {
			if ((grid[x][x]).equals(symbol)) {
				diagonal++;
				if (diagonal == 3) {
					return true;
				}
				continue;
			} else {
				diagonal = 0;
				break;
			}
		}
		return false;
	}
	
	public static boolean secondDiagonal(String[][] grid, String symbol) {
		int diagonal = 0;
		for (int x = 0; x < grid.length; x++) {
			if ((grid[x][grid.length - 1 - x]).equals(symbol)) {
				diagonal++;
				if (diagonal == 3) {
					return true;
				}
				continue;
			} else {
				diagonal = 0;
				break;
			}
		}
		return false;
	}

}
