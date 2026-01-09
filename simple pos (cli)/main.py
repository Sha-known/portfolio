from datetime import datetime
from MyClass import ShoppingCart
from item_data import item_prices
# Dictionary to store usernames and passwords
user_credentials = {
    "Shanon": "1234",
    "Lynchee": "12345",
    "Froilan": "123456",
    "Anthony": "1234567",
    "Sweet": "12345678",
    "Pia": "123456789",
    "Rafael": "12345678910"
}

while True:
    print("+---------------------------------------+")
    print("             🔑 USER LOGIN 🔑             ")
    print("+---------------------------------------+")
    username = input("Enter cashier name: ")
    password = input("Enter password: ")

    # Check if entered credentials are valid
    if username in user_credentials and password == user_credentials[username]:
        print("Login successful!")
        print("")
        break
    else:
        print("Invalid username or password. Please try again.")

cart = ShoppingCart()
while True:
    print("+---------------------------------------+")
    print("[   WELCOME TO SUPERPHARMA POS SYSTEM   ]")
    print("+---------------------------------------+")

    formatter = "%Y-%m-%d %H:%M:%S"
    now = datetime.now()
    formatted_date_time = now.strftime(formatter)

    print("Invoice Date/Time  : ", formatted_date_time)
    print(cart.invo())
    print("+---------------------------------------+")

    customername = input("Enter Customer Name: ")
    print("+---------------------------------------+")

    while True:
        item_name = input("Enter Item Name: ")

        # Check if the entered item is in the dictionary
        if item_name in item_prices:
            price = item_prices[item_name]["price"]
            qty = int(input("Enter Qty: "))
            cart.add(item_name, qty, item_prices)
        else:
            print("Invalid item name. Please enter a valid item.")

        print("")
        print("")
        print("===================================================================")
        print("                          -=[ITEM(S) LIST]=-                       ")
        print("===================================================================")
        print("{:<4} {:<30} {:<8} {:<10} {:<12}".format("#", "Item", "Qty", "Price", "Amount"))
        print("-------------------------------------------------------------------")

        for i in range(len(cart.varItemName)):
            item_total = cart.varQty[i] * cart.varPrice[i]
            print("{:<4} {:<30} {:<8} ₱{:<9.2f} ₱{:<11.2f}".format(
                i + 1, cart.varItemName[i], cart.varQty[i], cart.varPrice[i], item_total))

        print("-------------------------------------------------------------------")
        print("===================================================================")
        print("Total Amount >>>                        ₱{:.2f}".format(cart.calculate()))
        print("-------------------------------------------------------------------")

        add_more = input("Do you want to add more items? (Y/N): ").upper()
        if add_more != 'Y':
            break  # Exit the loop if the user doesn't want to add more items

    while True:
        option = input("Please Select Option: V - For Void | P - For Payment: ").upper()

        if option == 'V':
            # Void an item
            item_to_void = int(input("Enter Item Index to Void: ")) - 1
            manager_password = input("Enter manager password: ")
            cart.remove(manager_password, item_to_void)

            # Display recent item list after voiding an item
            print("\nRecent Item List:")
            print("{:<4} {:<30} {:<8} {:<10} {:<12}".format("#", "Item", "Qty", "Price", "Amount"))
            print("-------------------------------------------------------------------")
            for i in range(len(cart.varItemName)):
                item_total = cart.varQty[i] * cart.varPrice[i]
                print("{:<4} {:<30} {:<8} ₱{:<9.2f} ₱{:<11.2f}".format(
                    i + 1, cart.varItemName[i], cart.varQty[i], cart.varPrice[i], item_total))
            print("-------------------------------------------------------------------")

            # Display total amount after voiding an item
            print("Total Amount after Void >>>             ₱{:.2f}".format(cart.calculate()))

        elif option == 'P':
            # Process payment
            money = float(input("Enter Amount of Money ₱ "))
            print("Processing Payment...")
            print("")
            print("")

            print("-------------------------------------------------------------------")
            print("                             SUPER PHARMA                          ")
            print("===================================================================")
            print("                            -=[RECEIPT]=-                          ")
            print("===================================================================")
            print("Invoice Date/Time  : ", formatted_date_time)
            print(cart.invo())
            print("Customer           : {}".format(customername))
            print("Cashier            : {}".format(username))
            print("-------------------------------------------------------------------")
            print("{:<4} {:<30} {:<8} {:<10} {:<12}".format("#", "Item", "Qty", "Price", "Amount"))

            for i in range(len(cart.varItemName)):
                item_total = cart.varQty[i] * cart.varPrice[i]
                print("{:<4} {:<30} {:<8} ₱{:<9.2f} ₱{:<11.2f}".format(
                    i + 1, cart.varItemName[i], cart.varQty[i], cart.varPrice[i], item_total))

            print("-------------------------------------------------------------------")
            print("Total Amount >>>                             ₱{:.2f}".format(cart.calculate()))
            print("Amount Paid  >>>                             ₱{:.2f}".format(money))
            print("Change       >>>                             ₱{:.2f}".format(money - cart.calculate()))
            print("===================================================================")

            break  # Exit the option selection loop
        else:
            print("Invalid option. Please enter 'V' for Void or 'P' for Payment.")

    choice = input("1-New Transaction | 2-Exit System: ")
    if choice == '2':
        exit()
    elif choice == '1':
        cart.clear_cart()  # Clear the cart for a new transaction
    else:
        print("Invalid choice. Exiting.")
        exit()