package com.kshrd.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kshrd.model.Album;
import com.kshrd.service.MusicService;

@RestController
@RequestMapping("/albums")
public class AlbumController {

	
	@Autowired
	private MusicService musicService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Album> getAllAlbums(@PathVariable String id) {
		
		Album album = musicService.getAlbum(id);
		Resource<Album> resource = new Resource<Album>(album);
		resource.add(new Link("http://localhost:8080/albums/" + id, "self"));
		resource.add(new Link("http://localhost:8080/api/albums", "albums"));
		return resource;
	}
	
}
