package com.frankincense4ever.f4e;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Stress_Relief_Mixer_Menu extends AppCompatActivity {

    public void stress_relief_mixer_menu(View view) {
        Intent intent = new Intent(this,Stress_Relief_Mixer_Menu.class);
        startActivity(intent);
    }

    ImageButton just_chill;

    public void just_chill(View view) {
        Intent intent = new Intent(this,Just_Chill.class); //todo: note --> renamed just_chill class to just_chill_activity
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress__relief__mixer__menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
}
