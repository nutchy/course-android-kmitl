package lab09.kmitl.chayanon58070021.moneyflow;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddRecordActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText desc, amount;
    private RecordDB recordDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

        recordDB = Room.databaseBuilder(this, RecordDB.class, "RECORD").build();

        desc = findViewById(R.id.desc_et);
        amount = findViewById(R.id.amount_et);


    }

    @Override
    public void onClick(View view) {
        insertRecord();
    }

    private void insertRecord() {
        new AsyncTask<Void, Void, RecordInfo>(){
            @Override
            protected RecordInfo doInBackground(Void... voids) {
                RecordInfo recordInfo = new RecordInfo();
                recordInfo.setDetail(desc.getText()+"");
                recordInfo.setAmount(Integer.parseInt(amount.getText()+""));
                recordInfo.setType(getType());
                recordDB.getRecordInfoDAO().insert(recordInfo);

                return null;
            }
        }.execute();
    }

    public String getType(){
        RadioGroup radioBtnGroup = findViewById(R.id.radioGroup);
        int selectedId = radioBtnGroup.getCheckedRadioButtonId();

        String TYPE = "";

        if (selectedId == R.id.incomeRBtn) TYPE = "income";
        else if (selectedId == R.id.outcomeRBtn) TYPE = "outcome";

        System.out.println(TYPE);

        return TYPE;
    }
}
