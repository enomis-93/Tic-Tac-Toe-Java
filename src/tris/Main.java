package tris;

import java.util.Scanner;

public class Main {

	public static int numeroTurni = 0;

	public static void main(String[] args) {
		int numeroTurni = Main.numeroTurni;

		String[][] grid = new String[3][3];

		boolean gameActive = true;
		boolean continuePlaying = false;
		int maxTurni = 9;

		do {
			do {
				// Popolo la tabella con spazi vuoti per evitare di avere valori null
				for (int x = 0; x < grid.length; x++) {
					for (int y = 0; y < grid.length; y++) {
						grid[x][y] = " ";
					}
				}
			Scanner scan = new Scanner(System.in);
			System.out.println("Benvenuto nel gioco del tris !");
			System.out.println("Giocatore 1 inserisci il tuo nome:");
			String player1 = scan.nextLine();
			System.out.println("Giocatore 2 inserisci il tuo nome:");
			String player2 = scan.nextLine();	

				while (Main.numeroTurni < maxTurni || gameActive == false) {
					if (Main.numeroTurni % 2 == 0) {
						playerChoice(grid, player1, "X");
					} else if (Main.numeroTurni % 2 != 0) {
						playerChoice(grid, player2, "O");
					}

					if (checkWinner(grid, "X")) {
						System.out.println(player1 + " Vince !");
						printGrid(grid);
						gameActive = false;
						break;
					}

					if (checkWinner(grid, "O")) {
						System.out.println(player2 + " Vince !");
						printGrid(grid);
						gameActive = false;
						break;
					}
					
					if(Main.numeroTurni == 9 && (!checkWinner(grid, "O") || !checkWinner(grid, "X")) ) {
						System.out.println("La partita finisce in pareggio !");
						printGrid(grid);
						gameActive = false;
						break;
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
		// Check se una cella ? gi? stata riempita
		boolean isCellEmpty = true;
		do {
			Scanner scan = new Scanner(System.in);
			System.out.println("********************");
			System.out.print(player + ", fai la tua mossa!\n");
			printGrid(grid);
			System.out.println();

			System.out.print("Inserisci coordinate (" + symbol + "), nel formato (x,y):");
			String inputs = scan.next();
			String[] coordinates = inputs.split(",");
			int x = Integer.parseInt(coordinates[0]);
			int y = Integer.parseInt(coordinates[1]);
			

			
			if (x <= grid.length -1 && y <= grid.length -1) {
				// Verifica che la cella non sia gi? occupata, prima di scrivere il valore
				if (grid[x][y] != " ") {
					isCellEmpty = false;
					System.out.print("La cella contiene gi? un simbolo, ripetere l'inserimento");
					continue;
				} else {
					grid[x][y] = symbol;
					Main.numeroTurni++;
					break;
				}
			}else {
				System.out.println("Coordinate non valide,\nReinserisci le coordinate!");
				break;
			}
		} while (isCellEmpty);

		System.out.println();
	}

	public static boolean checkWinner(String[][] grid, String symbol) {
		if (rowFull(grid, symbol)) {
			System.out.println("Riga di " + symbol);
			return true;
		}

		if (columnFull(grid, symbol)) {
			System.out.println("Colonna di " + symbol);
			return true;
		}

		if (firstDiagonal(grid, symbol)) {
			System.out.println("Diagonale di " + symbol);
			return true;
		}

		if (secondDiagonal(grid, symbol)) {
			System.out.println("Diagonale di " + symbol);
			return true;
		}

		return false;

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
