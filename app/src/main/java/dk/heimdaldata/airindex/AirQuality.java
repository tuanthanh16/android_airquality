package dk.heimdaldata.airindex;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

public class AirQuality {
    private Set<String> keyset;
    float NO2;
    float O3;
    float SO2;
    float PM25;
    float PM10;
    int aqi;

    public AirQuality(float NO2, float o3, float SO2, float PM25, float PM10, int aqi) {
        this.NO2 = NO2;
        O3 = o3;
        this.SO2 = SO2;
        this.PM25 = PM25;
        this.PM10 = PM10;
        this.aqi = aqi;
    }

    public AirQuality() {
    }

    public int getOverall(String response) throws JSONException {

        JSONObject object = new JSONObject(response);
        if (object != null && object.has("overall_aqi")) {
            return object.getInt("overall_aqi");
        } else {
            return 0;
        }
        // read and set Aqi


        }
        // end of object get key



    public float getNO2() {
        return NO2;
    }

    public void setNO2(float NO2) {
        this.NO2 = NO2;
    }

    public float getO3() {
        return O3;
    }

    public void setO3(float o3) {
        O3 = o3;
    }

    public float getSO2() {
        return SO2;
    }

    public void setSO2(float SO2) {
        this.SO2 = SO2;
    }

    public float getPM25() {
        return PM25;
    }

    public void setPM25(float PM25) {
        this.PM25 = PM25;
    }

    public float getPM10() {
        return PM10;
    }

    public void setPM10(float PM10) {
        this.PM10 = PM10;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

}
