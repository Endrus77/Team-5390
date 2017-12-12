//checkImg()
//Compares passed image to phone image
//Returns bool

package commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class CheckImg extends Command {

    //variables
    boolean match;
    //placeholder
    int image;

    public int pictoNum = 0;
    private boolean found = false;

    int cameraMonitorViewId;

    //Constructor
    public CheckImg(int id) {
        cameraMonitorViewId = id;
    }

    private ElapsedTime runTime;

    VuforiaLocalizer vuforia;

    VuforiaTrackable relicTemplate;

    //Setup
    @Override
    public void init() {
        runTime = new ElapsedTime();

        VuforiaLocalizer vuforia;

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AbHP0yv/////AAAAGYHTynVo+U3RnQLYuxIP7EtAi8ulkQ00GcD95O5wZ7qrGTW0qRRTtzNay4BHlQIEZFdsXl1exUx5/44yxu5n3Rih/raiAJYwokfZvnHkoZi9kbqyh9HxDP3wFpQ3QFNHIRL9EP2NPKQYOPn6cwwX/qTvUl2VqPgsGQyG/ZIxl7lfNxxEcpksRl3NsptbdEDbf0N/l9SqJpUg8h5xkCMCA9rmZ9FDMXqxkGtHvw2i1cQ3TipfT9IIZ3VFRf4fFmfQtm6p9XLGgVM8KoodWoIursObmbJ8QHFQSHK4oi1ro7LzjN0p0eIoFr3hGY8X37Ymykl7ZQ1MRQGpiZvZZQfahbbdpb8knTPAU/JD2mDg829S";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();
    }

    //Runs at start
    //Runs once
    @Override
    public void start() {
        //empty
    }

    //Loops
    @Override
    public boolean loop() {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

            if (vuMark == RelicRecoveryVuMark.RIGHT) {
                pictoNum = 1;
                return false;
            } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                pictoNum = 2;
                return false;
            } else if (vuMark == RelicRecoveryVuMark.LEFT) {
                pictoNum = 3;
                return false;
            }
        }
        else if (runTime.time() > 999) {
            return false;
        }

        return true;
    }

        //Stops
        @Override
        public void stop () {
            //stop compare
        }

    public int getValue() {
        return (0);
    }
}
