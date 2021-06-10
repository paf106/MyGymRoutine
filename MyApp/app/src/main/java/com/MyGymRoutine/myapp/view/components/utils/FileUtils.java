package com.MyGymRoutine.myapp.view.components.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;


public class FileUtils {
    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        //Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap ByteArrayToBitmap(byte[] byteArray) {
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bmp;
    }

}