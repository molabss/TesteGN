package br.com.testmaster.view.adapter;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.testmaster.R;
import br.com.testmaster.domain.Address;
import br.com.testmaster.domain.OfferWrapper;

/**
 * Created by Moisés Santana on 05/10/2016.
 */

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {


    private OfferWrapper mOfferWrp;

    public static class OffersViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private TextView item_title;
        private TextView txtContr;
        private TextView data;
        private TextView local;

        public OffersViewHolder(View itemView) {
            super(itemView);
            item_title = (TextView) itemView.findViewById(R.id.item_title);
            txtContr = (TextView) itemView.findViewById(R.id.txtContr);
            data = (TextView) itemView.findViewById(R.id.data);
            local = (TextView) itemView.findViewById(R.id.local);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Click detected on item " + getAdapterPosition(),
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }


    public OffersAdapter(OfferWrapper mOfferWrp) {
        this.mOfferWrp = mOfferWrp;
    }

    @Override
    public OffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        OffersViewHolder ovh = new OffersViewHolder(v);
        return ovh;
    }


    @Override
    public void onBindViewHolder(OffersViewHolder holder, int position) {
        Address ad = mOfferWrp.getOffers().get(position).get_embedded().getRequest().get_embedded().getAddress();
        holder.item_title.setText(mOfferWrp.getOffers().get(position).get_embedded().getRequest().getTitle());
        holder.txtContr.setText(mOfferWrp.getOffers().get(position).get_embedded().getRequest().get_embedded().getUser().getName());
        holder.data.setText(mOfferWrp.getOffers().get(position).get_embedded().getRequest().getCreated_at());
        holder.local.setText(ad.getNeighborhood() + " - " + ad.getCity());
    }

    @Override
    public int getItemCount() {
        return mOfferWrp.getOffers().size();
    }
}