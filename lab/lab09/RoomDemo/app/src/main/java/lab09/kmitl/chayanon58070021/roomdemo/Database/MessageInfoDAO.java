package lab09.kmitl.chayanon58070021.roomdemo.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import lab09.kmitl.chayanon58070021.roomdemo.Database.MessageInfo;

/**
 * Created by nutchy on 3/11/2017 AD.
 */

@Dao
public interface MessageInfoDAO {

    @Insert
    void insert(MessageInfo messageInfo);

    @Query("SELECT * FROM MESSAGE_INFO")
    List<MessageInfo> findAll();


}
