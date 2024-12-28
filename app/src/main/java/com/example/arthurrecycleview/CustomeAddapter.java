package com.example.arthurrecycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomeAddapter extends RecyclerView.Adapter<CustomeAddapter.MyViewHolder> {

    private ArrayList<DataModel> dataset;
    private ArrayList<DataModel> filteredDataset;

    public CustomeAddapter(ArrayList<DataModel> dataset){
        this.dataset = new ArrayList<>(dataset);
        this.filteredDataset = dataset;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageViewIcon;

        public MyViewHolder (View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewRes);
            textViewName = itemView.findViewById(R.id.textView);
            textViewDescription = itemView.findViewById(R.id.textView2);
            imageViewIcon = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        DataModel clickedItem = filteredDataset.get(position);
                        String message = "Name: " + clickedItem.getName() + "\n" +
                                "Description: " + clickedItem.getDescription();
                        Toast.makeText(v.getContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public CustomeAddapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAddapter.MyViewHolder holder, int position) {
                TextView textViewName = holder.textViewName;
                TextView textViewDescription = holder.textViewDescription;
                ImageView imageView = holder.imageViewIcon;

                DataModel currentItem = filteredDataset.get(position);
                textViewName.setText(currentItem.getName());
                textViewDescription.setText(currentItem.getDescription());
                imageView.setImageResource(currentItem.getImage());
    }

    @Override
    public int getItemCount() {
        return filteredDataset.size();
    }


    public void filter(String query) {
        if (query == null || query.isEmpty()) {
            filteredDataset.clear();
            filteredDataset.addAll(dataset);
        } else {
            String lowerCaseQuery = query.toLowerCase().trim();
            filteredDataset.clear();

            for (DataModel item : dataset) {
                if (item.getName().toLowerCase().contains(lowerCaseQuery)) {
                    filteredDataset.add(item);
                }
            }
        }

        notifyDataSetChanged();
    }
}
