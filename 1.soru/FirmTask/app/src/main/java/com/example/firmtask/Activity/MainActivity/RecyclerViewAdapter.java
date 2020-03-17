package com.example.firmtask.Activity.MainActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firmtask.Model.Response;
import com.example.firmtask.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    public List<Response> responses;

    public RecyclerViewAdapter(Context context, List<Response> responses) {
        this.context = context;
        this.responses = responses;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        Response response = responses.get(position);
        holder.name.setText(response.getTitle());
        holder.rate.setText(response.getImdbRating());
        if(response.getPoster()!=null){
            Picasso.with(context)
                    .load(response.getPoster())
                    .into(holder.poster);
        }
    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;
        private TextView name;
        private TextView rate;

        public MyViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.film_poster);
            name = itemView.findViewById(R.id.film_name);
            rate = itemView.findViewById(R.id.film_rate);
        }
    }
}
