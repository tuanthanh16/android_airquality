package dk.heimdaldata.airindex;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import dk.heimdaldata.airindex.utils.NetworksUtils;

public class MainActivity extends AppCompatActivity {
    AirQuality myAqi;
    Button btnRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRun = (Button) findViewById(R.id.btnRun);
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    queryTask();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
        PieChart pieChart = findViewById(R.id.pieChart);
        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry((float) 8.65, "NO2"));
        visitors.add(new PieEntry((float) 52.21, "O3"));
        visitors.add(new PieEntry((float) 4.77, "SO2"));
        visitors.add(new PieEntry((float) 1.69, "PM2.5"));
        visitors.add(new PieEntry((float) 2.75, "PM10"));
//        visitors.add(new PieEntry((float) 216.96, "CO"));


        PieDataSet pieDataSet = new PieDataSet(visitors, "Air Quality");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLUE);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.setCenterText("Overall 44");
        pieChart.animate();



    }

    private void queryTask() throws MalformedURLException {
        String apiUrl = "https://api.api-ninjas.com/v1/airquality?city=London";
        URL queryUrl = new URL(apiUrl);
        new QueryTask().execute(queryUrl);
    }

    // Async class
    public class QueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String results = null;
            try {
                results = NetworksUtils.getResponseWithHeader(searchUrl);
                Log.d("doInBackGround", results);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPostExecute(String s) {

            // return String data to main UI
            // running on UI thread
            // convert result from doInBackground to JSon
            myAqi = new AirQuality();
            try {
                if (s != null) {
                    int overallIndex = myAqi.getOverall(s);
                    btnRun.setText(overallIndex);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}