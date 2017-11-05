package lab09.kmitl.chayanon58070021.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private RecordDB recordDB;
    private Intent intent;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordDB = Room.databaseBuilder(this, RecordDB.class, "RECORD").build();
        intent = new Intent(this, AddRecordActivity.class);

        Button addBtn = findViewById(R.id.addRecordBtn);
        addBtn.setOnClickListener(this);

        total = findViewById(R.id.totalIncomeTv);


        new AsyncTask<Void, Void, RecordInfo>() {

            @Override
            protected RecordInfo doInBackground(Void... voids) {
                RecordInfo recordInfo = new RecordInfo();
                recordInfo.setType("outcome");
                recordInfo.setAmount(100);
                recordInfo.setDetail("Food");
                recordDB.getRecordInfoDAO().insert(recordInfo);

                recordInfo.setType("income");
                recordDB.getRecordInfoDAO().insert(recordInfo);
                recordDB.getRecordInfoDAO().insert(recordInfo);
                return null;
            }
        }.execute();

        new AsyncTask<Void, Void, List<RecordInfo>>() {
            @Override
            protected List<RecordInfo> doInBackground(Void... voids) {
                List<RecordInfo> result = recordDB.getRecordInfoDAO().showAll();
//                List<RecordInfo> result = recordDB.getRecordInfoDAO().queryByType("outcome");

                int totalIncome = 0;
                for(RecordInfo r : result){
                    totalIncome += r.getAmount();
                }
                total.setText(totalIncome+"");
                return result;
            }

            @Override
            protected void onPostExecute(List<RecordInfo> recordInfos) {
                ArrayAdapter<RecordInfo> adapter = new ArrayAdapter<RecordInfo>(MainActivity.this
                        , android.R.layout.simple_list_item_1, recordInfos);
                ListView recordList = findViewById(R.id.recordList);
                recordList.setAdapter(adapter);

            }


        }.execute();



    }



    @Override
    public void onClick(View view) {
        startActivity(intent);
    }
}
