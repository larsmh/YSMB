package com.tdt4240.yousunkmybattleship;

public class Player {
	private String name;
	private PlayerState playerState;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void changePlayerState() {
		
	}
	
	public PlayerState getPlayerState() {
		return playerState;
	}
}
