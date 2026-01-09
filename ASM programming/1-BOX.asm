; Set video mode to 13h (320x200 graphics mode, 256 colors)
MOV AH, 0
MOV AL, 13H
INT 10H

; Draw the first box
MOV CX, 30    ; Starting column (X position)
MOV DX, 50    ; Starting row (Y position)
MOV BL, 70    ; Length of the box lines

; Draw the top horizontal line of the first box
firstline:
MOV AH, 0CH   ; Function to write a pixel
MOV AL, 10    ; Color
INT 10H       ; Interrupt to draw
INC CX        ; Move to the next column
DEC BL        ; Decrease box line length
JNZ firstline ; Repeat until BL reaches zero

; Draw the right vertical line of the first box
MOV BL, 70
secondline:
MOV AH, 0CH
MOV AL, 10
INT 10H
INC DX        ; Move to the next row
DEC BL
JNZ secondline

; Draw the bottom horizontal line of the first box
MOV BL, 70
thirdline:
MOV AH, 0CH
MOV AL, 10
INT 10H
DEC CX        ; Move back to the previous column
DEC BL
JNZ thirdline

; Draw the left vertical line of the first box
MOV BL, 70
fourthline:
MOV AH, 0CH
MOV AL, 10
INT 10H
DEC DX        ; Move back to the previous row
DEC BL
JNZ fourthline

; Draw the second box
MOV CX, 120   ; Starting column (X position) for the second box
MOV DX, 50    ; Starting row (Y position)
MOV BL, 70

; Draw the top horizontal line of the second box
firstline2:
MOV AH, 0CH
MOV AL, 12    ; Color
INT 10H
INC CX
DEC BL
JNZ firstline2

; Draw the right vertical line of the second box
MOV BL, 70
secondline2:
MOV AH, 0CH
MOV AL, 12
INT 10H
INC DX
DEC BL
JNZ secondline2

; Draw the bottom horizontal line of the second box
MOV BL, 70
thirdline2:
MOV AH, 0CH
MOV AL, 12
INT 10H
DEC CX
DEC BL
JNZ thirdline2

; Draw the left vertical line of the second box
MOV BL, 70
fourthline2:
MOV AH, 0CH
MOV AL, 12
INT 10H
DEC DX
DEC BL
JNZ fourthline2

; Draw the third box
MOV CX, 210   ; Starting column (X position) for the third box
MOV DX, 50    ; Starting row (Y position)
MOV BL, 70

; Draw the top horizontal line of the third box
firstline3:
MOV AH, 0CH
MOV AL, 14    ; Color
INT 10H
INC CX
DEC BL
JNZ firstline3

; Draw the right vertical line of the third box
MOV BL, 70
secondline3:
MOV AH, 0CH
MOV AL, 14
INT 10H
INC DX
DEC BL
JNZ secondline3

; Draw the bottom horizontal line of the third box
MOV BL, 70
thirdline3:
MOV AH, 0CH
MOV AL, 14
INT 10H
DEC CX
DEC BL
JNZ thirdline3

; Draw the left vertical line of the third box
MOV BL, 70
fourthline3:
MOV AH, 0CH
MOV AL, 14
INT 10H
DEC DX
DEC BL
JNZ fourthline3

; End of program - hang in infinite loop
HLT
