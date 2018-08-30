package com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment;

import android.icu.util.ULocale;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tecomca.mylogin_seccion05.Fragments.InforFragment;
import com.example.tecomca.mylogin_seccion05.Model.Category;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CatergorisFragment extends Fragment implements CategoriesAdapter.OnItemClickListener{

    private ComunViews comunViews;

    private RecyclerView recyclerCategories;

    private CategoriesAdapter adapter;

    private List<String> nombres;
    private List<Integer> images;
    private List<Category> categorias;
    private List<String> listFotos;

    private int asdf;
    private final String TAG=CatergorisFragment.class.getSimpleName();

    public CatergorisFragment() {
        // Required empty public constructor
    }

    public void initListImagenes(){
        listFotos = new ArrayList<>();
        listFotos.add("https://img.elcomercio.pe/files/article_content_ec_fotos/uploads/2018/02/22/5a8f26856f0b8.jpeg");
        listFotos.add("http://mouse.latercera.com/wp-content/uploads/2018/03/goku.jpg");
        listFotos.add("https://img.elcomercio.pe/files/article_content_ec_fotos/uploads/2018/02/22/5a8f26856f0b8.jpeg");
        listFotos.add("http://mouse.latercera.com/wp-content/uploads/2018/03/goku.jpg");
        listFotos.add("https://img.elcomercio.pe/files/article_content_ec_fotos/uploads/2018/02/22/5a8f26856f0b8.jpeg");
        listFotos.add("http://mouse.latercera.com/wp-content/uploads/2018/03/goku.jpg");
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
        recyclerCategories = view.findViewById(R.id.listCategories);
        // /initAll(view);
        RecyclerViewUpdate();
        initListImagenes();
        getDataFake();
        return view;
    }

    public void getDataFake(){
        List<Category> list= new ArrayList<>();
        for (int i=0;i<6;i++){
            list.add(new Category(i,"Nombre "+i,listFotos.get(i)));
        }
        categorias=list;
        RecyclerViewUpdate();
    }

    public void RecyclerViewUpdate() {
        recyclerCategories.setLayoutManager(new GridLayoutManager(getContext(), 2));
        if (adapter == null) {
            //Log.i(TAG, "--->listadoAdapter null");
            categorias = new ArrayList<>();
            adapter = new CategoriesAdapter(categorias, getContext());
            adapter.setOnItemClickListener(this);
        } else {
            //Log.i(TAG, "--->listadoAdapter update");
            adapter.updateAll(categorias);
        }
        recyclerCategories.setAdapter(adapter);
    }

//    public void initAll(View view){
//        listCategories = view.findViewById(R.id.listCategories);
//        nombres = new ArrayList<>();
//        images = new ArrayList<>();
//        categorias = new ArrayList<>();
//        nombres.add("Área fisico y personal");
//        nombres.add("Área psicomotriz");
//        nombres.add("Área intelectual");
//        nombres.add("Área escolar");
//        nombres.add("Área Habitos");
//        nombres.add("Área emocional solcial");
////        adapter = new CategoriesAdapter(nombres,this.comunViews);
////        listCategories.setLayoutManager(new GridLayoutManager(getContext(), 2));
////        listCategories.setAdapter(adapter);
//    }

    @Override
    public void onClickSelectedItem(Category category) {
        Log.i(TAG,"--->clickeado"+ category.getName());
        comunViews.changeFragment(new InforFragment());
    }
}