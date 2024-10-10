// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, 
// the screen should be cleared.


(LOOP)
   // n controls the current pixel address
   @n
   M=0   

   // Reading the keyboard input
   @KBD
   D=M
   
   // if (D == 0) goto WHITE
   @WHITE
   D;JEQ

   // else goto BLACK
   @BLACK
   0;JMP
   
(WHITE)
   // RAM[SCREEN + n] = 0 (white)
   @SCREEN
   D=A
   @n
   A=D+M
   M=0

   // n = n + 1
   @n
   M=M+1
   
   // if (n == 8.192) goto LOOP
   @8192
   D=A
   @n
   D=D-M
   @LOOP
   D;JEQ
   
   // else goto WHITE
   @WHITE
   0;JMP

(BLACK)
   // RAM[SCREEN + n] = 0 (white)
   @SCREEN
   D=A
   @n
   A=D+M
   M=-1

   // n = n + 1
   @n
   M=M+1
   
   // if (n == 8.192) goto LOOP
   @8192
   D=A
   @n
   D=D-M
   @LOOP
   D;JEQ

   // else goto BLACK
   @BLACK
   0;JMP