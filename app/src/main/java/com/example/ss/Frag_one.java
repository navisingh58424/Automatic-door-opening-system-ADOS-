package com.example.ss;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_one} factory method to
 * create an instance of this fragment.
 */
public class Frag_one extends Fragment {
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15;
    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11,bt12,bt13;
    EditText et0,et1,et2,et3,et4,et5,et6,et7,et8,et9,et10,et11,et12,et13,et14,et15;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_frag_one,container,false);
        et0=v.findViewById(R.id.editTextTextPersonName);
        et1=v.findViewById(R.id.editTextTextPersonName2);
        et2=v.findViewById(R.id.editTextTextPersonName3);
        et3=v.findViewById(R.id.editTextTextPersonName4);
        et4=v.findViewById(R.id.editTextTextPersonName5);
        et5=v.findViewById(R.id.editTextTextPersonName6);
        et6=v.findViewById(R.id.editTextTextPersonName8);
        et7=v.findViewById(R.id.editTextTextPersonName9);
        et8=v.findViewById(R.id.editTextTextPersonName10);
        et9=v.findViewById(R.id.editTextTextPersonName11);
        et10=v.findViewById(R.id.editTextTextPersonName12);
        et11=v.findViewById(R.id.editTextTextPersonName13);
        et12=v.findViewById(R.id.editTextTextPersonName14);
        et13=v.findViewById(R.id.editTextTextPersonName15);
        et14=v.findViewById(R.id.editTextTextPersonName16);

        tv1=v.findViewById(R.id.textView);
        tv2=v.findViewById(R.id.textView4);
        tv3=v.findViewById(R.id.textView5);
        tv4=v.findViewById(R.id.textView6);
        tv5=v.findViewById(R.id.textView7);
        tv6=v.findViewById(R.id.textView8);
        tv7=v.findViewById(R.id.textView9);
        tv8=v.findViewById(R.id.textView10);
        tv9=v.findViewById(R.id.textView11);
        tv10=v.findViewById(R.id.textView12);
        tv11=v.findViewById(R.id.textView13);
        tv12=v.findViewById(R.id.textView14);
        tv13=v.findViewById(R.id.textView15);
        tv14=v.findViewById(R.id.textView16);

        bt1=v.findViewById(R.id.button);
        bt2=v.findViewById(R.id.button2);
        bt3=v.findViewById(R.id.button3);
        bt4=v.findViewById(R.id.button4);
        bt5=v.findViewById(R.id.button5);
        bt6=v.findViewById(R.id.button6);
        bt7=v.findViewById(R.id.button7);
        bt8=v.findViewById(R.id.button8);
        bt9=v.findViewById(R.id.button9);
        bt10=v.findViewById(R.id.button10);
        bt11=v.findViewById(R.id.button11);
        bt12=v.findViewById(R.id.button12);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mgrpass="we";
                String s0=et0.getText().toString();
                String s1=et1.getText().toString();
                String s2=et2.getText().toString();
                String s3=et3.getText().toString();
                String s4=et4.getText().toString();
                String s5=et5.getText().toString();
                String s6=et6.getText().toString();
                String s7=et7.getText().toString();
                String s8=et8.getText().toString();
                String s9=et10.getText().toString();
                String s10=et11.getText().toString();
                String s11=et13.getText().toString();
                et0.setText("");
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
                et5.setText("");
                et6.setText("");
                et7.setText("");
                et8.setText("");
                et10.setText("");
                et11.setText("");
                et13.setText("");

                String times="sun-"+s5+"mon-"+s6+"tue-"+s7+"wed-"+s8+"thu-"+s9+"fri-"+s10+"sat-"+s11;
                //Toast.makeText(MainActivity.getInstance(),"pass "+s0+" "+mgrpass,Toast.LENGTH_LONG).show();
                if(s0.equals("we"))
                {
                    CompanyDb db=new CompanyDb(MainActivity.getInstance());
                    db.open();
                    String result=db.fetchData(s1);
                    String KEY_ROWID="_id";
                    String KEY_NAME="person_name";
                    String KEY_MAIL="person_mail";
                    String KEY_PHONE="person_phone";
                    String KEY_OUT="out_time";

                   /* int iRowID = cursor2.getColumnIndex(KEY_ROWID);
                    int iName = cursor2.getColumnIndex(KEY_NAME);
                    int iCell = cursor2.getColumnIndex(KEY_MAIL);
                    int iPhone=cursor2.getColumnIndex(KEY_PHONE);
                    int iTime=cursor2.getColumnIndex(KEY_OUT);*/
                    if(result.equals(""))
                    {
                        db.close();
                        try {
                            Log.d("we are creating ","thread "+Thread.currentThread());
                            CompanyDb ddb = new CompanyDb(MainActivity.getInstance());
                            ddb.open();
                            ddb.createEntry(s1, s2, s3,s4,times);
                            ddb.close();
                            Toast.makeText(MainActivity.getInstance(), "Successfully saved", Toast.LENGTH_SHORT).show();
                        } catch (SQLException e) {
                            Toast.makeText(MainActivity.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        db.close();
//                        Log.d("we are destroying ","thread "+Thread.currentThread()+" ");
//                        try{
//                            CompanyDb dbb=new CompanyDb(MainActivity.getInstance());
//                            dbb.open();
//                            dbb.deleteEntry(s1);
//                            dbb.close();
//                            Toast.makeText(MainActivity.getInstance(),"Sucessfully deleted||",Toast.LENGTH_SHORT).show();
//                        }
//                        catch (android.database.SQLException e)
//                        {
//                            Toast.makeText(MainActivity.getInstance(),e.getMessage(),Toast.LENGTH_SHORT).show();
//                        }
//                        try {
//                            Log.d("we are creating ","thread "+Thread.currentThread());
//                            CompanyDb ddb = new CompanyDb(MainActivity.getInstance());
//                            ddb.open();
//                            ddb.createEntry(s1, s2, s3,s4,times);
//                            ddb.close();
//                            Toast.makeText(MainActivity.getInstance(), "Successfully saved", Toast.LENGTH_SHORT).show();
//                        } catch (SQLException e) {
//                            Toast.makeText(MainActivity.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
                        Toast.makeText(MainActivity.getInstance(),"already a member of company ",Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                }
                else
                { Toast.makeText(MainActivity.getInstance(),"entervalid manager pass ",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mgrpass="we";
                Log.d("habhaiandr","thread "+Thread.currentThread());
                String s0=et0.getText().toString();
                Log.d("habhaiand1r","thread "+Thread.currentThread());
                String s1=et1.getText().toString();
                Log.d("habhaiand2r","thread "+Thread.currentThread());
                /*String s2=et2.getText().toString();
                String s3=et3.getText().toString();
                String s4=et4.getText().toString();
                String s5=et5.getText().toString();
                String s6=et6.getText().toString();
                String s7=et7.getText().toString();
                String s8=et8.getText().toString();
                String s9=et10.getText().toString();
                String s10=et11.getText().toString();
                String s11=et13.getText().toString();*/
                //String times="sun-"+s5+"mon-"+s6+"tue-"+s7+"wed-"+s8+"thu-"+s9+"fri-"+s10+"sat-"+s11;
                //Toast.makeText(MainActivity.getInstance(),"pass "+s0+" "+mgrpass,Toast.LENGTH_LONG).show();
                et0.setText("");
                et1.setText("");
                if(s0.equals("we"))
                {
                    try{
                        CompanyDb dbb=new CompanyDb(MainActivity.getInstance());
                        dbb.open();
                        dbb.deleteEntry(s1);
                        dbb.close();
                        Registered dbbbb=new Registered(MainActivity.getInstance());
                        dbbbb.open();
                        dbbbb.deleteEntry(s1);
                        dbbbb.close();
                        FlagDb ffd=new FlagDb(MainActivity.getInstance());
                        ffd.open();
                        ffd.deleteEntry(s1);
                        ffd.close();
                        ContactsDb dfs=new ContactsDb(MainActivity.getInstance());
                        dfs.open();
                        dfs.deleteEntry(s1);
                        dfs.close();
                        Toast.makeText(MainActivity.getInstance(),"Sucessfully deleted||",Toast.LENGTH_SHORT).show();
                    }
                    catch (android.database.SQLException e)
                    {
                        Toast.makeText(MainActivity.getInstance(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.getInstance(),"enter correct password",Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }




}