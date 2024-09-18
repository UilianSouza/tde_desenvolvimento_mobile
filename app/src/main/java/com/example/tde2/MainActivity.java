package com.example.tde2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview_data);
        button = findViewById(R.id.button_refresh);

        button.setOnClickListener(v -> fetchData());
    }

    private void fetchData() {
        CoinGeckoService service = RetrofitClient.getApi();
        Call<PriceResponse> call = service.getPrice("bitcoin", "brl");

        call.enqueue(new Callback<PriceResponse>() {
            @Override
            public void onResponse(Call<PriceResponse> call, Response<PriceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Float price = response.body().getBitcoin().get("brl");

                    // Formatar o preço
                    DecimalFormat formatter = new DecimalFormat("#,##0.00"); // Formato com vírgula como separador decimal
                    String formattedPrice = formatter.format(price);

                    textView.setText("Preço do Bitcoin: R$ " + formattedPrice);
                } else {
                    textView.setText("Erro: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PriceResponse> call, Throwable t) {
                textView.setText("Falha: " + t.getMessage());
            }
        });
    }
}
