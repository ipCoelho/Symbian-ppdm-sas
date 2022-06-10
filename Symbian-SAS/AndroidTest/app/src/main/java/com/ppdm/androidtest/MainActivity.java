package com.ppdm.androidtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ppdm.androidtest.models.User;
import com.ppdm.androidtest.remote.APIUtil;
import com.ppdm.androidtest.remote.RouterInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText emailText;
    private EditText celText;
    private EditText nameText;
    private EditText lastnameText;
    private Button registerButton;

    RouterInterface routerInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Window window = getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.beutifulBlue));

        nameText = findViewById(R.id.edit_text_nome);
        lastnameText = findViewById(R.id.edit_text_sobrenome);
        emailText = findViewById(R.id.edit_text_login_cadastro);
        celText = findViewById(R.id.edit_text_password_cadastro);
        registerButton = findViewById(R.id.button_cadastrar_cadastro);

        routerInterface = APIUtil.getUserInterface();

        registerButton.setOnClickListener(view -> {
            if (!validate()) {
                Toast.makeText(MainActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Confirmar")
                        .setMessage("Seus dados estão corretos?")
                        .setPositiveButton("Sim!",(dialog1, wich) -> {
                            String  name = nameText.getText().toString();
                            String  lastName = lastnameText.getText().toString();
                            String  email = emailText.getText().toString();
                            String  cel = celText.getText().toString();

                            User user = new User();
                            user.setName(name);
                            user.setLastname(lastName);
                            user.setEmail(email);
                            user.setCel(cel);
                            addUser(user);
                        })
                        .setNegativeButton("Revisar", (dialog1, wich) -> {}).create();
                dialog.show();
            }

        });
    }

    public boolean validate() {
        return (!celText.getText().toString().isEmpty() && !emailText.getText().toString().isEmpty() && !nameText.getText().toString().isEmpty() && !lastnameText.getText().toString().isEmpty());
    }

    public void addUser(User user){

        Call<User> call = routerInterface.postUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this,
                        "Usuário criado com sucesso!",
                        Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("API-ERRO", t.getMessage());
            }
        });

    }
}
