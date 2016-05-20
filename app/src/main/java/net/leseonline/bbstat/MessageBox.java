package net.leseonline.bbstat;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by mlese on 5/20/2016.
 */
public class MessageBox {
    public static void show(Context context, String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK", null);
        alert.show();
    }
}
