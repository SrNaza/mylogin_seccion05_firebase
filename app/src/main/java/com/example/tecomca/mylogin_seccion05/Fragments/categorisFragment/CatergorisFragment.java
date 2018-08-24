package com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;

import java.util.ArrayList;
import java.util.List;

public class CatergorisFragment extends Fragment {

    private ComunViews comunViews;

    private RecyclerView listCategories;

    private CategoriesAdapter adapter;

    private List<String> nombres;
    private List<Integer> images;

    private int asdf;
    public CatergorisFragment() {
        // Required empty public constructor
    }

    public static CatergorisFragment newInstance(ComunViews cv) {
        Bundle args = new Bundle();
        args.putParcelable("comun", cv);
        CatergorisFragment fragment = new CatergorisFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comunViews = (ComunViews) getArguments().getParcelable("comun");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        initAll(view);
        return view;
    }

    public void initAll(View view){
        listCategories = view.findViewById(R.id.listCategories);
        nombres = new ArrayList<>();
        images = new ArrayList<>();
        nombres.add("Área fisico y personal");
        nombres.add("Área psicomotriz");
        nombres.add("Área intelectual");
        nombres.add("Área escolar");
        nombres.add("Área Habitos");
        nombres.add("Área emocional solcial");
        adapter = new CategoriesAdapter(nombres,this.comunViews);
        listCategories.setLayoutManager(new GridLayoutManager(getContext(), 2));
        listCategories.setAdapter(adapter);
    }

}