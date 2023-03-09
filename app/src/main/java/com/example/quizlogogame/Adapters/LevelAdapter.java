package com.example.quizlogogame.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizlogogame.Models.Quiz;
import com.example.quizlogogame.R;
import com.example.quizlogogame.SubLevelActivity;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {

    Activity context;
    List<List<Quiz>> allQuiz;
    SharedPreferences preferences;

    public LevelAdapter(Activity context, List<List<Quiz>> allQuiz) {
        this.context = context;
        this.allQuiz = allQuiz;
        preferences = context.getSharedPreferences("myPref", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public LevelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.lvl_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LevelAdapter.ViewHolder holder, int position) {

        int cLevel = preferences.getInt("Level" + position + "TLogos", 0);
        int lastLevel = preferences.getInt("CurrentLevel", 0);
        String status = preferences.getString("Level" + position, "h");

        holder.levelTxtv.setText("Level " + (position + 1));

        if (position == lastLevel || position == (lastLevel + 1) || status.equals("Done") || position == 0){
            holder.level.setBackgroundResource(R.drawable.level_button_red_circle);
            holder.level.setText(cLevel + "/" + allQuiz.get(position).size());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SubLevelActivity.class);
                    intent.putExtra("pos", holder.getAdapterPosition());
                    context.startActivity(intent);
//                    context.finish();
                }
            });
        } else {
            holder.level.setBackgroundResource(R.drawable.level_button_locked_circle);
        }
    }

    @Override
    public int getItemCount() {
        return allQuiz.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView level, levelTxtv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            level = itemView.findViewById(R.id.level);
            levelTxtv = itemView.findViewById(R.id.levelTxtv);
        }
    }
}
