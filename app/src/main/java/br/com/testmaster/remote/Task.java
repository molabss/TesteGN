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

package br.com.testmaster.remote;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import br.com.testmaster.R;

/**
 * Created by casa on 10/10/2016.
 */

public abstract class Task {


    private Context mContext;
    private boolean houveErro = false;
    private StringBuilder msg;

    public Task(Context mContext){
        this.msg = new StringBuilder();
        this.mContext = mContext;
    }


    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public String getMsg() {
        return msg.toString();
    }

    public void setMsg(String msg) {
        this.msg.append(msg);
    }

    public void clearMsg(){
        if(msg.length() > 1){
            this.msg.delete(0,msg.length()-1);
        }
    }

    public void limpaErroHist() {
        this.houveErro = false;
    }

    public void erroOcorreu() {
        this.houveErro = true;
    }

    public boolean teveAlgumErro(){
        return this.houveErro;
    }


    public void alert(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage(msg);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface di, int arg) {
                di.dismiss();
            }
        });
        dialog.setTitle(mContext.getResources().getString(R.string.warning));
        dialog.show();
    }
}
