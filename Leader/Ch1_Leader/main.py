#!/usr/bin/env pybricks-micropython

from pybricks import ev3brick as brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor, InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import (Port, Stop, Direction, Button, Color, SoundFile, ImageFile, Align)
from pybricks.tools import print, wait, StopWatch
from pybricks.robotics import DriveBase

class HotRod():
    def __init__(self, engine, wheels, infrared, speed_e, speed_w, time_w):
        self.engine, self.wheels, self.infrared = Motor(Port.D), Motor(Port.A), InfraredSensor(Port.S4)
        self.speed_of_engine, self.speed_of_wheels, self.time_of_wheels = speed_e, speed_w, time_w
        self.brick = brick
    def setDisplay(self, imagefile, text, pos):
        self.brick.display.clear()
        self.brick.display.image(imagefile)
        self.brick.display.text(text, pos)
    def stopEverything(self):
        self.stopEngine()
        self.stopWheels()
    def forward(self): self.engine.run(self.speed_of_engine)
    def moveLeft(self): self.wheels.run_time(self.speed_of_wheels, self.time_of_wheels)
    def moveRight(self): self.wheels.run_time(-1 * self.speed_of_wheels, self.time_of_wheels)
    def checkBattery(self, limit): if self.brick.battery.voltage() < limit: self.brick.sound.beep()
    def setLight(self, color): self.brick.light(color)
    def stopEngine(self): self.engine.stop()
    def stopWheels(self): self.wheels.stop()
    def setBeep(self, pitch, time, volume): self.brick.sound.beep(pitch, time, volume)
    def setSound(self, soundfile): self.brick.sound.file(soundfile)
    def start(self):
        self.setBeep(1500, 1000, 50)
        self.setSound(SoundFile.HELLO)
        self.setDisplay(ImageFile.UP, "HotRod", (60,10))
        while True: 
            distance_to_front = self.infrared.distance()
            if distance_to_front < 50:
                self.stopEverything()
                self.setSound(SoundFile.ERROR_ALARM)
                self.setLight(Color.RED)
                self.setDisplay(ImageFile.KNOCKED_OUT,"HotRod", (60,10))
            else:
                self.setLight(Color.GREEN)
                self.setDisplay(ImageFile.UP,"HotRod", (60,10))
                pressed_key = self.infrared.buttons(1)
                for button in pressed_key: 
                    if button == 128: self.forward()
                    elif button == 2: self.stopEngine()
                    elif button == 512: self.moveLeft()
                    elif button == 8: self.moveRight()
                    else: self.stopEverything() 
            self.checkBattery(7000)
hr = HotRod(Motor(Port.D), Motor(Port.A), InfraredSensor(Port.S4), 2000, 500, 250)
hr.start()

