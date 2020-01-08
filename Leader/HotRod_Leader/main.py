#!/usr/bin/env pybricks-micropython

# Insert import Lego Librarys --> Only Standard
from pybricks import ev3brick as brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor, InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import (Port, Stop, Direction, Button, Color, SoundFile, ImageFile, Align)
from pybricks.tools import print, wait, StopWatch
from pybricks.robotics import DriveBase

# Init of Engine
engine, wheels, infrared = Motor(Port.D), Motor(Port.A), InfraredSensor(Port.S4)
# Init of Variables
speed_of_engine, speed_of_wheels, time_of_wheels = 2000, 500, 300

# Start Procedure
# - Make a Beep :-)
brick.sound.beep(1500, 1000, 50)
# - Say Hello
brick.sound.file(SoundFile.HELLO)
# - Write open eyes and "HotRod" on the Display
brick.display.clear()
brick.display.image(ImageFile.UP)
brick.display.text("HotRod", (60, 10))

# Infinity Loop
while True: 
    # Messure the Distance to the front
    distance_to_front = infrared.distance()
    # Is the Distance Okay? NO!
    if distance_to_front < 50: 
        # Stop Engine
        engine.stop()
        wheels.stop()
        # - Make Alarm --> Possible Crash
        brick.sound.file(SoundFile.ERROR_ALARM)
        # - Make Red Light on the Brick
        brick.light(Color.RED)
        # - Write closed eyes and "HotRod" on the Display
        brick.display.clear()
        brick.display.image(ImageFile.KNOCKED_OUT)
        brick.display.text("HotRod", (60, 10))
    # Distance is Okay. You can drive
    else: 
        # - Make Green Light on the Bright
        brick.light(Color.GREEN)
        # - Write open eyes and "HotRod" on the Display
        brick.display.clear()
        brick.display.image(ImageFile.UP)
        brick.display.text("HotRod", (60, 10))
        # Get the pushed Buttons of the Remote Controll
        pressed_key = infrared.buttons(1)
        # Iterate these list
        for button in pressed_key: 
            # Straight --> Upper Left
            if button == 128: 
                engine.run(speed_of_engine)
            # Break --> Bottom Left
            elif button == 2: 
                engine.stop()
            # Left --> Bottom Right
            elif button == 512:
                wheels.run_time(speed_of_wheels, time_of_wheels)
            # Right --> Upper Right
            elif button == 8:
                wheels.run_time(-1 * speed_of_wheels, time_of_wheels)
            # Nothing pressed
            else: 
                engine.stop()
                wheels.stop()
    # Are the Battery Okay?
    if brick.battery.voltage() < 7000:
        # Make a Beep :(
        brick.sound.beep()