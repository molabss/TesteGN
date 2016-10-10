/*
 * Copyright (c) 2016 by Moisés Santana.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package br.com.testmaster.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import br.com.testmaster.R;

/**
 * Created by casa on 10/10/2016.
 */

public class CallApp {


    public static boolean shareWithWhatsApp(String text, Activity current){

        String wzpPack = current.getResources().getString(R.string.wzppack);

        PackageManager pm = current.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(wzpPack, 0);

            Intent sendIntent = new Intent();
            sendIntent.setPackage(wzpPack);
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, text);
            sendIntent.setType("text/plain");
            current.startActivity(sendIntent);

        } catch (Exception e) {

            return false;
        }

        return true;
    }

    public static void dialPhone(String phone, Activity current){
        Uri uri = Uri.parse("tel:"+phone);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        current.startActivity(intent);
    }
}
