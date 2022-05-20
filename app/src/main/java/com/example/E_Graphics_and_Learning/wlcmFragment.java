package com.example.E_Graphics_and_Learning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class wlcmFragment extends Fragment {


    public wlcmFragment() {
        // Required empty public constructor
    }
    private Button btnloginwlcm;
    private  Button btnsignupwlcm;
    private LinearLayout parentframlayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_wlcm, container, false);

        btnloginwlcm = view.findViewById(R.id.btn_loginwlcm);
        btnsignupwlcm = view.findViewById(R.id.btn_signupwlcm);
        parentframlayout=getActivity().findViewById(R.id.register_layout);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnloginwlcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });

        btnsignupwlcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignUpFragment());
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_out_from_left);
        fragmentTransaction.replace(parentframlayout.getId(),fragment);
        fragmentTransaction.commit();

    }
}