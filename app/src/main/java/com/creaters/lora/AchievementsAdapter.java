package com.creaters.lora;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
/**
 * Own adapter for set */
public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.ViewHolder> {
    private final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mNames = new ArrayList<>();
    ImageComponent component;
    private Context context;

    public AchievementsAdapter(ArrayList<String> mNames, Context context) {
        component = new ImageComponent();
        this.mNames = mNames;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String short_path = mNames.get(position)+".png";
        Bitmap bitmap = component.loadImageBitmap(context, short_path);
        holder.image.setImageBitmap(bitmap);
        holder.text.setText(mNames.get(position));
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }
    }
}
