package com.rodrigo.clase5g46;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LeerContenidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_contenido);

        /* OBTENIENDO LOS COMPONENTES */
        TextView txtContenidoArchivoExterno = findViewById(R.id.txt_contenido_archivo_externo);

        //VALIDANDO EL PERMISO
        pedirPermisos();

        File ruta = Environment.getExternalStorageDirectory();
        File archivo = new File(ruta, "clase5.txt");

        int tamano = (int) archivo.length();
        byte[] bytes = new byte[tamano];

        FileInputStream input = null;

        try {
            input = new FileInputStream(archivo);
            input.read(bytes);
            input.close();

            String contenido_txt = new String(bytes);
            txtContenidoArchivoExterno.setText(contenido_txt);

        } catch (FileNotFoundException fileNotFoundException) {

            Log.d("ERROR: ARCHIVO NO ENCONTRADO", fileNotFoundException.getMessage());
            Toast.makeText(LeerContenidoActivity.this, "Error: archivo no encontrado", Toast.LENGTH_SHORT).show();

        } catch (IOException ioException) {

            Log.d("ERROR AL ESCRIBIR EL EL ARCHIVO", ioException.getMessage());
            Toast.makeText(LeerContenidoActivity.this, "Error en lectura", Toast.LENGTH_SHORT).show();

        }
    }

    public void pedirPermisos(){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            }else{
                ActivityCompat.requestPermissions(LeerContenidoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
            }
        }
    }
}