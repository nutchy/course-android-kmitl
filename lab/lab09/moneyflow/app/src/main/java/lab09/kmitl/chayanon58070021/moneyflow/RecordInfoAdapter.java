package lab09.kmitl.chayanon58070021.moneyflow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nutchy on 7/11/2017 AD.
 */

public class RecordInfoAdapter extends RecyclerView.Adapter<RecordInfoAdapter.Holder> {

    private Context context;
    private List<RecordInfo> recordInfoList;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<RecordInfo> getRecordInfoList() {
        return recordInfoList;
    }

    public void setRecordInfoList(List<RecordInfo> recordInfoList) {
        this.recordInfoList = recordInfoList;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.record_item, parent, false);

        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        TextView symbol = holder.symbol;
        TextView detail_item = holder.detail_item;
        TextView amount_item = holder.amount_item;

        symbol.setText(String.valueOf(recordInfoList.get(position).getType()));
        detail_item.setText(String.valueOf(recordInfoList.get(position).getDetail()));
        amount_item.setText(String.valueOf(recordInfoList.get(position).getAmount()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateRecordActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (recordInfoList == null) {
            recordInfoList = new ArrayList<>();
        }
        return recordInfoList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        public TextView symbol;
        public TextView detail_item;
        public TextView amount_item;

        public Holder(View itemView) {
            super(itemView);
            symbol = itemView.findViewById(R.id.type_symbol_Tv);
            detail_item = itemView.findViewById(R.id.detail_item);
            amount_item = itemView.findViewById(R.id.amount_item);
        }
    }
}
