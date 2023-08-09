package com.example.serviciomusica;

import static com.example.serviciomusica.ServicioMusica.CANAL_ID;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.app.NotificationCompat;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button botonArrancar = findViewById(R.id.boton_arrancar);
        botonArrancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, ServicioMusica.class));
            }
        });

        Button botonDetener = findViewById(R.id.boton_detener);
        botonDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, ServicioMusica.class));
            }
        });

        Button botonSocorro = findViewById(R.id.boton_socorro);
        botonSocorro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearNotificacionSocorro();
            }
        });
    }

    private void crearNotificacionSocorro() {
        NotificationChannel notificationChannel = new NotificationChannel(CANAL_ID,
                "Mis notificaciones", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this, CANAL_ID)
                .setContentText("SOCORRO!!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.explosion))
                .setWhen(System.currentTimeMillis() + 1000 * 60 * 60);

        notificationChannel.setDescription("Descripción del canal");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setVibrationPattern(new long[]{0L, 100L, 300L, 100L});
        notificationChannel.enableVibration(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        notificationManager.notify(2, notificacion.build());
    }
}
