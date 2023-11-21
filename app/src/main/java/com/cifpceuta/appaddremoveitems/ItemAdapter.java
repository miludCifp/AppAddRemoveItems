package com.cifpceuta.appaddremoveitems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    ArrayList<String> list_items;
    public ItemAdapter(ArrayList<String> list_items){
        this.list_items = list_items;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nuevo_item_layout,parent,false);
        return new ItemAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        holder.bindData(list_items.get(position));
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvItem;
        Button btnBorrar;
        private ItemAdapter adapter;

        public ViewHolder(View itemView){
            super(itemView);

            tvItem = itemView.findViewById(R.id.tvItem);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);

            btnBorrar.setOnClickListener(view -> {
                String text = list_items.get(getAdapterPosition());
                Toast.makeText(itemView.getContext(), "Elemento eliminado: "+text, Toast.LENGTH_SHORT).show();
                list_items.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
            });

        }

        void bindData(final String item) {
            tvItem.setText(item);
        }

    }

    public void setList_items(ArrayList<String> list_items) {
        this.list_items = list_items;
        notifyDataSetChanged();
    }
}
