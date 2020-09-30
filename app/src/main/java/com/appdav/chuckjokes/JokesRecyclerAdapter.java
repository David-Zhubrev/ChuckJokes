package com.appdav.chuckjokes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.appdav.chuckjokes.jokegetter.Joke;

import java.util.List;

public class JokesRecyclerAdapter extends Adapter<JokesRecyclerAdapter.JokesRecyclerViewHolder> {

    private List<Joke> jokes;

    public JokesRecyclerAdapter(List<Joke> jokes){
        this.jokes = jokes;
    }

    @NonNull
    @Override
    public JokesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joke, parent, false);
        return new JokesRecyclerViewHolder(v);
    }

    public void updateAdapter(List<Joke> jokes){
        if (this.jokes != jokes) {
            this.jokes = jokes;
            notifyDataSetChanged();
        }
    }


    @Override
    public void onBindViewHolder(@NonNull JokesRecyclerViewHolder holder, int position) {
        Joke joke = jokes.get(position);
        holder.title.setText(joke.getTitle());
        holder.joke.setText(joke.getValue());
    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    class JokesRecyclerViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView joke;

        JokesRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            joke = itemView.findViewById(R.id.tvJoke);
        }
    }

}
