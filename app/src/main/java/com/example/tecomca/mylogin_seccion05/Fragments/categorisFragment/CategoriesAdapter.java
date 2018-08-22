package com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tecomca.mylogin_seccion05.Fragments.InforFragment;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private final ComunViews comunViews;
    private List<String> names;
    private List<Integer> images;

    public CategoriesAdapter(List<String> name, List<Integer> images, ComunViews comunViews) {
        this.names = name;
        this.images = images;
        this.comunViews = comunViews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_categories, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_name.setText(this.names.get(position));
//        holder.iv_logo.setImageResource(R.drawable.bella);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunViews.changeFragment(new InforFragment());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout item;
        TextView tv_name;
        ImageView iv_logo;

        public ViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.item);
            tv_name = view.findViewById(R.id.tv_name);
            iv_logo = view.findViewById(R.id.iv_image);
        }

    }

}