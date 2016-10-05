package br.com.testmaster.view.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.testmaster.R;

/**
 * Created by Moisés Santana on 05/10/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter{

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CardViewHolder holder = (CardViewHolder)viewHolder;
        //holder.item_title
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView item_title;
        private TextView txtContr;
        private TextView data;
        private TextView local;

        public CardViewHolder(View itemView) {
            super(itemView);
            item_title = (TextView) itemView.findViewById(R.id.item_title);
            txtContr = (TextView) itemView.findViewById(R.id.txtContr);
            data = (TextView) itemView.findViewById(R.id.data);
            local = (TextView) itemView.findViewById(R.id.local);
        }
    }
}
