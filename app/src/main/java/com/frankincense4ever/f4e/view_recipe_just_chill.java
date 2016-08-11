package com.frankincense4ever.f4e;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class view_recipe_just_chill extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe_just_chill);

        Button Just_Chill;
        Just_Chill = (Button) findViewById(R.id.Just_Chill);
        Button view_recipe_just_chill;
        view_recipe_just_chill = (Button)findViewById(R.id.view_recipe_just_chill2);
        view_recipe_just_chill.setEnabled(false); //TODO: does this do what we want? We want to disable by default until purchased

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
