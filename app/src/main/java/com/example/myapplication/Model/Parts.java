package com.example.myapplication.Model;

public class Parts {
    private String partname,description,image,pid,data,time,Vendor,VendorPhoneNumber,VendorAddress,CarType,CarSeries,CarModel ;
    private int price;

    public Parts()
    {

    }


    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public String getCarSeries() {
        return CarSeries;
    }

    public void setCarSeries(String carSeries) {
        CarSeries = carSeries;
    }

    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String carModel) {
        CarModel = carModel;
    }

    public Parts(String partname, String description, int price,String image, String pid, String data, String time, String Vendor, String VendorPhoneNumber , String VendorAddress, String CarType, String CarSeries, String CarModel ) {
        this.partname = partname;
        this.description = description;

        this.price = price;
        this.image = image;
        this.pid = pid;
        this.data = data;
        this.time = time;
        this.Vendor=Vendor;
        this.VendorPhoneNumber  =VendorPhoneNumber  ;
        this.VendorAddress = VendorAddress;
        this.CarType =CarType;
        this.CarSeries =CarSeries;
        this.CarModel =CarModel;
    }

    public String getpartname() {
        return partname;
    }

    public void setpartname(String partname) {
        this.partname = partname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }
    public void setVendor(String Vendor)
    {
        this.Vendor=Vendor;
    }
    public String getVendor()
    {
        return Vendor;
    }
    public void setVendorPhoneNumber  (String VendorPhoneNumber  )
    {
        this.VendorPhoneNumber  =VendorPhoneNumber  ;
    }
    public String getVendorPhoneNumber  ()
    {
        return VendorPhoneNumber  ;
    }
    public void setVendorAddress(String VendorAddress)
    {
        this.VendorAddress= VendorAddress;
    }
    public String getVendorAddress()
    {
        return VendorAddress;
    }
}
