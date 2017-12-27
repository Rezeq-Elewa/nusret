package com.example.rezeq.nusret.utility;

import android.content.Intent;

import com.example.rezeq.nusret.R;
import com.example.rezeq.nusret.activities.CongratulationActivity;
import com.example.rezeq.nusret.models.Profile;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Util {

    private static Util instance;

    private Util() {
    }

    public static Util getInstance(){
        if(instance == null)
            instance = new Util();
        return instance;
    }

    public int itemInCartCount(){
        return 2;
    }

    public String getAccessToken(){
        String accessToken;
        accessToken = "";
        return accessToken;
    }

    public String getDeviceLanguage(){

        return " ";
    }

    public boolean isLoggedIn(){
        return true;
    }

    public Profile getUserProfile(){
        return new Profile();
    }
}
