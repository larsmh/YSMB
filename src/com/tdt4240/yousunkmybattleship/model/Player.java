package com.tdt4240.yousunkmybattleship.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.tdt4240.yousunkmybattleship.Constants;

/**
 * Contains the relevant methods and attributes linked to the players.
 * 
 * Each player is represented by an object of this class and has a name, ships,
 * a board with his ships, a number of bombs per turn, etc.
 * 
 */

public class Player {
	private String name;
	private int bombsPerTurn, totalHits;
	private boolean shipsPlaced;
	protected PropertyChangeSupport pcs;

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

	/**
	 * List of the player's ships.
	 * 
	 */
	private Ship[] ships;

	/**
	 * Constructor of the Player class.
	 * 
	 * @param name
	 *            the name of the new player
	 * 
	 */
	public Player(String name) {
		this.name = name;
		shipsRemaining = Constants.NUMBER_SHIPS;
		shipsPlaced = false;
		bombsPerTurn = Constants.NUMBER_BOMBS;
		pcs = new PropertyChangeSupport(this);

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

	/**
	 * Creates the player's ships: one ship of each type, and place them on the
	 * board to a default location.
	 * 
	 */
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

	/**
	 * Getter of the property name
	 * 
	 * @return Returns the player's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Notifies where a new bomb has been dropped on the drops list
	 * 
	 * @param i
	 *            the x-coordinate of the dropped bomb
	 * @param j
	 *            the y-coordinate of the dropped bomb
	 */
	public void bombDropped(int i, int j) {
		boolean old = drops[i][j];
		drops[i][j] = true;
		
		pcs.firePropertyChange("BOMB_DROPPED", old, drops[i][j]);
	}

	/**
	 * Saves the ships' place on the board list
	 * 
	 * @param i
	 *            the x-coordinate of the moved ship
	 * @param j
	 *            the y-coordinate of the moved ship
	 * @param ship
	 *            the moved ship
	 */
	public void shipPlaced(int i, int j, Ship ship) {
		board[i][j] = ship.getType().getID();
		ship.placeShip(i, j);
	}

	/**
	 * Updates the number of remaining ships when one of them has been sunk
	 * 
	 */
	public void shipSunk() {
		shipsRemaining -= 1;
	}

	/**
	 * Getter of the property ships
	 * 
	 * @return Returns the player's ships
	 */
	public Ship[] getShips() {
		return ships;
	}

	public boolean registerDrop(int x, int y) {
		if (!drops[y][x]) {
			return (drops[y][x] = true);
		}
		return false;
	}

	/**
	 * Getter of the property board
	 * 
	 * @return Returns the player's board
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * Getter of the property drops
	 * 
	 * @return Returns the drops
	 */
	public boolean[][] getDrops() {
		return drops;
	}

	public void setReady() {
		shipsPlaced = true;
		for (Ship s : ships) {
			if (s.isVertical()) {
				for (int j = 0; j < s.getType().getSize(); j++)
					board[s.getPosY() + j][s.getPosX()] = s.getType().getID();

			} else {
				for (int j = 0; j < s.getType().getSize(); j++)
					board[s.getPosY()][s.getPosX() + j] = s.getType().getID();
			}
		}
	}

	public boolean isReady() {
		return shipsPlaced;
	}

	/**
	 * Getter of the property bombsPerTurn
	 * 
	 * @return Returns the number of bombs per turn
	 */
	public int getBombsPerTurn() {
		return bombsPerTurn;
	}

	public void reduceBombsPerTurn() {
		if (bombsPerTurn != 1)
			bombsPerTurn--;
	}

	public boolean shipIsHit(int x, int y) {
		if (board[y][x] == -1)
			return false;
		if (ships[board[y][x]].shipHit()) {
			Constants.p.reduceBombsPerTurn();
		}
		return true;
	}

	public void changePlayerState() {

	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    protected void firePropertyChangeEvent(String propertyName, Object oldValue, Object newValue) {
        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }
}
