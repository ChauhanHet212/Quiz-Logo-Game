package com.example.quizlogogame.Adapters;

import android.content.Context;
import android.content.Intent;
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

    Context context;
    List<List<Quiz>> allQuiz;

    public LevelAdapter(Context context, List<List<Quiz>> allQuiz) {
        this.context = context;
        this.allQuiz = allQuiz;
    }

    @NonNull
    @Override
    public LevelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.lvl_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LevelAdapter.ViewHolder holder, int position) {
        holder.level.setText("" + (position + 1));
        holder.levelTxtv.setText("Level " + (position + 1));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SubLevelActivity.class);
                intent.putExtra("pos", holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });
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
