#!/usr/bin/env pybricks-micropython

from pybricks import ev3brick as brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor, InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import (Port, Stop, Direction, Button, Color, SoundFile, ImageFile, Align)
from pybricks.tools import print, wait, StopWatch
from pybricks.robotics import DriveBase

engine, wheels, infrared = Motor(Port.D), Motor(Port.A), InfraredSensor(Port.S4)
#i_left = InfraredSensor(Port.S2)
#i_right = InfraredSensor(Port.S3)
#i_back = InfraredSensor(Port.S1)
speed_of_engine, speed_of_wheels, time_of_wheels = 2000, 500, 250

brick.sound.beep(1500, 1000, 50)
brick.sound.file(SoundFile.HELLO)
brick.display.clear()
brick.display.image(ImageFile.UP)
brick.display.text("HotRod", (60, 10))

def move(sensor): 
    pressed_key = sensor.buttons(1)
    for button in pressed_key: 
        if button == 128: 
            engine.run(speed_of_engine)
        elif button == 2: 
            engine.stop()
        elif button == 512: #left
            wheels.run_time(speed_of_wheels, time_of_wheels)
        elif button == 8: #right
            wheels.run_time(-1 * speed_of_wheels, time_of_wheels)
        else: 
            engine.stop()
            wheels.stop()

while True: 
    distance_to_front = infrared.distance()
    if distance_to_front < 50: 
        engine.stop()
        wheels.stop()
        brick.sound.file(SoundFile.ERROR_ALARM)
        brick.light(Color.RED)
        brick.display.clear()
        brick.display.image(ImageFile.KNOCKED_OUT)
        brick.display.text("HotRod", (60, 10))
    else: 
        brick.light(Color.GREEN)
        brick.display.clear()
        brick.display.image(ImageFile.UP)
        brick.display.text("HotRod", (60, 10))
        '''
        move(infrared)
        move(i_left)
        move(i_right)
        move(i_back)
        '''
        pressed_key = infrared.buttons(1)
        for button in pressed_key: 
            if button == 128: 
                engine.run(speed_of_engine)
            elif button == 2: 
                engine.stop()
            elif button == 512: #left
                wheels.run_time(speed_of_wheels, time_of_wheels)
            elif button == 8: #right
                wheels.run_time(-1 * speed_of_wheels, time_of_wheels)
            else: 
                engine.stop()
                wheels.stop()
    if brick.battery.voltage() < 7000: 
        brick.sound.beep()