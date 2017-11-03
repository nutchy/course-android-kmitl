package lab09.kmitl.chayanon58070021.moneyflow;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by nutchy on 4/11/2017 AD.
 */
@Entity(tableName = "RECORD_INFO")
class RecordInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "TYPE")
    private String type;

    @ColumnInfo(name = "DETAIL")
    private String detail;

    @ColumnInfo(name = "AMOUNT")
    private float amount;

    @Override
    public String toString() {
        return String.format("%s - %s - %s", type, detail, amount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
