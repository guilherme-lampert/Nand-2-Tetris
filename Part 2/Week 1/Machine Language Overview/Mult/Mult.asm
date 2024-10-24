// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
// The algorithm is based on repetitive addition.

// Improvements from my first version: the code is shorter and more readable. The variable "i" is no longer used.

   // Setting R2 to 0
   @R2
   M=0

(LOOP)
   
   // If R1 is less or equal to 0, goto END
   @R1
   D=M
   @END
   D;JLE
   
   // If R1 is greater than zero, select R0 and add it to R2
   @R0
   D=M
   @R2
   M=D+M

   // Decrement 1 from R1, so that it adds R0 to R2 R1 times
   @R1
   M=M-1
   
   // goto LOOP
   @LOOP
   0;JMP
   
(END)
   @END
   0;JMP
