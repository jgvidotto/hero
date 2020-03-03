package com.jgvidotto.hero.ui.main.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jgvidotto.hero.R;
import com.jgvidotto.hero.ui.main.CircleTransform;
import com.jgvidotto.hero.ui.main.Model.CharacterDataWrapper;
import com.squareup.picasso.Picasso;


public class HeroAdapter  extends RecyclerView.Adapter<HeroAdapter.CustomViewHolder> {

    private CharacterDataWrapper dataList;
    private Context context;
    private OnHeroListener mOnHeroListener;

    public HeroAdapter(Context context, CharacterDataWrapper dataList, OnHeroListener onHeroListener){
        this.context = context;
        this.dataList = dataList;
        this.mOnHeroListener = onHeroListener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View mView;

        TextView txtTitle;
        private ImageView heroImage;
        OnHeroListener onHeroListener;

        CustomViewHolder(View itemView, OnHeroListener onHeroListener) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);
            heroImage = mView.findViewById(R.id.heroImage);

            this.onHeroListener = onHeroListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onHeroListener.onHeroClick(getAdapterPosition());

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.hero_row, parent, false);
        return new CustomViewHolder(view, mOnHeroListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.getData().getResults().get(position).getName());
        Picasso.get().load(dataList.getData().getResults().get(position).getThumbnail().getPath()).transform(new CircleTransform()).into(holder.heroImage);

    }

    @Override
    public int getItemCount() {
        return dataList.getData().getResults().size();
    }

    public interface OnHeroListener {
        void onHeroClick(int position);
    }
}
