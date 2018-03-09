package com.nataliafavero.iddog.ui.menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nataliafavero.iddog.R;

/**
 * Created by nataliafavero on 06/03/18.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private String[] dataSource;
    private static RecyclerViewClickListener itemListener;
    private Context context;

    public static class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;

        public MenuViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_dog);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }
    }

    public MenuAdapter(String[] dataArgs, Context context, RecyclerViewClickListener itemListener) {
        dataSource = dataArgs;
        this.itemListener = itemListener;
        this.context = context;
    }


    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MenuViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_menu, parent, false));


    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.colorEven));
        } else {
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.coloOdd));
        }
        holder.textView.setText(dataSource[position]);
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }
}
