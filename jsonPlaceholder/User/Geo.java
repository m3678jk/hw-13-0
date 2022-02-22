package jsonPlaceholder.User;

import lombok.Data;
@Data
public
class Geo {
    private float lat;
    private float lng;

    public Geo(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }
}

