//checkImg()
//Compares passed image to phone image
//Returns bool

package colorDetection;

import org.lasarobotics.vision.android.Cameras;
import org.lasarobotics.vision.detection.ColorBlobDetector;
import org.lasarobotics.vision.ftc.resq.Beacon;
import org.lasarobotics.vision.opmode.VisionOpMode;
import org.lasarobotics.vision.opmode.extensions.CameraControlExtension;
import org.lasarobotics.vision.util.ScreenOrientation;
import org.lasarobotics.vision.util.color.ColorHSV;
import org.opencv.core.Size;


public class ReadImg extends VisionOpMode {

    //variables
    boolean match;
    //placeholder
    int pictoNumber;

    //Constructor
    public ReadImg() {
        match = false;
        //image = passedImage;
    }

    //Setup
    @Override
    public void init() {
        super.init();

        //initializes the color black
        ColorHSV COLOR_BLACK = new ColorHSV(0, 0, 0);

        //initializes the color detector
        ColorBlobDetector blackDetector = new ColorBlobDetector(COLOR_BLACK);

        this.setCamera(Cameras.PRIMARY);

        this.setFrameSize(new Size(900, 900));

        enableExtension(Extensions.BEACON);
        enableExtension(Extensions.ROTATION);
        enableExtension(Extensions.CAMERA_CONTROL);

        beacon.setAnalysisMethod(Beacon.AnalysisMethod.FAST);

        rotation.setIsUsingSecondaryCamera(false);
        rotation.disableAutoRotate();
        rotation.setActivityOrientationFixed(ScreenOrientation.PORTRAIT);

        cameraControl.setColorTemperature(CameraControlExtension.ColorTemperature.AUTO);
        cameraControl.setAutoExposureCompensation();
    }

    //Runs at start
    //Runs once
    @Override
    public void start() {
        //empty
    }

    //Loops
    @Override
    public void loop() {
        //compare images here








    }

    //Stops
    @Override
    public void stop() {
        //stop compare
    }

    public int getValue(){
        return (0);
    }
}
