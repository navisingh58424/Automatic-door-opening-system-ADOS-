package com.example.ss;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag_three extends Fragment {
 TextView tv1;
 Button bt1,bt2,bt3;
 EditText et1,et2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_three, container, false);
        tv1=v.findViewById(R.id.textView17);
        tv1.setMovementMethod(new ScrollingMovementMethod());
        bt1=v.findViewById(R.id.button14);
        bt2=v.findViewById(R.id.button15);
        bt3=v.findViewById(R.id.button16);
        et1=v.findViewById(R.id.editTextTextPersonName17);
        et2=v.findViewById(R.id.editTextTextPersonName18);
        //display user details
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mgr_pass="we";
                CompanyDb db=new CompanyDb(MainActivity.getInstance());
                db.open();
                String s1=et1.getText().toString();
                String s2=et2.getText().toString();
                if(s1.equals(mgr_pass)) {
                    String result = db.fetchData(s2);
                    String gg="";
                    for(int j=0;j<result.length();j++)
                    {
                        if(result.charAt(j)==' '||result.charAt(j)==';')
                            gg=gg+"\n";
                        else
                            gg=gg+result.charAt(j);
                    }
                    tv1.setText("USER DETAILS ARE "+gg);
                }
                else
                {
                    String result="enter valid password";
                    tv1.setText(result);
                }
                db.close();
            }
        });
        //flag for user
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mgr_pass="we";
                FlagDb db=new FlagDb(MainActivity.getInstance());
                db.open();
                String s1=et1.getText().toString();
                String s2=et2.getText().toString();
                if(s1.equals(mgr_pass)) {
                    String result = db.fetchData(s2);
                    String gg="";
                    for(int j=0;j<result.length();j++)
                    {
                        if(result.charAt(j)==' '||result.charAt(j)==';')
                            gg=gg+"\n";
                        else
                            gg=gg+result.charAt(j);
                    }
                    tv1.setText("USER FLAG IS -"+gg);
                }
                else
                {
                    String result="enter valid password";
                    tv1.setText(result);
                }
                db.close();
            }
        });
        //clear flag data
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mgr_pass="we";
                FlagDb db=new FlagDb(MainActivity.getInstance());
                db.open();
                String s1=et1.getText().toString();
                String s2=et2.getText().toString();
                if(s1.equals(mgr_pass)) {
                db.updateEntry(s2,"none");
                }
                else
                {
                    String result="enter valid password";
                    tv1.setText(result);
                }
            }
        });
        return v;
    }

}