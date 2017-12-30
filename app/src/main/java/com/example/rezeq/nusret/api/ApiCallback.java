package com.example.rezeq.nusret.api;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public interface ApiCallback {

    void onSuccess( Object response );

    void onFailure( String errorMsg );

}
