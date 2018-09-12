package ikon.ikon.Model;

/**
 * Created by ic on 9/9/2018.
 */

public class OrderMaintenence {
    private  String productid;
    private String issueid;
    private String tybe;
    private String color;
    private String note;
    private String address;
    private String  latitude;
    private String longetude;
    private String user_token;

    public OrderMaintenence(String productid, String issueid, String tybe, String color, String note, String address, String latitude, String longetude, String user_token) {
        this.productid = productid;
        this.issueid = issueid;
        this.tybe = tybe;
        this.color = color;
        this.note = note;
        this.address = address;
        this.latitude = latitude;
        this.longetude = longetude;
        this.user_token = user_token;
    }

    public String getProductid() {
        return productid;
    }

    public String getIssueid() {
        return issueid;
    }

    public String getTybe() {
        return tybe;
    }

    public String getColor() {
        return color;
    }

    public String getNote() {
        return note;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongetude() {
        return longetude;
    }

    public String getUser_token() {
        return user_token;
    }
}
