package com.rodrigo.clase5g46;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* OBTENIENDO COMPONENTES */
        TextInputEditText inputContenido = findViewById(R.id.npt_contenido);
        MaterialButton btnEscribir = findViewById(R.id.btn_escribir);
        MaterialButton btnVerContenido = findViewById(R.id.btn_verContenido);

        btnEscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* PETICION DE PERMISOS */
                pedirPermisos();
                File ruta = Environment.getExternalStorageDirectory();
                File archivo = new File(ruta, "clase5.txt");
                FileOutputStream stream = null;

                try {

                    stream = new FileOutputStream(archivo, true);
                    String contenidoTXT = inputContenido.getText()+"\n";
                    stream.write(contenidoTXT.getBytes());
                    stream.close();
                    Toast.makeText(MainActivity.this, "Contenido agregado satisfactoriamente", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException fileNotFoundException) {

                    Log.d("ERROR: ARCHIVO NO ENCONTRADO", fileNotFoundException.getMessage());
                    Toast.makeText(MainActivity.this, "Error: archivo no encontrado", Toast.LENGTH_SHORT).show();

                } catch (IOException ioException) {//ñó╬6)MzVƒ╔ú╒gtδì§d«2

                    Log.d("ERROR AL ESCRIBIR EL EL ARCHIVO", ioException.getMessage());
                    Toast.makeText(MainActivity.this, "Error en escritura", Toast.LENGTH_SHORT).show();

                }

            }
        });

        btnVerContenido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LeerContenidoActivity.class);
                startActivity(i);
            }
        });

    }

    public void pedirPermisos(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            }else{
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
            }
        }
    }

}