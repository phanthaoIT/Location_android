package Modules;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by nguyenthang on 4/19/18.
 */

public class PlacesDisplayTask extends AsyncTask<Object, Integer, List<HashMap<String, String>>>{
    JSONObject googlePlacesJson;
    GoogleMap googleMap;
    Activity Context;
    public PlacesDisplayTask(Activity Context){
        this.Context=Context;
    }
    @Override
    protected List<HashMap<String, String>> doInBackground(Object... inputObj) {

        List<HashMap<String, String>> googlePlacesList = null;
        Places placeJsonParser = new Places();
        try {
            googleMap = (GoogleMap) inputObj[0];
            googlePlacesJson = new JSONObject((String) inputObj[1]);
            googlePlacesList = placeJsonParser.parse(googlePlacesJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return googlePlacesList;
    }
    protected void onPostExecute(List<HashMap<String, String>> list) {
        googleMap.clear();
        ImageLoad imgTask;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                MarkerOptions markerOptions = new MarkerOptions();
                HashMap<String, String> googlePlace = list.get(i);
                double lat = Double.parseDouble(googlePlace.get("lat"));
                double lng = Double.parseDouble(googlePlace.get("lng"));
                String placeName = googlePlace.get("place_name");
                String vicinity = googlePlace.get("vicinity");
                String photo=googlePlace.get("photo");
                String rating = googlePlace.get("rating");
                LatLng latLng = new LatLng(lat, lng);
                markerOptions.position(latLng);
                markerOptions.title(placeName+"\n"+vicinity+"\nRating: "+rating);
                Marker marker = googleMap.addMarker(markerOptions);
                marker.showInfoWindow();
                imgTask = new ImageLoad(Context, photo, googleMap, marker);
                imgTask.execute();
            }
        }

    }
}