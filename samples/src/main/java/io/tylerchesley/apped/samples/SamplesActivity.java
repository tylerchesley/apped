package io.tylerchesley.apped.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.tylerchesley.apped.Apped;
import io.tylerchesley.apped.Layout;
import io.tylerchesley.apped.Title;

@Title(R.string.app_name)
@Layout(R.layout.activity_samples)
public class SamplesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Apped.configure(this);
    }

}
