package com.example.quizlogogame.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizlogogame.GameActivity;
import com.example.quizlogogame.Models.Quiz;
import com.example.quizlogogame.R;

import java.util.List;

public class SubLevelAdapter extends RecyclerView.Adapter<SubLevelAdapter.ViewHolder> {

    Context context;
    List<Quiz> quizList;
    int pos;
    SharedPreferences preferences;

    public SubLevelAdapter(Context context, List<Quiz> quizList, int pos) {
        this.context = context;
        this.quizList = quizList;
        this.pos = pos;
        preferences = context.getSharedPreferences("myPref", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public SubLevelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.sublvl_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String status = preferences.getString("Level" + pos + "Logo" + position, "h");

        if (status.equals("Win")){
            holder.tick.setImageResource(R.drawable.checked);
            holder.logo.setAlpha(0.2f);
        }

        holder.logo.setImageResource(quizList.get(position).getS_img());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra("pos1", pos);
                intent.putExtra("pos2", holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView logo, tick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.logo);
            tick = itemView.findViewById(R.id.tick);
        }
    }
}