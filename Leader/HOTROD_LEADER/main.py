#!/usr/bin/env pybricks-micropython

from pybricks import ev3brick as brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor,  InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import (Port, Stop, Direction, Button, Color, SoundFile, ImageFile, Align)
from pybricks.tools import print, wait, StopWatch
from pybricks.robotics import DriveBase

# Init of Engine and Sensor
engine, wheels, infrared = Motor(Port.D), Motor(Port.A), InfraredSensor(Port.S4)

# Init of Variables
speed_of_engine, speed_of_wheels, time_of_wheels = 2000, 500, 300

def attentionPlease2(soundfile, beep):
    # Make a Beep :-)
    if beep: brick.sound.beep(1500, 1000, 50)

    # Say Hello
    brick.sound.file(soundfile)

def attentionPlease(imagefile, color, text, pos):
    
    # Make Red Light on the Brick
    brick.light(color)

    # Write open eyes and "HotRod" on the Display
    brick.display.clear()
    brick.display.image(imagefile)
    brick.display.text(text, pos)

# Start Procedure --> Hey User, all okay. I'm Ready now.
attentionPlease2(SoundFile.HELLO, True)
attentionPlease(ImageFile.UP, Color.GREEN, "Hotrod", (60,10))

# Infinity Loop
while True:

    # Messure the Distance to the front
    distance_to_front = infrared.distance()

    # Check if there are any Obstacles or something read
    if distance_to_front < 50:
        
        # Stop Engine
        engine.stop()
        wheels.stop()

        # Troubleshooting for the User
        attentionPlease2(SoundFile.ERROR_ALARM, False) 
        attentionPlease(ImageFile.KNOCKED_OUT, Color.RED, "Hotrod", (60,10))

    # Distance is Okay. You can drive
    else:

        # Feedback for the User --> All okay
        attentionPlease(ImageFile.UP, Color.GREEN, "Hotrod", (60,10))

        # Get the pushed Buttons of the Remote Controll
        pressed_key = infrared.buttons(1)

        # Iterate these list
        for button in pressed_key:

            # Straight --> Upper Left
            if button == 128: engine.run(speed_of_engine)
            # Break --> Bottom Left | >>ATTENTION PLEASE: HERE COULD BE BACKWARDS<<
            elif button == 2: engine.stop()
            # Left --> Bottom Right
            elif button == 512: wheels.run_time(speed_of_wheels, time_of_wheels)
            # Right --> Upper Right
            elif button == 8: wheels.run_time(-1 * speed_of_wheels, time_of_wheels)
            # Nothing pressed
            else: wheels.stop()

    # Are the Battery Okay?
    if brick.battery.voltage() < 7000:
        # Make a Beep :(
        brick.sound.beep()
