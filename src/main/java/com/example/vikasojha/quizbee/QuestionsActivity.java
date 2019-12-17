package com.example.vikasojha.quizbee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    String questions[] = {
                            "Mida teha, kui mootori õlirõhu märgutuli süttib ja jääb püsivalt põlema?",
                            "Kui kaugele tohib veos sõldukist ette- või tahapoole ulatuda, ilma et oleks vajalik seda tähistada?",
                            "Kas autorongil, mille veduk on B- kategooria auto, peab olema autorongi tunnusmärk?",
                            "Kuidas tuleks käituda, kui buss on ühissõiduki¬peatuses peatunud?",
                            "Milliseid liikluskindlustuse lepinguid sõlmitakse kehtiva liikluskindlustuse seaduse alusel?",
                            "Te jõuate ootamatult teeosale, mis on kaetud lahtise killustikuga. Kuidas käitute?",
                            "Mida toob kaasa sõit suurel kiirusel?",
                            "Kas eritasandiliste teede lõikumise koht on ristmik?",
                            "Alates millisest minimaalsest alkoholisisaldusest juhi veres tuleb arvestada mõjuga juhi juhtimisvõimele?",
                            "Mis põhjustab auto ülemäärast õõtsumist konarlikul teel?"
                         };
    String answers[] = {"Peatuda ja pärast süüte väljalülimist kontrollida õli taset mootoris.","1 m.","Ei","Jätkan sõitu ettevaatlikult arvestades võimalusega, et jalakäijad hakkavad bussi varjust teed ületama.","Tavaleping.","Võimalusel ei pidurda.","Välismüra vähenemise.","Ei","Alates 0,2 promillist.","Amortisaatori rike."};
    String opt[] = {
                    "Kui mootorist ei kostu kloppimist, võib edasi sõita.","Peatuda ja pärast süüte väljalülimist kontrollida õli taset mootoris.","Midagi","Peab sõitma lähima remonditöökojani",
                    "1 m.","2 m.","5 m.","3 m.",
                    "Ei","Jah","Vist","Võib",
                    "Suretan auto välja","Peab signaali andma mis annab teistele teada, et sõidan mööda","Pean igal juhul seisma jääma.","Jätkan sõitu ettevaatlikult arvestades võimalusega, et jalakäijad hakkavad bussi varjust teed ületama.",
                    "Tavaleping.","Piirikindlustusleping.","Kaskokindlustuse leping.","Muu leping.",
                    "Pidurdate tugevalt","Pidurdate tugevalt käsipiduriga.","Võimalusel ei pidurda.","Hoiate ohutut sõidujoont.",
                    "Välismüra vähenemise.","Välismüra suurenemise.","Hea tunde.","Midagi.",
                    "Jah","Ei","Ei tea","Kindlasti",
                    "Alates 0,2 promillist.","Alates 0,5 promillist.","Alates 1,0 promillist.","Alates 0,8 promillist.",
                     "Tagavedru purunemine.","Amortisaatori rike.","Esivedru purunemine.","Hea muusika."
                   };
    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        final TextView score = (TextView)findViewById(R.id.textView4);
        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();
        String name= intent.getStringExtra("myname");

        if (name.trim().equals(""))
            textView.setText("Tere, nimetu");
        else
        textView.setText("Tere " + name);

        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if(ansText.equals(answers[flag])) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;

                if (score != null)
                    score.setText(""+correct);

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
        });
    }

}