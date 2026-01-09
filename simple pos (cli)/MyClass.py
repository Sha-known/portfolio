from item_data import item_prices
class ShoppingCart:
    invoice_number = 0
    manager_password = "password123"  # Change this to your desired manager password

    def __init__(self):
        self.varItemName = []
        self.varQty = []
        self.varPrice = []
        self.invoice_no = self.generate_invoice_number()

    def add(self, item, quantity, item_prices):
        while True:
            # Check if the entered item is in the dictionary
            if item in item_prices:
                price = item_prices[item]["price"]

                # Check if there is enough quantity in stock
                if quantity <= item_prices[item]["quantity"]:
                    # Update quantity in stock
                    item_prices[item]["quantity"] -= quantity

                    # Store data in lists
                    self.varItemName.append(item)
                    self.varQty.append(quantity)
                    self.varPrice.append(price)
                    print(f"{quantity} {item}(s) added to the cart.")
                    break  # Exit the loop if the quantity is valid
                else:
                    # Prompt user to enter a lower quantity
                    quantity = int(input(f"Insufficient stock for {item}. Please enter a lower quantity: "))
            else:
                # Prompt user to enter a valid item name
                item = input("Invalid item name. Please enter a valid item: ")

    def remove(self, manager_password, item_index):
        # Manager verification
        if manager_password != self.manager_password:
            print("Manager password incorrect. Item removal failed.")
            return

        # Check if index is valid
        if 0 <= item_index < len(self.varItemName):
            del self.varItemName[item_index]
            del self.varQty[item_index]
            del self.varPrice[item_index]
            print("Item removed successfully.")
        else:
            print("Invalid item index. Item removal failed.")

    def calculate(self):
        total_amount = 0
        for i in range(len(self.varItemName)):
            item_price = self.varPrice[i]
            item_quantity = self.varQty[i]
            item_amount = item_price * item_quantity
            total_amount += item_amount

        return total_amount

    def clear_cart(self):
        self.varItemName = []
        self.varQty = []
        self.varPrice = []
        self.invoice_no = self.generate_invoice_number()
        print("------------Shopping cart cleared for a new transaction------------")
        print()
        print("-------------------------------------------------------------------")
        print()

    @classmethod
    def generate_invoice_number(cls):
        cls.invoice_number += 1
        return f"{cls.invoice_number:04d}"  # Format as a four-digit invoice number with leading zeros

    def invo(self):
        return f"Invoice no.        :                 {self.invoice_no}"
