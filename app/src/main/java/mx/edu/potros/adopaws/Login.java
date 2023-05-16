package mx.edu.potros.adopaws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button loginButton, RegistrarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.et_usuario_login);
        password = findViewById(R.id.et_password_login);
        RegistrarText = findViewById(R.id.btn_registrate);
        loginButton = findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validarUsername() | !validarPassword()){

                }else{
                    checarUsuario();
                }
            }
        });

        RegistrarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegistrarUsuario.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validarUsername(){
        String val = username.getText().toString();
        if(val.isEmpty()){
            username.setError("El nombre de usuario está vacío");
            return false;
        }else{
            username.setError(null);
            return true;
        }
    }

    public Boolean validarPassword(){
        String val = password.getText().toString();
        if(val.isEmpty()){
            password.setError("Contraseña vacía");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }

    public void checarUsuario(){
        String userUsername = username.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("usuarios");
        Query CheckUserDatabase = reference.orderByChild("nomusuario").equalTo(userUsername);

        CheckUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    username.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("correo").getValue(String.class);

                    if(!Objects.equals(passwordFromDB, userPassword)){
                        username.setError(null);
                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);
                    }else{
                        password.setError("Credenciales no validas");
                        password.requestFocus();
                    }
                }else{
                    username.setError("el nombre de usuario no existe");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
