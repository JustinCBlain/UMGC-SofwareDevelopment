import copy
from math import sqrt

# Define Soul class (player)
class Soul:
    def __init__(self, name, y, x):
        self.name = name
        self.y = y
        self.x = x

# Function to calculate visibility, checking if a wall blocks the view
def is_visible(maze, soul, target_y, target_x, visibility_range):
    # Simple distance check first
    if distance(soul.y, soul.x, target_y, target_x) > visibility_range:
        return False

    # Line tracing from player to target (Bresenham’s line algorithm)
    for y, x in bresenham(soul.x, soul.y, target_x, target_y):
        if maze[y][x] in ['#', '+', '-', '|']:  # Blocked by wall
            return False  # If there is a wall between, visibility is blocked

    return True

# Bresenham's line algorithm to trace a line between two points (player and target)
def bresenham(x1, y1, x2, y2):
    points = []
    dx = abs(x2 - x1)
    dy = abs(y2 - y1)
    sx = 1 if x1 < x2 else -1
    sy = 1 if y1 < y2 else -1
    err = dx - dy
    while True:
        points.append((y1, x1))  # Store the coordinates in (y, x) order
        if x1 == x2 and y1 == y2:
            break
        e2 = err * 2
        if e2 > -dy:
            err -= dy
            x1 += sx
        if e2 < dx:
            err += dx
            y1 += sy
    return points

# Function to calculate distance
def distance(y1, x1, y2, x2):
    return sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)

# Display function
def display(maze, soul, visibility_range=3):
    # Create a copy of the maze
    visible_maze = copy.deepcopy(maze)

    # Iterate through the copy of the maze
    for y in range(len(visible_maze)):
        for x in range(len(visible_maze[y])):
            # Check if the tile is visible
            if not is_visible(maze, soul, y, x, visibility_range):
                visible_maze[y][x] = '?'  # Hidden tile

    # Display the visible maze
    for row in visible_maze:
        print(''.join(row))

# Example maze
maze = [
    ['+', '-', '+', '-', '+'],
    ['|', ' ', '|', ' ', '|'],
    ['+', '-', '+', '-', '+'],
    ['|', ' ', '|', ' ', '|'],
    ['+', '-', '+', '-', '+'],
]

# Create a player soul
soul = Soul('Player', y=2, x=2)

# Display the maze with visibility based on walls and range
display(maze, soul, visibility_range=3)