dseg segment
    inptnum db "Input letter and stop at specific character: $"
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
    invalidnum db "Invalid input, please enter a letter... $"
dseg ends

cseg segment
    assume cs:cseg, ds:dseg

    main proc far
        mov ax, dseg
        mov ds, ax
        call AZ
        ret
    main endp

    AZ proc near
        ; Display the input prompt
        lea dx, inptnum
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
        jmp invalid_input

    display1:
        mov dx, 0300h
        call set
        lea dx, msg1
        call display
        ret

    display2:
        mov dx, 0300h
        call set
        lea dx, msg2
        call display
        ret

    display3:
        mov dx, 0300h
        call set
        lea dx, msg3
        call display
        ret

    display4:
        mov dx, 0300h
        call set
        lea dx, msg4
        call display
        ret

    display5:
        mov dx, 0300h
        call set
        lea dx, msg5
        call display
        ret

    display6:
        mov dx, 0300h
        call set
        lea dx, msg6
        call display
        ret

    display7:
        mov dx, 0300h
        call set
        lea dx, msg7
        call display
        ret

    display8:
        mov dx, 0300h
        call set
        lea dx, msg8
        call display
        ret

    display9:
        mov dx, 0300h
        call set
        lea dx, msg9
        call display
        ret

    display10:
        mov dx, 0300h
        call set
        lea dx, msg10
        call display
        ret
    
    display11:
        mov dx, 0300h
        call set
        lea dx, msg11
        call display
        ret
    
    display12:
        mov dx, 0300h
        call set
        lea dx, msg12
        call display
        ret
        
    display13:
        mov dx, 0300h
        call set
        lea dx, msg13
        call display
        ret

    display14:
        mov dx, 0300h
        call set
        lea dx, msg14
        call display
        ret

    display15:
        mov dx, 0300h
        call set
        lea dx, msg15
        call display
        ret
    
    display16:
        mov dx, 0300h
        call set
        lea dx, msg16
        call display
        ret
    
    display17:
        mov dx, 0300h
        call set
        lea dx, msg17
        call display
        ret 
        
    display18:
        mov dx, 0300h
        call set
        lea dx, msg18
        call display
        ret

    display19:
        mov dx, 0300h
        call set
        lea dx, msg19
        call display
        ret

    display20:
        mov dx, 0300h
        call set
        lea dx, msg20
        call display
        ret
    
    display21:
        mov dx, 0300h
        call set
        lea dx, msg21
        call display
        ret
    
    display22:
        mov dx, 0300h
        call set
        lea dx, msg22
        call display
        ret 
        
    display23:
        mov dx, 0300h
        call set
        lea dx, msg23
        call display
        ret

    display24:
        mov dx, 0300h
        call set
        lea dx, msg24
        call display
        ret
    
    display25:
        mov dx, 0300h
        call set
        lea dx, msg25
        call display
        ret
    
    display26:
        mov dx, 0300h
        call set
        lea dx, msg26
        call display
        ret   

    invalid_input:
        call clear
        mov dx, 0000h
        call set
        lea dx, invalidnum
        call display
        jmp inputlet
        ret

    input proc near
        mov ah, 01h ; Input a single character
        int 21h
        ret
    input endp
    
    clear proc near
        mov ax, 0002h
        int 10h
        ret
    clear endp
    
    set proc near
        mov ah, 02h
        int 10h
        ret
    set endp

    display proc near
        mov ah, 09h ; Display a string
        int 21h
        ret
    display endp 
    
cseg ends
end main
