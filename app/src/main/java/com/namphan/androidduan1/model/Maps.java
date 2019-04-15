package com.namphan.androidduan1.model;

public class Maps {
    private Double longtitui;
    private Double latitui;

    public Maps() {
    }

    public Maps(Double longtitui, Double latitui) {
        this.longtitui = longtitui;
        this.latitui = latitui;
    }

    public Double getLongtitui() {
        return longtitui;
    }

    public void setLongtitui(Double longtitui) {
        this.longtitui = longtitui;
    }

    public Double getLatitui() {
        return latitui;
    }

    public void setLatitui(Double latitui) {
        this.latitui = latitui;
    }
}
