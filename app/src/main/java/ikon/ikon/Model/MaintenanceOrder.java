package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/16/2018.
 */

public class MaintenanceOrder {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("customers_id")
    @Expose
    private String customersId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sparePart")
    @Expose
    private String sparePart;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("issue")
    @Expose
    private String issue;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("customers_firstname")
    @Expose
    private String customersFirstname;
    @SerializedName("customers_lastname")
    @Expose
    private String customersLastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("customers_telephone")
    @Expose
    private String customersTelephone;
    @SerializedName("products_name")
    @Expose
    private String productsName;
    @SerializedName("products_image")
    @Expose
    private String productsImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomersId() {
        return customersId;
    }

    public void setCustomersId(String customersId) {
        this.customersId = customersId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSparePart() {
        return sparePart;
    }

    public void setSparePart(String sparePart) {
        this.sparePart = sparePart;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomersFirstname() {
        return customersFirstname;
    }

    public void setCustomersFirstname(String customersFirstname) {
        this.customersFirstname = customersFirstname;
    }

    public String getCustomersLastname() {
        return customersLastname;
    }

    public void setCustomersLastname(String customersLastname) {
        this.customersLastname = customersLastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomersTelephone() {
        return customersTelephone;
    }

    public void setCustomersTelephone(String customersTelephone) {
        this.customersTelephone = customersTelephone;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public String getProductsImage() {
        return productsImage;
    }

    public void setProductsImage(String productsImage) {
        this.productsImage = productsImage;
    }

}