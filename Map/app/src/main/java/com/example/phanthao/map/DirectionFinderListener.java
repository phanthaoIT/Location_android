package com.example.phanthao.map;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mai Thanh Hiep on 4/3/2016.
 */
public interface DirectionFinderListener {
    public abstract void onDirectionFinderStart();
    public abstract void onDirectionFinderSuccess(List<Route> route);
}
