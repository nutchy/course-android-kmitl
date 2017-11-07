package lab09.kmitl.chayanon58070021.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private RecordDB recordDB;
    private Intent intent;
    TextView total;
    RecordInfoAdapter recordInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordDB = Room.databaseBuilder(this, RecordDB.class, "RECORD").build();
        intent = new Intent(this, AddRecordActivity.class);

        Button addBtn = findViewById(R.id.addRecordBtn);
        addBtn.setOnClickListener(this);

        total = findViewById(R.id.totalIncomeTv);


        recordInfoAdapter = new RecordInfoAdapter();
        recordInfoAdapter.setContext(this);
        RecyclerView recyclerView = findViewById(R.id.rc_record_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recordInfoAdapter);

        new AsyncTask<Void, Void, RecordInfo>() {

            @Override
            protected RecordInfo doInBackground(Void... voids) {
                RecordInfo recordInfo = new RecordInfo();
                recordInfo.setType("outcome");
                recordInfo.setAmount(10000);
                recordInfo.setDetail("Food");
                recordDB.getRecordInfoDAO().insert(recordInfo);

                return null;
            }
        }.execute();

        loadList();
    }

    private void loadList(){

        new AsyncTask<Void, Void, List<RecordInfo>>() {
            @Override
            protected List<RecordInfo> doInBackground(Void... voids) {
                List<RecordInfo> result = recordDB.getRecordInfoDAO().showAll();
//                List<RecordInfo> result = recordDB.getRecordInfoDAO().queryByType("outcome");
                return result;
            }

            @Override
            protected void onPostExecute(List<RecordInfo> recordInfos) {
                recordInfoAdapter.setRecordInfoList(recordInfos);
                System.out.println("size :"+recordInfos.size());
                recordInfoAdapter.notifyDataSetChanged();

                int totalIncome = 0;
                for(RecordInfo r : recordInfos){
                    totalIncome += r.getAmount();
                }
                total.setText(totalIncome+"");
            }
        }.execute();

    }

    @Override
    public void onClick(View view) {
        startActivity(intent);
    }


}
