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
import com.example.tecomca.mylogin_seccion05.Fragments.Reconoce1.Reconoce1Fragment;
import com.example.tecomca.mylogin_seccion05.Model.Category;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private final ComunViews comunViews;
    private List<Category> categories;

    public CategoriesAdapter(List<Category> categories, ComunViews comunViews) {
        this.categories = categories;
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
        holder.tv_name.setText(this.categories.get(position).getName());
//        holder.iv_logo.setImageResource(R.drawable.bella);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunViews.changeFragment(new Reconoce1Fragment());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
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