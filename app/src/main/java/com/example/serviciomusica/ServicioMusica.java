package com.example.serviciomusica;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ServicioMusica extends Service {

    MediaPlayer reproductor;

    private NotificationManager notificationManager;

    static final String CANAL_ID = "mi_canal";
    static final int NOTIFICACION_ID = 1;

    @Override
    public void onCreate() {
        Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this, R.raw.audio);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int idArranque) {
        Toast.makeText(this, "Servicio arrancado " + idArranque, Toast.LENGTH_SHORT).show();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel notificacionChannel = new NotificationChannel(CANAL_ID, "Mis notificaciones",
                NotificationManager.IMPORTANCE_DEFAULT);

        notificacionChannel.setDescription("Descripción del canal");
        notificationManager.createNotificationChannel(notificacionChannel);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this, CANAL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(Utilidades.SERVICIO.bitMapADrawable(this, R.drawable.ic_launcher_background))
                .setContentTitle("Título")
                .setContentText("Notificación Wear OS")
                .setWhen(System.currentTimeMillis() + 1000 * 60 * 60)
                .setContentInfo("Mas info")
                .setTicker("Texto en barra de estado");

        notificationManager.notify(NOTIFICACION_ID, notificacion.build());

        reproductor.start();
        return START_STICKY;
    }

    @Override public void onDestroy(){
        Toast.makeText(this, "Servicio detenido", Toast.LENGTH_SHORT).show();

        notificationManager.cancel(NOTIFICACION_ID);
        reproductor.stop();
        reproductor.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
