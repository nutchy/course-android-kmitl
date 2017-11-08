package lab09.kmitl.chayanon58070021.moneyflow;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nutchy on 4/11/2017 AD.
 */
@Entity(tableName = "RECORD_INFO")
class RecordInfo implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.type);
        dest.writeString(this.detail);
        dest.writeFloat(this.amount);
    }

    public RecordInfo() {
    }

    protected RecordInfo(Parcel in) {
        this.id = in.readInt();
        this.type = in.readString();
        this.detail = in.readString();
        this.amount = in.readFloat();
    }

    public static final Parcelable.Creator<RecordInfo> CREATOR = new Parcelable.Creator<RecordInfo>() {
        @Override
        public RecordInfo createFromParcel(Parcel source) {
            return new RecordInfo(source);
        }

        @Override
        public RecordInfo[] newArray(int size) {
            return new RecordInfo[size];
        }
    };
}
