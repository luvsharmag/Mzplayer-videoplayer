package com.example.mzplayer;

public  class videofiles {
    private String id;
    private String path;
    private String title;
    private String filename;
    private String size;
    private String dateAdded;
    private String Duration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public videofiles(String id, String path, String title, String filename, String size, String dateAdded, String duration) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.filename = filename;
        this.size = size;
        this.dateAdded = dateAdded;
        Duration = duration;
    }
}
