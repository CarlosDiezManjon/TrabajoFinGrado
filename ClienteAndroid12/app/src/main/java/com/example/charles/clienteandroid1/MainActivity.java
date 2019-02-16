package com.example.charles.clienteandroid1;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class MainActivity extends AppCompatActivity {
    EditText pass, user;
    Button btnLogIn, btnSignUp;
    String profesor_ID;
    public static final String EXTRA_PROF_ID = "com.example.charles.clienteandroid1.EXTRA_PROF_ID";
    public static final String URL_CON = "http://10.0.2.2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("EasySki");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.txtUser);
        pass = findViewById(R.id.txtPass);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singUp();
            }
        });

    }

    public void logIn() {
        String username = user.getText().toString();
        String password = pass.getText().toString();
        String login_url = URL_CON+"/logIn.php";

        try {
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                    + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String entrada;

            entrada = bufferedReader.readLine();

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            String result;
            String resultado[] = entrada.split("-");
            result = resultado[0];



            if(result.equals("1")) {
                profesor_ID = resultado[1];
                Intent intent = new Intent(this, ListActivity.class);
                intent.putExtra(EXTRA_PROF_ID, profesor_ID);
                startActivity(intent);

            }else{
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void singUp(){
        String username = user.getText().toString();
        String password = pass.getText().toString();
        if(password.equals("")){
            Toast.makeText(this,"Se requiere contraseña",Toast.LENGTH_SHORT).show();
        }else {
            String signup_url = URL_CON+"/signUp.php";
            try {
                URL url = new URL(signup_url);
                HttpURLConnection httpurl = (HttpURLConnection) url.openConnection();
                httpurl.setRequestMethod("POST");
                httpurl.setDoOutput(true);
                httpurl.setDoInput(true);
                OutputStream outputStream = httpurl.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpurl.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String entrada;

                entrada = bufferedReader.readLine();
                Toast.makeText(this, entrada, Toast.LENGTH_SHORT).show();

                bufferedReader.close();
                inputStream.close();
                httpurl.disconnect();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
