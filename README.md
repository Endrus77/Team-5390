# Team 5390 Codebase

## General Idea / Structure

   The purpose of this project is to make coding for FTC competitions easier, more accesible and more organized. It accomplishes this through a system similar to block coding - one or more arrays of simple commands (or blocks) are maintained, and executed in order. There are three levels to this program; the base loop, Segments, and Commands.

### Base Loop

   The base loop is the actual Op Mode that will be ran on the phone, and contains an array of Segments. It is where things like your Robot variable, location and team color are defined, as well where your Segments you will be using are defined. Otherwise, not much changes here, as the core code should remain the same for all Op Modes you write.

### Segments

#### Command Array

   The Segment will hold an array filled with Command objects. When the Segment reached the looping stage, it will iterate through the command array and execute each Command. First, all Commands should be defined at the top of each Segment. For example, if I wanted to create a new `MoveForward` command, I would write, 

```java
MoveForward moveForward = new MoveForward(robot, powerR, powerL, distanceR, distanceL);
```

   After being defined, Commands should have their motors set in the `init()` function  using,

```java
	moveForward.setMotors("leftDrive", "rightDrive");
```

   `.setMotors()` takes String values, and uses them as keys with your Robot object to access your hardware. (The Robot object will be covered later). Finally, the command array is put together in the `build()` function using,

```java
	addCommand(moveForward);
```

This places the Command in an Array list maintined by the parent Segment object.

#### Conditionals

   As there is no framework for changing the command array once it starts to execute, all changes to it based on a conditional must be made at the start, in the `condtional()` and `build()` functions. This also means that there must be a new segment for every conditional that is evaluated. For example, for a challenge with one conditional, there must be one Segment to move the robot into place to evaluate the conditional, and another Segment to evaluate the condtional and complete the rest of the challenge based on its evaluation. The program will only move from `condtional()` to `build()` once `condtional()` returns `false`. `conditional()` must also always return `true` to continue looping. Generally, a variable defined at the start of the program should keep track of the results of the conditional, so it can be accesed in `build()` and used to create the command array.
