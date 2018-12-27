package com.android.chatmail.chatapp.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    public static boolean checkPermission(int requestCod, Activity activity, String[] permissions){
        List<String> listPermissions = new ArrayList<String>();

        for(String permission: permissions){
            boolean validPermission = ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission) == PackageManager.PERMISSION_GRANTED;
            if(!validPermission) listPermissions.add(permission);
        }

        if(listPermissions.isEmpty()) return true;

        String[] newPermissions = new String[listPermissions.size()];
        listPermissions.toArray(newPermissions);

        ActivityCompat.requestPermissions(activity, newPermissions, requestCod);

        return true;
    }
}
