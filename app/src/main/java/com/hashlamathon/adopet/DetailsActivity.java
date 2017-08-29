package com.hashlamathon.adopet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText(textView.getText() + " Rex");

        textView = (TextView) findViewById(R.id.age);
        textView.setText(textView.getText() + " 2 Months");

        textView = (TextView) findViewById(R.id.gender);
        textView.setText(textView.getText() + " Male");

        textView = (TextView) findViewById(R.id.weight);
        textView.setText(textView.getText() + " 1kg");

        textView = (TextView) findViewById(R.id.gender);
        textView.setText(textView.getText() + " Male");

        textView = (TextView) findViewById(R.id.castrated);
        textView.setText(textView.getText() + " No");

        textView = (TextView) findViewById(R.id.purebread);
        textView.setText(textView.getText() + " Yes");
    }
}
