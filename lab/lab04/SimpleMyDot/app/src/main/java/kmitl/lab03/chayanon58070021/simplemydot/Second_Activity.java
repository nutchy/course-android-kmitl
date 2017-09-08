package kmitl.lab03.chayanon58070021.simplemydot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kmitl.lab03.chayanon58070021.simplemydot.model.DotParcelable;
import kmitl.lab03.chayanon58070021.simplemydot.model.DotSerializable;

public class Second_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btnBack = (Button) findViewById(R.id.back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int x = getIntent().getIntExtra("xValue", 0);
        TextView tvValueX = (TextView) findViewById(R.id.tvValueX);

        DotSerializable dotSerializable = (DotSerializable) getIntent().getSerializableExtra("dotSerializable");
        DotParcelable dotParcelable =  getIntent().getParcelableExtra("dotParcelable");
        tvValueX.setText(String.valueOf(dotParcelable.getCenterY()));
    }
}
