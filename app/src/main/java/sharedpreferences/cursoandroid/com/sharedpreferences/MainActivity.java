package sharedpreferences.cursoandroid.com.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    

    private Button botaoSalvar;
    private TextView textoSalvo;
    private EditText textoInserido;

    //Constante
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoSalvar = (Button) findViewById(R.id.botaoSalvarId);
        textoSalvo = (TextView) findViewById(R.id.textoSalvoId);
        textoInserido = (EditText) findViewById(R.id.editNomeId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //Verifica se o campo de digitação esta vazio
                if(textoInserido.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Por favor, inserir algum texto acima", Toast.LENGTH_SHORT).show();
                } else {
                    //recebe valor digitado no edit text
                    editor.putString("nome", textoInserido.getText().toString());
                    editor.commit();
                    textoSalvo.setText(textoInserido.getText().toString());
                }
            }
        });

        //Recuperar dados salvos
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if(sharedPreferences.contains("nome")){
            String nomeDigitado = sharedPreferences.getString("nome", "usuario não definido");
            textoSalvo.setText(nomeDigitado);
        } else {
            textoSalvo.setText("Usuário não definido");
        }
    }
}
