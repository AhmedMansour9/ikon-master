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
    private String price;
    private String phoneid;
    private String phone;

    public OrderMaintenence(String phone,String phoneid,String productid, String issueid, String tybe, String color, String note, String address, String latitude, String longetude, String user_token,String price) {
      this.phone=phone;
      this.phoneid=phoneid;
       this.productid = productid;
        this.issueid = issueid;
        this.tybe = tybe;
        this.color = color;
        this.note = note;
        this.address = address;
        this.latitude = latitude;
        this.longetude = longetude;
        this.user_token = user_token;
        this.price=price;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoneid() {
        return phoneid;
    }

    public String getPrice() {
        return price;
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
