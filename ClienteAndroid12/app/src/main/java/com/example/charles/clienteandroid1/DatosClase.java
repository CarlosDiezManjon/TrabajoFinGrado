package com.example.charles.clienteandroid1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import entidades.Clase;

public class DatosClase extends AppCompatActivity {

    String clase_ID;
    String atributos[];
    Clase clase;
    TextView horaIni, horaFin, nombre, tlfno, num_alumnos, zona_clase, estado;
    int blue = Color.parseColor("#0000FF");
    int green = Color.parseColor("#32CD32");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_clase);

        nombre = findViewById(R.id.txtNombre);
        tlfno = findViewById(R.id.txtTlfno);
        num_alumnos = findViewById(R.id.txtNumAlum);
        zona_clase = findViewById(R.id.txtZona);
        estado = findViewById(R.id.txtEstado);

        Intent intent = getIntent();
        clase_ID = intent.getStringExtra(ListActivity.EXTRA_CLASE_ID);
        getClase();
        setTitle("De   "+clase.getHoraIni()+":00   a   "+clase.getHoraFin()+":00");
        nombre.setText(clase.getNombre_alumno());
        tlfno.setText(clase.getTlfno_alumno());
        num_alumnos.setText(clase.getNumero_alumnos()+"");
        zona_clase.setText(clase.getZona_clase());
        if(clase.getEstado().equals("Reservada")){
            estado.setTextColor(blue);
        }
        if(clase.getEstado().equals("Confirmada")){
            estado.setTextColor(green);
        }
        estado.setText(clase.getEstado());



    }

    public void getClase(){
        String getClase_url = MainActivity.URL_CON + "/getClase.php";


        try {
            URL url = new URL(getClase_url);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

             con.setDoOutput(true);
             con.setDoInput(true);
             OutputStream outputStream = con.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("clase_ID","UTF-8")+"="+URLEncoder.encode(clase_ID , "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = con.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String entrada = bufferedReader.readLine();
            atributos = entrada.split("-");
            clase = new Clase(atributos[0], atributos[1], Integer.valueOf(atributos[2]), Integer.valueOf(atributos[3]), Integer.valueOf(atributos[4]), atributos[5], atributos[6]);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
