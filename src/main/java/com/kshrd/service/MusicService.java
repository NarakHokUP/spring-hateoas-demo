package com.kshrd.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kshrd.model.Album;
import com.kshrd.model.Artist;

/**
 * Hard coded simulation of a Service + Data Access Layer.
 */
@Service
public class MusicService {

	private Map<String, Album> albums;
	private Map<String, Artist> artists;

	/**
	 * Constructor populates the 'database' (i.e. Maps) of Artists and Albums.
	 */
	public MusicService() {

		albums = new HashMap<String, Album>();
		artists = new HashMap<String, Artist>();
		
		Artist artist1 = new Artist("sovath", "Preap Sovath");
		Artist artist2 = new Artist("reach", "Chhorn Sovannreach");
		artists.put(artist1.getId(), artist1);
		artists.put(artist2.getId(), artist2);
		
		Album album1 = new Album("1", "Solo Album #1", artist1, 2);
		Album album2 = new Album("2", "Solo Album #2", artist1, 3);
		Album album3 = new Album("3", "Solo Album #3", artist1, 0);
		Album album4 = new Album("3", "Solo Album #4", artist2, 1);
		
		albums.put(album1.getId(), album1);
		albums.put(album2.getId(), album2);
		albums.put(album3.getId(), album3);
		albums.put(album4.getId(), album4);
	}

	public Collection<Album> getAllAlbums() {
		return albums.values();
	}

	public Album getAlbum(String id) {
		return albums.get(id);
	}

	public Artist getArtist(String id) {
		return artists.get(id);
	}
}