package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public  interface OnClickListener {
        void onItemClicked(int position);
    }

    public interface OnLongClickListener { void onItemLongClick(int position); }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener onClickListener) {
        this.clickListener = onClickListener;
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    //creates each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflator to inflate view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        //wrap it inside a viewholder and return it
        return new ViewHolder(todoView);
    }

    @Override
    //responsible for binding data to a particular viewholder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //grab the item at the position
        String item = items.get(position);

        //Bind the item into the specified viewholder
        holder.bind(item);
    }

    @Override
    //Tells recycler view # of items in list
    public int getItemCount() {
        return items.size();
    }

    //Container to provide easy access to views that represent each row of the lists
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //update the view inside of the holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //notify listener which position was long pressed
                    longClickListener.onItemLongClick(getAdapterPosition());
                    return true;
                }

            });

        }

    }

}
