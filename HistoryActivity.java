package //packagename

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.neoastra.vrmall.R;
import com.example.neoastra.vrmall.data.remote.GetDataService;
import com.example.neoastra.vrmall.data.remote.RetrofitClientInstance;
import com.example.neoastra.vrmall.domain.LoginModel;
import com.example.neoastra.vrmall.domain.TransactionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("HISTORY");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent i = getIntent();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<TransactionModel>> call = service.getTransaction(i.getStringExtra("email"),i.getStringExtra("vehicleNo"));

        call.enqueue(new Callback<List<TransactionModel>>() {
            @Override
            public void onResponse(Call<List<TransactionModel>> call, Response<List<TransactionModel>> response) {
                if(response.isSuccessful())
                getTransactionAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<TransactionModel>> call, Throwable t) {

            }
        });

    }
    private void getTransactionAdapter(List<TransactionModel> transactionModels){
        RecyclerView recyclerView = findViewById(R.id.rv_history);
        adapter = new HistoryAdapter(this, transactionModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));// change this to grid with no of colmn by --recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerView.setAdapter(adapter);
    }
}
