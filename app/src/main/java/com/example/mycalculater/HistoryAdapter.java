package com.example.mycalculater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<HistoryItem> historyItems;
    private Context context;



    public HistoryAdapter(List<HistoryItem> historyItems, Context context) {
        this.historyItems = historyItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryItem item = historyItems.get(position);
        holder.resultTv.setText(item.getResult());
        holder.expressionTv.setText(item.getExpression());
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView resultTv, expressionTv;

        public ViewHolder(View itemView) {
            super(itemView);
            resultTv = itemView.findViewById(R.id.result_tv);
            expressionTv = itemView.findViewById(R.id.expression_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            HistoryItem item = historyItems.get(position);
            showDeleteDialog(item, position);
        }

        private void showDeleteDialog(final HistoryItem item, final int position) {
            DeleteDialog.show(context, new DeleteDialog.DeleteDialogListener() {
                @Override
                public void onDeleteConfirmed() {
                    DatabaseHelper dbHelper = new DatabaseHelper(context);
                    dbHelper.deleteHistoryItem(item.getId());
                    historyItems.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }
    }
}