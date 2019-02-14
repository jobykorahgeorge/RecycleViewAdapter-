package //packagename

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.neoastra.vrmall.R;
import com.example.neoastra.vrmall.domain.TransactionModel;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter <HistoryAdapter.HistoryViewHolder>{

    private Context context;
    private List<TransactionModel> transactionModels;

    HistoryAdapter(Context context, List<TransactionModel> transactionModels){
        this.context=context;
        this.transactionModels=transactionModels;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_history,parent,false));
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {

        holder.vehicleNo.setText(transactionModels.get(position).getVehicleNo());
        holder.vehicleMo.setText(transactionModels.get(position).getVehicleModel());
        holder.inTime.setText(transactionModels.get(position).getInTime());
        holder.outTime.setText(transactionModels.get(position).getOutTime());
        holder.deductedMoney.setText("₹ "+Integer.toString(transactionModels.get(position).getDeductedMoney()));
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{

        public final View mView;

        TextView vehicleNo;
        TextView vehicleMo;
        TextView inTime;
        TextView outTime;
        TextView deductedMoney;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            vehicleNo = mView.findViewById(R.id.txt_number);
            vehicleMo = mView.findViewById(R.id.txt_label);
            inTime =  mView.findViewById(R.id.txt_entryTime);
            outTime = mView.findViewById(R.id.txt_exitTime);
            deductedMoney = mView.findViewById(R.id.deductedMoney);
        }
    }


}
