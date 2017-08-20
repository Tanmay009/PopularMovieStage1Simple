package com.example.tanmayshrivastava.popularmoviestage1simple;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.squareup.picasso.Picasso;

/**
 * Created by tanmayshrivastava on 19/08/17.
 */

public class MovieAdapter extends ArrayAdapter<MovieModel> {
    Context context;

    MainActivity mainActivity=new MainActivity();
    public MovieAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MovieModel> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        MovieModel movieModel=getItem(position);

        //   SquaredImageView view = (SquaredImageView) convertView;
        //   if (view == null) {
        //      view = new SquaredImageView(context);
        //  }
        // String url = getItem(position);

        //  Picasso.with(context).load(url).into(imgview);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_list_row,parent,false);
        }
        ImageView imgview=(ImageView)convertView.findViewById(R.id.customImageView);
        context=getContext();
        Picasso.with(context).load("https://image.tmdb.org/t/p/w185" + movieModel.getPoster_path())
                .into(imgview);
        TextView titleMovie=(TextView)convertView.findViewById(R.id.title);
      /*  TextView overview=(TextView)convertView.findViewById(R.id.overview);
        TextView vote=(TextView)convertView.findViewById(R.id.rating);
        TextView rbarvote=(TextView) convertView.findViewById(R.id.rating);
        TextView release=(TextView)convertView.findViewById(R.id.releaseDate);
        RatingBar rbar=(RatingBar)convertView.findViewById(R.id.ratingBar);
         // assert movieModel != null;
        titleMovie.setText(movieModel.getTitle());
        overview.setText(movieModel.getOverview());
        vote.setText(movieModel.getVote_average());
        rbarvote.setText(movieModel.getVote_average());
        //   rbar.setRating(movieModel.get(position));
        release.setText(movieModel.getRelease_date());
        //  imgview.setImageResource(movieModel.getPoster_path()); */
        return convertView;
    }
}



