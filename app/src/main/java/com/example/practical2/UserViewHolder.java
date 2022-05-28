package com.example.practical2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView name, description;
    ImageView profileImage, secondImage;

    public UserViewHolder(View viewItem){
        super(viewItem);
        name = viewItem.findViewById(R.id.name);
        description = viewItem.findViewById(R.id.description);
        profileImage = viewItem.findViewById(R.id.profilePhoto);
        secondImage = viewItem.findViewById((R.id.img2));
    }
}
