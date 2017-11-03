package lab09.kmitl.chayanon58070021.moneyflow;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by nutchy on 4/11/2017 AD.
 */
@Database(entities = { RecordInfo.class }, version = 1)
public abstract class RecordDB extends RoomDatabase {
    public abstract RecordInfoDAO getRecordInfoDAO();

}
