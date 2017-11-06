package lab09.kmitl.chayanon58070021.moneyflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddRecordActivity extends AppCompatActivity implements View.OnClickListener{


    private String TYPE = "income";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        getType();

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        getType();

    }

    public void getType(){
        RadioGroup radioBtnGroup = findViewById(R.id.radioGroup);
        int selectedId = radioBtnGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.incomeRBtn) TYPE = "income";
        else if (selectedId == R.id.outcomeRBtn) TYPE = "outcome";

        System.out.println(TYPE);
    }
}
