Name: Blain, Justin
Class: CYOP 300
Date: 10.3.24
Goal: Rouge like Dungeon Crawler

dependancies:
    ???
    
modules:
    main.py     main interface
    setup.py    generates maze, player, and contents
    input.py    custom input method to override native input method
    backend.py  bucket and object related methods
    
User Guide

    Welcome to Dark and Full of Terrors! This is intended to be a small command-line Rouge like Dungeon Crawler

Current Features:
  - You can specify the features of the dungeon you want to play in. The dimensions of each cell are 2*3 steps and you may specify a dungeon between two and eight cells tall and between 2 and 15 cells wide.
  - Create a little bit of info about your character. You can select your character's name and start mental, physical, and social abilities. These abilities must be between 1 and 20 and total no more than 30 combined.
  - You (@) can move around the maze. Using the WASD keys, you can avoid traps (~) navigate encounters with enemies(&) and collecting loot(*) while searching for the exit(E).
  - All entities within the dungeon are rendered at a distance that depends on your mental acumen. And you may be able to glean information about the enemies you encounter depending on your social score.

Future Features 
  - Wandering enemies 
  - Dungeon floors which grow in size and difficulty with each level 
  - Items which modify specific stats depending on their description
  - An inventory system to hold, use, or drop items 
  - Gathering and casting wildly whimsical magic spells 
  - The ability to retreat and record your highscore in the annuls of the heroes or dive deeper and risk it all

Current Limitations 
  - Inability to refresh the screen and play the game without a scrolling history
  - Dead enemies which remain on the map.
  - Having to press enter with every keystroke 

Thank you for playing and I hope you enjoy
