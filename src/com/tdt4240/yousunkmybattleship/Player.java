package com.tdt4240.yousunkmybattleship;

import com.tdt4240.yousunkmybattleship.state.GameOverState;

public class Player {
	private String name;
	private int bombsPerTurn, totalHits;
	private boolean shipsPlaced;

	/**
	 * The number of non sunk ships of the player
	 * 
	 */
	private int shipsRemaining;

	/**
	 * Represents the player grid. It is the sprite integer of the ship which is
	 * on the case. -1 means there is no ship on the case
	 * 
	 */
	private int board[][];

	/**
	 * Represents the grid. True means the player dropped a bomb on the case.
	 * 
	 */
	private boolean drops[][];

	Ship[] ships;

	public Player(String name) {
		this.name = name;
		shipsRemaining = Constants.NUMBER_SHIPS;
		shipsPlaced = false;
		bombsPerTurn = 5;

		drops = new boolean[Constants.GRID_HEIGHT][Constants.GRID_WIDTH];
		board = new int[Constants.GRID_HEIGHT][Constants.GRID_WIDTH];
		for (int i = 0; i < Constants.GRID_HEIGHT; i++) {
			for (int j = 0; j < Constants.GRID_WIDTH; j++) {
				board[i][j] = -1;
				drops[i][j] = false;
			}
		}

		createShips();
	}

	public String getName() {
		return this.name;
	}

	public void bombDropped(int i, int j) {
		drops[i][j] = true;
	}

	public void shipPlaced(int i, int j, Ship ship) {
		board[i][j] = ship.getType().getSprite();

		ship.placeShip(i, j);
	}

	public void shipSunk() {
		shipsRemaining -= 1;

		if (shipsRemaining == 0) {
			Constants.game.pushState(new GameOverState(this));
		}
	}

	private void createShips() {
		ships = new Ship[Constants.NUMBER_SHIPS];
		ships[0] = new Ship(ShipType.AC_Carrier);
		ships[1] = new Ship(ShipType.Battleship);
		ships[2] = new Ship(ShipType.Submarine);
		ships[3] = new Ship(ShipType.Destroyer);
		ships[4] = new Ship(ShipType.Boat);

		for (int i = 0; i < ships.length; i++) {
			ships[i].placeShip(0, i * 2);
		}
	}

	public Ship[] getShips() {
		return ships;
	}

	public boolean registerDrop(int x, int y) {
		if (!drops[y][x]) {
			return (drops[y][x] = true);
		}
		return false;
	}

	public int[][] getBoard() {
		return board;
	}

	public boolean[][] getDrops() {
		return drops;
	}

	public void setReady() {
		shipsPlaced = true;
	}

	public boolean isReady() {
		return shipsPlaced;
	}

	public int getBombsPerTurn() {
		return bombsPerTurn;
	}

	public void reduceBombsPerTurn() {
		if (bombsPerTurn != 1)
			bombsPerTurn--;
	}

	public void changePlayerState() {

	}
}
