package lab09.kmitl.chayanon58070021.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateRecordActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText desc, amount;
    private RecordDB recordDB;
    private RecordInfo recordInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);
        Button saveBtn = findViewById(R.id.save_Btn);
        Button deleteBtn = findViewById(R.id.deleteBtn);

        saveBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        recordInfo = getIntent().getParcelableExtra("RecordInfoParcel");
        System.out.println(recordInfo.getDetail());
        recordDB = Room.databaseBuilder(this, RecordDB.class, "RECORD").build();

        desc = findViewById(R.id.update_desc_et);
        amount = findViewById(R.id.update_amount_et);

        desc.setText(recordInfo.getDetail());
        amount.setText(String.valueOf((int) recordInfo.getAmount()));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_Btn:
                if (desc.getText().length() == 0 || amount.getText().toString().length() == 0){
                    Toast.makeText(this, "Please enter in this field.",
                            Toast.LENGTH_LONG).show();
                }else {
                    updateRecord();
                    finishActivity();
                }
                break;
            case R.id.deleteBtn:
                deleteRecord();
                finishActivity();
                break;
        }

    }

    private void deleteRecord() {
        new AsyncTask<Void, Void, RecordInfo>(){
            @Override
            protected RecordInfo doInBackground(Void... voids) {
                recordDB.getRecordInfoDAO().delete(recordInfo);
                return null;
            }
        }.execute();

    }

    private void finishActivity(){
        Intent output = new Intent();
        setResult(RESULT_OK, output);
        finish();
    }

    private void updateRecord() {
        new AsyncTask<Void, Void, RecordInfo>(){
            @Override
            protected RecordInfo doInBackground(Void... voids) {
                recordInfo.setDetail(desc.getText()+"");
                recordInfo.setAmount(Integer.parseInt(amount.getText()+""));
                recordInfo.setType(getType());
                recordDB.getRecordInfoDAO().update(recordInfo);
                return null;
            }

            @Override
            protected void onPostExecute(RecordInfo recordInfo) {
                super.onPostExecute(recordInfo);
            }
        }.execute();

    }

    public String getType(){
        RadioGroup radioBtnGroup = findViewById(R.id.radio_Group);
        int selectedId = radioBtnGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.income_RBtn) return  "income";
        else if (selectedId == R.id.outcome_RBtn) return  "expense";
        else return null;
    }
}
