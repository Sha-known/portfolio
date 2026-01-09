dseg segment
namesys db "----!Multi-Function Console Sytem: Patterns, Calculations, and Conversions!---- $"
intro db "----!Welcome to our System!---- $" 
key db "----!Press any key to continue!---- $"
dseg ends

cseg segment
    
assume ds:dseg, cs:cseg

main proc far

mov ax, dseg
mov ds, ax

mov ah, 02h
mov dx, 0101h
int 10h

lea dx, namesys
mov ah, 09h
int 21h

mov ah, 02h
mov dx, 0201h
int 10h

lea dx, intro
mov ah, 09h
int 21h 

mov ah, 02h
mov dx, 0301h
int 10h

lea dx, key
mov ah, 09h
int 21h 

mov ah, 4ch
int 21h   

ret
main endp


end