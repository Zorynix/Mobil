
package com.example.coursach.Domain;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ListFilm {

    @SerializedName("data")
    @Expose
    private List<FilmItem> data;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public List<FilmItem> getData() {
        return data;
    }

    public void setData(List<FilmItem> data) {
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
