 ;Diamond shape
.model small
.stack 100h
.data
star db ?         ; Declare a byte variable 'star'
blank db ?        ; Declare a byte variable 'blank'
.code 
main PROC
    MOV cx,5      ; Initialize CX with 5 for outer loop
    MOV bx,1      ; Initialize BX with 1 for the number of stars
    
L1:               ; Outer loop label
    PUSH CX       ; Save CX on the stack
    
L2:               ; Inner loop for printing spaces
    MOV ah,2      ; Set AH for DOS print character function
    MOV dl,32     ; Set DL to ASCII code for space
    INT 21H       ; Print space
    LOOP L2       ; Loop until CX is zero
    
    MOV cx,bx     ; Load BX into CX for printing stars
L3:               ; Inner loop for printing stars
    MOV ah,2      ; Set AH for DOS print character function
    MOV dl,'*'    ; Set DL to ASCII code for ''
    INT 21H       ; Print star
    LOOP L3       ; Loop until CX is zero
    
    MOV ah,2      ; Set AH for DOS print character function
    MOV dl,10     ; Line feed
    INT 21H       
    MOV dl,13     ; Carriage return
    INT 21H
    
    INC bx        ; Increment BX by 2 (number of stars to print)
    INC bx
    
    POP cx        ; Restore CX from the stack
    LOOP L1       ; Loop until CX is zero
    
    MOV cx,4      ; Initialize CX with 4 for the second pattern
    MOV bh,7      ; Set BH with 7 (initial stars count)
    MOV bl,2      ; Set BL with 2 (initial spaces count)
    
    MOV STAR,bh   ; Move value of BH to 'star'
    MOV BLANK,bl  ; Move value of BL to 'blank'
    
L4:               ; Loop for second pattern
    CMP BLANK,0   ; Compare BLANK with 0
    JE L5         ; If BLANK is 0, jump to L5
    MOV ah,2      ; Set AH for DOS print character function
    MOV dl,32     ; Set DL to ASCII code for space
    INT 21H       ; Print space
    DEC BLANK     ; Decrement BLANK
    JMP L4        ; Loop until BLANK is zero
    
L5:               ; Loop for printing stars
    MOV ah,2      ; Set AH for DOS print character function
    MOV dl,'*'    ; Set DL to ASCII code for ''
    INT 21H       ; Print star
    DEC STAR      ; Decrement STAR
    CMP STAR,0    ; Compare STAR with 0
    JNE L5        ; If STAR is not zero, loop back to L5
    
L6:               ; Newline for second pattern
    MOV ah,2      ; Set AH for DOS print character function
    MOV dl,10     ; Line feed
    INT 21h       
    MOV dl,13     ; Carriage return
    INT 21h
    
    DEC bh        ; Decrement BH by 2 (number of stars to print)
    DEC bh
    MOV STAR,bh   ; Move new value to 'star'
    
    INC bl        ; Increment BL by 1 (number of spaces to print)
    MOV BLANK,bl  ; Move new value to 'blank'
    
    LOOP L4       ; Loop until CX is zero
    
main endp