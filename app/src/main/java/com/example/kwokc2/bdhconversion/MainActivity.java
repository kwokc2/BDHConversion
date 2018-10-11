package com.example.kwokc2.bdhconversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.util.Arrays;

import com.example.kwokc2.bdhconversion.R;

public class MainActivity extends AppCompatActivity {
    private EditText decimal;
    private EditText binary;
    private EditText hex;
    private Button convert;
    private Boolean error;
    private String lastClicked;

    static String decToBinary(int n)
    {
        String binaryNum = "";
        int i = 0;
        while (n > 0)
        {
            binaryNum = Integer.toString(n % 2) + binaryNum;
            n = n / 2;
            i++;
        }
        return binaryNum;
    }
    static String hexToDecimal(String n)
    {
        String digits = "0123456789ABCDEF";
        n = n.toUpperCase();
        int val = 0;
        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return Integer.toString(val);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decimal = (EditText) findViewById(R.id.decimal);
        binary = (EditText) findViewById(R.id.binary);
        hex = (EditText) findViewById(R.id.hex);
        convert = (Button) findViewById(R.id.convert);
        error = false;
        decimal.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lastClicked = "decimal";
            }
        });

        binary.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lastClicked = "binary";
            }
        });
        hex.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lastClicked = "hex";
            }
        });
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error = false;
                if((lastClicked == "decimal") && (decimal.getText().length() > 0))
                {
                    int decIn = Integer.parseInt(decimal.getText().toString());
                    String binOut = decToBinary(decIn);
                    binary.setText(binOut);
                    String hexOut = (Integer.toHexString(decIn)).toUpperCase();
                    hex.setText(hexOut);
                }
                else if ((lastClicked == "binary") && binary.getText().length()>0)
                {
                    for (int i=0; i<binary.getText().length(); i++)
                        if (binary.getText().charAt(i) != '1' && binary.getText().charAt(i) != '0')
                            error = true;
                    if (error) {
                        Toast toast = Toast.makeText(MainActivity.this,"Only enter 1s and 0s for binary!",Toast.LENGTH_LONG);
                        toast.show(); }
                        else
                   {
                        String binIn =  binary.getText().toString();
                        int decOut = Integer.parseInt(binIn, 2);
                        decimal.setText(Integer.toString(decOut));
                        String hexOut = (Integer.toHexString(decOut)).toUpperCase();
                        hex.setText(hexOut);
                   }
                }
                else if ((lastClicked == "hex") && (hex.getText().length() > 0))
                {
                    for (int i = 0; i < hex.getText().length(); i++)
                        if ("1234567890ABCDEFabcdef".indexOf(hex.getText().charAt(i)) == -1)
                            error = true;
                    if (error)
                    {
                        Toast toast = Toast.makeText(MainActivity.this,"Only enter numbers and letters ABCDEF for Hex!",Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else
                        {
                        int decOut = Integer.parseInt(hexToDecimal(hex.getText().toString()));
                        decimal.setText(Integer.toString(decOut));
                        String binOut = decToBinary(decOut);
                        binary.setText(binOut);
                        hex.setText(hex.getText().toString().toUpperCase()); //optional: changes hex input to uppercase
                    }
                }
                else{
                    Toast toast= Toast.makeText(MainActivity.this,"Don't forget to enter a number into one of the boxes!",Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

    }
}