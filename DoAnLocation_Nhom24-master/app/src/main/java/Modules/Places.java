
        package Modules;

        import android.provider.Contacts;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

/**
 * Created by nguyenthang on 4/19/18.
 */

public class Places {
    public List<HashMap<String, String>> parse(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }

    private List<HashMap<String, String>> getPlaces(JSONArray jsonArray) {
        int placesCount = jsonArray.length();
        List<HashMap<String, String>> placesList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> placeMap = null;

        for (int i = 0; i < placesCount; i++) {
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(i));
                placesList.add(placeMap);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placesList;
    }
    private HashMap<String, String> getPlace(JSONObject googlePlaceJson) {
        HashMap<String, String> googlePlaceMap = new HashMap<String, String>();
        String placeName = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitude = "";
        String reference = "";
        String photo ="";
        Double rating = 0.0;
        try {
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }
            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity");
            }
            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = googlePlaceJson.getString("reference");
            if (!googlePlaceJson.isNull("photos")){
                JSONArray photos = googlePlaceJson.getJSONArray("photos");
                JSONObject pt = photos.getJSONObject(0);
                photo="https://maps.googleapis.com/maps/api/place/photo?maxwidth=500&photoreference="+ pt.getString("photo_reference")
                        +"&sensor=true&key=AIzaSyD73ix-2OxsdM03JnoTj5gbxwbPRAJZSiM";
            }
            if (!googlePlaceJson.isNull("rating")) {
                rating= googlePlaceJson.getDouble("rating");
            }
            googlePlaceMap.put("place_name", placeName);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", reference);
            googlePlaceMap.put("photo",photo);
            googlePlaceMap.put("rating",Double.toString(rating));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlaceMap;
    }
}
