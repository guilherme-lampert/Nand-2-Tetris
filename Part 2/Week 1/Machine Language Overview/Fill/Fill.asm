// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, 
// the screen should be cleared.

// Improvements from my first version: now, the program only fills the screen if the KBD command is different from the color on the screen.
// Ex: If the screen is already white and no key is being pressed, the program will simply read the keyboard again.
// The code is also more readable

(LOOP)   
   // Read the KBD
   @KBD
   D=M
   
   // If KBD is white, goto WHITE
   @WHITE
   D;JEQ

   // else, goto BLACK
   @BLACK
   0;JMP

(WHITE)   
   // If screen is already white, goto LOOP
   @SCREEN
   D=M
   @LOOP
   D;JEQ

   // else, set the paint color variable to white and goto FILL 
   @color
   M=0
   @FILL
   0;JMP

(BLACK)
   // If screen is already black, goto LOOP
   @SCREEN
   D=M
   @LOOP
   D;JNE

   // else, set the paint color variable to black and goto FILL
   @color
   M=-1
   @FILL
   0;JMP

(FILL)
   // Start the filling by selecting the first word of the screen map and saving it into the current screen address variable (csa)
   @SCREEN
   D=A
   @csa
   M=D
   
(FILLING)
   // Selecting the current screen address and switching its color with the color variable value
   @color
   D=M
   @csa
   A=M
   M=D
   
   // Moving to the next screen address
   @csa
   M=M+1
   
   // If the current screen address is less or equal to 24.575 (the last screen address) the filling continues
   @24575
   D=A
   @csa
   D=M-D
   @FILLING
   D;JLE

   // Else the filling is complete and the program goes back to LOOP
   @LOOP
   0;JMP
