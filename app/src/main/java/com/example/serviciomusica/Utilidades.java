package com.example.serviciomusica;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public enum Utilidades {

    SERVICIO;

    public Bitmap bitMapADrawable(Context elContexto, int idDrawable){
        return BitmapFactory.decodeResource(elContexto.getResources(), idDrawable);
    }
}
