"""
Name: Blain, Justin
Class: For Fun
Date: 10.9.24

Goal: DAFOT 0.2 - engine
"""

# Import
from setup import *
import subprocess
import time
from input import secure_input
from random import choice
from datetime import datetime
import sys
import copy
from math import sqrt

# Variables
can_step = [" ", ".", "*", "E", "~", "&"]
move_opts = ['w', 'a', 's', 'd']
encounter_opts = ["C", "F", "T"]

# Funcs
def encounter(enemy):
    socl_dc = enemy.socl + difficulty
    if roll(20) + soul.socl + soul.ment >= socl_dc:
        print(stats(enemy))
    else:
        print("You haven\'t the foggiest")
    choice = secure_input("Action?\n"
                          "\'T\'rick,\'F\'ight, or \'C\'ast?",
                          encounter_opts)
    if choice == 't':
        if roll(20) + soul.socl >= socl_dc:
            print ("I am not the droid you're looking for.")
            pass
        else:
            print("Strange. That usually works!")
            soul.hp -= enemy.phys + difficulty
            choice = "f"
    elif choice == 'c':
        print("Kame...Hame...")
        soul.ment -= difficulty
        if soul.ment>= enemy.ment:
            print("HAAA!!!")
            enemy.hp -= soul.ment
        choice = "f"
    if choice == 'f':
        print("So you've chosen death.")
        while enemy.hp > 0 and soul.hp > 0:
            print("Have at thee!")
            phys_dc = roll(20) + enemy.phys + difficulty
            attack = roll(20) + soul.phys
            if attack >= phys_dc: enemy.hp -= soul.phys
            else: soul.hp -= enemy.phys
            print(f"{soul.name}'s HP: {soul.hp}")
            print(f"Enemy HP: {enemy.hp}")
        print("Feel my wrath!")
        
def finish():
    now = datetime.now()
    time_msg = now.strftime("%m/%d/%y %H:%M")
    print(f"Time of death:{time_msg}.")
    print("So long, and thanks for all the fish!")
    sys.exit()

def interact():
    if soul.hp <= 0:
        print("Ah and that was where your story ended.")
        finish()
        return True
    elif maze[soul.y][soul.x] == "E":
        print ("You've reached the exit!\n Congratulations!")
        return True
    elif maze[soul.y][soul.x] == "~":
        print("Snake!")
        if soul.phys + roll(20) < choice(traps).dc:
            soul.hp -= choice(traps).dc
            print("Bitten!")
        return False
    elif maze[soul.y][soul.x] == "*":
        soul.hp += choice(items).mod
        print("Item get!")
        return False
    elif maze[soul.y][soul.x] == "&":
        print("Alas we meet again!")
        enemy = enemies[0]
        encounter(enemy)
        return False

def move (creature, choice):
    maze[creature.y][creature.x] = '.'
    if choice == 'w' and maze[creature.y-1][creature.x] in can_step: creature.y -=1
    elif choice == 's' and maze[creature.y + 1][creature.x] in can_step: creature.y += 1
    elif choice == 'a' and maze[creature.y][creature.x-1] in can_step: creature.x -= 1
    elif choice == 'd' and maze[creature.y ][creature.x+1] in can_step: creature.x += 1
    return creature.y, creature.x

def visible (x,y, dc):
    dist = sqrt((x - soul.x) ** 2 + (y - soul.y) ** 2)
    percep = (roll(4) + soul.ment) / ((dist + 1)**2 * difficulty)
    return percep >= dc

def render ():
    for e in enemies:
        en_move = choice(move_opts)
        e.y, e.x = move(e, en_move)
        """        e_vis = e.socl + difficulty
        if visible(e,e_vis):"""
        maze[e.y][e.x] = '&'

    for t in traps:
        # if visible(t, t.dc):
        maze[t.y][t.x] = '~'

    for i in items:
        dc = difficulty * 2
        # if visible(i, dc):
        maze[i.y][i.x] = '*'

    door = doors[0]
    # if visible(door, 5):
    maze[door.y][door.x] = 'E'

    maze[soul.y][soul.x] = '@'

def prompt():
    soul_move = secure_input("Action?\n"
                        "W,A,S,D to move", move_opts)
    soul.y, soul.x = move (soul, soul_move)

def display():
    shown_maze = copy.deepcopy(maze)
    for y in range(len(shown_maze)):
        for x in range(len(shown_maze[y])):
            if not visible(x,y, difficulty):
                shown_maze[y][x] = " "
            if shown_maze[y][x] == "~":
                shown_maze[y][x] = "*"

    for row in shown_maze:
        print(''.join(row))
    print(soul.name)
    print(f"{soul.hp}/{soul.phys}")

# Main
print(" +-+-+-+-+ +-+-+"
      "\n |F|U|L|L| |O|F|"
      "\n +-+-+-+-+-+-+-+"
      "\n |T|E|R|R|O|R|S|"
      "\n +-+-+-+-+-+-+-+")
print("\nWelcome adventurous soul!"
      "\nTell me something of your tale.\n")

maze = make_maze(cols, rows)
soul = char_create((len(maze))-2, (len(maze[0]))-2)

while True:
    render()
    display ()
    prompt()
    if interact():
        break

    subprocess.Popen("cls", shell=True)
    time.sleep(.1)
