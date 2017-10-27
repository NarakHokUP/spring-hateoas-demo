package com.kshrd.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kshrd.model.Album;
import com.kshrd.service.MusicService;

@RestController
@RequestMapping("/api")
public class AlbumRestController {

	@Autowired
	private MusicService musicService;

	@RequestMapping(value = "/albums", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Resource<Album>> getAllAlbums() {

		Collection<Album> albums = musicService.getAllAlbums();

		List<Resource<Album>> resources = new ArrayList<Resource<Album>>();
		for (Album album : albums) {
			resources.add(getAlbumResource(album));
		}
		return resources;
	}

	@RequestMapping(value = "/album/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Album> getAlbum(@PathVariable(value = "id") String id) {
		Album album = musicService.getAlbum(id);
		return getAlbumResource(album);
	}

	@RequestMapping(value = "/album/{id}/purchase", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Album> purchaseAlbum(@PathVariable(value = "id") String id) {
		Album alb = musicService.getAlbum(id);
		alb.setStockLevel(alb.getStockLevel() - 1);
		Resource<Album> resource = new Resource<Album>(alb);
		resource.add(linkTo(methodOn(AlbumRestController.class).purchaseAlbum(id)).withSelfRel());
		return resource;
	}

	private Resource<Album> getAlbumResource(Album album) {

		Resource<Album> resource = new Resource<Album>(album);
		
		//link to album
		resource.add(linkTo(methodOn(AlbumRestController.class).getAlbum(album.getId())).withSelfRel());
		
		//link to artist
		resource.add(linkTo(methodOn(ArtistRestController.class).getArtist(album.getArtist().getId())).withRel("artist"));
		
		if (album.getStockLevel() > 0) {
			// Option to purchase Album
			resource.add(linkTo(methodOn(AlbumRestController.class).purchaseAlbum(album.getId())).withRel("album.purchase"));
		}
		return resource;
	}
}
