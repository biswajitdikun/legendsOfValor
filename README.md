# CS611-Assignment 3
## Legends of Valor
---------------------------------------------------------------------------
- Name: Biswajit Satapathy
- Email: biswa@bu.edu
- Student ID: U62897143

## Files
---------------------------------------------------------------------------
src/
├── domain/
│   ├── board/
│   │   ├── Board.java             # Abstract board class
│   │   ├── BoardLoV.java          # Legends of Valor board implementation
│   │   └── BoardMaH.java          # Monsters and Heroes board implementation
│   │
│   ├── characters/
│   │   ├── GameEntity.java        # Base class for all game entities
│   │   ├── Heros/
│   │   │   ├── Hero.java          # Abstract hero class
│   │   │   ├── Paladin.java       # Paladin implementation
│   │   │   ├── Sorcerer.java      # Sorcerer implementation
│   │   │   ├── Warrior.java       # Warrior implementation
│   │   │   └── Teamhero.java      # Hero team management
│   │   └── Monsters/
│   │       ├── Monster.java       # Monster class
│   │       └── Teammonster.java   # Monster team management
│   │
│   ├── elements/
│   │   ├── Piece.java            # Base piece class
│   │   ├── PieceHero.java        # Hero piece implementation
│   │   ├── PieceMonster.java     # Monster piece implementation
│   │   ├── Tile.java             # Game board tile
│   │   ├── containHero.java      # Hero container interface
│   │   └── containMonster.java    # Monster container interface
│   │
│   ├── game/
│   │   ├── Game.java             # Abstract game class
│   │   ├── GameLOV.java          # Legends of Valor implementation
│   │   ├── GameMAH.java          # Monsters and Heroes implementation
│   │   ├── Battle.java           # Combat system
│   │   └── Market.java           # Market system
│   │
│   └── Item/
│       ├── Item.java             # Abstract item class
│       ├── Armor.java            # Armor implementation
│       ├── Weapon.java           # Weapon implementation
│       ├── Spell.java            # Spell implementation
│       ├── Potion.java           # Potion implementation
│       └── Inventory.java        # Inventory management
│
├── factory/
│   ├── Factory.java              # Main factory class
│   ├── FactoryHero.java          # Hero creation factory
│   ├── FactoryItem.java          # Item creation factory
│   └── FactoryMonster.java       # Monster creation factory
│
├── interfaces/
│   ├── Attributeincreae.java     # Attribute increase interface
│   ├── Consumable.java           # Consumable item interface
│   ├── DamageReduction.java      # Damage reduction interface
│   ├── Spellcaster.java          # Spell casting interface
│   └── Weaponizable.java         # Weapon interface
│
├── manager/
│   ├── Gamemanager.java          # Game management
│   └── TeamSetup.java            # Team setup management
│
├── util/
│   ├── ConsoleColor.java         # Console color utilities
│   ├── Inputhandler.java         # Input handling
│   ├── Outputhandler.java        # Output formatting
│   └── Loader.java               # Resource loading
│
├── launch/
│   └── Main.java                 # Application entry point
│
└── resources/                    # Game data files
├── Warriors.txt
├── Sorcerers.txt
        .
        .
        .
    <Text Files>

## Cool Features:
1) Dynamic Terrain System:
Special terrain types (Bush, Cave, Koulou)
Terrain bonuses affecting character stats
Monster terrain bonuses in hard mode (Difficulty Hard)
2) Strategic Combat:
Lane-based movement
Teleportation between lanes
Attack range limitations
Team coordination mechanics
3) Market and Economy:
Buy/sell items
Experience and gold rewards
Item management system
4) Colorful Terminal and interactive UI

## Design Patterns Used
1. Factory Pattern (factory/Factory.java, FactoryHero.java, FactoryItem.java)
Creates game objects
Manages object instantiation
Provides centralized object creation
2. Strategy Pattern (domain/characters/Heros/)
Different hero types with unique behaviors
Interchangeable combat strategies
Flexible attribute scaling
3. Observer Pattern (domain/game/GameLOV.java)
Monitors game state changes
Handles win conditions
Manages round progression
4. Singleton Pattern (util/Inputhandler.java, Outputhandler.java)
Manages game I/O
Ensures single instance of handlers
Centralizes I/O operations
5. Iterator Pattern  (domain/characters/Teammonster, domain/manager/TeamSetup)
Traverse through collections of heroes and monsters
Remove dead monsters from the team
Display hero statistics
Manage team compositions
## Notes
---------------------------------------------------------------------------
1. For Weapon, Spell, Armor and Potion, they inherit from Item class and implement their own interface,provide scalability, reduce similar code.
2. We have utils that can load from files and handle input from users, so we dont need to pass Scanner through the whole code and reduce coupling.
3. By placing the game logic in a specific class, we enhance the scalability and reusability of other components such as tile, hero, board, and team, while reducing coupling.
4. We create factory pattern to reduce coupling.

## How to compile and run
---------------------------------------------------------------------------
1. Unzip the file
2. Navigate to the directory legendsOfValor  
3. Run the command in the terminal: 
       java -cp bin launch.Main

## Input/Output Example
---------------------------------------------------------------------------
Welcome to the Game Manager!
1. Heroes and Monsters
2. Legends of Valor
3. Exit
   Enter your choice: 2
   Please choose the difficulty of the game
   (1) Easy (2) Medium (3) Hard
   Enter your choice: 2
   You have chosen MEDIUM difficulty.
   Name                      Profession   Level      Health     MP         Strength   Dexterity  Agility    Money      Experience
   Warriors (favored on strength and agility):
   Gaerdal_Ironhand          Warrior      1          100        100        700        600        500        1354       7         
   Sehanine_Monnbow          Warrior      1          100        600        700        500        800        2500       8         
   Muamman_Duathall          Warrior      1          100        300        900        750        500        2546       6         
   Flandal_Steelskin         Warrior      1          100        200        750        700        650        2500       7         
   Undefeated_Yoj            Warrior      1          100        400        800        700        400        2500       7         
   Eunoia_Cyn                Warrior      1          100        400        700        600        800        2500       6         
   Sorcerers (favored on dexterity and agility):
   Rillifane_Rallathil       Sorcerer     1          100        1300       750        500        450        2500       9         
   Segojan_Earthcaller       Sorcerer     1          100        900        800        650        500        2500       5         
   Reign_Havoc               Sorcerer     1          100        800        800        800        800        2500       8         
   Reverie_Ashels            Sorcerer     1          100        900        800        400        700        2500       7         
   Kalabar                   Sorcerer     1          100        800        850        600        400        2500       6         
   Skye_Soar                 Sorcerer     1          100        1000       700        500        400        2500       5         
   Paladins (favored on strength and dexterity):
   Parzival                  Paladin      1          100        300        750        700        650        2500       7         
   Sehanine_Moonbow          Paladin      1          100        300        750        700        700        2500       7         
   Skoraeus_Stonebones       Paladin      1          100        250        650        350        600        2500       4         
   Garl_Glittergold          Paladin      1          100        100        600        400        500        2500       5         
   Amaryllis_Astra           Paladin      1          100        500        500        500        500        2500       5         
   Caliber_Heist             Paladin      1          100        400        400        400        400        2500       8         
   Please enter the name of the character 1 you want to select (or press Enter for a random one):
   Enter a string:
   Randomly selecting a character for you...
   Character 1 selected: Parzival
   Please enter the name of the character 2 you want to select (or press Enter for a random one):
   Enter a string:
   Randomly selecting a character for you...
   Character 2 selected: Sehanine_Moonbow
   Please enter the name of the character 3 you want to select (or press Enter for a random one):
   Enter a string:
   Randomly selecting a character for you...
   Character 3 selected: Garl_Glittergold
   Heroes:
   Name                         Profession   Level  Health   MP    Strength   Dexterity  Agility    Money      Experience
1. Parzival                  Paladin      1          100        300        750        700        650        2500       7
2. Sehanine_Moonbow          Paladin      1          100        300        750        700        700        2500       7
3. Garl_Glittergold          Paladin      1          100        100        600        400        500        2500       5         
   Please choose the hero for the lane 1
   index 1: 3
   Please choose the hero for the lane 2
   index 2: 1
   Please choose the hero for the lane 3
   index 3: 2
   Number of rows and cols of the Board are fixed at 8
   Monster Casper spawned in lane 1
   Monster Casper spawned in lane 2
   Monster BigBad-Wolf spawned in lane 3
   Round 0 begins.
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
   |       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
| H1    |  |       |  | X X X |  | H2    |  |       |  | X X X |  | H3    |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H1 Garl_Glittergold's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 7 oldCol: 0
newRow: 6 newCol: 0
Hero Garl_Glittergold moved to 6, 0
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
| H1    |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  | H2    |  |       |  | X X X |  | H3    |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H2 Parzival's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 7 oldCol: 3
newRow: 6 newCol: 3
Hero Parzival moved to 6, 3
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
| H1    |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  | H3    |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H3 Sehanine_Moonbow's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 7 oldCol: 6
newRow: 6 newCol: 6
Sehanine_Moonbow gains strength bonus from Koulou!
Hero Sehanine_Moonbow moved to 6, 6
Monsters' turn:
oldRow: 0 oldCol: 4
newRow: 1 newCol: 4
Monster Casper moved to 1, 4
oldRow: 0 oldCol: 7
newRow: 1 newCol: 7
Monster BigBad-Wolf moved to 1, 7
Round 1 begins.
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |    M1 |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
| H1    |  |       |  | X X X |  | H2    |  |       |  | X X X |  | H3    |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H1 Garl_Glittergold's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 6 oldCol: 0
newRow: 5 newCol: 0
Garl_Glittergold gains strength bonus from Koulou!
Hero Garl_Glittergold moved to 5, 0
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |    M1 |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
| H1    |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  | H2    |  |       |  | X X X |  | H3    |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H2 Parzival's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 6 oldCol: 3
newRow: 5 newCol: 3
Parzival gains strength bonus from Koulou!
Hero Parzival moved to 5, 3
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |    M1 |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
| H1    |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  | H3    |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H3 Sehanine_Moonbow's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 6
Choose a hero to teleport to:
1. Hero H1 Garl_Glittergold
2. Hero H2 Parzival
   Choose a hero (1-2): 2
   Choose a direction of target hero to teleport:
1. W
2. S
3. D
   Choose a direction (1-3): d
   Invalid input. Please enter a valid Integer!
   Choose a direction (1-3): s
   Invalid input. Please enter a valid Integer!
   Choose a direction (1-3): w
   Invalid input. Please enter a valid Integer!
   Choose a direction (1-3): 2
   oldRow: 6 oldCol: 6
   newRow: 6 newCol: 3
   Hero Sehanine_Moonbow moved to 6, 3
   Monsters' turn:
   oldRow: 1 oldCol: 4
   newRow: 2 newCol: 4
   Monster Casper moved to 2, 4
   oldRow: 1 oldCol: 7
   newRow: 2 newCol: 7
   Monster BigBad-Wolf moved to 2, 7
   oldRow: 2 oldCol: 4
   newRow: 3 newCol: 4
   Monster Casper moved to 3, 4
   Round 2 begins.
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
   |       |  |    M1 |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |    M3 |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
| H1    |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  | H3    |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H1 Garl_Glittergold's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 5 oldCol: 0
newRow: 4 newCol: 0
Hero Garl_Glittergold moved to 4, 0
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |    M1 |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |    M3 |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
| H1    |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  | H3    |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H2 Parzival's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 5 oldCol: 3
newRow: 4 newCol: 3
Hero Parzival moved to 4, 3
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |    M1 |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |    M3 |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
| H1    |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  | H3    |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H3 Sehanine_Moonbow's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): d
oldRow: 6 oldCol: 3
newRow: 6 newCol: 4
Sehanine_Moonbow gains strength bonus from Koulou!
Hero Sehanine_Moonbow moved to 6, 4
Monsters' turn:
Casper attacked Parzival! Parzival took 10 damage!
oldRow: 2 oldCol: 7
newRow: 3 newCol: 7
Monster BigBad-Wolf moved to 3, 7
oldRow: 3 oldCol: 4
newRow: 4 newCol: 4
Monster Casper moved to 4, 4
Round 3 begins.
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |    M1 |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |    M3 |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
| H1    |  |       |  | X X X |  | H2    |  |    M2 |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  | H3    |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H1 Garl_Glittergold's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 4 oldCol: 0
newRow: 3 newCol: 0
Garl_Glittergold gains strength bonus from Koulou!
Hero Garl_Glittergold moved to 3, 0
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |    M1 |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
| H1    |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |    M3 |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  | H2    |  |    M2 |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  | H3    |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H2 Parzival's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 3
Choose a monster to attack:
1. Casper          Spirit          1          100        100        100        50             
   Position: (0, 4)
   Choose a monster (1-1): 1
   Parzival attacked Casper! Casper dodged the attack!
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
   |       |  |    M1 |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
| H1    |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |    M3 |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  | H2    |  |    M2 |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  | H3    |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H3 Sehanine_Moonbow's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 3
No monsters within range to attack.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 6 oldCol: 4
newRow: 5 newCol: 4
Sehanine_Moonbow gains dexterity bonus from Bush!
Hero Sehanine_Moonbow moved to 5, 4
Monsters' turn:
Casper attacked Parzival! Parzival took 10 damage!
oldRow: 3 oldCol: 7
newRow: 4 newCol: 7
Monster BigBad-Wolf moved to 4, 7
oldRow: 4 oldCol: 4
newRow: 5 newCol: 4
Monster Casper moved to 5, 4
Round 4 begins.
Monster Andrealphus spawned in lane 1
Monster Casper spawned in lane 2
Monster Chrysophylax spawned in lane 3
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
| H1    |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |    M3 |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  | H3 M2 |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H1 Garl_Glittergold's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 3 oldCol: 0
newRow: 2 newCol: 0
Garl_Glittergold gains agility bonus from Cave!
Hero Garl_Glittergold moved to 2, 0
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
| H1    |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |    M3 |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  | H3 M2 |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H2 Parzival's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 3
Choose a monster to attack:
1. Casper          Spirit          1          100        100        100        50             
   Position: (0, 4)
   Choose a monster (1-1): 1
   Parzival attacked Casper! Casper dodged the attack!
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
   |       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
| H1    |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |    M3 |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  | H3 M2 |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H3 Sehanine_Moonbow's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 4
No spells to cast!
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 3
Choose a monster to attack:
1. Casper          Spirit          1          100        100        100        50             
   Position: (0, 4)
   Choose a monster (1-1): 1
   Sehanine_Moonbow attacked Casper! Casper dodged the attack!
   Monsters' turn:
   Casper attacked Parzival! Parzival took 10 damage!
   oldRow: 4 oldCol: 7
   newRow: 5 newCol: 7
   Monster BigBad-Wolf moved to 5, 7
   oldRow: 0 oldCol: 1
   newRow: 1 newCol: 1
   Monster Andrealphus moved to 1, 1
   oldRow: 0 oldCol: 4
   newRow: 1 newCol: 4
   Monster Casper moved to 1, 4
   oldRow: 0 oldCol: 7
   newRow: 1 newCol: 7
   Monster Chrysophylax moved to 1, 7
   Round 5 begins.
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
   |       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
|       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
| H1    |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  | H3 M2 |  | X X X |  |       |  |    M3 |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H1 Garl_Glittergold's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 2 oldCol: 0
newRow: 1 newCol: 0
Garl_Glittergold gains strength bonus from Koulou!
Hero Garl_Glittergold moved to 1, 0
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
| H1    |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  | H3 M2 |  | X X X |  |       |  |    M3 |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H2 Parzival's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 3
Choose a monster to attack:
1. Casper          Spirit          1          100        100        100        50             
   Position: (0, 4)
   Choose a monster (1-1): 1
   Parzival attacked Casper! Casper took 65 damage!
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
   |       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
| H1    |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  | H3 M2 |  | X X X |  |       |  |    M3 |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H3 Sehanine_Moonbow's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 3
Choose a monster to attack:
1. Casper          Spirit          1          35         100        100        50             
   Position: (0, 4)
   Choose a monster (1-1): 1
   Sehanine_Moonbow attacked Casper! Casper took 65 damage!
   Casper has been defeated!
   Monsters' turn:
   Casper attacked Parzival! Parzival took 10 damage!
   oldRow: 5 oldCol: 7
   newRow: 6 newCol: 7
   Monster BigBad-Wolf moved to 6, 7
   Andrealphus attacked Garl_Glittergold! Garl_Glittergold took 60 damage!
   oldRow: 1 oldCol: 4
   newRow: 2 newCol: 4
   Monster Casper moved to 2, 4
   oldRow: 1 oldCol: 7
   newRow: 2 newCol: 7
   Monster Chrysophylax moved to 2, 7
   Round 6 begins.
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
   |       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
   N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B  
| H1    |  |    M1 |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  B - B - B

C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P  
|       |  |       |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |  
C - C - C  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  P - P - P  P - P - P

K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
K - K - K  C - C - C  I - I - I  C - C - C  C - C - C  I - I - I  K - K - K  P - P - P

P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P  
|       |  |       |  | X X X |  | H2    |  |       |  | X X X |  |       |  |       |  
P - P - P  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  K - K - K  P - P - P

K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C  
|       |  |       |  | X X X |  |       |  | H3 M2 |  | X X X |  |       |  |       |  
K - K - K  K - K - K  I - I - I  K - K - K  B - B - B  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |    M3 |  
P - P - P  C - C - C  I - I - I  P - P - P  K - K - K  I - I - I  K - K - K  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |  
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

It's hero H1 Garl_Glittergold's turn.
Next movement?
(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall (8) Pass turn (9) Enter market (10) Show Statistics
Enter your choice: 5
(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right
Enter your choice (W/A/S/D): w
oldRow: 1 oldCol: 0
newRow: 0 newCol: 0
Hero H1 Garl_Glittergold cannot move behind a monster
Hero Garl_Glittergold moved to 0, 0
Hero Garl_Glittergold has reached the monster Nexus! Heroes win!
Welcome to the Game Manager!
1. Heroes and Monsters
2. Legends of Valor
3. Exit
   Enter your choice: 
