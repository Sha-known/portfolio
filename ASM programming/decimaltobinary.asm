; decimal to binary
.model small
.stack 100h
.data

msg db "Enter the Decimal Number (0-9): $" ; Message prompt for the user to enter a decimal number
msg1 db 0dh,0ah, "Binary Number is: $" ; Message that will display the binary number result

.code

main proc
    mov ax, @data        ; Load the data segment address into ax
    mov ds, ax           ; Move the value in ax to ds, setting up the data segment
    
    mov ah, 09h          ; DOS function to display a string
    lea dx, msg          ; Load the address of msg into dx
    int 21h              ; Call DOS interrupt to display the message

    mov ah, 01h          ; DOS function to read a single character from the keyboard
    int 21h              ; Call DOS interrupt to get input (character) into al
    sub al, 48           ; Convert ASCII to integer by subtracting 48 ('0' = 48 in ASCII)
    mov ah, 0            ; Clear ah, now ax contains the decimal value (e.g., 5)
    mov bx, 2            ; Set divisor to 2 for binary conversion
    mov dx, 0            ; Clear dx, will be used to hold the remainder
    mov cx, 0            ; Initialize counter cx to 0 (to count the number of binary digits)
    
again:
    div bx               ; Divide ax by bx (2), result in ax, remainder in dx (binary digit)
    push dx              ; Push remainder (binary digit) onto the stack
    mov ah, 0            ; Clear ah to prepare for the next division
    inc cx               ; Increment cx to keep track of the number of binary digits
    cmp ax, 0            ; Check if the quotient (ax) is zero
    jne again            ; If not zero, jump back to continue division (loop)
    
    mov ah, 09h          ; DOS function to display a string
    lea dx, msg1         ; Load the address of msg1 (binary result prompt) into dx
    int 21h              ; Call DOS interrupt to display "Binary Number is:"

disp:
    pop dx               ; Pop the next binary digit from the stack into dx
    add dx, 48           ; Convert the binary digit (0 or 1) to ASCII ('0' = 48, '1' = 49)
    mov ah, 02h          ; DOS function to display a character in dl
    int 21h              ; Call DOS interrupt to display the binary digit (in dl)
    loop disp            ; Decrement cx and repeat if cx != 0, displaying the next binary digit

    mov ah, 4ch          ; DOS function to terminate the program
    int 21h              ; Call DOS interrupt to exit the program
main endp

end main
