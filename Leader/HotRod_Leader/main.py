#!/usr/bin/env pybricks-micropython

# Einfügen der Lego Library --> Standardset
from pybricks import ev3brick as brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor, InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import (Port, Stop, Direction, Button, Color, SoundFile, ImageFile, Align)
from pybricks.tools import print, wait, StopWatch
from pybricks.robotics import DriveBase

# Initialisierung der Motoren
engine, wheels, infrared = Motor(Port.D), Motor(Port.A), InfraredSensor(Port.S4)
# Initialisierung der Constanten
speed_of_engine, speed_of_wheels, time_of_wheels = 2000, 500, 300

# Start Prozedur
# - Ton ausgeben
brick.sound.beep(1500, 1000, 50)
# - Hallo sagen
brick.sound.file(SoundFile.HELLO)
# - Display beschreiben
brick.display.clear()
brick.display.image(ImageFile.UP)
brick.display.text("HotRod", (60, 10))

# Endlosschleife
while True: 
    # Messe die Distanz nach vorne
    distance_to_front = infrared.distance()
    # Verarbeite den Abstand --> sollte ich bremsen?
    if distance_to_front < 50: 
        # Antriebsmotor stoppen
        engine.stop()
        # Lenkmotor stoppen
        wheels.stop()
        # Bisschen Ton und Licht Effekte zur Verdeutlichung
        brick.sound.file(SoundFile.ERROR_ALARM)
        brick.light(Color.RED)
        brick.display.clear()
        brick.display.image(ImageFile.KNOCKED_OUT)
        brick.display.text("HotRod", (60, 10))
    # ich kann fahren
    else: 
        # Bisschen Licht zur Verdeutlichtung
        brick.light(Color.GREEN)
        brick.display.clear()
        brick.display.image(ImageFile.UP)
        brick.display.text("HotRod", (60, 10))
        # Bekomme alle gedrückten Knöpfe als Liste (pressed_key = [])
        pressed_key = infrared.buttons(1)
        # Gehe die Liste mit einer For-Each Schleife durch
        for button in pressed_key: 
            # Gerade aus --> Oben Links
            if button == 128: 
                engine.run(speed_of_engine)
            # Bremsen --> Unten Links 
            elif button == 2: 
                engine.stop()
            # Links --> Unten Rechts
            elif button == 512: #left
                wheels.run_time(speed_of_wheels, time_of_wheels)
            # Rechts --> Oben Rechts
            elif button == 8: #right
                wheels.run_time(-1 * speed_of_wheels, time_of_wheels)
            # Nichts wurde gedrückt --> Motoren anhalten
            else: 
                engine.stop()
                wheels.stop()
    # Geht es der Batterie noch gut?
    if brick.battery.voltage() < 7000: 
        brick.sound.beep()