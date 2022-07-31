package com.example.mivok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourseID;
    public WordAdapter(Activity context, ArrayList<Word> pword,int colorResorseID) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, pword);
        this.mColorResourseID= colorResorseID;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

         Word current_word = getItem(position);

        TextView English_word = listItemView.findViewById(R.id.Englishword);
        English_word.setText(current_word.getMdefaultTranslation());

        TextView hindi_word = listItemView.findViewById(R.id.HindiWord);
        hindi_word.setText(current_word.getmMivokTranslation());

        ImageView icon_view  = listItemView.findViewById(R.id.imageView);

        if(current_word.hasimage()){
            icon_view.setImageResource(current_word.getMimage());
        }else{
            icon_view.setVisibility(View.GONE);
        }

        View imageview = listItemView.findViewById(R.id.horizontal_layout);

        int color = ContextCompat.getColor(getContext(),mColorResourseID);
        imageview.setBackgroundColor(color);


        return listItemView;
    }
}
