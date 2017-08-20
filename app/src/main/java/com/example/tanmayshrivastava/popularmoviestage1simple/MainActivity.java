package com.example.tanmayshrivastava.popularmoviestage1simple;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String IMAGE_URL = "http://image.tmdb.org/t/p/";

        Button btn=(Button)findViewById(R.id.btnhit);
        gridView=(GridView)findViewById(R.id.gridview);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JsonTask().execute("https://api.themoviedb.org/3/movie/now_playing?api_key=");


            }
        });
    }

    class JsonTask extends AsyncTask<String, String, List<MovieModel>> {
        @Override
        protected List<MovieModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                //return buffer.toString();
                String finalJson = buffer.toString();
                Log.i("Data", finalJson);
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("results");
                List<MovieModel> movieModelList = new ArrayList<>();
                for (int i = 0; i < parentArray.length(); i++) {
                    MovieModel movieModel = new MovieModel();
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    movieModel.setTitle(finalObject.getString("title"));
                    //movieModel.setPoster_path();
                    movieModel.setPoster_path(finalObject.getString("poster_path"));
                    movieModel.setOverview(finalObject.getString("overview"));
                    // movieModel.setRelease_date(finalObject.getString("date"));

                    movieModel.setVote_average(finalObject.getString("vote_average"));
                    movieModelList.add(movieModel);
                }
                //JSONObject finalObject=parentArray.getJSONObject(0);
                // String movieName= finalObject.getString("name");
                // Log.i("Movie name",movieName);
                return movieModelList;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();

            } finally {

                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(List<MovieModel> movieModels) {
            super.onPostExecute(movieModels);
            MovieAdapter movieAdapter=new MovieAdapter(getApplicationContext(),R.layout.custom_list_row,movieModels);
            gridView.setAdapter(movieAdapter);

        }
    }


}

