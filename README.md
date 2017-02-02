# BankEscape
Labyrinth tactic infiltration game. Original idea by Bauwens Alexandre and Dupont Jules

## What is it??
* Solo : You are playing a thief in a bank who's purpose is to steal the money in a vault. This is a labyrinthinc bank that you'll have to explore in order to find a drill which will give you the ability to enter the vault and get the money. Just run to the entry, or if you have the secret key you can run to the special exit, in order to flee with your theft. You'll then go to the next level or finish the game.
* Editor : You can create your own level and save it in the directory levels. To manage to create a valid maze you'll have to create corridors and avoid rooms of more than 2 squares of width.

## Known issues
* If you save when being in a door or a vault, then you will not be able to load the game correctly because during the loading from the database your player was not saved in the DB.
* In editor, the resize option make strange design to the window and the images due to a bad resize method.
* In editor, when trying to save a level, it may happens that you can't validate your level because the pathfinding is not working between your player and the needed items.

## Features not implemented
* The editor still can't give you the ability to link your levels.
* The IA is waaaaay too random so they will sometimes stay stuck in a corner for a while. We need to make them remember their path.
* Your player has only one chance to win a level (or succession of level) meaning he will instantly die and close the game. There is no score implemented for your player either.