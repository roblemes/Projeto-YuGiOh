package com.example.projetoyu_gi_oh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textResult;
    //List<Carta> cartasList;
    EditText inputNome;
    EditText inputDescricao;
    EditText inputAtaque;
    EditText inputDefesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //cartasList = new ArrayList<>();
        textResult = findViewById(R.id.textResult);
        inputNome = findViewById(R.id.inputNome);
        inputDescricao = findViewById(R.id.inputDescricao);
        inputAtaque = findViewById(R.id.inputAtaque);
        inputDefesa = findViewById(R.id.inputDefesa);
    }

    public void solicitarDado(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        textResult.setText("Carregando..");
        String url = "http://10.0.2.2:8000/cartas/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            //cartas.add(carta);
                            Carta carta = new Carta();
                            StringBuilder sb = new StringBuilder();
                            JSONArray cartas = response.getJSONArray("results");

                            for (int i = 0; i < cartas.length(); i++) {
                                JSONObject carta_info = cartas.getJSONObject(i);
                                carta.setCartaNome(carta_info.getString("cartas_nome"));
                                carta.setCartaDescricao(carta_info.getString("cartas_descricao"));
                                carta.setCartaATK(carta_info.getInt("cartas_atk"));
                                carta.setCartaDEF(carta_info.getInt("cartas_def"));

                                //cartasList.add(carta);
                                sb.append("Carta " + "(" + i + "):" + "\n" + carta);
                            }

                            String x = "Lista de cartas:\n\n" + sb.toString();

                            textResult.setText(x);

                        } catch (JSONException e) {
                            textResult.setText(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textResult.setText(error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Api-Key", "JISBaIhz.yJTHqlcL0xjdfWOgMzDOXTxzPYy4aAMm");

                return params;
            }
        };

        queue.add(request);
    }

    public void enviarDados(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        //textResult.setText("Carregando..");
        String url = "http://10.0.2.2:8000/cartas/";

        String nomeC = inputNome.getText().toString();
        String descC = inputDescricao.getText().toString();
        String atkC = inputAtaque.getText().toString();
        String defC = inputDefesa.getText().toString();

        JSONObject cartaObj = new JSONObject();
        try {
            cartaObj.put("cartas_nome", nomeC);
            cartaObj.put("cartas_descricao", descC);
            cartaObj.put("cartas_atk", atkC);
            cartaObj.put("cartas_def", defC);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,
                cartaObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        textResult.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textResult.setText(error.getMessage());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Api-Key", "JISBaIhz.yJTHqlcL0xjdfWOgMzDOXTxzPYy4aAMm");

                return params;
            }
        };

        queue.add(request);
    }
}