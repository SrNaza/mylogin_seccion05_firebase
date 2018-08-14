package com.example.tecomca.mylogin_seccion05.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tecomca.mylogin_seccion05.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment {


    private Button btnFirebase;
    private DatabaseReference mDatabase;

    public EmailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);

        btnFirebase = (Button) view.findViewById(R.id.btnFirebase);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1- crear un hijo en nuestro root object
                // 2 - assign some value to the child object

                mDatabase.child("Name").setValue("Nazareth villalba");

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
