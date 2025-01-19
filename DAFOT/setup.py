# Import
from random import shuffle, randrange, randint

from input import secure_input

# Variables
cols, rows, difficulty = 2, 2, 1
enemies = []
items = []
traps = []
doors = []

# Classes
class Soul:
    def __init__(self, name, phys, ment, socl, y, x):
        self.name = name
        self.phys = phys
        self.ment = ment
        self.socl = socl
        self.hp = phys
        self.y = y
        self.x = x

class Enemy:
    def __init__(self, name, phys, ment, socl, y, x):
        self.name = name
        self.phys = phys
        self.ment = ment
        self.socl = socl
        self.hp = phys
        self.y = y
        self.x = x

class Item:
    def __init__(self, name, mod, y, x):
        self.name = name
        self.mod = mod
        self.y = y
        self.x = x

class Trap:
    def __init__(self, name, dc, y, x):
        self.name = name
        self.dc = dc
        self.y = y
        self.x = x

class Door:
    def __init__(self, y, x):
        self.y = y
        self.x = x

# Functions
def roll(sides):
    # print(f"Rolling 1d{sides}")
    result = randint(1, sides)
    # print(f"Result: {result}")
    return result

def set_dc():
    dc = roll(20) + difficulty
    # print(f"DC: {dc}")
    return dc

def stats(self):
    print(f"Name: {self.name}")
    print(f"HP: {self.hp}")
    print(f"Physical: {self.phys}")
    print(f"Mental: {self.ment}")
    print(f"Social: {self.socl}")

def make_maze(rows, cols):
    print("\nNow about this dungeon...")
    """rows = secure_input("How deep was it?", [5,17], int)
    cols = secure_input("And how wide?", [5,17], int)"""

    vis = [[0] * cols + [1] for _ in range(rows)] + [[1] * (cols + 1)]
    ver = [["|   "] * cols + ['|'] for _ in range(rows)] + [[]]
    hor = [["+---"] * cols + ['+'] for _ in range(rows + 1)]

    maze = [[' ' for _ in range(cols * 4 + 1)] for _ in range(rows * 3 + 1)]

    def walk(x, y):
        vis[y][x] = 1
        d = [(x - 1, y), (x, y + 1), (x + 1, y), (x, y - 1)]
        shuffle(d)
        for (xx, yy) in d:
            if vis[yy][xx]: continue
            if xx == x:
                hor[max(y, yy)][x] = "+   "
                maze[max(y, yy) * 3][x * 4 + 1:x * 4 + 4] = '   '
            if yy == y:
                ver[y][max(x, xx)] = "    "
                maze[y * 3 + 1][max(x, xx) * 4] = ' '
                maze[y * 3 + 2][max(x, xx) * 4] = ' '
            walk(xx, yy)

    walk(randrange(cols), randrange(rows))

    for i, (a, b) in enumerate(zip(hor, ver)):
        maze[i * 3] = list(''.join(a))
        if i < len(ver) - 1:
            maze[i * 3 + 1] = list(''.join(b))
            maze[i * 3 + 2] = list(''.join(b))


    height, width = (len(maze))-2, (len(maze[0])-2)
    print("Ah yes I remember it well.\n")

    populate(height, width)

    return maze

# The following function is largely adapted from https://rosettacode.org/wiki/Maze_generation#Python
def populate(height, width):
    max_dif = min (height, width)
    difficulty = secure_input("And on a scale of say 1 to the smallest "
                        "breadth of the dungeon, how difficult did you find it?",
                        [1, max_dif], int)

    door = Door(roll(height), roll(width))
    doors.append(door)

    for i in range (difficulty):
        ment, phys, socl = set_dc(), set_dc(), set_dc()
        enemy = Enemy("goblin", ment, phys, socl, roll(height), roll(width))
        enemies.append(enemy)

        trap = Trap("spikes", set_dc(), roll(height), roll(width))
        traps.append(trap)

        item = Item("sword", 10, roll(height), roll(width))
        items.append(item)

    print(enemies, traps, items)


def char_create(height, width):
    print("\nWhat of yourself.\n")
    """name = secure_input("Your name adventurer?", input_type=str)
    while True:
        phys = secure_input("How physically capable were you at the "
                            f"time, {name}?", [0,20], int)
        ment = secure_input("Mentally?", [0,20], int)
        socl = secure_input("How about socially?", [0,20], int)
        if phys + ment + socl <= 30:
            break
        else:
            print("Now, we must remember to be humble."
                "\n(The total of these scores may be no more than 30 and no "
                "individual score may be higher than 20)")"""

    name, phys, ment, socl = 'dingleberry', 10, 10, 10

    print("\nYes your soul is becoming clear to me now.\n")
    soul = Soul(name, phys, ment, socl, roll(height), roll(width))
    print("\nAh. There you are.\n")
    return soul
