package com.example.charles.clienteandroid1;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ListActivity extends AppCompatActivity {

    public static final String EXTRA_CLASE_ID = "com.example.charles.clienteandroid1.EXTRA_CLASE_ID";
    ListView listView;
    Button botonAct;
    ArrayAdapter<String> adapter;
    String result = null;
    String data;
    String profesor_ID;
    String clases[];
    String itemSelectec;
    String aux[];
    String clase_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = new Date();
        setTitle("Lista clases de "+formatoFecha.format(fecha));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        profesor_ID = intent.getStringExtra(MainActivity.EXTRA_PROF_ID);

        listView = findViewById(R.id.lista);
        botonAct = findViewById(R.id.btnUpdate);
        getData();


        botonAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getData();
            }
        });


        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelectec = (String) listView.getItemAtPosition(position);
                nextActivity(view);
            }
        }
        );


    }

    private int getData(){
        String getList_url = MainActivity.URL_CON + "/getList.php";
        int salida=0;

        try
        {
            DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = new Date();
            String fechaAct = formatoFecha.format(fecha);
            URL url = new URL(getList_url);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            OutputStream outputStream = con.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("fechaAct","UTF-8")+"="+URLEncoder.encode(fechaAct , "UTF-8")+"&"
                    +URLEncoder.encode("profesor_ID","UTF-8")+"="+URLEncoder.encode(profesor_ID, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = con.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String entrada = bufferedReader.readLine();
            if(entrada.equals("0")){
                Toast.makeText(this,"No hay clases actualmente" , Toast.LENGTH_SHORT).show();
                salida = 0;
            }else {
                clases = entrada.split("\\.");
                adapter = new ArrayAdapter<String>(this, R.layout.textviewfont, clases);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                salida = 1;
            }

            bufferedReader.close();
            inputStream.close();








        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return salida;

    }

    public void showNotification(){

    }

    public void nextActivity(View v){

        aux = itemSelectec.split(" ");
        clase_ID = aux[0];
        Intent intent = new Intent(this, DatosClase.class);
        intent.putExtra(EXTRA_CLASE_ID, clase_ID);
        startActivity(intent);
    }

}
