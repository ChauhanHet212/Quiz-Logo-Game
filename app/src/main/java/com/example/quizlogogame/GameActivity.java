package com.example.quizlogogame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ImageView quiz_logo, previousBtn, nextBtn;
    TextView[] ans = new TextView[9];
    TextView[] btn = new TextView[14];
    Button clearBtn, removeBtn, nextLogoBtn;

    int pos1, pos2, count, completedLogo, thislevellogos;
    String logo_ans, status;
    ArrayList<Character> chars = new ArrayList<>();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pos1 = getIntent().getIntExtra("pos1", 0);
        pos2 = getIntent().getIntExtra("pos2", 0);

        preferences = getSharedPreferences("myPref", MODE_PRIVATE);
        editor = preferences.edit();

        completedLogo = preferences.getInt("CompletedLogos", 0);
        thislevellogos = preferences.getInt("Level" + pos1 + "TLogos", 0);
        status = preferences.getString("Level" + pos1 + "Logo" + pos2, "h");

        quiz_logo = findViewById(R.id.quiz_logo);
        clearBtn = findViewById(R.id.clearBtn);
        removeBtn = findViewById(R.id.removeBtn);
        nextLogoBtn = findViewById(R.id.nextLogoBtn);
        previousBtn = findViewById(R.id.previousBtn);
        nextBtn = findViewById(R.id.nextBtn);


        ((TextView) findViewById(R.id.logo_count)).setText("logo " + (pos2 + 1) + "/" + AllQuiz.ALL_QUIZ.get(pos1).size());
        quiz_logo.setImageResource(AllQuiz.ALL_QUIZ.get(pos1).get(pos2).getS_img());

        logo_ans = AllQuiz.ALL_QUIZ.get(pos1).get(pos2).getName().toUpperCase();

        for (int i = 0; i < logo_ans.length(); i++) {
            chars.add(logo_ans.charAt(i));
        }

        for (count = logo_ans.length(); count < 14; count++) {
            char r = (char) (new Random().nextInt(91 - 65) + 65);
            chars.add(r);
        }

        Collections.shuffle(chars);

        for (int i = 0; i < 9; i++) {
            int id = getResources().getIdentifier("ans" + i, "id", getPackageName());
            ans[i] = findViewById(id);
            int finalI = i;
            ans[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!ans[finalI].getText().toString().isEmpty()) {
                        for (int j = 0; j < 14; j++) {
                            if (ans[finalI].getText().toString().equals(btn[j].getText().toString())) {
                                if (btn[j].getVisibility() == View.INVISIBLE) {
                                    btn[j].setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                        }
                        ans[finalI].setText("");
                    }
                }
            });
        }

        for (int i = 0; i < 9; i++) {
            if (i >= logo_ans.length()) {
                ans[i].setVisibility(View.GONE);
            }
        }

        for (int i = 0; i < 14; i++) {
            int id = getResources().getIdentifier("btn" + i, "id", getPackageName());
            btn[i] = findViewById(id);
            btn[i].setText(chars.get(i).toString());
            int finalI = i;
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String ch = btn[finalI].getText().toString();
                    for (int j = 0; j < 9; j++) {
                        if (ans[j].getText().toString().isEmpty()) {
                            ans[j].setText(ch);
                            btn[finalI].setVisibility(View.INVISIBLE);
                            break;
                        }
                    }
                    checkWin();
                }
            });
        }

        if (status.equals("Win")){
            winScreen();
        }

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos2 != 0) {
                    pos2--;
                    Intent intent = new Intent(GameActivity.this, GameActivity.class);
                    intent.putExtra("pos1", pos1);
                    intent.putExtra("pos2", pos2);
                    startActivity(intent);
                    finish();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos2 != (AllQuiz.ALL_QUIZ.get(pos1).size() - 1)) {
                    pos2++;
                    Intent intent = new Intent(GameActivity.this, GameActivity.class);
                    intent.putExtra("pos1", pos1);
                    intent.putExtra("pos2", pos2);
                    startActivity(intent);
                    finish();
                }
            }
        });

        nextLogoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos2 != (AllQuiz.ALL_QUIZ.get(pos1).size() - 1)) {
                    pos2++;
                    Intent intent = new Intent(GameActivity.this, GameActivity.class);
                    intent.putExtra("pos1", pos1);
                    intent.putExtra("pos2", pos2);
                    startActivity(intent);
                    finish();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 9; i++) {
                    ans[i].setText("");
                }
                for (int i = 0; i < 14; i++) {
                    btn[i].setVisibility(View.VISIBLE);
                }
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 8; i >= 0; i--) {
                    if (!ans[i].getText().toString().isEmpty()) {
                        for (int j = 0; j < 14; j++) {
                            if (ans[i].getText().toString().equals(btn[j].getText().toString())) {
                                if (btn[j].getVisibility() == View.INVISIBLE) {
                                    btn[j].setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                        }
                        ans[i].setText("");
                        break;
                    }
                }
            }
        });

        findViewById(R.id.backBtn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void checkWin() {
        int j = 0;
        int k = 0;
        for (int i = 0; i < logo_ans.length(); i++) {
            if (ans[i].getText().toString().isEmpty()) {
                j++;
            }
        }

        if (j == 0) {
            for (int i = 0; i < logo_ans.length(); i++) {
                if (ans[i].getText().toString().charAt(0) != logo_ans.charAt(i)) {
                    k++;
                }
            }

            if (k == 0) {
                completedLogo ++;
                thislevellogos ++;
                editor.putInt("CompletedLogos", completedLogo);
                editor.putInt("Level" + pos1 + "TLogos", thislevellogos);
                editor.putString("Level" + pos1 + "Logo" + pos2, "Win");
                if (thislevellogos == 5){
                    editor.putInt("CurrentLevel", pos1);
                    editor.putString("Level" + pos1, "Done");
                }
                editor.commit();
                winScreen();
            } else {
                Dialog dialog = new Dialog(GameActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.incorrect_dialog);

                Button okBtn = dialog.findViewById(R.id.okBtn);
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        }
    }

    public void winScreen(){
        for (int i = 0; i < logo_ans.length(); i++) {
            ans[i].setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i < 14; i++) {
            btn[i].setVisibility(View.INVISIBLE);
        }
        clearBtn.setVisibility(View.INVISIBLE);
        removeBtn.setVisibility(View.INVISIBLE);
        quiz_logo.setImageResource(AllQuiz.ALL_QUIZ.get(pos1).get(pos2).getE_img());

        findViewById(R.id.win).setVisibility(View.VISIBLE);
    }
}