package com.kshrd.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


import com.kshrd.model.Artist;
import com.kshrd.service.MusicService;

@RestController
@RequestMapping("/api")
public class ArtistRestController {

	@Autowired
	private MusicService musicService;

	@RequestMapping(value = "/artist/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Artist> getArtist(@PathVariable(value = "id") String id) {
		Artist alb = musicService.getArtist(id);
		
		Resource<Artist> resource = new Resource<Artist>(alb);
		
		resource.add(linkTo(methodOn(ArtistRestController.class).getArtist(id)).withSelfRel());
		resource.add(linkTo(methodOn(AlbumRestController.class).getAllAlbums()).withRel("album"));
		
		return resource;
	}
}
