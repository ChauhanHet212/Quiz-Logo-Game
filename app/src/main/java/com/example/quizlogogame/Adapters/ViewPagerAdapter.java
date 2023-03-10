package com.example.quizlogogame.Adapters;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.quizlogogame.AllQuiz;
import com.example.quizlogogame.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ViewPagerAdapter extends PagerAdapter {

    Activity context;
    ImageView quiz_logo, previousBtn, nextBtn;
    TextView[] ans = new TextView[9];
    TextView[] btn = new TextView[14];
    Button clearBtn, removeBtn, nextLogoBtn;
    ViewPager viewPager;
    LinearLayout win;

    int pos1, pos2, count, completedLogo, thislevellogos;
    String logo_ans, status;
    boolean start;
    ArrayList<Character> chars = new ArrayList<>();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public ViewPagerAdapter(Activity context, int pos1, int pos2, ViewPager viewPager, boolean start) {
        this.context = context;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.viewPager = viewPager;
        this.start = start;
        preferences = context.getSharedPreferences("myPref", MODE_PRIVATE);
        editor = preferences.edit();
    }

    @Override
    public int getCount() {
        return AllQuiz.ALL_QUIZ.get(pos1).size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.pager_item, container, false);

        if (start) {
            playGame(view, pos2);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                playGame(view, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void playGame(View view, int position){
        start = false;

        pos2 = position;

        completedLogo = preferences.getInt("CompletedLogos", 0);
        thislevellogos = preferences.getInt("Level" + pos1 + "TLogos", 0);
        status = preferences.getString("Level" + pos1 + "Logo" + pos2, "h");

        quiz_logo = view.findViewById(R.id.quiz_logo);
        clearBtn = view.findViewById(R.id.clearBtn);
        removeBtn = view.findViewById(R.id.removeBtn);
        nextLogoBtn = view.findViewById(R.id.nextLogoBtn);
        previousBtn = view.findViewById(R.id.previousBtn);
        nextBtn = view.findViewById(R.id.nextBtn);
        win = view.findViewById(R.id.win);

        quiz_logo.setImageResource(AllQuiz.ALL_QUIZ.get(pos1).get(pos2).getS_img());

        logo_ans = AllQuiz.ALL_QUIZ.get(pos1).get(pos2).getName().toUpperCase();

        chars.clear();
        for (int i = 0; i < logo_ans.length(); i++) {
            chars.add(logo_ans.charAt(i));
        }

        for (count = logo_ans.length(); count < 14; count++) {
            char r = (char) (new Random().nextInt(91 - 65) + 65);
            chars.add(r);
        }

        Collections.shuffle(chars);

        for (int i = 0; i < 9; i++) {
            int id = context.getResources().getIdentifier("ans" + i, "id", context.getPackageName());
            ans[i] = view.findViewById(id);
            int finalI = i;
            ans[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < 14; i++) {
                        btn[i].setClickable(true);
                    }

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
            int id = context.getResources().getIdentifier("btn" + i, "id", context.getPackageName());
            btn[i] = view.findViewById(id);
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
        } else {
            defaultScreen();
        }

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position != 0) {
                    viewPager.setCurrentItem(position - 1, true);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position != (AllQuiz.ALL_QUIZ.get(pos1).size() - 1)) {
                    viewPager.setCurrentItem(position + 1, true);
                }
            }
        });

        nextLogoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position != (AllQuiz.ALL_QUIZ.get(pos1).size() - 1)) {
                    viewPager.setCurrentItem(position + 1, true);
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
                    btn[i].setClickable(true);
                    btn[i].setVisibility(View.VISIBLE);
                }
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 14; i++) {
                    btn[i].setClickable(true);
                }
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
                for (int i = 0; i < 14; i++) {
                    btn[i].setClickable(false);
                }
                Dialog dialog = new Dialog(context);
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

        win.setVisibility(View.VISIBLE);
    }

    public void defaultScreen(){
        for (int i = 0; i < logo_ans.length(); i++) {
            ans[i].setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < 14; i++) {
            btn[i].setVisibility(View.VISIBLE);
        }
        clearBtn.setVisibility(View.VISIBLE);
        removeBtn.setVisibility(View.VISIBLE);
        quiz_logo.setImageResource(AllQuiz.ALL_QUIZ.get(pos1).get(pos2).getS_img());

        win.setVisibility(View.GONE);
    }
}
