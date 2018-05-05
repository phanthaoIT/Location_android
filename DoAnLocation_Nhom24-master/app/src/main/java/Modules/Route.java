package Modules;


import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Phan Thao on 4/29/2018.
 */

public class Route {
    public Distance distance;
    public Duration duration;
    public String startAddress;
    public String endAddress;
    public LatLng startLocation;
    public LatLng endLocation;
    public List<LatLng> points;
}
