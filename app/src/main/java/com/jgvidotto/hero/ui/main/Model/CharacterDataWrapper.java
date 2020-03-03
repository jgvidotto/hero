package com.jgvidotto.hero.ui.main.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class CharacterDataWrapper implements Serializable {

    @SerializedName("code")
    int code;
    @SerializedName("status")
    String status;
    @SerializedName("copyright")
    String copyright;
    @SerializedName("attributionText")
    String attributionText;
    @SerializedName("attributionHTML")
    String attributionHTML;
    @SerializedName("data")
    CharacterDataContainer data;
    @SerializedName("etag")
    String etag;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public CharacterDataContainer getData() {
        return data;
    }

    public void setData(CharacterDataContainer data) {
        this.data = data;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
