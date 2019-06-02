package com.example.json.Model;

public class JsonModel {

    String type, kind, artistName, CollectionName, trackName, artistViewURL;

    public  JsonModel (){}

    public JsonModel(String type, String kind, String artistName,
                     String collectionName, String trackName, String artistViewURL){
        setType(type);
        setKind(kind);
        setArtistName(artistName);
        setCollectionName(collectionName);
        setTrackName(trackName);
        setArtistViewURL(artistViewURL);

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return CollectionName;
    }

    public void setCollectionName(String collectionName) {
        CollectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistViewURL() {
        return artistViewURL;
    }

    public void setArtistViewURL(String artistViewURL) {
        this.artistViewURL = artistViewURL;
    }
}
