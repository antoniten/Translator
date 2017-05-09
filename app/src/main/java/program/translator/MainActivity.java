package program.translator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText input;
    private Spinner spinner;
    private TextView output;

    private String[] languages = getResources().getStringArray(R.array.languages), languageCodes;
    private String apiKey = "trnsl.1.1.20170426T143701Z.5557cd4b1aca325d.724a862bb07c2a187c3ab41ceb1650408bf91a8b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(this);
        input = (EditText) findViewById(R.id.input);
        spinner = (Spinner) findViewById(R.id.spinner);
        output = (TextView) findViewById(R.id.output);

        languageCodes = getResources().getStringArray(R.array.language_codes);

        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, languages));
    }

    @Override
    public void onClick(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.yandex.net/api/v1.5/tr.json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(TranslateApi.class).translate(apiKey, languageCodes[spinner.getSelectedItemPosition()], input.getText().toString()).enqueue(
                new Callback<TranslateResponse>() {
                    @Override
                    public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                        if(response.code() != 200){
                            Toast.makeText(getApplicationContext(), "Response code:"+response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        output.setText(response.body().text.get(0));
                    }

                    @Override
                    public void onFailure(Call<TranslateResponse> call, Throwable t) {

                    }
                }
        );
    }
}
