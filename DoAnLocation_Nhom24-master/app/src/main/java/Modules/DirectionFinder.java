package Modules;

import android.os.AsyncTask;

import com.example.thanh.doanlocation_nhom24.MainActivity;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Phan Thao on 4/29/2018.
 */

public class DirectionFinder {
    private static final String URL_API = "https://maps.googleapis.com/maps/api/directions/json?origin=";
    private MainActivity listener;
    private LatLng origin;
    private LatLng destination;
    private final String APIKEY = "AIzaSyD73ix-2OxsdM03JnoTj5gbxwbPRAJZSiM";

    public DirectionFinder(MainActivity listener,LatLng origin, LatLng destination) {
        this.listener = listener;
        this.origin = origin;
        this.destination = destination;
    }

    public void execute() throws UnsupportedEncodingException {
        listener.onDirectionFinderStart();
        new DownloadData().execute(createurl());
    }

    private String createurl() throws UnsupportedEncodingException {
        String originLat, originLong, destinationLat, destinationLong;
        originLat = String.valueOf(origin.latitude);
        originLong = String.valueOf(origin.longitude);
        destinationLat = String.valueOf(destination.latitude);
        destinationLong = String.valueOf(destination.longitude);
        return URL_API + originLat + "," + originLong + "&destination=" + destinationLat + "," + destinationLong + "&key="+APIKEY;
    }
// dữ liệu json
    private class DownloadData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String link = strings[0];
            try {
                URL url = new URL(link);
                InputStream is = url.openConnection().getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String s;
                while ((s = reader.readLine()) != null) {
                    buffer.append(s + "\n");
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                parseJSon(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }

        private void parseJSon(String s) throws JSONException {
            if (s == null)
                return;
            List<Route> routes = new ArrayList<Route>();
            JSONObject jsonData = new JSONObject(s); // data trong file json
            JSONArray jsonRoutes = jsonData.getJSONArray("routes"); // tat ca ca node routes
            for (int i = 0; i < jsonRoutes.length(); i++) {
                JSONObject jsonRoute = jsonRoutes.getJSONObject(i);
                Route route = new Route();
                JSONObject overview_polylineJson = jsonRoute.getJSONObject("overview_polyline");
                JSONArray jsonLegs = jsonRoute.getJSONArray("legs");
                JSONObject jsonLeg = jsonLegs.getJSONObject(0);
                JSONObject jsonDistance = jsonLeg.getJSONObject("distance");
                JSONObject jsonDuration = jsonLeg.getJSONObject("duration");
                JSONObject jsonStart = jsonLeg.getJSONObject("start_location");
                JSONObject jsonEnd = jsonLeg.getJSONObject("end_location");

                route.distance = new Distance(jsonDistance.getString("text"), jsonDistance.getInt("value")); // lay khang cach
                route.duration = new Duration(jsonDuration.getString("text"), jsonDuration.getInt("value")); // lay thoi gian
                route.startAddress = jsonLeg.getString("start_address");
                route.endAddress = jsonLeg.getString("end_address");
                route.startLocation = new LatLng(jsonStart.getDouble("lat"), jsonStart.getDouble("lng"));
                route.endLocation = new LatLng(jsonEnd.getDouble("lat"), jsonEnd.getDouble("lng"));
                route.points = decode(overview_polylineJson.getString("points")); // giai ma cac toa do
                routes.add(route);

            }
            listener.onDirectionFinderSuccess(routes);
        }
// giả mã chuỗi poly
        private ArrayList<LatLng> decode(String encoded) {
            ArrayList<LatLng> poly = new ArrayList<LatLng>();
            int index = 0, len = encoded.length();
            int lat = 0, lng = 0;

            while (index < len) {
                int b, shift = 0, result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;

                shift = 0;
                result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                LatLng p = new LatLng((((double) lat / 1E5)),(((double) lng / 1E5)));
                poly.add(p);
            }

            return poly;
        }

    }
}
