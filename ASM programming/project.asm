.model small
.stack 100h
dseg segment                                   
tryAgainInput db "Do you want to try again? [y/n]: $"
invalid db "Invalid input. $"
bye db "*----THANK YOU----* $"

enterPass db "Enter Password: $"
passAc1 db "*-----------------* $"
passAcc db "*Password Accepted* $"
passAc2 db "*-----------------* $"
passDe1 db "*-----------------* $"
passDen db "* Password Denied * $"
passDe2 db "*-----------------* $"

cca db "City College of Angeles $"
icslis db "Institute of Computing Studies and Library Information Science $"
finalreq db "Final Requirement Assembly Language Program $"
subby db "Submitted by: $"
lpc db "Lynchee P. Circulo$"
rcn db "Rafael Charlie S. Nain$"
jrp db "Jerald Patangan$"
sgt db "Shanon G. Ticse$"
key db "Press any key to proceed to the Main Menu... $"

mainmenu db "MAIN MENU$"
1 db "[1] Display Drawing Box $"
2 db "[2] Verify if Vowel or Consonant $"
3 db "[3] Display Colored Cat $"
4 db "[4] Display Hello Kitty $" 
5 db "[5] Input Letter and Stop at Specific Character $"
ex db "[X] Exit $"
choice db "Enter your choice (1-5) $"

inptletter db "Input letter to verify if it's vowel or consonant: $"
vow db "Vowel$"
cons db "Consonant$"
invalidletter db "Invalid input, please enter a letter... $"

inptletlet db "Input letter and stop at specific character: $"
msg1 db "A$"
msg2 db "AB$"
msg3 db "ABC$"
msg4 db "ABCD$"
msg5 db "ABCDE$"
msg6 db "ABCDEF$"
msg7 db "ABCDEFG$"
msg8 db "ABCDEFGH$"
msg9 db "ABCDEFGHI$"
msg10 db "ABCDEFGHIJ$"
msg11 db "ABCDEFGHIJK$"
msg12 db "ABCDEFGHIJKL$"
msg13 db "ABCDEFGHIJKLM$"
msg14 db "ABCDEFGHIJKLMN$"
msg15 db "ABCDEFGHIJKLMNO$"
msg16 db "ABCDEFGHIJKLMNOP$"
msg17 db "ABCDEFGHIJKLMNOPQ$"
msg18 db "ABCDEFGHIJKLMNOPQR$"
msg19 db "ABCDEFGHIJKLMNOPQRS$"
msg20 db "ABCDEFGHIJKLMNOPQRST$"
msg21 db "ABCDEFGHIJKLMNOPQRSTU$"
msg22 db "ABCDEFGHIJKLMNOPQRSTUV$"
msg23 db "ABCDEFGHIJKLMNOPQRSTUVW$"
msg24 db "ABCDEFGHIJKLMNOPQRSTUVWX$"
msg25 db "ABCDEFGHIJKLMNOPQRSTUVWXY$"
msg26 db "ABCDEFGHIJKLMNOPQRSTUVWXYZ$"

msgnum1 db   'Enter a number between 0-256: $'
msgnum2 db   'Number is even $'
msgnum3 db   'Number is odd $'
invalidnum db "Invalid input, please enter a number... $"
newline db 0Dh, 0Ah, '$'  ; Carriage return + line feed for new line

inpt db 8 
dseg ends   
cseg segment
assume cs:cseg,ds:dseg

main proc far
mov ax, dseg
mov ds, ax
call pword
ret
main endp

pword proc near
call clear
password_entry:
mov si, 0
mov inpt[si], 'I'
inc si
mov inpt[si], 'M'
inc si
mov inpt[si], 'S'
inc si
mov inpt[si], 'U'
inc si
mov inpt[si], 'K'
inc si
mov inpt[si], 'O'
inc si
mov inpt[si], 'N'
inc si
mov inpt[si], 'A'

    mov dx, 0A16h
    call set
    lea dx, enterPass
    call display
    
mov si, 0
psentry:
call con
cmp inpt[si], al
call asterisk

inc si
call con
cmp inpt[si], al
call asterisk

inc si
call con
cmp inpt[si], al
call asterisk

inc si
call con
cmp inpt[si], al
call asterisk

inc si
call con
cmp inpt[si], al
call asterisk

inc si
call con
cmp inpt[si], al
call asterisk

inc si
call con
cmp inpt[si], al
call asterisk

inc si
call con
cmp inpt[si], al
call asterisk

jne psinvalid

;kapag valid
mov dx, 0E19h
call set
lea dx, passAc1
call display
mov dx, 0F19h
call set
lea dx, passAcc
call display
mov dx, 1019h
call set
lea dx, passAc2
call display
call clear
jmp intro 

psinvalid:
mov dx, 0E19h
call set
lea dx, passDe1
call display
mov dx, 0F19h
call set
lea dx, passDen
call display 
mov dx, 1019h
call set
lea dx, passDe2
call display
call tryPass

jmp exit
pword endp

intro proc near
mov dx, 031Ah
call set
lea dx, cca
call display

mov dx, 0506h
call set
lea dx, icslis
call display

mov dx, 060Fh
call set
lea dx, finalreq
call display

mov dx, 081Ch
call set
lea dx, subby
call display

mov dx, 091Ah
call set
lea dx, lpc
call display

mov dx, 0A1Ah
call set
lea dx, rcn
call display

mov dx, 0B1Ah
call set
lea dx, jrp
call display

mov dx, 0C1Ah
call set
lea dx, sgt
call display 

mov dx, 0E10h
call set
lea dx, key
call display
call con
call clear
je menu
intro endp

menu proc near
call clear
mov dx, 0608h
call set
lea dx, mainmenu
call display

mov dx, 0708h
call set
lea dx, 1
call display

mov dx, 0808h
call set
lea dx, 2
call display

mov dx, 0908h
call set
lea dx, 3
call display

mov dx, 0A08h
call set
lea dx, 4
call display 

mov dx, 0B08h
call set
lea dx, 5
call display

mov dx, 0C08h
call set
lea dx, ex
call display

mov dx, 0E08h
call set
lea dx, choice
call display

call input
cmp al, '1'
je option1
cmp al, '2'
je option2
cmp al, '3'
je option3
cmp al, '4'
je option4
cmp al, '5'
je option5
cmp al, 'X'
je exit
cmp al, 'x'
je exit

;invalid input
call clear
jmp invalidInput


invalidInput:
mov dx, 091Fh
call set
lea dx, invalid
call display 
mov dx, 0B16h
call set
lea dx, tryAgainInput
call display    
call input
cmp al, 'y'
je menu
cmp al, 'Y'
je menu
cmp al, 'n'
je exit
cmp al, 'N'
je exit  
jne invalidInput

menu endp 

tryPass proc near
call clear
mov dx, 091Fh
call set
lea dx, invalid
call display
mov dx, 0B16h
call set
lea dx, tryAgainInput
call display

call input 
cmp al, 'y'
je pword
cmp al, 'Y'
je pword
cmp al, 'n'
je exit
cmp al, 'N'
je exit  
jne tryPass
tryPass endp

option1 proc near
call clear

; Set video mode to 13h (320x200 graphics mode, 256 colors)
MOV AH, 0
MOV AL, 13H
INT 10H

mov dx, 0307h
call set
lea dx, 1
call display

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


mov dx, 1104h
call set
lea dx, tryAgainInput
call display    
call input
cmp al, 'y'
je option1
cmp al, 'Y'
je option1
cmp al, 'n'
je menu
cmp al, 'N'
je menu  
jne againOption1
option1 endp 

againOption1 proc near
call clear
mov dx, 0900h
call set
lea dx, invalid
call display
mov dx, 0a00h
call set
lea dx, tryAgainInput
call display    
call input
cmp al, 'y'
je option1
cmp al, 'Y'
je option1
cmp al, 'n'
je menu
cmp al, 'N'
je menu  
jne againOption1
againOption1 endp

option2 proc near
call clear
mov dx, 041Bh
call set
lea dx, 2
call display

mov dx, 0610h
call set
lea dx, inptletter
call display

input_loop:
; Take input from the user
call input

; Check if the input is a valid letter
cmp al, 'A'
jl invalid_input ; If below 'A', invalid
cmp al, 'Z'
jle check_vowel_upper ; If between 'A' and 'Z', proceed
cmp al, 'a'
jl invalid_input ; If below 'a', invalid
cmp al, 'z'
jg invalid_input ; If above 'z', invalid

; If valid lowercase, check vowels
jmp check_vowel_lower

check_vowel_upper:
cmp al, 'A'
je vowel
cmp al, 'E'
je vowel
cmp al, 'I'
je vowel
cmp al, 'O'
je vowel
cmp al, 'U'
je vowel
jmp consonant

check_vowel_lower:
cmp al, 'a'
je vowel
cmp al, 'e'
je vowel
cmp al, 'i'
je vowel
cmp al, 'o'
je vowel
cmp al, 'u'
je vowel
jmp consonant

invalid_input:
call clear
mov dx, 0816h
call set
lea dx, invalidletter
call display  
jmp input_loop ; Loop back to take input again

option2 endp



vowel proc near
mov dx, 0A27h
call set
lea dx, vow
call display 

mov dx, 0C1Ah
call set
lea dx, tryAgainInput
call display    
call input
cmp al, 'y'
je option2
cmp al, 'Y'
je option2
cmp al, 'n'
je menu
cmp al, 'N'
je menu  
jne againOption2
ret
vowel endp

consonant proc near
mov dx, 0A27h
call set
lea dx, cons
call display
mov dx, 0C1Ah
call set
lea dx, tryAgainInput
call display    
call input
cmp al, 'y'
je option2
cmp al, 'Y'
je option2
cmp al, 'n'
je menu
cmp al, 'N'
je menu  
jne againOption2
ret
consonant endp

againOption2 proc near
call clear
mov dx, 0900h
call set
lea dx, invalid
call display
mov dx, 0a00h
call set
lea dx, tryAgainInput
call display    
call input
cmp al, 'y'
je option2
cmp al, 'Y'
je option2
cmp al, 'n'
je menu
cmp al, 'N'
je menu  
jne againOption2
againOption2 endp

option3 proc near
call clear
mov ax, 03h    
int 10h    

MOV AX, 0600h    
MOV CX, 0000h    
MOV DX, 184Fh  
MOV BH, 0f0h   
INT 10h

MOV CX, 051Dh    
MOV DX, 0A1Dh  
MOV BH, 00h   
INT 10h

MOV CX, 041Eh    
MOV DX, 0420h  
MOV BH, 00h   
INT 10h

MOV CX, 051Eh    
MOV DX, 0A1Eh  
MOV BH, 0D0h   
INT 10h

MOV CX, 051Fh    
MOV DX, 0A1Fh  
MOV BH, 0D0h   
INT 10h

MOV CX, 0620h    
MOV DX, 0920h  
MOV BH, 0D0h   
INT 10h

MOV CX, 0520h    
MOV DX, 0522h  
MOV BH, 00h   
INT 10h

MOV CX, 0B1Eh    
MOV DX, 0C1Eh  
MOV BH, 00h   
INT 10h 

MOV CX, 0621h    
MOV DX, 0621h  
MOV BH, 00h   
INT 10h    

MOV CX, 0623h    
MOV DX, 0623h  
MOV BH, 00h   
INT 10h

MOV CX, 0722h    
MOV DX, 0B22h  
MOV BH, 00h   
INT 10h

MOV CX, 0724h    
MOV DX, 0824h  
MOV BH, 00h   
INT 10h

MOV CX, 0825h    
MOV DX, 0829h  
MOV BH, 00h   
INT 10h                

MOV CX, 0B1Fh    
MOV DX, 0B1Fh  
MOV BH, 00h   
INT 10h

MOV CX, 0A20h    
MOV DX, 0A20h  
MOV BH, 00h   
INT 10h

MOV CX, 0921h    
MOV DX, 0923h  
MOV BH, 00h   
INT 10h 

MOV CX, 0721h    
MOV DX, 0821h  
MOV BH, 0D0h   
INT 10h  

MOV CX, 0D1fh    
MOV DX, 0d1fh  
MOV BH, 00h   
INT 10h  

MOV CX, 0D21h    
MOV DX, 0d23h  
MOV BH, 00h   
INT 10h 

MOV CX, 0e1eh    
MOV DX, 121eh  
MOV BH, 00h   
INT 10h 

MOV CX, 131fh    
MOV DX, 131fh  
MOV BH, 00h   
INT 10h

MOV CX, 141eh    
MOV DX, 1420h  
MOV BH, 00h   
INT 10h

MOV CX, 1521h    
MOV DX, 1522h  
MOV BH, 00h   
INT 10h

MOV CX, 1623h    
MOV DX, 162Bh  
MOV BH, 00h   
INT 10h 

MOV CX, 152ch    
MOV DX, 152dh  
MOV BH, 00h   
INT 10h 

MOV CX, 142eh    
MOV DX, 1430h  
MOV BH, 00h   
INT 10h 

MOV CX, 132fh    
MOV DX, 132fh  
MOV BH, 00h   
INT 10h 

MOV CX, 0e30h    
MOV DX, 1230h  
MOV BH, 00h   
INT 10h

MOV CX, 0d2fh    
MOV DX, 0d2fh  
MOV BH, 00h   
INT 10h  

MOV CX, 0b30h    
MOV DX, 0c30h  
MOV BH, 00h   
INT 10h   

MOV CX, 0b2fh    
MOV DX, 0b2fh  
MOV BH, 00h   
INT 10h 

MOV CX, 0531h    
MOV DX, 0a31h  
MOV BH, 00h   
INT 10h

MOV CX, 042eh    
MOV DX, 0430h  
MOV BH, 00h   
INT 10h
    
MOV CX, 052Ch    
MOV DX, 052Eh  
MOV BH, 00h   
INT 10h  

MOV CX, 062Dh    
MOV DX, 062Dh  
MOV BH, 00h   
INT 10h

MOV CX, 062Bh    
MOV DX, 062Bh  
MOV BH, 00h   
INT 10h
      
MOV CX, 072Ah    
MOV DX, 082Ah  
MOV BH, 00h   
INT 10h 

MOV CX, 072Ch    
MOV DX, 0B2Ch  
MOV BH, 00h   
INT 10h 

MOV CX, 092Bh    
MOV DX, 092Dh  
MOV BH, 00h   
INT 10h  

MOV CX, 0A2Eh    
MOV DX, 0A2Eh  
MOV BH, 00h   
INT 10h

MOV CX, 0D2Bh    
MOV DX, 0D2Dh  
MOV BH, 00h   
INT 10h 

MOV CX, 0E2Ah    
MOV DX, 0E2Ah  
MOV BH, 00h   
INT 10h  

MOV CX, 0E2Eh    
MOV DX, 0E2Eh  
MOV BH, 00h   
INT 10h   

MOV CX, 0E20h    
MOV DX, 0E20h  
MOV BH, 00h   
INT 10h    

MOV CX, 0E24h    
MOV DX, 0E24h  
MOV BH, 00h   
INT 10h 

MOV CX, 0F1Fh    
MOV DX, 101Fh  
MOV BH, 00h   
INT 10h

MOV CX, 0F25h    
MOV DX, 1025h  
MOV BH, 00h   
INT 10h

MOV CX, 0F29h    
MOV DX, 1029h  
MOV BH, 00h   
INT 10h

MOV CX, 0F2Fh    
MOV DX, 102Fh  
MOV BH, 00h   
INT 10h  

MOV CX, 1120h    
MOV DX, 1120h  
MOV BH, 00h   
INT 10h

MOV CX, 1124h    
MOV DX, 1124h  
MOV BH, 00h   
INT 10h  

MOV CX, 112Ah    
MOV DX, 112Ah  
MOV BH, 00h   
INT 10h 

MOV CX, 112Eh    
MOV DX, 112Eh  
MOV BH, 00h   
INT 10h 

MOV CX, 1221h    
MOV DX, 1223h  
MOV BH, 00h   
INT 10h  

MOV CX, 122Bh    
MOV DX, 122Dh  
MOV BH, 00h   
INT 10h 

MOV CX, 1227h    
MOV DX, 1327h  
MOV BH, 00h   
INT 10h  

MOV CX, 1426h    
MOV DX, 1426h  
MOV BH, 00h   
INT 10h 

MOV CX, 1428h    
MOV DX, 1428h  
MOV BH, 00h   
INT 10h  

MOV CX, 0827h    
MOV DX, 0A27h  
MOV BH, 00h   
INT 10h   

MOV CX, 052Fh    
MOV DX, 0A30h  
MOV BH, 0D0h   
INT 10h

MOV CX, 062Eh    
MOV DX, 092Eh  
MOV BH, 0D0h   
INT 10h
   
MOV CX, 072Dh    
MOV DX, 082Dh  
MOV BH, 0D0h   
INT 10h 

MOV CX, 0f22h    
MOV DX, 1023h  
MOV BH, 020h   
INT 10h

MOV CX, 0e22h    
MOV DX, 0e23h  
MOV BH, 0a0h   
INT 10h

MOV CX, 1122h    
MOV DX, 1123h  
MOV BH, 0a0h   
INT 10h 

MOV CX, 0f21h    
MOV DX, 1021h  
MOV BH, 0a0h   
INT 10h 

MOV CX, 0f24h    
MOV DX, 1024h  
MOV BH, 0a0h   
INT 10h 

MOV CX, 0f2bh    
MOV DX, 102ch  
MOV BH, 020h   
INT 10h 

MOV CX, 0e2bh    
MOV DX, 0e2ch  
MOV BH, 0a0h   
INT 10h 

MOV CX, 112bh    
MOV DX, 112ch  
MOV BH, 0a0h   
INT 10h

MOV CX, 0f2dh    
MOV DX, 102dh  
MOV BH, 0a0h   
INT 10h

MOV CX, 0f2ah    
MOV DX, 102ah  
MOV BH, 0a0h   
INT 10h

mov dx, 0000h
call set
lea dx, tryAgainInput
call display    
call input

cmp al, 'y'
je clear_and_retry
cmp al, 'Y'
je clear_and_retry
cmp al, 'n'
je clear_and_menu
cmp al, 'N'
je clear_and_menu
jne againOption3

clear_and_retry:
; Clear screen and reset to default attributes
mov ah, 06h       ; Scroll up function
mov al, 00h       ; Clear entire screen
mov bh, 07h       ; Default attribute: white text on black background
mov cx, 0000h     ; Upper-left corner (row 0, column 0)
mov dx, 184fh     ; Lower-right corner (row 24, column 79)
int 10h             

; Reset cursor position to the top left
mov ah, 02h       ; Set cursor position
mov bh, 00h       ; Page number
mov dh, 00h       ; Row 0
mov dl, 00h       ; Column 0
int 10h 

jmp option3

clear_and_menu:
; Clear screen and reset to default attributes
mov ah, 06h       ; Scroll up function
mov al, 00h       ; Clear entire screen
mov bh, 07h       ; Default attribute: white text on black background
mov cx, 0000h     ; Upper-left corner (row 0, column 0)
mov dx, 184fh     ; Lower-right corner (row 24, column 79)
int 10h             

; Reset cursor position to the top left
mov ah, 02h       ; Set cursor position
mov bh, 00h       ; Page number
mov dh, 00h       ; Row 0
mov dl, 00h       ; Column 0
int 10h 

jmp menu

option3 endp

againOption3 proc near
call clear
mov dx, 0900h
call set
lea dx, invalid
call display
mov dx, 0a00h
call set
lea dx, tryAgainInput
call display    
call input
cmp al, 'y'
je option3
cmp al, 'Y'
je option3
cmp al, 'n'
je menu
cmp al, 'N'
je menu  
jne againOption3
againOption3 endp

option4 proc near
call clear
   
mov ax,03h
int 10h

mov ax,0600h
mov cx,0000h
mov dx,5050h
mov bh,0f0h
int 10h  

mov cx,0721h
mov dx,0728h
mov bh,000h
int 10h   

mov cx,0620h
mov dx,0620h
mov bh,000h
int 10h 

mov cx,051bh
mov dx,051fh
mov bh,000h
int 10h

mov cx,061ah
mov dx,081ah
mov bh,000h
int 10h

mov cx,091bh
mov dx,091bh
mov bh,000h
int 10h

mov cx,091ch
mov dx,091ch
mov bh,000h
int 10h

mov cx,0a1ah
mov dx,0b1ah
mov bh,000h
int 10h  

mov cx,0c19h
mov dx,0c19h
mov bh,000h
int 10h

mov cx,0d19h
mov dx,0f19h
mov bh,000h
int 10h

mov cx,0f1ah
mov dx,0f1ah
mov bh,000h
int 10h 

mov cx,101ah
mov dx,101ah
mov bh,000h
int 10h

mov cx,111ah
mov dx,111ah
mov bh,000h
int 10h

mov cx,121bh
mov dx,121bh
mov bh,000h
int 10h 

mov cx,131ch
mov dx,131ch
mov bh,000h
int 10h

mov cx,131dh
mov dx,131dh
mov bh,000h
int 10h

;chin
mov cx,141eh
mov dx,1430h
mov bh,000h
int 10h 


mov cx,1331h
mov dx,1331h
mov bh,000h
int 10h

mov cx,1232h
mov dx,1232h
mov bh,000h
int 10h   

mov cx,1133h
mov dx,1133h
mov bh,000h
int 10h 

mov cx,0f33h
mov dx,1033h
mov bh,000h
int 10h

mov cx,0c34h
mov dx,0e34h
mov bh,000h
int 10h 

mov cx,0a83h
mov dx,0a83h
mov bh,000h
int 10h

mov cx,0934h
mov dx,0a34h
mov bh,000h
int 10h 


mov cx,0583h
mov dx,0783h
mov bh,000h
int 10h 


mov cx,047fh
mov dx,0482h
mov bh,000h
int 10h

;Ribon upper part
mov cx,0780h
mov dx,0782h
mov bh,000h
int 10h

mov cx,0680h
mov dx,0680h
mov bh,000h
int 10h

mov cx,067dh
mov dx,067fh
mov bh,000h
int 10h

mov cx,057eh
mov dx,057eh
mov bh,000h
int 10h

mov cx,077ch
mov dx,077ch
mov bh,000h
int 10h

mov cx,047ah
mov dx,047dh
mov bh,000h
int 10h  

mov cx,0579h
mov dx,0579h
mov bh,000h
int 10h  

mov cx,0880h
mov dx,0880h
mov bh,000h
int 10h


;Ribon lower part
mov cx,0b80h
mov dx,0b82h
mov bh,000h
int 10h

mov cx,097fh
mov dx,0a7fh
mov bh,000h
int 10h 

mov cx,097ch
mov dx,097eh
mov bh,000h
int 10h

mov cx,087ch
mov dx,087ch
mov bh,000h
int 10h

mov cx,087ah
mov dx,087bh
mov bh,000h
int 10h  

mov cx,0779h
mov dx,0779h
mov bh,000h
int 10h

mov cx,0679h
mov dx,0679h
mov bh,000h
int 10h

;Ribbon Color 
mov cx,0a80h
mov dx,0a82h
mov bh,0c0h
int 10h

mov cx,0980h
mov dx,0983h
mov bh,0c0h
int 10h


mov cx,0881h
mov dx,0883h
mov bh,0c0h
int 10h 

mov cx,087dh
mov dx,087fh
mov bh,0c0h
int 10h


mov cx,077dh
mov dx,077fh
mov bh,0c0h
int 10h

mov cx,077ah
mov dx,077bh
mov bh,0c0h
int 10h 

mov cx,067ah
mov dx,067ch
mov bh,0c0h
int 10h

mov cx,057ah
mov dx,057dh
mov bh,0c0h
int 10h

;Right eye 
mov cx,0d2eh
mov dx,0e2eh
mov bh,000h
int 10h

mov cx,0d2dh
mov dx,0e2dh
mov bh,000h
int 10h

;Left eye
mov cx,0d20h
mov dx,0d21h
mov bh,000h
int 10h

mov cx,0e20h
mov dx,0e21h
mov bh,000h
int 10h

;mouth
mov cx,1026h
mov dx,1028h
mov bh,0e0h
int 10h


mov dx, 0000h
call set
lea dx, tryAgainInput
call display    
call input

cmp al, 'y'
je clearretry4
cmp al, 'Y'
je clearretry4
cmp al, 'n'
je clearmenu4
cmp al, 'N'
je clearmenu4
jne againOption4

clearretry4:
; Clear screen and reset to default attributes
mov ah, 06h       ; Scroll up function
mov al, 00h       ; Clear entire screen
mov bh, 07h       ; Default attribute: white text on black background
mov cx, 0000h     ; Upper-left corner (row 0, column 0)
mov dx, 184fh     ; Lower-right corner (row 24, column 79)
int 10h             

; Reset cursor position to the top left
mov ah, 02h       ; Set cursor position
mov bh, 00h       ; Page number
mov dh, 00h       ; Row 0
mov dl, 00h       ; Column 0
int 10h 

jmp option4

clearmenu4:
; Clear screen and reset to default attributes
mov ah, 06h       ; Scroll up function
mov al, 00h       ; Clear entire screen
mov bh, 07h       ; Default attribute: white text on black background
mov cx, 0000h     ; Upper-left corner (row 0, column 0)
mov dx, 184fh     ; Lower-right corner (row 24, column 79)
int 10h             

; Reset cursor position to the top left
mov ah, 02h       ; Set cursor position
mov bh, 00h       ; Page number
mov dh, 00h       ; Row 0
mov dl, 00h       ; Column 0
int 10h 

jmp menu
option4 endp

againOption4 proc near
call clear
mov dx, 0900h
call set
lea dx, invalid
call display
mov dx, 0a00h
call set
lea dx, tryAgainInput
call display    
call input
cmp al, 'y'
je option4
cmp al, 'Y'
je option4
cmp al, 'n'
je menu
cmp al, 'N'
je menu  
jne againOption4
againOption4 endp

option5 proc near
call clear
mov dx, 0310h
call set
lea dx, 5
call display

mov dx, 0610h
call set
lea dx, inptletlet
call display

inputlet:
call input
cmp al, 'A'
je display1
cmp al, 'a'
je display1
cmp al, 'B'
je display2
cmp al, 'b'
je display2
cmp al, 'C'
je display3
cmp al, 'c'
je display3
cmp al, 'D'
je display4
cmp al, 'd'
je display4
cmp al, 'E'
je display5
cmp al, 'e'
je display5
cmp al, 'F'
je display6
cmp al, 'f'
je display6
cmp al, 'G'
je display7
cmp al, 'g'
je display7
cmp al, 'H'
je display8
cmp al, 'h'
je display8
cmp al, 'I'
je display9
cmp al, 'i'
je display9
cmp al, 'J'
je display10
cmp al, 'j'
je display10
cmp al, 'K'
je display11
cmp al, 'k'
je display11
cmp al, 'L'
je display12
cmp al, 'l'
je display12
cmp al, 'M'
je display13
cmp al, 'm'
je display13
cmp al, 'N'
je display14
cmp al, 'n'
je display14
cmp al, 'O'
je display15
cmp al, 'o'
je display15
cmp al, 'P'
je display16
cmp al, 'p'
je display16
cmp al, 'Q'
je display17
cmp al, 'q'
je display17
cmp al, 'R'
je display18
cmp al, 'r'
je display18
cmp al, 'S'
je display19
cmp al, 's'
je display19
cmp al, 'T'
je display20
cmp al, 't'
je display20
cmp al, 'U'
je display21
cmp al, 'u'
je display21
cmp al, 'V'
je display22
cmp al, 'v'
je display22
cmp al, 'W'
je display23
cmp al, 'w'
je display23
cmp al, 'X'
je display24
cmp al, 'x'
je display24
cmp al, 'Y'
je display25
cmp al, 'y'
je display25
cmp al, 'Z'
je display26
cmp al, 'z'
je display26
jmp invalid_inputlet

display1:
mov dx, 0828h
call set
lea dx, msg1
call display
call tryOption5

display2:
mov dx, 0827h
call set
lea dx, msg2
call display
call tryOption5

display3:
mov dx, 0826h
call set
lea dx, msg3
call display
call tryOption5

display4:
mov dx, 0825h
call set
lea dx, msg4
call display
call tryOption5

display5:
mov dx, 0824h
call set
lea dx, msg5
call display
call tryOption5

display6:
mov dx, 0823h
call set
lea dx, msg6
call display
call tryOption5

display7:
mov dx, 0822h
call set
lea dx, msg7
call display
call tryOption5

display8:
mov dx, 0821h
call set
lea dx, msg8
call display
call tryOption5

display9:
mov dx, 0820h
call set
lea dx, msg9
call display
call tryOption5

display10:
mov dx, 081Fh
call set
lea dx, msg10
call display
call tryOption5

display11:
mov dx, 081Eh
call set
lea dx, msg11
call display
call tryOption5

display12:
mov dx, 081Dh
call set
lea dx, msg12
call display
call tryOption5

display13:
mov dx, 081Ch
call set
lea dx, msg13
call display 
call tryOption5

display14:
mov dx, 081Bh
call set
lea dx, msg14
call display
call tryOption5

display15:
mov dx, 081Ah
call set
lea dx, msg15
call display
call tryOption5

display16:
mov dx, 0819h
call set
lea dx, msg16
call display 
call tryOption5

display17:
mov dx, 0818h
call set
lea dx, msg17
call display  
call tryOption5

display18:
mov dx, 0817h
call set
lea dx, msg18
call display 
call tryOption5

display19:
mov dx, 0816h
call set
lea dx, msg19
call display 
call tryOption5

display20:
mov dx, 0815h
call set
lea dx, msg20
call display  
call tryOption5

display21:
mov dx, 0814h
call set
lea dx, msg21
call display 
call tryOption5

display22:
mov dx, 0813h
call set
lea dx, msg22
call display 
call tryOption5

display23:
mov dx, 0812h
call set
lea dx, msg23
call display  
call tryOption5

display24:
mov dx, 0811h
call set
lea dx, msg24
call display  
call tryOption5

display25:
mov dx, 0810h
call set
lea dx, msg25
call display  
call tryOption5

display26:
mov dx, 080Fh
call set
lea dx, msg26
call display
call tryOption5   

invalid_inputlet:
call clear
mov dx, 0619h
call set
lea dx, invalidletter
call display
jmp inputlet
   
option5 endp 

tryOption5 proc near
mov dx, 0a16h
call set
lea dx, tryAgainInput
call display    
call input
cmp al, 'y'
je option5
cmp al, 'Y'
je option5
cmp al, 'n'
je menu
cmp al, 'N'
je menu  
jne againOption5
tryOption5 endp    

againOption5 proc near
call clear
mov dx, 0616h
call set
lea dx, invalid
call display
mov dx, 0a06h
call set
lea dx, tryAgainInput
call display    
call input
cmp al, 'y'
je option5
cmp al, 'Y'
je option5
cmp al, 'n'
je menu
cmp al, 'N'
je menu  
jne againOption5
againOption5 endp

exit proc near
call clear
mov dx, 0918h
call set
lea dx, bye
call display
mov ah, 4ch
int 21h
exit endp

asterisk proc near
mov ah, 02h
mov dl, '*'
int 21h
ret
asterisk endp

set proc near
mov ah, 02h
int 10h
ret
set endp  

input proc near
mov ah, 01h
int 21h
ret
input endp

display proc near
mov ah, 09h
int 21h
ret
display endp

clear proc near
mov ax, 0002h
int 10h
ret
clear endp

con proc near
mov ah, 07h
int 21h
ret
con endp  

cls proc near
mov ax, 0600h
mov bh, 07h
mov cx, 0000h
mov dx, 184fh
int 10h
ret
cls endp



cseg ends
end main
