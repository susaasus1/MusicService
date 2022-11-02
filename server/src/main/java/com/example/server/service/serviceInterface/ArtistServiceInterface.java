package com.example.server.service.serviceInterface;

import com.example.server.entity.Organisation;

import java.util.List;


public interface ArtistServiceInterface {
    void addArtist(String description, String login, String name);

    List<Organisation> getAllOrganisation();

    void addAlbum(Long userId, String name, String description, String link);

    void setOrganisation(Long userId, Long orgId);

    void quitFromOrganisation(Long userId);

    void addSong(Long userId, String name, Long duration, String albumName, String genre, String link);

}
