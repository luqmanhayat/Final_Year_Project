package com.example.E_Graphics_and_Learning;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    public SignUpFragment() {
        // Required empty public constructor
    }
    private TextView accestosignin;
    private LinearLayout parentframlayout;
    private EditText fisrtName, lastName, email, passward, confirmPassward;
    private Button buttonSignup;

    private FirebaseAuth mAuth;

    private FirebaseFirestore firebaseFirestore;

    private ProgressBar progressBar;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        accestosignin=view.findViewById(R.id.btn_login);

        parentframlayout=getActivity().findViewById(R.id.register_layout);

        fisrtName = view.findViewById(R.id.edtfirstname);
        lastName = view.findViewById(R.id.edtlastname);
        email = view.findViewById(R.id.edtemail);
        passward = view.findViewById(R.id.edtpassward);
        confirmPassward = view.findViewById(R.id.edtconfirmpassward);
        buttonSignup =  view.findViewById(R.id.btn_sigup);
        progressBar=view.findViewById(R.id.signup_progressBar);

        mAuth = FirebaseAuth.getInstance();

        firebaseFirestore = FirebaseFirestore.getInstance();

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        accestosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });

        //check inputs
        fisrtName.addTextChangedListener(new TextWatcher() {
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
        lastName.addTextChangedListener(new TextWatcher() {
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
        confirmPassward.addTextChangedListener(new TextWatcher() {
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

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmailAndPassward();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slide_out_from_right);
        fragmentTransaction.replace(parentframlayout.getId(),fragment);
        fragmentTransaction.commit();
    }
    //Enable Button
    @SuppressLint("ResourceAsColor")
    private void checkinputs() {
        if(!TextUtils.isEmpty(fisrtName.getText())){
            if(!TextUtils.isEmpty(lastName.getText())){
                if(!TextUtils.isEmpty(email.getText())){
                    if(!TextUtils.isEmpty(passward.getText()) && passward.length() >= 8){
                        if (!TextUtils.isEmpty(confirmPassward.getText())){
                            buttonSignup.setEnabled(true);
                            buttonSignup.setTextColor(Color.rgb(255,255,255));
                            buttonSignup.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(97,48,93)));
                        }else{
                            buttonSignup.setEnabled(false);
                            buttonSignup.setTextColor(Color.argb(50, 255,255,255));
                            buttonSignup.setBackgroundTintList(ColorStateList.valueOf(Color.argb(50,255,255,255)));
                        }
                    }else{
                        buttonSignup.setEnabled(false);
                        buttonSignup.setTextColor(Color.argb(50, 255,255,255));
                        buttonSignup.setBackgroundTintList(ColorStateList.valueOf(Color.argb(50,255,255,255)));
                    }
                }else{
                    buttonSignup.setEnabled(false);
                    buttonSignup.setTextColor(Color.argb(50, 255,255,255));
                    buttonSignup.setBackgroundTintList(ColorStateList.valueOf(Color.argb(50,255,255,255)));
                }

            }else{
                buttonSignup.setEnabled(false);
                buttonSignup.setTextColor(Color.argb(50, 255,255,255));
                buttonSignup.setBackgroundTintList(ColorStateList.valueOf(Color.argb(50,255,255,255)));
            }
        }else{
            buttonSignup.setEnabled(false);
            buttonSignup.setTextColor(Color.argb(50, 255,255,255));
            buttonSignup.setBackgroundTintList(ColorStateList.valueOf(Color.argb(50,255,255,255)));
        }
    }

    // Email Pattern & Match Passward
    private void checkEmailAndPassward() {
        Drawable yellowerror = getResources().getDrawable(R.drawable.ic_yellow_error);
        yellowerror.setBounds(0,0,yellowerror.getIntrinsicWidth(),yellowerror.getIntrinsicHeight());

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        if(email.getText().toString().matches(emailPattern)){
            if (passward.getText().toString().equals(confirmPassward.getText().toString())){

                progressBar.setVisibility(View.VISIBLE);

                buttonSignup.setEnabled(false);
                buttonSignup.setTextColor(Color.argb(50, 255,255,255));
                buttonSignup.setBackgroundTintList(ColorStateList.valueOf(Color.argb(50,255,255,255)));


                mAuth.createUserWithEmailAndPassword(email.getText().toString(),passward.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    Map<Object, String> userdata = new HashMap<>();
                                    userdata.put("FirstName",fisrtName.getText().toString());
                                    userdata.put("LasttName",lastName.getText().toString());

                                    firebaseFirestore.collection("USERS")
                                            .add(userdata)
                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                                    if (task.isSuccessful()){
                                                        Intent intent = new Intent(getActivity(), Home_page.class);
                                                        startActivity(intent);
                                                        getActivity().finish();

                                                    }else {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        buttonSignup.setEnabled(true);
                                                        buttonSignup.setTextColor(Color.rgb(255,255,255));
                                                        buttonSignup.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(97,48,93)));
                                                        String error = task.getException().getMessage();
                                                        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();

                                                    }

                                                }
                                            });
                                }
                                else if (!PASSWORD_PATTERN.matcher(passward.getText().toString().trim()).matches()){
                                    passward.setError("Password must contain special character Alphanumeric  upper/lower (Ali1234#)");
                                }
                                    else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    buttonSignup.setEnabled(true);
                                    buttonSignup.setTextColor(Color.rgb(255,255,255));
                                    buttonSignup.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(97,48,93)));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }else{
                confirmPassward.setError("Passward not matched!", yellowerror);
            }
        }else{
            email.setError("Invalid Email!", yellowerror);
        }
    }
}

