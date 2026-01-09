dseg segment
    inptletter db "Input letter to verify if it's vowel or consonant: $"
    vow db "Vowel$"
    cons db "Consonant$"
    invalid db "Invalid input, please enter a letter.$"
dseg ends

cseg segment
    assume cs:cseg, ds:dseg

    main proc far
        mov ax, dseg
        mov ds, ax
        call vowcon
        ret
    main endp

    vowcon proc near
        ; Display the input prompt
        mov dx, 0100h
        call set
        lea dx, inpt
        call display

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
        mov dx, 0200h
        call set
        lea dx, invalid
        call display
        ret

    vowel proc near
        mov dx, 0200h
        call set
        lea dx, vow
        call display
        ret
    vowel endp

    consonant proc near
        mov dx, 0200h
        call set
        lea dx, cons
        call display
        ret
    consonant endp

    input proc near
        mov ah, 01h ; Input a single character
        int 21h
        ret
    input endp

    display proc near
        mov ah, 09h ; Display a string
        int 21h
        ret
    display endp 
    
    set proc near
        mov ah, 02h
        int 10h
        ret
    set endp  

cseg ends
end main
