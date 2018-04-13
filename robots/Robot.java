//Overall structure of segments
//Will be extended by indivisual objects
//All commands must have these functions and variables

//imports

package robots;

//import Commands


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Robot{

	//Hardware Hashmap, holds all the hardware mapped motors, servos and sensors. Accesed by Commands.
	private Map<String, Object> hardware = new HashMap<String, Object>();

	//Constructor
	public Robot() {
		//Set passed values to object values here
		//Ex: super.setHardware("motorR", hardwareMap.get(DcMotor.class, "Right_Front")
	}

	public void setHardware(String key, Object value) {
		hardware.put(key, value);
	}

	public Map getHardware() {
		return hardware;
		//get values from returned Map with hardware.get((DcMotor)key) replacing DcMotor with the appropriate hardware class
	}
}
