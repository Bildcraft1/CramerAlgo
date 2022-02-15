package com.whixard.crameralgo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input_X1, input_X2, input_Y1, input_Y2, input_Risultato1, input_Risultato2;
    TextView risultato_X, risultato_Y;
    Button button;

    static private String convertDecimalToFraction(double x){
        if (x < 0){
            return "-" + convertDecimalToFraction(-x);
        }
        double tolerance = 1.0E-6;
        double h1=1; double h2=0;
        double k1=0; double k2=1;
        double b = x;
        do {
            double a = Math.floor(b);
            double aux = h1; h1 = a*h1+h2; h2 = aux;
            aux = k1; k1 = a*k1+k2; k2 = aux;
            b = 1/(b-a);
        } while (Math.abs(x-h1/k1) > x*tolerance);

        return h1+"/"+k1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_X1 = (EditText) findViewById(R.id.input_X1);
        input_X2 = (EditText) findViewById(R.id.input_X2);
        input_Y1 = (EditText) findViewById(R.id.input_Y1);
        input_Y2 = (EditText) findViewById(R.id.input_Y2);
        input_Risultato1 = (EditText) findViewById(R.id.input_Risultato1);
        input_Risultato2 = (EditText) findViewById(R.id.input_Risultato2);
        risultato_X = (TextView) findViewById(R.id.risultatoX2);
        risultato_Y = (TextView) findViewById(R.id.risultatoY);
        button = (Button) findViewById(R.id.button);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    // Import first Equation
                    String aStr = input_X1.getText().toString();
                    double a = Double.parseDouble(aStr);
                    String bStr = input_Y1.getText().toString();
                    double b = Double.parseDouble(bStr);
                    String r1Str = input_Risultato1.getText().toString();
                    double r1 = Double.parseDouble(r1Str);

                    // Import second Equation
                    String cStr = input_X2.getText().toString();
                    double c = Double.parseDouble(cStr);
                    String dStr = input_Y2.getText().toString();
                    double d = Double.parseDouble(dStr);
                    String r2Str = input_Risultato2.getText().toString();
                    double r2 = Double.parseDouble(r2Str);

                    double x = ((r1*d)-(b*r2))/((a*d)-(b*c));
                    double y = ((a*r2)-(r1*c))/((a*d)-(b*c));

                    risultato_X.setText("X: " + convertDecimalToFraction(x));
                    risultato_Y.setText("Y: " + convertDecimalToFraction(y));
                }
                catch(Exception e) {
                    risultato_X.setTextColor(Color.parseColor("#FF5733"));
                    risultato_X.setText("Valori inseriti incorretti");
                    risultato_Y.setText("");
                }
            }
        });
    }

}