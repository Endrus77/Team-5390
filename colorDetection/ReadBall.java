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


public class ReadBall extends VisionOpMode {

    //variables
    boolean match;
    //placeholder
    public static int ballColor;

    //Constructor
    public ReadBall() {
        match = false;
        //image = passedImage;
    }
    private static final ColorHSV lowerBoundRed = new ColorHSV((int) (305 / 360.0 * 255.0), (int) (0.200 * 255.0), (int) (0.300 * 255.0));
    private static final ColorHSV upperBoundRed = new ColorHSV((int) ((360.0 + 5.0) / 360.0 * 255.0), 255, 255);
    private static final ColorHSV lowerBoundBlue = new ColorHSV((int) (170.0 / 360.0 * 255.0), (int) (0.200 * 255.0), (int) (0.750 * 255.0));
    private static final ColorHSV upperBoundBlue = new ColorHSV((int) (227.0 / 360.0 * 255.0), 255, 255);
    private ColorBlobDetector detectorRed = new ColorBlobDetector(lowerBoundRed, upperBoundRed);
    private ColorBlobDetector detectorBlue = new ColorBlobDetector(lowerBoundBlue, upperBoundBlue);

    //Setup
    @Override
    public void init() {
        super.init();

        this.setCamera(Cameras.SECONDARY);

        this.setFrameSize(new Size(900, 900));

        enableExtension(Extensions.BEACON);
        enableExtension(Extensions.ROTATION);
        enableExtension(Extensions.CAMERA_CONTROL);

        beacon.setAnalysisMethod(Beacon.AnalysisMethod.FAST);

        beacon.setColorToleranceRed(0);
        beacon.setColorToleranceBlue(0);

        rotation.setIsUsingSecondaryCamera(false);
        rotation.disableAutoRotate();
        rotation.setActivityOrientationFixed(ScreenOrientation.LANDSCAPE);

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
        super.loop();

        telemetry.addData("Beacon Color", beacon.getAnalysis().getColorString());
        telemetry.addData("Beacon Center", beacon.getAnalysis().getLocationString());
        telemetry.addData("Beacon Confidence", beacon.getAnalysis().getConfidenceString());
        telemetry.addData("Beacon Buttons", beacon.getAnalysis().getButtonString());
        telemetry.addData("Screen Rotation", rotation.getScreenOrientationActual());
        telemetry.addData("Frame Rate", fps.getFPSString() + " FPS");
        telemetry.addData("Frame Size", "Width: " + width + " Height: " + height);
    }

    //Stops
    @Override
    public void stop() {
        super.stop();
    }

    public int getValue(){
        return (0);
    }
}
