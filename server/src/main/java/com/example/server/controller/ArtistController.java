package com.example.server.controller;

import com.example.server.request.*;
import com.example.server.response.MessageResponse;
import com.example.server.service.ArtistService;
import com.example.server.service.serviceInterface.ArtistServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artist")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArtistController {

    @Autowired
    private ArtistServiceInterface artistService;

    @PostMapping("/addArtist")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addArtist(@RequestBody ArtistRequest artistRequest) {
        artistService.addArtist(artistRequest.getDescription(), artistRequest.getLogin(), artistRequest.getName());
        return ResponseEntity.ok(new MessageResponse("Артист добавлен"));
    }

    @GetMapping("/getOrganisation")
    @PreAuthorize("hasRole('ARTIST') or hasRole('ADMIN')" )
    public ResponseEntity<?> getOrganisation() {
        return ResponseEntity.ok().body(artistService.getAllOrganisation());
    }

    @PostMapping("/addAlbum")
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<?> addAlbum(@RequestBody AlbumCreateRequest albumCreateRequest){
        artistService.addAlbum(Long.parseLong(albumCreateRequest.getUserId()),albumCreateRequest.getName(),albumCreateRequest.getDescription());
        return ResponseEntity.ok(new MessageResponse("Альбом добавлен!"));
    }
    @PostMapping("/setOragnisationToArtist")
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<?> setOrg(@RequestBody SetOrganisationRequest setOrganisationRequest){
        artistService.setOrganisation(Long.parseLong(setOrganisationRequest.getUserId()),Long.parseLong(setOrganisationRequest.getOrgId()));
        return ResponseEntity.ok(new MessageResponse("Вы успешно вступили в организацию!"));
    }
    @PostMapping("/quitFromOrganisation")
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<?> quitFromOrg(@RequestBody QuitFromOrgRequest quitFromOrgRequest){
        artistService.quitFromOrganisation(Long.parseLong(quitFromOrgRequest.getUserId()));
        return ResponseEntity.ok(new MessageResponse("Вы вышли из организации!"));
    }

    @PostMapping("/addSong")
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<?> addSong(@RequestBody SongCreateRequest songCreateRequest){
        artistService.addSong(songCreateRequest.getUserId(),songCreateRequest.getName(),songCreateRequest.getDuration(), songCreateRequest.getAlbumName(), songCreateRequest.getGenre(), songCreateRequest.getLink());
        return ResponseEntity.ok(new MessageResponse("Песня добавлена!"));
    }
}
