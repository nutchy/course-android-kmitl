package lab09.kmitl.chayanon58070021.roomdemo.Database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
;


@Database(entities = { MessageInfo.class }, version = 1) // Define Table
public abstract class MessageDB extends RoomDatabase {

    public abstract MessageInfoDAO getMessageInfoDAO();

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
