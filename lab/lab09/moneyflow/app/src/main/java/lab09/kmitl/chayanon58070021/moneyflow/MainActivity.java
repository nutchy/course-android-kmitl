package lab09.kmitl.chayanon58070021.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecordInfoAdapter.RecordInfoAdapterListener {

    public final int RESULT_ACTIVITY = 999;
    public final int RESULT_UPDATE = 888;
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
        recordInfoAdapter.setListener(this);
        RecyclerView recyclerView = findViewById(R.id.rc_record_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recordInfoAdapter);

        loadList();
    }

    private void loadList() {

        new AsyncTask<Void, Void, List<RecordInfo>>() {
            @Override
            protected List<RecordInfo> doInBackground(Void... voids) {
                List<RecordInfo> result = recordDB.getRecordInfoDAO().showAll();
                return result;
            }

            @Override
            protected void onPostExecute(List<RecordInfo> recordInfos) {
                recordInfoAdapter.setRecordInfoList(recordInfos);
                System.out.println("size :" + recordInfos.size());
                recordInfoAdapter.notifyDataSetChanged();

                float totalBalance = 0;
                float totalIncome = 0;
                for (RecordInfo r : recordInfos) {

                    if (r.getType().equals("income")) {
                        totalIncome += r.getAmount();
                        totalBalance += r.getAmount();
                    } else totalBalance -= r.getAmount();
                }
                float ratio;
                try {
                    ratio = totalBalance / totalIncome;
                } catch (ArithmeticException e) {
                    ratio = 0;
                }
                System.out.println(ratio);
                if (ratio > 0.5) {
                    total.setTextColor(Color.GREEN);
                    System.out.println(ratio);
                } else if (ratio >= 0.25) {
                    total.setTextColor(Color.YELLOW);
                    System.out.println("25-50");
                } else {
                    total.setTextColor(Color.RED);
                    System.out.println("< 25");
                }
                total.setText(totalBalance + "");
            }
        }.execute();
    }

    @Override
    public void onClick(View view) {
        startActivityForResult(intent, RESULT_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_ACTIVITY && resultCode == RESULT_OK) {
            loadList();
        }
    }

    @Override
    public void onClickRecordInfoItem(RecordInfo recordInfo) {
        Intent intent = new Intent(this, UpdateRecordActivity.class);
        intent.putExtra("RecordInfoParcel", recordInfo);
        startActivityForResult(intent, RESULT_ACTIVITY);

    }
}
