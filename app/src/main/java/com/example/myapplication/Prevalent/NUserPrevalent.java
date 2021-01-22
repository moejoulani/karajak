package com.example.myapplication.Prevalent;

import com.example.myapplication.Model.NUsers;


public class NUserPrevalent  {
    public static NUsers currentOnlineNUsers;


    public static final String NUserPhoneKey="NUserPhone";
    public static final String NUserPasswordKey="NUserPassword";
    public static boolean isEmpty()
    {
        if(currentOnlineNUsers==null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
