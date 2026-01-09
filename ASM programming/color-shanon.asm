.model small
.stack 100h
.data
.code
main proc
    
    mov ax, @data
    mov ds, ax

    
    mov ax, 03h    
    int 10h        

    
    MOV AX, 0600h   
    MOV CX, 0000h    
    MOV DX, 184Fh  
    MOV BH, 090h    
    INT 10h
    
    MOV CX, 0101h    
    MOV DX, 174Eh  
    MOV BH, 0F0h    
    INT 10h
    
    MOV CX, 0202h    
    MOV DX, 164Dh  
    MOV BH, 020h    
    INT 10h 
    
    MOV CX, 0303h    
    MOV DX, 154Ch  
    MOV BH, 0F0h    
    INT 10h
    
    MOV CX, 0404h    
    MOV DX, 144Bh  
    MOV BH, 0B0h    
    INT 10h
    
    MOV CX, 0505h    
    MOV DX, 134Ah  
    MOV BH, 0F0h    
    INT 10h
    
    MOV CX, 0606h    
    MOV DX, 1249h  
    MOV BH, 040h    
    INT 10h 
    
    MOV CX, 0707h    
    MOV DX, 1148h  
    MOV BH, 0F0h    
    INT 10h
    
    MOV CX, 0808h    
    MOV DX, 1047h  
    MOV BH, 0D0h    
    INT 10h
    
    MOV CX, 0909h    
    MOV DX, 0F46h  
    MOV BH, 0F0h    
    INT 10h
    
    MOV CX, 0A0Ah    
    MOV DX, 0E45h  
    MOV BH, 0E0h    
    INT 10h 
    
    MOV CX, 0B0Bh    
    MOV DX, 0D44h  
    MOV BH, 0F0h    
    INT 10h
    
    MOV CX, 0C0Ch    
    MOV DX, 0C43h  
    MOV BH, 000h    
    INT 10h
    
     
    
    
    
    
    again:
    mov ah, 01h    
    int 21h 
    jmp again

    mov ah, 4Ch  
    int 21h
main endp
end main
