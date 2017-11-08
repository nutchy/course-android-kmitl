package lab09.kmitl.chayanon58070021.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by nutchy on 4/11/2017 AD.
 */
@Dao
interface RecordInfoDAO {


    @Insert
    void insert(RecordInfo recordInfo);

    @Query("SELECT * FROM RECORD_INFO")
    List<RecordInfo> showAll();

    @Query("SELECT * FROM RECORD_INFO WHERE TYPE = :type")
    List<RecordInfo> queryByType(String type);

    @Update
    void update(RecordInfo recordInfo);

    @Delete
    void delete(RecordInfo recordInfo);
}
