package com.bitspilani.apogeear.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bitspilani.apogeear.LoginActivity;
import com.bitspilani.apogeear.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Time;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    private CountDownTimer countDownTimer;
    private long totalTimeCountInMilliseconds;
    private long startTime,endTime;
    TextView showtime;
    ImageView logout;
    FirebaseAuth mAuth;
    ProgressBar timer;
    private long timeBlinkInMilliseconds; // start time of start blinking
    private boolean blink;
    Calendar c;
    GoogleSignInOptions gso;
    GoogleSignInClient googleSignInClient;


    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showtime = view.findViewById(R.id.tvTimeCount);
        timer = view.findViewById(R.id.progressbar);
        logout=view.findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        c=Calendar.getInstance();
        c.getTimeInMillis();
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();
        googleSignInClient= GoogleSignIn.getClient(getActivity(),gso);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignInClient.signOut();
                mAuth.signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                Toast.makeText(getContext(),"Signed Out",
                        Toast.LENGTH_SHORT).show();
            }
        });


        db.collection("Coins").document("Universal Coins").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot documentSnapshot=task.getResult();
                        totalTimeCountInMilliseconds=documentSnapshot.getTimestamp("Expire Time").getSeconds()-c.getTimeInMillis()/1000;
                        startTime=documentSnapshot.getTimestamp("Start Time").getSeconds()*1000;
                        endTime=documentSnapshot.getTimestamp("Expire Time").getSeconds()*1000;
                        setTimer();
                    }
                });
        return view;
    }

    private void setTimer() {
        int time = 0;
        //Toast.makeText(getContext(), "Please Enter Minutes...",
          //      Toast.LENGTH_LONG).show();

        totalTimeCountInMilliseconds=totalTimeCountInMilliseconds*1000;

        //totalTimeCountInMilliseconds = 60 * time * 1000;

        timeBlinkInMilliseconds = 30 * 1000;

        startTimer();

    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 1000) {
            // 500 means, onTick function will be called at every 500
            // milliseconds

            @Override
            public void onFinish() {

            }

            @Override
            public void onTick(long leftTimeInMilliseconds) {

                //Log.d("Time left",""+leftTimeInMilliseconds);
                int minutes = (int) ((leftTimeInMilliseconds / (1000 * 60)) % 60);
                int seconds = (int) (leftTimeInMilliseconds / 1000) % 60;
                int hours = (int) ((leftTimeInMilliseconds/ (1000 * 60 * 60)) % 24);
                //i++;
                //Setting the Progress Bar to decrease wih the timer
                Log.d("total count",""+(endTime-startTime));
                Log.d("lefttt",""+leftTimeInMilliseconds);
                if(endTime!=startTime) {
                    timer.setMax((int)(endTime-startTime));
                    timer.setProgress((int) (endTime-startTime-leftTimeInMilliseconds));
                }
                else
                    timer.setProgress(0);


                if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {
                    // change the style of the textview .. giving a red
                    // alert style

                    if (blink) {
                        showtime.setVisibility(View.VISIBLE);
                        // if blink is true, textview will be visible
                    } else {
                        showtime.setVisibility(View.INVISIBLE);
                    }

                    blink = !blink; // toggle the value of blink
                }

                showtime.setText(hours+" hrs "+minutes+" min "+seconds+" sec");
                // format the textview to show the easily readable format

            }

        }.start();
    }
}
