package com.example.tecomca.mylogin_seccion05.Fragments.Juegos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tecomca.mylogin_seccion05.Fragments.Reconoce1.Reconoce1Fragment;
import com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment.CategoriesAdapter;
import com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment.CatergorisFragment;
import com.example.tecomca.mylogin_seccion05.Model.Category;
import com.example.tecomca.mylogin_seccion05.Model.Juegos;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;

import java.util.ArrayList;
import java.util.List;


public class ListaJuegosFragment extends Fragment implements ListaJuegosAdapter.OnItemClickListener {

    private ComunViews comunViews;

    private RecyclerView recyclerJuegos;

    private ListaJuegosAdapter adapter;

    private List<String> nombres;
    private List<Integer> images;
    private List<String> listNombres;
    private List<Juegos> juegos;
    private List<Integer> listFotos;

    private int asdf;
    private final String TAG=CatergorisFragment.class.getSimpleName();

    public ListaJuegosFragment() {
        // Required empty public constructor
    }

    public void initListImagenes(){
        listFotos = new ArrayList<>();
        listFotos.add(R.drawable.kids);
        listFotos.add(R.drawable.buho);
        listFotos.add(R.drawable.friendship);
        listFotos.add(R.drawable.caras);
    }

    public void initListNombres(){
        listNombres = new ArrayList<>();
        listNombres.add("Reconocer imagen");
        listNombres.add("Reconocer dias semanales");
        listNombres.add("Reconocer meses");
        listNombres.add("El dos mas dos");

    }

    public static ListaJuegosFragment newInstance(ComunViews cv) {
        Bundle args = new Bundle();
        args.putParcelable("comun", cv);
        ListaJuegosFragment fragment = new ListaJuegosFragment();
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
        View view = inflater.inflate(R.layout.fragment_juegos, container, false);
        recyclerJuegos = view.findViewById(R.id.listJuegos);
        // /initAll(view);
        RecyclerViewUpdate();
        initListImagenes();
        initListNombres();
        getDataFake();
        return view;
    }

    public void getDataFake(){
        List<Juegos> list= new ArrayList<>();
        for (int i=0;i<4;i++){
            list.add(new Juegos(i,listNombres.get(i),listFotos.get(i)));
        }
        juegos=list;
        RecyclerViewUpdate();
    }

    public void RecyclerViewUpdate() {
        recyclerJuegos.setLayoutManager(new GridLayoutManager(getContext(), 2));
        if (adapter == null) {
            //Log.i(TAG, "--->listadoAdapter null");
            juegos = new ArrayList<>();
            adapter = new ListaJuegosAdapter(juegos, getContext());
            adapter.setOnItemClickListener(this);
        } else {
            //Log.i(TAG, "--->listadoAdapter update");
            adapter.updateAll(juegos);
        }
        recyclerJuegos.setAdapter(adapter);
    }

    @Override
    public void onClickSelectedItem(Juegos juegos) {
        Log.i(TAG,"--->clickeado"+ juegos.getName());
        comunViews.changeFragment(new Reconoce1Fragment());
    }
}