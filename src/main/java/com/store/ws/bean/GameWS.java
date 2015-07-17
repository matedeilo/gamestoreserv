package com.store.ws.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameWS")
public class GameWS {

    private int id;

    private String name;

    private String licensorName;

    private String defaultImageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicensorName() {
        return licensorName;
    }

    public void setLicensorName(String licensorName) {
        this.licensorName = licensorName;
    }

    public String getDefaultImageUrl() {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
    }
}
