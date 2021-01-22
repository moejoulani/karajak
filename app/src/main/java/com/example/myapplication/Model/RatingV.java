package com.example.myapplication.Model;

public class RatingV {
    private String Comment,U_name,Vendorphone;
    private  int Rating;
    public RatingV()
    {

    }
    public RatingV(String comment,String u_name,String vendorphone,int rating)
    {
        this.Comment=comment;
        this.U_name=u_name;
        this.Vendorphone=vendorphone;
        this.Rating=rating;

    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getU_name() {
        return U_name;
    }

    public void setU_name(String u_name) {
        U_name = u_name;
    }

    public String getVendorphone() {
        return Vendorphone;
    }

    public void setVendorphone(String vendorphone) {
        Vendorphone = vendorphone;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }
}
