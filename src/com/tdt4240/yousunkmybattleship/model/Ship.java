package com.tdt4240.yousunkmybattleship.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Constants.DirectionType;

/**
 * Contains the relevant methods and attributes linked to the ships.
 * 
 * Each ship has a type (from a closed list), a position on the player's grid, a
 * direction, etc.
 * 
 */

public class Ship {
	protected PropertyChangeSupport pcs;
	private ShipType type;

	/**
	 * The position of the top left square of the ship in the grid.
	 * 
	 */
	private int posX, posY;

	/**
	 * Number of the ship's block hit.
	 * 
	 */
	private int hits;

	/**
	 * Indicates if the ship is horizontal or vertical.
	 * 
	 */
	private DirectionType direction;

	/**
	 * Indicates if the ship is placed on the board.
	 * 
	 */
	private boolean placedOnBoard;

	/**
	 * Indicates if the ship is sunk.
	 * 
	 */
	private boolean shipSunk;

	public Ship(ShipType type) {
		this.type = type;
		hits = 0;
		shipSunk = false;
		direction = DirectionType.HORIZONTAL;
		placedOnBoard = false;
		posX = 0;
		posY = 0;
		
		pcs = new PropertyChangeSupport(this);
	}

	public boolean isSunk() {
		return this.shipSunk;
	}

	public boolean shipHit() {
		hits++;
		if (hits == type.getSize()) {
			Constants.getOther().shipSunk();
			return (shipSunk = true);
		}
		return false;
	}

	public void changeDirection() {
		int adjustment;
		DirectionType oldDirection;
		
		if (isVertical()) {
			oldDirection = DirectionType.VERTICAL;
			direction = DirectionType.HORIZONTAL;
			adjustment = posX + getType().getSize() - Constants.GRID_WIDTH;
			if (adjustment > 0)
				posX -= adjustment;
		} else {
			direction = DirectionType.VERTICAL;
			oldDirection = DirectionType.HORIZONTAL;
			adjustment = posY + getType().getSize() - Constants.GRID_HEIGHT;
			if (adjustment > 0)
				posY -= adjustment;
		}
		pcs.firePropertyChange(Constants.CHANGE_DIRECTION, oldDirection, direction);
	}

	public boolean isVertical() {
		return (direction == DirectionType.VERTICAL);
	}

	public void placeShip(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		placedOnBoard = true;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public ShipType getType() {
		return type;
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
