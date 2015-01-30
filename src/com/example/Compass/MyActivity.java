package com.example.Compass;

import android.app.Activity;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MyActivity extends Activity implements SensorEventListener{
    private ImageView image;
    18

            19
            // record the compass picture angle turned
            20
    private float currentDegree = 0f;
    21

            22
            // device sensor manager
            23
    private SensorManager mSensorManager;
    24

            25
    TextView tvHeading;
    26

            27
    @Override
    28
    protected void onCreate(Bundle savedInstanceState) {
        29
        super.onCreate(savedInstanceState);
        30
        setContentView(R.layout.activity_main);
        31

        32
        //
        33
        image = (ImageView) findViewById(R.id.main_iv);
        34

        35
        // TextView that will tell the user what degree is he heading
        36
        tvHeading = (TextView) findViewById(R.id.tvHeading);
        37

        38
        // initialize your android device sensor capabilities
        39
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        40
    }
    41

            42
    @Override
    43
    protected void onResume() {
        44
        super.onResume();
        45

        46
        // for the system's orientation sensor registered listeners
        47
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                48
                SensorManager.SENSOR_DELAY_GAME);
        49
    }
    50

            51
    @Override
    52
    protected void onPause() {
        53
        super.onPause();
        54

        55
        // to stop the listener and save battery
        56
        mSensorManager.unregisterListener(this);
        57
    }
    58

            59
    @Override
    60
    public void onSensorChanged(SensorEvent event) {
        61

        62
        // get the angle around the z-axis rotated
        63
        float degree = Math.round(event.values[0]);
        64

        65
        tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");
        66

        67
        // create a rotation animation (reverse turn degree degrees)
        68
        RotateAnimation ra = new RotateAnimation(
                69
                currentDegree,
                70
                        -degree,
                71
                Animation.RELATIVE_TO_SELF, 0.5f,
                72
                Animation.RELATIVE_TO_SELF,
                73
        0.5f);



        // how long the animation will take place

        ra.setDuration(210);



        // set the animation after the end of the reservation status

        ra.setFillAfter(true);



        // Start the animation

        image.startAnimation(ra);

        currentDegree = -degree;



    }



    @Override

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

        // not in use

    }

}

