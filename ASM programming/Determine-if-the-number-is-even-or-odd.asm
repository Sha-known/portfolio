;Determine if even and odd
.model small
.stack 100h
.data
msg1 db 10,13,  'Enter a number $'
msg2 db 10,13,  'Number is even $'
msg3 db 10,13,  'Number is odd $'
.code
main proc
    mov ax,@data ;load the data segment address into AX.
    mov ds,ax    ;Set DS (Data Segment) register to the value in AX
    lea dx,msg1  ;Load the effective address of msg1 into DX.
    mov ah,09h   ;Set AH to 9 for DOS print string function.
    int 21h      ;Call DOS interrupt to read a character into AL.
    
    mov ah,01h   ;Set AH to 1 for DOS read character function.
    int 21h      ;Call DOS interrupt to read a character into AL.
    
    mov dl,02h   ;Load the value 2 into DL (the divisor).
    div dl       ;Divide AL by DL, storing the quotient in AL and remainder
    cmp ah,00h   ;Compare AH (remainder) to 0.
    je even      ;Jump to label 'even' if the remainder is 0 (meaning the number is even).
    lea dx,msg3  ;Load the effective address of msg3 into DX.
    mov ah,09h   ;Set AH to 9 for DOS print string function.
    int 21h      ;Call DOS interrupt to print the string at DX.
    
    mov ah,04ch  ;Set AH to 4Ch for DOS terminate program function.
    int 21h      ;Call DOS interrupt to terminate the program.
    
    Even:        
    lea dx,msg2  ;Load the effective address of msg2 into DX. 
    mov ah,09    ;Set AH to 9 for DOS print string function.
    int 21h      ;Call DOS interrupt to print the string at DX.
    mov ah,04ch  ;Set AH to 4Ch for DOS terminate program function.
    int 21h      ;Call DOS interrupt to terminate the program.
main endp        
end main         
    