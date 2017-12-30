package com.example.rezeq.nusret.utility;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.rezeq.nusret.models.Profile;

import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Util {

    private Context context;

    public Util(Context context) {
        this.context = context;
    }


    public int itemInCartCount(){

        return 2;
    }

    public String getAccessToken(){
        return getUserProfile().getAccessToken();
    }

    public String getDeviceLanguage(){
        return Locale.getDefault().getLanguage();
    }

    public boolean isLoggedIn(){
        return true;
    }

    public Profile getUserProfile(){
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name("nusret.realm").build();
        Realm.setDefaultConfiguration(config);
        final Realm realm = Realm.getDefaultInstance();
        return realm.where(Profile.class).findFirst();
    }

    public void saveUserProfile(final Profile profile){
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name("nusret.realm").build();
        Realm.setDefaultConfiguration(config);
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.delete(Profile.class);
                realm.copyToRealmOrUpdate(profile);
            }
        });
    }
}
