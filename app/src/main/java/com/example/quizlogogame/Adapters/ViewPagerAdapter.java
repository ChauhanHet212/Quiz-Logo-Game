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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.quizlogogame.AllQuiz;
import com.example.quizlogogame.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    Activity context;
    ViewPager2 viewPager;

    int pos1, pos2, count, completedLogo, thislevellogos, currentLevel;
    String logo_ans, status;
    ArrayList<Character> chars = new ArrayList<>();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public ViewPagerAdapter(Activity context, int pos1, int pos2, ViewPager2 viewPager) {
        this.context = context;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.viewPager = viewPager;
        preferences = context.getSharedPreferences("myPref", MODE_PRIVATE);
        editor = preferences.edit();
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.pager_item, parent, false));

        playGame(holder, pos2);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolder holder, int position) {
        if (start) {
            playGame(holder, pos2);
        }

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                playGame(holder, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public int getItemCount() {
        return AllQuiz.ALL_QUIZ.get(pos1).size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView quiz_logo, previousBtn, nextBtn;
        TextView[] ans = new TextView[9];
        TextView[] btn = new TextView[14];
        Button clearBtn, removeBtn, nextLogoBtn;
        LinearLayout win;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            quiz_logo = itemView.findViewById(R.id.quiz_logo);
            clearBtn = itemView.findViewById(R.id.clearBtn);
            removeBtn = itemView.findViewById(R.id.removeBtn);
            nextLogoBtn = itemView.findViewById(R.id.nextLogoBtn);
            previousBtn = itemView.findViewById(R.id.previousBtn);
            nextBtn = itemView.findViewById(R.id.nextBtn);
            win = itemView.findViewById(R.id.win);

            for (int i = 0; i < 9; i++) {
                int id = context.getResources().getIdentifier("ans" + i, "id", context.getPackageName());
                ans[i] = itemView.findViewById(id);
            }

            for (int i = 0; i < 14; i++) {
                int id = context.getResources().getIdentifier("btn" + i, "id", context.getPackageName());
                btn[i] = itemView.findViewById(id);
            }
        }
    }

    public void playGame(ViewPagerAdapter.ViewHolder holder, int position){
        pos2 = position;

        completedLogo = preferences.getInt("CompletedLogos", 0);
        thislevellogos = preferences.getInt("Level" + pos1 + "TLogos", 0);
        status = preferences.getString("Level" + pos1 + "Logo" + pos2, "h");
        currentLevel = preferences.getInt("CurrentLevel", 0);

        holder.quiz_logo.setImageResource(AllQuiz.ALL_QUIZ.get(pos1).get(pos2).getS_img());

        logo_ans = AllQuiz.ALL_QUIZ.get(pos1).get(pos2).getName().toUpperCase();
        System.out.println(logo_ans);

        chars.clear();
        for (int i = 0; i < logo_ans.length(); i++) {
            chars.add(logo_ans.charAt(i));
            System.out.println(chars.get(i));
        }

        for (count = logo_ans.length(); count < 14; count++) {
            char r = (char) (new Random().nextInt(91 - 65) + 65);
            chars.add(r);
        }

        Collections.shuffle(chars);

        for (int i = 0; i < 9; i++) {
            int finalI = i;
            holder.ans[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < 14; i++) {
                        holder.btn[i].setClickable(true);
                    }

                    if (!holder.ans[finalI].getText().toString().isEmpty()) {
                        for (int j = 0; j < 14; j++) {
                            if (holder.ans[finalI].getText().toString().equals(holder.btn[j].getText().toString())) {
                                if (holder.btn[j].getVisibility() == View.INVISIBLE) {
                                    holder.btn[j].setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                        }
                        holder.ans[finalI].setText("");
                    }
                }
            });
        }

        for (int i = 0; i < 9; i++) {
            if (i >= logo_ans.length()) {
                holder.ans[i].setVisibility(View.GONE);
            }
        }

        for (int i = 0; i < 14; i++) {
            holder.btn[i].setText(chars.get(i).toString());

            int finalI = i;
            holder.btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String ch = holder.btn[finalI].getText().toString();
                    for (int j = 0; j < 9; j++) {
                        if (holder.ans[j].getText().toString().isEmpty()) {
                            holder.ans[j].setText(ch);
                            holder.btn[finalI].setVisibility(View.INVISIBLE);
                            break;
                        }
                    }
                    checkWin(holder);
                }
            });
        }

        if (status.equals("Win")){
            winScreen(holder);
        } else {
            defaultScreen(holder);
        }

        holder.previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position != 0) {
                    viewPager.setCurrentItem(position - 1, true);
                }
            }
        });

        holder.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position != (AllQuiz.ALL_QUIZ.get(pos1).size() - 1)) {
                    viewPager.setCurrentItem(position + 1, true);
                }
            }
        });

        holder.nextLogoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position != (AllQuiz.ALL_QUIZ.get(pos1).size() - 1)) {
                    viewPager.setCurrentItem(position + 1, true);
                }
            }
        });

        holder.clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 9; i++) {
                    holder.ans[i].setText("");
                }
                for (int i = 0; i < 14; i++) {
                    holder.btn[i].setClickable(true);
                    holder.btn[i].setVisibility(View.VISIBLE);
                }
            }
        });

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 14; i++) {
                    holder.btn[i].setClickable(true);
                }
                for (int i = 8; i >= 0; i--) {
                    if (!holder.ans[i].getText().toString().isEmpty()) {
                        for (int j = 0; j < 14; j++) {
                            if (holder.ans[i].getText().toString().equals(holder.btn[j].getText().toString())) {
                                if (holder.btn[j].getVisibility() == View.INVISIBLE) {
                                    holder.btn[j].setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                        }
                        holder.ans[i].setText("");
                        break;
                    }
                }
            }
        });
    }

    public void checkWin(ViewPagerAdapter.ViewHolder holder) {
        int j = 0;
        int k = 0;
        for (int i = 0; i < logo_ans.length(); i++) {
            if (holder.ans[i].getText().toString().isEmpty()) {
                j++;
            }
        }

        if (j == 0) {
            for (int i = 0; i < logo_ans.length(); i++) {
                if (holder.ans[i].getText().toString().charAt(0) != logo_ans.charAt(i)) {
                    k++;
                }
            }

            if (k == 0) {
                completedLogo ++;
                thislevellogos ++;
                editor.putInt("CompletedLogos", completedLogo);
                editor.putInt("Level" + pos1 + "TLogos", thislevellogos);
                editor.putString("Level" + pos1 + "Logo" + pos2, "Win");
                if (thislevellogos >= 5 && pos1 > currentLevel){
                    editor.putInt("CurrentLevel", pos1);
                    editor.putString("Level" + pos1, "Done");
                }
                editor.commit();
                winScreen(holder);
            } else {
                for (int i = 0; i < 14; i++) {
                    holder.btn[i].setClickable(false);
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

    public void winScreen(ViewPagerAdapter.ViewHolder holder){
        for (int i = 0; i < logo_ans.length(); i++) {
            holder.ans[i].setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i < 14; i++) {
            holder.btn[i].setVisibility(View.INVISIBLE);
        }
        holder.clearBtn.setVisibility(View.INVISIBLE);
        holder.removeBtn.setVisibility(View.INVISIBLE);
        holder.quiz_logo.setImageResource(AllQuiz.ALL_QUIZ.get(pos1).get(pos2).getE_img());

        holder.win.setVisibility(View.VISIBLE);
    }

    public void defaultScreen(ViewPagerAdapter.ViewHolder holder){
        for (int i = 0; i < logo_ans.length(); i++) {
            holder.ans[i].setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < 14; i++) {
            holder.btn[i].setVisibility(View.VISIBLE);
        }
        holder.clearBtn.setVisibility(View.VISIBLE);
        holder.removeBtn.setVisibility(View.VISIBLE);
        holder.quiz_logo.setImageResource(AllQuiz.ALL_QUIZ.get(pos1).get(pos2).getS_img());

        holder.win.setVisibility(View.GONE);
    }
}
