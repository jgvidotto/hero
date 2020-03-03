package com.jgvidotto.hero.ui.main.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image implements Serializable {

    @SerializedName("path")
    String path;
    @SerializedName("extension")
    String extension;

    public String getPath() {
        return path + "/portrait_xlarge.jpg";
    }

    public String getPathBig() {
        return path + "/standard_amazing.jpg";
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
