package com.example.tecomca.mylogin_seccion05.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tecomca.mylogin_seccion05.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment {


    private Button btnFirebase;
    private DatabaseReference mDatabase;

    private EditText mNameField;
    private EditText mEmailField;

    public EmailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);

        btnFirebase = (Button) view.findViewById(R.id.btnFirebase);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Email");

        mNameField = (EditText) view.findViewById(R.id.name_field);
        mEmailField = (EditText) view.findViewById(R.id.email_field);



        btnFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1- crear un hijo en nuestro root object
                // 2 - assign some value to the child object

                // aqui reescribe el qe ya esta .child("Name") con .push() pone otro
                String name = mNameField.getText().toString().trim();
                String email = mEmailField.getText().toString().trim();

                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Name", name);
                dataMap.put("Email", email);



                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "Stored..", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "Error..", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
