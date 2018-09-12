package ikon.ikon.Model;

/**
 * Created by ic on 9/12/2018.
 */

public class OrderShop {

    private String address;
    private String latitude;
    private String longetude;
    private String user_token;
    private String lang;
    private String phone;
    private String totalprice;
    private String products;
    private String postcode;
    private String city;
    private String country;

    public OrderShop(String address, String latitude, String longetude, String user_token, String lang, String phone, String totalprice, String products, String postcode, String city, String country) {
        this.address = address;
        this.latitude = latitude;
        this.longetude = longetude;
        this.user_token = user_token;
        this.lang = lang;
        this.phone = phone;
        this.totalprice = totalprice;
        this.products = products;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongetude() {
        return longetude;
    }

    public void setLongetude(String longetude) {
        this.longetude = longetude;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
