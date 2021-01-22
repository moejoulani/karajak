package com.example.myapplication.Model;

public class Review  {
    private String RnuserComment,Rtime,RnuserName;
    private float RnuserRate;
    public Review()
    {

    }
    public Review(String rnuserComment,String rtime,String rnuserName,float rnuserRate)
    {
        this.RnuserComment=rnuserComment;
        this.RnuserName=rnuserName;
        this.RnuserRate=rnuserRate;
        this.Rtime=rtime;
    }

    public String getRnuserComment() {
        return RnuserComment;
    }

    public void setRnuserComment(String rnuserComment) {
        RnuserComment = rnuserComment;
    }

    public String getRtime() {
        return Rtime;
    }

    public void setRtime(String rtime) {
        Rtime = rtime;
    }

    public String getRnuserName() {
        return RnuserName;
    }

    public void setRnuserName(String rnuserName) {
        RnuserName = rnuserName;
    }

    public float getRnuserRate() {
        return RnuserRate;
    }

    public void setRnuserRate(float rnuserRate) {
        RnuserRate = rnuserRate;
    }
}
