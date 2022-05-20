package com.example.E_Graphics_and_Learning;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInFragment extends Fragment {
    private TextView accestosignup;
    private LinearLayout parentframlayout;

    private EditText email, passward;
    private Button signinbtn;

    private ProgressBar progressBar;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    private FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_in, container, false);
        accestosignup = view.findViewById(R.id.accestosignup);
        parentframlayout=getActivity().findViewById(R.id.register_layout);
        email= view.findViewById(R.id.edtemaillogin);
        passward= view.findViewById(R.id.edtpasswardlogin);
        signinbtn=view.findViewById(R.id.btn_login);
        progressBar=view.findViewById(R.id.signin_progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        accestosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignUpFragment());

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkinputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passward.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkinputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkEmailAndPassward();
            }
        });
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_out_from_left);
        fragmentTransaction.replace(parentframlayout.getId(),fragment);
        fragmentTransaction.commit();
    }


    // Button Disable and Enable
    private void checkinputs() {
        if(!TextUtils.isEmpty(email.getText()) ){
            if (!TextUtils.isEmpty(passward.getText())){
                signinbtn.setEnabled(true);
                signinbtn.setTextColor(Color.rgb(255,255,255));
                signinbtn.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(97,48,93)));
            }else{
                signinbtn.setEnabled(false);
                signinbtn.setTextColor(Color.argb(50, 255,255,255));
                signinbtn.setBackgroundTintList(ColorStateList.valueOf(Color.argb(50,255,255,255)));
            }
        }else{
            signinbtn.setEnabled(false);
            signinbtn.setTextColor(Color.argb(50, 255,255,255));
            signinbtn.setBackgroundTintList(ColorStateList.valueOf(Color.argb(50,255,255,255)));
        }
    }

    //check email and password
    private void checkEmailAndPassward() {
        if (email.getText().toString().equals(emailPattern)){
            if(passward.length() >=6){

                progressBar.setVisibility(View.VISIBLE);
                signinbtn.setEnabled(false);
                signinbtn.setTextColor(Color.argb(50, 255,255,255));
                signinbtn.setBackgroundTintList(ColorStateList.valueOf(Color.argb(50,255,255,255)));

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString().trim(),passward.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    Intent intent = new Intent(getActivity(), Home_page.class);
                                    startActivity(intent);
                                    getActivity().finish();

                                }else{
                                    progressBar.setVisibility(View.INVISIBLE);

                                    signinbtn.setEnabled(true);
                                    signinbtn.setTextColor(Color.argb(50, 255,255,255));
                                    signinbtn.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(97,48,93)));

                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                Toast.makeText(getActivity(),"incorrect email or password!",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(getActivity(),"incorrect email or password!",Toast.LENGTH_SHORT).show();


            }

        }else{

        }

    }
}