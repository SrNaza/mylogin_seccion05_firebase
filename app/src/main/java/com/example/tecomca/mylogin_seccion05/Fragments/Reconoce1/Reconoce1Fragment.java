package com.example.tecomca.mylogin_seccion05.Fragments.Reconoce1;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tecomca.mylogin_seccion05.R;

public class Reconoce1Fragment extends Fragment {

    Button btn_opc1, btn_opc2, btn_opc3, btn_opc4, btn_opc5, btn_opc6;
    ImageView imgView;

    public Reconoce1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reconoce1, container, false);

        initViews();

        return view;

    }

    private void initViews(){
        btn_opc1 = (Button) getActivity().findViewById(R.id.btn_ans_1);
        btn_opc2 = (Button) getActivity().findViewById(R.id.btn_ans_2);
        btn_opc3 = (Button) getActivity().findViewById(R.id.btn_ans_3);
        btn_opc4 = (Button) getActivity().findViewById(R.id.btn_ans_4);
        btn_opc5 = (Button) getActivity().findViewById(R.id.btn_ans_5);
        btn_opc6 = (Button) getActivity().findViewById(R.id.btn_ans_6);

        imgView= (ImageView) getActivity().findViewById(R.id.iv_image);
    }

}