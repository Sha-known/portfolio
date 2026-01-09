.model small
.stack 100h
.data
.code
main proc
    ; Initialize the data segment
    mov ax, @data
    mov ds, ax

    ; Set the video mode (Text mode, 80x25, 16 colors)
    mov ax, 03h    ; 03h is the 80x25 color text mode
    int 10h        ; BIOS interrupt to set the video mode

    
    MOV AX, 0600h    
    MOV CX, 031ch    
    MOV DX, 1732h  
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
    
    
    again:
    mov ah, 01h    ; Keyboard input (wait for a key press)
    int 21h 
    jmp again

    
    mov ah, 4Ch  
    int 21h
main endp
end main
