package lab09.kmitl.chayanon58070021.moneyflow;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private RecordDB recordDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordDB = Room.databaseBuilder(this, RecordDB.class, "RECORD").build();



    }
}
