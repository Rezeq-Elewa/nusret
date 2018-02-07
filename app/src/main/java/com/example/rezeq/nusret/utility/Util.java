package com.example.rezeq.nusret.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

import com.example.rezeq.nusret.models.Profile;

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

    public String getAccessToken(){
        return getUserProfile().getAccessToken();
    }

    public String getDeviceLanguage(){
        return "ar";
    }

    public boolean isLoggedIn(){
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name("nusret.realm").build();
        Realm.setDefaultConfiguration(config);
        final Realm realm = Realm.getDefaultInstance();
        return realm.where(Profile.class).findFirst() != null;
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

    public void logout(){
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name("nusret.realm").build();
        Realm.setDefaultConfiguration(config);
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.delete(Profile.class);
            }
        });
    }


    public boolean hasDeviceKeys() {
        boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

        return hasMenuKey && hasBackKey;
    }

    public int getStatusBarHeight(){
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

}
