package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/20/2018.
 */

public class Myordershop {
    @SerializedName("orders_id")
    @Expose
    private String ordersId;
    @SerializedName("customers_name")
    @Expose
    private String customersName;
    @SerializedName("customers_company")
    @Expose
    private Object customersCompany;
    @SerializedName("customers_street_address")
    @Expose
    private String customersStreetAddress;
    @SerializedName("customers_city")
    @Expose
    private String customersCity;
    @SerializedName("customers_postcode")
    @Expose
    private String customersPostcode;
    @SerializedName("customers_state")
    @Expose
    private Object customersState;
    @SerializedName("customers_telephone")
    @Expose
    private String customersTelephone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("order_price")
    @Expose
    private String orderPrice;
    @SerializedName("shipping_method")
    @Expose
    private String shippingMethod;
    @SerializedName("ordered_source")
    @Expose
    private String orderedSource;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getCustomersName() {
        return customersName;
    }

    public void setCustomersName(String customersName) {
        this.customersName = customersName;
    }

    public Object getCustomersCompany() {
        return customersCompany;
    }

    public void setCustomersCompany(Object customersCompany) {
        this.customersCompany = customersCompany;
    }

    public String getCustomersStreetAddress() {
        return customersStreetAddress;
    }

    public void setCustomersStreetAddress(String customersStreetAddress) {
        this.customersStreetAddress = customersStreetAddress;
    }

    public String getCustomersCity() {
        return customersCity;
    }

    public void setCustomersCity(String customersCity) {
        this.customersCity = customersCity;
    }

    public String getCustomersPostcode() {
        return customersPostcode;
    }

    public void setCustomersPostcode(String customersPostcode) {
        this.customersPostcode = customersPostcode;
    }

    public Object getCustomersState() {
        return customersState;
    }

    public void setCustomersState(Object customersState) {
        this.customersState = customersState;
    }

    public String getCustomersTelephone() {
        return customersTelephone;
    }

    public void setCustomersTelephone(String customersTelephone) {
        this.customersTelephone = customersTelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getOrderedSource() {
        return orderedSource;
    }

    public void setOrderedSource(String orderedSource) {
        this.orderedSource = orderedSource;
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

}