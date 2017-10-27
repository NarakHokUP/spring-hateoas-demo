package com.kshrd.model;

/**
 * Album (available to purchase - hence 'stockLevel' attribute).
 *
 */
public class Album{

	private String id;
	private String title;
	private int stockLevel;
	private Artist artist;

	public Album(String id, String title, Artist artist, int stockLevel) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.stockLevel = stockLevel;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Artist getArtist() {
		return artist;
	}

	public int getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
		this.stockLevel = stockLevel;
	}
}