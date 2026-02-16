from tkinter import *
from tkinter import ttk
import mysql.connector
from PIL import Image, ImageTk
from tkinter import messagebox
from tkinter import simpledialog
from tkinter.simpledialog import askstring
from tkinter import Toplevel, scrolledtext
global admin_password

admin = 'admin'
admin_password = 'Instantdeath'

connection = mysql.connector.connect(host='localhost',
                                     user='root',
                                     password='',
                                     database='superpharma_database')
global cursor
global product_id
image_clicked = False
selected_image_name = ""
selected_image_price = 0
cursor = connection.cursor()
total_sum = 0
count = 0
old_total = 0

login = Tk()
login.title('Login')
login.state('zoomed')
login.configure(bg='light blue')
login.resizable(False, False)


def close_window(event=None):
    connection.close()
    login.destroy()


def login_function(event=None):
    query = 'SELECT user_name, password FROM users WHERE user_name = %s and password = %s'
    values = (usernameEntry.get(), passwordEntry.get())
    cursor.execute(query, values)
    user = cursor.fetchone()
    if user is None:
        messagebox.showerror('Account Not Found', 'Sorry, the account you are looking for could not be found. '
                                                  'Please check the account details and try again.')
        usernameEntry.delete(0, END)
        passwordEntry.delete(0, END)
        usernameEntry.focus_set()
    else:
        if user[0] == usernameEntry.get() and user[1] == passwordEntry.get():
            login.withdraw()
            root = Toplevel()
            root.title('SuperPharma Store')
            root.resizable(False, False)
            root.state('zoomed')
            root.update()
            root.configure(bg='light blue')
            style = ttk.Style()
            style.theme_use('clam')
            style.configure("Treeview",
                            background='light blue',
                            rowheight=25,
                            fieldbackground='silver'
                            )
            style.map('Treeview', background=[('selected', 'blue')])

            def add_item(event=None):
                global total_sum

                try:
                    global image_clicked, selected_image_name, selected_image_price

                    product_id = int(entry.get())
                    quantity = int(entry2.get())

                    if product_id <= 0 or quantity <= 0:
                        messagebox.showwarning('Invalid Input', 'The entered Product ID or Quantity is not valid. '
                                                                'Please ensure you have provided valid values for '
                                                                'both Product ID and Quantity.')
                        entry.delete(0, END)
                        entry2.delete(0, END)
                        entry.focus_set()
                        return

                    select_query = f'SELECT * FROM product_list WHERE product_id = {product_id}'
                    cursor.execute(select_query)
                    items = cursor.fetchall()

                    if not items:
                        messagebox.showwarning('Product Not Found', f'No product was found with the ID {product_id}. '
                                                                    'Please check the product ID and try again.')
                        entry.delete(0, END)
                        entry2.delete(0, END)
                        entry.focus_set()
                        return

                    product = items[0]
                    product_name, product_price, product_stock = product[1], product[2], product[3]

                    if quantity <= 0:
                        messagebox.showwarning('Invalid Quantity', 'The quantity should be greater than zero. '
                                                                   'Please enter a valid quantity and try again.')
                        entry.delete(0, END)
                        entry2.delete(0, END)
                        entry.focus_set()
                        return

                    total = quantity * int(product_price)

                    existing_item_id = None
                    for child in my_tree.get_children():
                        if product_name == my_tree.item(child, 'values')[0]:
                            existing_item_id = child
                            break

                    if existing_item_id:
                        current_quantity = int(my_tree.item(existing_item_id, "values")[1])
                        new_quantity = current_quantity + quantity
                        new_price = int(product_price) * new_quantity
                        my_tree.item(existing_item_id,
                                     values=(product_name, new_quantity, f'₱{product_price}', f'₱{new_price:,}'))

                        update_stock_sold_query = 'UPDATE dprod_sold SET stock_sold = stock_sold + %s ' \
                                                  'WHERE product_name = %s'
                        cursor.execute(update_stock_sold_query, (quantity, product_name))

                        update_remaining_stock_query = 'UPDATE dprod_sold SET remaining_stock = remaining_stock - %s ' \
                                                       'WHERE product_name = %s'
                        cursor.execute(update_remaining_stock_query, (quantity, product_name))

                        update_productlist_stock = 'UPDATE product_list SET stock_quantity = stock_quantity - %s ' \
                                                   'WHERE product_name = %s'
                        cursor.execute(update_productlist_stock, (quantity, product_name))

                        try:
                            get_stock = f'SELECT stock_quantity FROM product_list WHERE product_name = "{product_name}"'
                            cursor.execute(get_stock)
                            items = cursor.fetchone()

                            if items and items[0] < 0:
                                messagebox.showwarning('Out of Stock', 'This product is currently out of stock. '
                                                                       'You cannot purchase it at the moment.')
                                connection.rollback()

                                last_row_id = my_tree.get_children()[-1]
                                my_tree.delete(last_row_id)

                            elif items and items[0] <= 10:
                                messagebox.showwarning('Low Stock Warning',
                                                       f'The remaining stock for this product is {items[0]}. '
                                                       'Consider checking the stock and updating as needed.')

                        except Exception as e:
                            print(f"Error: {e}")
                            connection.rollback()
                        finally:
                            connection.commit()

                    else:

                        my_tree.insert(parent='', index='end',
                                       values=(product_name, quantity, f'₱{product_price}', f'₱{total:,}'))

                        insert_query = 'INSERT INTO dprod_sold (product_id, product_name, price, stock_sold, ' \
                                       'remaining_stock) VALUES (%s, %s, %s, %s, %s)'
                        stock_sold_value = quantity
                        values = (product_id, product_name, product_price, stock_sold_value,
                                  product_stock - stock_sold_value)

                        try:
                            cursor.execute(insert_query, values)
                            update_productlist_stock = 'UPDATE product_list SET stock_quantity = %s ' \
                                                       'WHERE product_name = %s'
                            cursor.execute(update_productlist_stock, (product_stock - stock_sold_value, product_name))
                        except Exception as e:
                            print(f"Error: {e}")
                            connection.rollback()

                        try:
                            get_stock = f'SELECT stock_quantity FROM product_list WHERE product_name = "{product_name}"'
                            cursor.execute(get_stock)
                            items = cursor.fetchone()

                            if items and items[0] < 0:
                                messagebox.showwarning('Out of Stock', 'Sorry, this product is currently out of stock. '
                                                                       'You cannot purchase it at the moment.')
                                connection.rollback()

                                last_row_id = my_tree.get_children()[-1]
                                my_tree.delete(last_row_id)

                            elif items and items[0] <= 10:
                                messagebox.showwarning('Limited Stock Alert',
                                                       f'The remaining stock for this product is {items[0]}. '
                                                       'Consider checking the stock and making necessary adjustments.')

                        except Exception as e:
                            print(f"Error: {e}")
                            connection.rollback()
                        finally:
                            connection.commit()

                    total_sum += total
                    total_price.config(text=f'₱{total_sum:,}')

                    connection.commit()

                    entry.focus_set()
                    entry.delete(0, END)
                    entry2.delete(0, END)

                except ValueError:
                    messagebox.showwarning('Invalid Input',
                                           'Please enter valid numeric values for Product ID and Quantity. '
                                           'Ensure that both fields contain numerical values.')
                    entry.delete(0, END)
                    entry2.delete(0, END)
                    entry.focus_set()

            def insert_item_to_treeview(product_name, quantity, price):
                global total_sum

                existing_item_id = None

                for child in my_tree.get_children():
                    if product_name == my_tree.item(child, 'values')[0]:
                        existing_item_id = child
                        break

                cursor.execute('SELECT * FROM product_list')
                items = cursor.fetchall()

                cursor.execute('SELECT product_id, product_name FROM dprod_sold')
                existing_products = {item_tuple[1]: item_tuple[0] for item_tuple in cursor.fetchall()}

                if existing_item_id and product_name in existing_products:
                    current_quantity = int(my_tree.item(existing_item_id, "values")[1])
                    new_quantity = current_quantity + quantity
                    new_price = price * new_quantity
                    my_tree.item(existing_item_id, values=(product_name, new_quantity, f'₱{price}', f'₱{new_price}'))
                    total_sum += price
                    total_price.config(text=f'₱{total_sum:,}')
                    print("Updating...")
                    product_id = existing_products[product_name]
                    update_stock_sold_query = 'UPDATE dprod_sold SET stock_sold = stock_sold + %s ' \
                                              'WHERE product_id = %s'
                    cursor.execute(update_stock_sold_query, (quantity, product_id))
                    update_remaining_stock_query = 'UPDATE dprod_sold SET remaining_stock = remaining_stock - %s ' \
                                                   'WHERE product_id = %s'
                    cursor.execute(update_remaining_stock_query, (quantity, product_id))
                    update_productlist_stock = 'UPDATE product_list SET stock_quantity = stock_quantity - %s ' \
                                               'WHERE product_id = %s'
                    cursor.execute(update_productlist_stock, (quantity, product_id))
                else:
                    existing_treeview_item = None
                    for child in my_tree.get_children():
                        if product_name == my_tree.item(child, 'values')[0]:
                            existing_treeview_item = child
                            break

                    if existing_treeview_item:
                        current_quantity = int(my_tree.item(existing_treeview_item, "values")[1])
                        new_quantity = current_quantity + quantity
                        new_price = price * new_quantity
                        my_tree.item(existing_treeview_item,
                                     values=(product_name, new_quantity, f'₱{price}', f'₱{new_price}'))
                    else:
                        my_tree.insert(parent='', index='end',
                                       values=(product_name, quantity, f'₱{price}', f'₱{price}', "\n"))
                        total_sum += price
                        total_price.config(text=f'₱{total_sum:,}')

                        for product in items:
                            if product[1] == product_name:
                                query1 = 'INSERT INTO dprod_sold (product_id, product_name, price, ' \
                                         'stock_sold, remaining_stock) VALUES (%s,%s,%s,%s,%s)'
                                values1 = (product[0], product[1], product[2], quantity,
                                           product[3] - quantity)

                                try:
                                    cursor.execute(query1, values1)
                                    new_total = product[3] - quantity
                                    query2 = 'UPDATE product_list SET stock_quantity = %s WHERE product_name = %s'
                                    values2 = (new_total, product_name)
                                    cursor.execute(query2, values2)

                                except Exception as e:
                                    print(f"Error: {e}")
                                    connection.rollback()

                connection.commit()

            def void_item(event=None):
                global total_sum
                global items

                try:
                    product_id = int(entry.get())
                    quantity_to_void = int(entry2.get())

                    if product_id == 0 or quantity_to_void == 0:
                        messagebox.showwarning('Invalid Input', 'The product ID or quantity to void is not valid. '
                                                                'Please ensure you have entered a valid product ID '
                                                                'and quantity.')
                        entry.delete(0, END)
                        entry2.delete(0, END)
                        entry.focus_set()
                    else:
                        password = simpledialog.askstring("Void Item", "Enter Manager Password: ", show='*')

                        if password == admin_password:
                            select_query = f'SELECT * FROM dprod_sold WHERE product_id = {product_id}'
                            cursor.execute(select_query)
                            items = cursor.fetchall()

                            for item_with in items:
                                if quantity_to_void > item_with[3]:
                                    messagebox.showwarning('Invalid Void Quantity',
                                                           'The void quantity entered is not valid. '
                                                           'Please ensure you have entered a valid quantity '
                                                           'for voiding.')
                                else:
                                    existing_item_id = None
                                    for item_id in my_tree.get_children():
                                        if my_tree.item(item_id, "values")[0] == item_with[1]:
                                            existing_item_id = item_id
                                            break

                                    if existing_item_id:
                                        current_quantity = int(my_tree.item(existing_item_id, "values")[1])
                                        new_quantity = current_quantity - quantity_to_void
                                        voided_value = item_with[2] * quantity_to_void
                                        updated_total_value = item_with[2] * new_quantity

                                        my_tree.item(existing_item_id, values=(
                                            item_with[1], new_quantity, f'₱{item_with[2]}',
                                            f'₱{updated_total_value:,}'))
                                        product_id = entry.get()
                                        update_value = quantity_to_void
                                        update_stock_sold_query = 'UPDATE dprod_sold SET stock_sold = stock_sold - %s ' \
                                                                  'WHERE product_id = %s'
                                        cursor.execute(update_stock_sold_query, (update_value, product_id))
                                        update_remaining_stock_query = 'UPDATE dprod_sold SET remaining_stock = ' \
                                                                       'remaining_stock + %s WHERE product_id = %s'
                                        cursor.execute(update_remaining_stock_query, (update_value, product_id))
                                        update_productlist_stock = 'UPDATE product_list SET stock_quantity = ' \
                                                                   'stock_quantity + %s WHERE product_id = %s'
                                        cursor.execute(update_productlist_stock, (update_value, product_id))
                                        connection.commit()
                                    else:
                                        messagebox.showwarning('Cannot Void Item',
                                                               'You cannot void an item that is not in the cart. '
                                                               'Please ensure the item is in the cart before '
                                                               'attempting to void it.')

                                    total_sum -= voided_value
                                    total_price.config(text=f'₱{total_sum:,}')

                                entry.focus_set()
                                entry.delete(0, END)
                                entry2.delete(0, END)

                        else:
                            messagebox.showwarning('Incorrect Password', 'The entered manager password is incorrect. '
                                                                         'Please double-check your password and try '
                                                                         'again.')

                except Exception as e:
                    error_message = "An error occurred while processing your request. Please try again later."
                    messagebox.showerror('Error!', f'{error_message}\nError Details: {str(e)}')

            def print_receipt(event=None):
                global count
                cash = entry_1.get()

                if not my_tree.get_children():
                    messagebox.showwarning("Incomplete Payment",
                                           "Please enter the payment amount before printing the receipt. "
                                           "Also, make sure to check if items exist before processing the payment.")
                    return

                try:
                    top = Toplevel()
                    top.title('Receipt')
                    top.geometry('685x450')

                    cash = entry_1.get()
                    cash = float(cash) if cash else 0.0

                    receipt = scrolledtext.ScrolledText(top, width=200, height=60)
                    receipt.pack(padx=10, pady=10)

                    receipt.insert(END, "\n")
                    receipt.insert(END,"                               Super Pharma Receipt                               ")
                    receipt.insert(END, "\n")
                    receipt.insert(END,"--------------------------------------------------------------------------------\n")
                    receipt.insert(END, "{:55} {:5} {:5} {:5}\n".format('Item', 'Quantity', 'Price', 'Total'))
                    receipt.insert(END,"--------------------------------------------------------------------------------\n")

                    total_amount = 0.0

                    for item in my_tree.get_children():
                        values = my_tree.item(item, 'values')
                        item_name = values[0]
                        quantity = int(values[1])
                        price = float(values[2][1:])
                        total = float(values[3][1:])
                        total_amount += total

                        receipt.insert(END, "{:55} {:5} {:>6} {:>6}\n".format(item_name, quantity, price, total))

                    receipt.insert(END,"\n")
                    receipt.insert(END, "\n")
                    receipt.insert(END,"--------------------------------------------------------------------------------\n")
                    receipt.insert(END, "TOTAL:                                                               P{:.2f}\n".format(total_amount))
                    receipt.insert(END, "CASH:                                                                P{:.2f}\n".format(cash))

                    if cash >= total_amount:
                        change = cash - total_amount
                        receipt.insert(END, "CHANGE:                                                              P{:.2f}\n".format(change))
                        receipt.insert(END,
                                       "--------------------------------------------------------------------------------\n")
                    else:
                        receipt.insert(END, "INSUFFICIENT FUNDS\n")

                    entry_1.delete(0, END)
                    entry.focus_set()

                except ValueError as ve:
                    messagebox.showerror('ERROR', f'Invalid input: {ve}')
                    entry_1.focus_set()

            def reset_window(event=None):
                global total_sum
                for item in my_tree.get_children():
                    my_tree.delete(item)
                total_price.config(text='')
                change_label.config(text='')
                entry_1.delete(0, END)
                total_sum = 0
                entry.focus_set()

            def pay_bill(event=None):

                try:
                    if not my_tree.get_children():
                        messagebox.showinfo('Notice', 'There are no items to pay for. Please add items to the '
                                                      'cart before proceeding with payment.')
                        return
                    cash = int(entry_1.get())
                    change = cash - total_sum
                    if cash >= total_sum:
                        entry_1.delete(0, END)
                        change_label.config(text=f'₱{change:,}')
                        entry_1.insert(0, cash)
                        entry.focus_set()
                    else:
                        messagebox.showinfo('Notice', 'The payment amount entered is insufficient. '
                                                      'Please make sure to enter the correct amount.')
                        entry_1.delete(0, END)
                        entry_1.focus_set()
                except ValueError:
                    messagebox.showerror('Error', 'Please enter a valid numeric value for the cash payment.')
                    entry_1.delete(0, END)
                    entry_1.focus_set()
                except Exception as e:
                    messagebox.showerror('Error', f'An error occurred: {e}. Please try again.')
                    entry_1.delete(0, END)
                    entry_1.focus_set()

            def destroy(event=None):
                try:
                    connection.close()
                    login.destroy()
                    root.destroy()
                except:
                    pass

            # create a frame and a scrollbar
            tree_frame = Frame(root, borderwidth=5)
            tree_frame.place(x=0, y=5)
            tree_scroll = Scrollbar(tree_frame)
            tree_scroll.pack(side=RIGHT, fill=Y)

            my_tree = ttk.Treeview(tree_frame, yscrollcommand=tree_scroll.set, height=20)
            tree_scroll.config(command=my_tree.yview)
            my_tree_style = ttk.Style()
            my_tree_style.configure('Treeview', rowheight=25)

            my_tree['columns'] = ("ITEM DESCRIPTION", "QUANTITY", "PRICE", "TOTAL")

            my_tree.column("#0", width=120, minwidth=25)
            my_tree.column("ITEM DESCRIPTION", anchor=CENTER, width=350)
            my_tree.column("QUANTITY", anchor=CENTER, width=100)
            my_tree.column("PRICE", anchor=CENTER, width=100)
            my_tree.column("TOTAL", anchor=CENTER, width=100)

            my_tree['show'] = 'headings'
            my_tree.heading("#0", text="Label", anchor=CENTER)
            my_tree.heading("ITEM DESCRIPTION", text="ITEM DESCRIPTION", anchor=CENTER)
            my_tree.heading("QUANTITY", text="QUANTITY", anchor=CENTER, )
            my_tree.heading("PRICE", text="PRICE", anchor=CENTER)
            my_tree.heading("TOTAL", text="TOTAL", anchor=CENTER)
            my_tree.bind('<Motion>', 'break')

            my_tree.pack()
            # create a bottom frame
            buttons_frame = Frame(root, bg='light blue', borderwidth=8, highlightthickness=5,
                                  highlightbackground='black')
            buttons_frame.pack(side=BOTTOM, fill=BOTH)
            # create a frame inside the bottom frame
            first_frame = Frame(buttons_frame, bg='light blue')
            first_frame.grid(row=0, column=0, padx=5, pady=13, ipadx=50)
            first_frame.pack_propagate(False)
            # create a label for entry
            label = Label(first_frame, text="PRODUCT NUMBER:", font=('TIMES', 15))
            label.grid(row=0, column=0, pady=10, padx=10)
            label.config(bg='light blue')
            label1 = Label(first_frame, text="QUANTITY:", font=('TIMES', 15))
            label1.grid(row=1, column=0, pady=10, padx=(0, 78))
            label1.config(bg='light blue')
            label_s = Label(first_frame, text="TOTAL:", font=('TIMES', 15))
            label_s.grid(row=2, column=0, pady=10, padx=(0, 78))
            label_s.config(bg='light blue')
            # create an entry for label
            entry = Entry(first_frame, width=20, borderwidth=5, justify=CENTER)
            entry.focus_set()
            entry.focus_force()
            entry.grid(row=0, column=1, padx=5)
            entry2 = Entry(first_frame, width=20, justify=CENTER, borderwidth=5)
            entry2.grid(row=1, column=1)
            total_price = Label(first_frame, text='', bg='light blue', font=('TIMES', 15))
            total_price.grid(row=2, column=1)
            # create the second frame inside the buttons_frame
            second_frame = Frame(buttons_frame, bg='light blue')
            second_frame.grid(row=0, column=1, padx=5, pady=5, ipadx=50)
            second_frame.pack_propagate(False)
            # create a label inside second_frame
            label_1 = Label(second_frame, text=":CASH", font=('TIMES', 15))
            label_1.grid(row=0, column=1, pady=10, padx=(0, 17))
            label_1.config(bg='light blue')
            label_2 = Label(second_frame, text=":CHANGE", font=('TIMES', 15))
            label_2.grid(row=1, column=1, pady=10, padx=(10, 0))
            label_2.config(bg='light blue')
            # create entry inside second frame
            entry_1 = Entry(second_frame, width=50, borderwidth=5, justify=CENTER)
            entry_1.grid(row=0, column=0)
            change_label = Label(second_frame, text='', bg='light blue', font=('TIMES', 15))
            change_label.grid(row=1, column=0)
            # create third frame inside the buttons frame
            third_frame = Frame(buttons_frame, bg='light blue')
            third_frame.grid(row=0, column=2, padx=5, pady=5)
            third_frame.pack_propagate(False)
            # create buttons inside the third frame
            # create an add item button
            add_button = Button(third_frame, text="ADD ITEM", font=('TIMES', 8),
                                command=lambda: add_item(),
                                padx=17, pady=15)
            add_button.grid(row=0, column=0, padx=10, pady=10)
            # create a void button for void item
            void_button = Button(third_frame, text="VOID ITEM", font=('TIMES', 8), command=void_item,
                                 padx=17, pady=15)
            void_button.grid(row=1, column=0, padx=10, pady=10)
            # create a button for receipt
            print_receipt = Button(third_frame, text="RECEIPT", font=('TIMES', 8), command=print_receipt,
                                   padx=17, pady=15)
            print_receipt.grid(row=0, column=1, padx=(0, 22), pady=10)
            reset_button = Button(third_frame, text="RESET", font=('TIMES', 8), command=reset_window,
                                  padx=23, pady=15)
            reset_button.grid(row=1, column=1, padx=(0, 22), pady=10)
            pay_button = Button(third_frame, text="PAY", font=('TIMES', 8), command=pay_bill,
                                padx=30, pady=15)
            pay_button.grid(row=0, column=2)

            exit_button = Button(third_frame, text="EXIT", font=('TIMES', 8), command=destroy,
                                 padx=28, pady=15)
            exit_button.grid(row=1, column=2)

            # shortcuts for void item
            root.bind('<Control_R>v', void_item)
            root.bind('<Control_L>v', void_item)
            root.bind('<Control_R>V', void_item)
            root.bind('<Control_L>V', void_item)
            void_button.bind('<Button-1>', void_item)
            # shortcuts for reset window
            root.bind('<Delete>', reset_window)
            reset_button.bind('<Button-1>', reset_window)
            # shortcuts for pay bill
            root.bind('<Control_R>p', pay_bill)
            root.bind('<Control_L>p', pay_bill)
            root.bind('<Control_R>P', pay_bill)
            root.bind('<Control_L>P', pay_bill)
            pay_button.bind('<Button-1>', pay_bill)
            # shortcut for exit button
            root.bind('<Escape>', destroy)
            exit_button.bind('<Button-1>', destroy)

            # create a labelframe
            product_frame = LabelFrame(root, text='PRODUCT LIST', font=('TIMES', 20), bg='light blue')
            product_frame.place(x=685, y=0)

            def insert_item_to_treeview_by_id(item_id):
                global total_sum, image_clicked, selected_image_name, selected_image_price
                cursor.execute(f'SELECT product_name, price FROM product_list WHERE product_id = {item_id}')
                records = cursor.fetchall()
                for record in records:
                    price = int(record[1])
                    insert_item_to_treeview(record[0], 1, price)

                image_clicked = True
                selected_image_name = records[0][0]
                selected_image_price = price

                entry.focus_set()
                entry.delete(0, END)
                entry2.delete(0, END)

            def item1():
                insert_item_to_treeview_by_id(1)

            def item2():
                insert_item_to_treeview_by_id(2)

            def item3():
                insert_item_to_treeview_by_id(3)

            def item4():
                insert_item_to_treeview_by_id(4)

            def item5():
                insert_item_to_treeview_by_id(5)

            def item6():
                insert_item_to_treeview_by_id(6)

            def item7():
                insert_item_to_treeview_by_id(7)

            def item8():
                insert_item_to_treeview_by_id(8)

            def item9():
                insert_item_to_treeview_by_id(9)

            def item10():
                insert_item_to_treeview_by_id(10)

            def item11():
                insert_item_to_treeview_by_id(11)

            def item12():
                insert_item_to_treeview_by_id(12)

            def item13():
                insert_item_to_treeview_by_id(13)

            def item14():
                insert_item_to_treeview_by_id(14)

            def item15():
                insert_item_to_treeview_by_id(15)

            def item16():
                insert_item_to_treeview_by_id(16)

            def item17():
                insert_item_to_treeview_by_id(17)

            def item18():
                insert_item_to_treeview_by_id(18)

            def item19():
                insert_item_to_treeview_by_id(19)

            def item20():
                insert_item_to_treeview_by_id(20)

            def item21():
                insert_item_to_treeview_by_id(21)

            def item22():
                insert_item_to_treeview_by_id(22)

            def item23():
                insert_item_to_treeview_by_id(23)

            def item24():
                insert_item_to_treeview_by_id(24)

            def item25():
                insert_item_to_treeview_by_id(25)

            def item26():
                insert_item_to_treeview_by_id(26)

            def item27():
                insert_item_to_treeview_by_id(27)

            def item28():
                insert_item_to_treeview_by_id(28)

            def item29():
                insert_item_to_treeview_by_id(29)

            def item30():
                insert_item_to_treeview_by_id(30)

            def item31():
                insert_item_to_treeview_by_id(31)

            def item32():
                insert_item_to_treeview_by_id(32)

            def item33():
                insert_item_to_treeview_by_id(33)

            def item34():
                insert_item_to_treeview_by_id(34)

            def item35():
                insert_item_to_treeview_by_id(35)

            def item36():
                insert_item_to_treeview_by_id(36)

            def item37():
                insert_item_to_treeview_by_id(37)

            def item38():
                insert_item_to_treeview_by_id(38)

            def item39():
                insert_item_to_treeview_by_id(39)

            def item40():
                insert_item_to_treeview_by_id(40)

            def item41():
                insert_item_to_treeview_by_id(41)

            def item42():
                insert_item_to_treeview_by_id(42)

            def item43():
                insert_item_to_treeview_by_id(43)

            def item44():
                insert_item_to_treeview_by_id(44)

            def item45():
                insert_item_to_treeview_by_id(45)

            def item46():
                insert_item_to_treeview_by_id(46)

            def item47():
                insert_item_to_treeview_by_id(47)

            def item48():
                insert_item_to_treeview_by_id(48)

            def item49():
                insert_item_to_treeview_by_id(49)

            def item50():
                insert_item_to_treeview_by_id(50)

            def item51():
                insert_item_to_treeview_by_id(51)

            def item52():
                insert_item_to_treeview_by_id(52)

            def item53():
                insert_item_to_treeview_by_id(53)

            def item54():
                insert_item_to_treeview_by_id(54)

            def item55():
                insert_item_to_treeview_by_id(55)

            def item56():
                insert_item_to_treeview_by_id(56)

            def item57():
                insert_item_to_treeview_by_id(57)

            def item58():
                insert_item_to_treeview_by_id(58)

            def item59():
                insert_item_to_treeview_by_id(59)

            def item60():
                insert_item_to_treeview_by_id(60)

            def item61():
                insert_item_to_treeview_by_id(61)

            def item62():
                insert_item_to_treeview_by_id(62)

            def item63():
                insert_item_to_treeview_by_id(63)

            def item64():
                insert_item_to_treeview_by_id(64)

            def item65():
                insert_item_to_treeview_by_id(65)

            def item66():
                insert_item_to_treeview_by_id(66)

            def item67():
                insert_item_to_treeview_by_id(67)

            def item68():
                insert_item_to_treeview_by_id(68)

            def item69():
                insert_item_to_treeview_by_id(69)

            def item70():
                insert_item_to_treeview_by_id(70)

            def item71():
                insert_item_to_treeview_by_id(71)

            def item72():
                insert_item_to_treeview_by_id(72)

            def item73():
                insert_item_to_treeview_by_id(73)

            def item74():
                insert_item_to_treeview_by_id(74)

            def item75():
                insert_item_to_treeview_by_id(75)

            def item76():
                insert_item_to_treeview_by_id(76)

            def item77():
                insert_item_to_treeview_by_id(77)

            def item78():
                insert_item_to_treeview_by_id(78)

            def item79():
                insert_item_to_treeview_by_id(79)

            def item80():
                insert_item_to_treeview_by_id(80)

            def item81():
                insert_item_to_treeview_by_id(81)

            def item82():
                insert_item_to_treeview_by_id(82)

            def item83():
                insert_item_to_treeview_by_id(83)

            def item84():
                insert_item_to_treeview_by_id(84)

            def item85():
                insert_item_to_treeview_by_id(85)

            def item86():
                insert_item_to_treeview_by_id(86)

            def item87():
                insert_item_to_treeview_by_id(87)

            def item88():
                insert_item_to_treeview_by_id(88)

            def item89():
                insert_item_to_treeview_by_id(89)

            def item90():
                insert_item_to_treeview_by_id(90)

            def item91():
                insert_item_to_treeview_by_id(91)

            def item92():
                insert_item_to_treeview_by_id(92)

            def item93():
                insert_item_to_treeview_by_id(93)

            def item94():
                insert_item_to_treeview_by_id(94)

            def item95():
                insert_item_to_treeview_by_id(95)

            def item96():
                insert_item_to_treeview_by_id(96)

            def item97():
                insert_item_to_treeview_by_id(97)

            def item98():
                insert_item_to_treeview_by_id(98)

            def item99():
                insert_item_to_treeview_by_id(99)

            def item100():
                insert_item_to_treeview_by_id(100)

            def item101():
                insert_item_to_treeview_by_id(101)

            def item102():
                insert_item_to_treeview_by_id(102)

            def item103():
                insert_item_to_treeview_by_id(103)

            def item104():
                insert_item_to_treeview_by_id(104)

            # open image
            x_coordinate = 45
            y_coordinate = 56
            image1 = Image.open('C:\pythonProject/image1.png')
            # resize image
            resized1 = image1.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image1 = ImageTk.PhotoImage(resized1)
            image2 = Image.open('C:\pythonProject/image2.png')
            resized2 = image2.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image2 = ImageTk.PhotoImage(resized2)
            image3 = Image.open('C:\pythonProject/image3.png')
            resized3 = image3.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image3 = ImageTk.PhotoImage(resized3)
            image4 = Image.open('C:\pythonProject/image4.png')
            resized4 = image4.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image4 = ImageTk.PhotoImage(resized4)
            image5 = Image.open('C:\pythonProject/image5.png')
            resized5 = image5.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image5 = ImageTk.PhotoImage(resized5)
            image6 = Image.open('C:\pythonProject/image6.png')
            resized6 = image6.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image6 = ImageTk.PhotoImage(resized6)
            image7 = Image.open('C:\pythonProject/image7.png')
            resized7 = image7.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image7 = ImageTk.PhotoImage(resized7)
            image8 = Image.open('C:\pythonProject/image8.png')
            resized8 = image8.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image8 = ImageTk.PhotoImage(resized8)
            image9 = Image.open('C:\pythonProject/image9.png')
            resized9 = image9.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image9 = ImageTk.PhotoImage(resized9)
            image10 = Image.open('C:\pythonProject/image10.png')
            resized10 = image10.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image10 = ImageTk.PhotoImage(resized10)
            image11 = Image.open('C:\pythonProject/image11.jpeg')
            resized11 = image11.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image11 = ImageTk.PhotoImage(resized11)
            image12 = Image.open('C:\pythonProject/image12.jpeg')
            resized12 = image12.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image12 = ImageTk.PhotoImage(resized12)
            image13 = Image.open('C:\pythonProject/image13.jpeg')
            resized13 = image13.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image13 = ImageTk.PhotoImage(resized13)
            image14 = Image.open('C:\pythonProject/image14.jpeg')
            resized14 = image14.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image14 = ImageTk.PhotoImage(resized14)
            image15 = Image.open('C:\pythonProject/image15.png')
            resized15 = image15.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image15 = ImageTk.PhotoImage(resized15)
            image16 = Image.open('C:\pythonProject/image16.jpg')
            resized16 = image16.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image16 = ImageTk.PhotoImage(resized16)
            image17 = Image.open('C:\pythonProject/image17.jpg')
            resized17 = image17.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image17 = ImageTk.PhotoImage(resized17)
            image18 = Image.open('C:\pythonProject/image18.jpeg')
            resized18 = image18.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image18 = ImageTk.PhotoImage(resized18)
            image19 = Image.open('C:\pythonProject/image19.jpeg')
            resized19 = image19.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image19 = ImageTk.PhotoImage(resized19)
            image20 = Image.open('C:\pythonProject/image20.png')
            resized20 = image20.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image20 = ImageTk.PhotoImage(resized20)
            image21 = Image.open('C:\pythonProject/image21.png')
            resized21 = image21.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image21 = ImageTk.PhotoImage(resized21)
            image22 = Image.open('C:\pythonProject/image22.jpg')
            resized22 = image22.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image22 = ImageTk.PhotoImage(resized22)
            image23 = Image.open('C:\pythonProject/image23.jpg')
            resized23 = image23.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image23 = ImageTk.PhotoImage(resized23)
            image24 = Image.open('C:\pythonProject/image24.jpg')
            resized24 = image24.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image24 = ImageTk.PhotoImage(resized24)
            image25 = Image.open('C:\pythonProject/image25.jpg')
            resized25 = image25.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image25 = ImageTk.PhotoImage(resized25)
            image26 = Image.open('C:\pythonProject/image26.jpg')
            resized26 = image26.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image26 = ImageTk.PhotoImage(resized26)
            image27 = Image.open('C:\pythonProject/image27.jpeg')
            resized27 = image27.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image27 = ImageTk.PhotoImage(resized27)
            image28 = Image.open('C:\pythonProject/image28.jpeg')
            resized28 = image28.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image28 = ImageTk.PhotoImage(resized28)
            image29 = Image.open('C:\pythonProject/image29.jpeg')
            resized29 = image29.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image29 = ImageTk.PhotoImage(resized29)
            image30 = Image.open('C:\pythonProject/image30.jpeg')
            resized30 = image30.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image30 = ImageTk.PhotoImage(resized30)
            image31 = Image.open('C:\pythonProject/image31.jpg')
            resized31 = image31.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image31 = ImageTk.PhotoImage(resized31)
            image32 = Image.open('C:\pythonProject/image32.jpg')
            resized32 = image32.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image32 = ImageTk.PhotoImage(resized32)
            image33 = Image.open('C:\pythonProject/image33.jpg')
            resized33 = image33.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image33 = ImageTk.PhotoImage(resized33)
            image34 = Image.open('C:\pythonProject/image34.png')
            resized34 = image34.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image34 = ImageTk.PhotoImage(resized34)
            image35 = Image.open('C:\pythonProject/image35.png')
            resized35 = image35.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image35 = ImageTk.PhotoImage(resized35)
            image36 = Image.open('C:\pythonProject/image36.jpg')
            resized36 = image36.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image36 = ImageTk.PhotoImage(resized36)
            image37 = Image.open('C:\pythonProject/image37.webp')
            resized37 = image37.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image37 = ImageTk.PhotoImage(resized37)
            image38 = Image.open('C:\pythonProject/image38.webp')
            resized38 = image38.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image38 = ImageTk.PhotoImage(resized38)
            image39 = Image.open('C:\pythonProject/image39.jpg')
            resized39 = image39.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image39 = ImageTk.PhotoImage(resized39)
            image40 = Image.open('C:\pythonProject/image40.jpg')
            resized40 = image40.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image40 = ImageTk.PhotoImage(resized40)
            image41 = Image.open('C:\pythonProject/image41.jpg')
            resized41 = image41.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image41 = ImageTk.PhotoImage(resized41)
            image42 = Image.open('C:\pythonProject/image42.jpg')
            resized42 = image42.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image42 = ImageTk.PhotoImage(resized42)
            image43 = Image.open('C:\pythonProject/image43.jpg')
            resized43 = image43.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image43 = ImageTk.PhotoImage(resized43)
            image44 = Image.open('C:\pythonProject/image44.jpg')
            resized44 = image44.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image44 = ImageTk.PhotoImage(resized44)
            image45 = Image.open('C:\pythonProject/image45.webp')
            resized45 = image45.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image45 = ImageTk.PhotoImage(resized45)
            image46 = Image.open('C:\pythonProject/image46.webp')
            resized46 = image46.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image46 = ImageTk.PhotoImage(resized46)
            image47 = Image.open('C:\pythonProject/image47.jpg')
            resized47 = image47.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image47 = ImageTk.PhotoImage(resized47)
            image48 = Image.open('C:\pythonProject/image48.webp')
            resized48 = image48.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image48 = ImageTk.PhotoImage(resized48)
            image49 = Image.open('C:\pythonProject/image49.jpg')
            resized49 = image49.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image49 = ImageTk.PhotoImage(resized49)
            image50 = Image.open('C:\pythonProject/image50.webp')
            resized50 = image50.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image50 = ImageTk.PhotoImage(resized50)
            image51 = Image.open('C:\pythonProject/image51.png')
            resized51 = image51.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image51 = ImageTk.PhotoImage(resized51)
            image52 = Image.open('C:\pythonProject/image52.jpg')
            resized52 = image52.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image52 = ImageTk.PhotoImage(resized52)
            image53 = Image.open('C:\pythonProject/image53.jpg')
            resized53 = image53.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image53 = ImageTk.PhotoImage(resized53)
            image54 = Image.open('C:\pythonProject/image54.jpg')
            resized54 = image54.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image54 = ImageTk.PhotoImage(resized54)
            image55 = Image.open('C:\pythonProject/image55.jpg')
            resized55 = image55.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image55 = ImageTk.PhotoImage(resized55)
            image56 = Image.open('C:\pythonProject/image56.jpg')
            resized56 = image56.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image56 = ImageTk.PhotoImage(resized56)
            image57 = Image.open('C:\pythonProject/image57.jpeg')
            resized57 = image57.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image57 = ImageTk.PhotoImage(resized57)
            image58 = Image.open('C:\pythonProject/image58.jpeg')
            resized58 = image58.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image58 = ImageTk.PhotoImage(resized58)
            image59 = Image.open('C:\pythonProject/image59.jpg')
            resized59 = image59.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image59 = ImageTk.PhotoImage(resized59)
            image60 = Image.open('C:\pythonProject/image60.jpg')
            resized60 = image60.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image60 = ImageTk.PhotoImage(resized60)
            image61 = Image.open('C:\pythonProject/image61.jpg')
            resized61 = image61.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image61 = ImageTk.PhotoImage(resized61)
            image62 = Image.open('C:\pythonProject/image62.png')
            resized62 = image62.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image62 = ImageTk.PhotoImage(resized62)
            image63 = Image.open('C:\pythonProject/image63.jpg')
            resized63 = image63.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image63 = ImageTk.PhotoImage(resized63)
            image64 = Image.open('C:\pythonProject/image64.jpg')
            resized64 = image63.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image64 = ImageTk.PhotoImage(resized64)
            image65 = Image.open('C:\pythonProject/image65.jpg')
            resized65 = image65.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image65 = ImageTk.PhotoImage(resized65)
            image66 = Image.open('C:\pythonProject/image66.jpeg')
            resized66 = image66.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image66 = ImageTk.PhotoImage(resized66)
            image67 = Image.open('C:\pythonProject/image67.webp')
            resized67 = image67.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image67 = ImageTk.PhotoImage(resized67)
            image68 = Image.open('C:\pythonProject/image68.webp')
            resized68 = image68.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image68 = ImageTk.PhotoImage(resized68)
            image69 = Image.open('C:\pythonProject/image69.jpg')
            resized69 = image69.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image69 = ImageTk.PhotoImage(resized69)
            image70 = Image.open('C:\pythonProject/image70.webp')
            resized70 = image70.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image70 = ImageTk.PhotoImage(resized70)
            image71 = Image.open('C:\pythonProject/image71.png')
            resized71 = image71.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image71 = ImageTk.PhotoImage(resized71)
            image72 = Image.open('C:\pythonProject/image72.webp')
            resized72 = image72.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image72 = ImageTk.PhotoImage(resized72)
            image73 = Image.open('C:\pythonProject/image73.jpeg')
            resized73 = image71.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image73 = ImageTk.PhotoImage(resized73)
            image74 = Image.open('C:\pythonProject/image74.jpg')
            resized74 = image74.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image74 = ImageTk.PhotoImage(resized74)
            image75 = Image.open('C:\pythonProject/image75.png')
            resized75 = image75.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image75 = ImageTk.PhotoImage(resized75)
            image76 = Image.open('C:\pythonProject/image76.jpg')
            resized76 = image76.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image76 = ImageTk.PhotoImage(resized76)
            image77 = Image.open('C:\pythonProject/image77.webp')
            resized77 = image77.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image77 = ImageTk.PhotoImage(resized77)
            image78 = Image.open('C:\pythonProject/image78.jpg')
            resized78 = image78.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image78 = ImageTk.PhotoImage(resized78)
            image79 = Image.open('C:\pythonProject/image79.jpg')
            resized79 = image79.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image79 = ImageTk.PhotoImage(resized79)
            image80 = Image.open('C:\pythonProject/image80.jpeg')
            resized80 = image80.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image80 = ImageTk.PhotoImage(resized80)
            image81 = Image.open('C:\pythonProject/image81.jpg')
            resized81 = image81.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image81 = ImageTk.PhotoImage(resized81)
            image82 = Image.open('C:\pythonProject/image82.webp')
            resized82 = image82.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image82 = ImageTk.PhotoImage(resized82)
            image83 = Image.open('C:\pythonProject/image83.webp')
            resized83 = image83.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image83 = ImageTk.PhotoImage(resized83)
            image84 = Image.open('C:\pythonProject/image84.webp')
            resized84 = image84.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image84 = ImageTk.PhotoImage(resized84)
            image85 = Image.open('C:\pythonProject/image85.webp')
            resized85 = image85.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image85 = ImageTk.PhotoImage(resized85)
            image86 = Image.open('C:\pythonProject/image86.webp')
            resized86 = image86.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image86 = ImageTk.PhotoImage(resized86)
            image87 = Image.open('C:\pythonProject/image87.jpg')
            resized87 = image87.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image87 = ImageTk.PhotoImage(resized87)
            image88 = Image.open('C:\pythonProject/image88.jpeg')
            resized88 = image88.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image88 = ImageTk.PhotoImage(resized88)
            image89 = Image.open('C:\pythonProject/image89.webp')
            resized89 = image89.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image89 = ImageTk.PhotoImage(resized89)
            image90 = Image.open('C:\pythonProject/image90.png')
            resized90 = image90.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image90 = ImageTk.PhotoImage(resized90)
            image91 = Image.open('C:\pythonProject/image91.jpeg')
            resized91 = image91.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image91 = ImageTk.PhotoImage(resized91)
            image92 = Image.open('C:\pythonProject/image92.webp')
            resized92 = image92.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image92 = ImageTk.PhotoImage(resized92)
            image93 = Image.open('C:\pythonProject/image93.jpg')
            resized93 = image93.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image93 = ImageTk.PhotoImage(resized93)
            image94 = Image.open('C:\pythonProject/image94.jpeg')
            resized94 = image94.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image94 = ImageTk.PhotoImage(resized94)
            image95 = Image.open('C:\pythonProject/image95.jpg')
            resized95 = image95.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image95 = ImageTk.PhotoImage(resized95)
            image96 = Image.open('C:\pythonProject/image96.webp')
            resized96 = image96.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image96 = ImageTk.PhotoImage(resized96)
            image97 = Image.open('C:\pythonProject/image97.png')
            resized97 = image97.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image97 = ImageTk.PhotoImage(resized97)
            image98 = Image.open('C:\pythonProject/image98.jpg')
            resized98 = image98.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image98 = ImageTk.PhotoImage(resized98)
            image99 = Image.open('C:\pythonProject/image99.webp')
            resized99 = image99.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image99 = ImageTk.PhotoImage(resized99)
            image100 = Image.open('C:\pythonProject/image100.jpeg')
            resized100 = image100.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image100 = ImageTk.PhotoImage(resized100)
            image101 = Image.open('C:\pythonProject/image101.jpeg')
            resized101 = image101.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image101 = ImageTk.PhotoImage(resized101)
            image102 = Image.open('C:\pythonProject/image102.jpg')
            resized102 = image102.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image102 = ImageTk.PhotoImage(resized102)
            image103 = Image.open('C:\pythonProject/image103.webp')
            resized103 = image103.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image103 = ImageTk.PhotoImage(resized103)
            image104 = Image.open('C:\pythonProject/image104.jpg')
            resized104 = image104.resize((x_coordinate, y_coordinate), Image.LANCZOS)
            new_image104 = ImageTk.PhotoImage(resized104)
            # create a button inside the product frame
            button = Button(product_frame, image=new_image1, command=item1)
            button.grid(row=0, column=0)
            button = Button(product_frame, image=new_image2, command=item2)
            button.grid(row=0, column=1)
            button = Button(product_frame, image=new_image3, command=item3)
            button.grid(row=0, column=2)
            button = Button(product_frame, image=new_image4, command=item4)
            button.grid(row=0, column=3)
            button = Button(product_frame, image=new_image5, command=item5)
            button.grid(row=0, column=4)
            button = Button(product_frame, image=new_image6, command=item6)
            button.grid(row=0, column=5)
            button = Button(product_frame, image=new_image7, command=item7)
            button.grid(row=0, column=6)
            button = Button(product_frame, image=new_image8, command=item8)
            button.grid(row=0, column=7)
            button = Button(product_frame, image=new_image9, command=item9)
            button.grid(row=0, column=8)
            button = Button(product_frame, image=new_image10, command=item10)
            button.grid(row=0, column=9)
            button = Button(product_frame, image=new_image11, command=item11)
            button.grid(row=0, column=10)
            button = Button(product_frame, image=new_image12, command=item12)
            button.grid(row=0, column=11)
            button = Button(product_frame, image=new_image13, command=item13)
            button.grid(row=0, column=12)
            button = Button(product_frame, image=new_image14, command=item14)
            button.grid(row=1, column=0)
            button = Button(product_frame, image=new_image15, command=item15)
            button.grid(row=1, column=1)
            button = Button(product_frame, image=new_image16, command=item16)
            button.grid(row=1, column=2)
            button = Button(product_frame, image=new_image17, command=item17)
            button.grid(row=1, column=3)
            button = Button(product_frame, image=new_image18, command=item18)
            button.grid(row=1, column=4)
            button = Button(product_frame, image=new_image19, command=item19)
            button.grid(row=1, column=5)
            button = Button(product_frame, image=new_image20, command=item20)
            button.grid(row=1, column=6)
            button = Button(product_frame, image=new_image21, command=item21)
            button.grid(row=1, column=7)
            button = Button(product_frame, image=new_image22, command=item22)
            button.grid(row=1, column=8)
            button = Button(product_frame, image=new_image23, command=item23)
            button.grid(row=1, column=9)
            button = Button(product_frame, image=new_image24, command=item24)
            button.grid(row=1, column=10)
            button = Button(product_frame, image=new_image25, command=item25)
            button.grid(row=1, column=11)
            button = Button(product_frame, image=new_image26, command=item26)
            button.grid(row=1, column=12)
            button = Button(product_frame, image=new_image27, command=item27)
            button.grid(row=2, column=0)
            button = Button(product_frame, image=new_image28, command=item28)
            button.grid(row=2, column=1)
            button = Button(product_frame, image=new_image29, command=item29)
            button.grid(row=2, column=2)
            button = Button(product_frame, image=new_image30, command=item30)
            button.grid(row=2, column=3)
            button = Button(product_frame, image=new_image31, command=item31)
            button.grid(row=2, column=4)
            button = Button(product_frame, image=new_image32, command=item32)
            button.grid(row=2, column=5)
            button = Button(product_frame, image=new_image33, command=item33)
            button.grid(row=2, column=6)
            button = Button(product_frame, image=new_image34, command=item34)
            button.grid(row=2, column=7)
            button = Button(product_frame, image=new_image35, command=item35)
            button.grid(row=2, column=8)
            button = Button(product_frame, image=new_image36, command=item36)
            button.grid(row=2, column=9)
            button = Button(product_frame, image=new_image37, command=item37)
            button.grid(row=2, column=10)
            button = Button(product_frame, image=new_image38, command=item38)
            button.grid(row=2, column=11)
            button = Button(product_frame, image=new_image39, command=item39)
            button.grid(row=2, column=12)
            button = Button(product_frame, image=new_image40, command=item40)
            button.grid(row=3, column=0)
            button = Button(product_frame, image=new_image41, command=item41)
            button.grid(row=3, column=1)
            button = Button(product_frame, image=new_image42, command=item42)
            button.grid(row=3, column=2)
            button = Button(product_frame, image=new_image43, command=item43)
            button.grid(row=3, column=3)
            button = Button(product_frame, image=new_image44, command=item44)
            button.grid(row=3, column=4)
            button = Button(product_frame, image=new_image45, command=item45)
            button.grid(row=3, column=5)
            button = Button(product_frame, image=new_image46, command=item46)
            button.grid(row=3, column=6)
            button = Button(product_frame, image=new_image47, command=item47)
            button.grid(row=3, column=7)
            button = Button(product_frame, image=new_image48, command=item48)
            button.grid(row=3, column=8)
            button = Button(product_frame, image=new_image49, command=item49)
            button.grid(row=3, column=9)
            button = Button(product_frame, image=new_image50, command=item50)
            button.grid(row=3, column=10)
            button = Button(product_frame, image=new_image51, command=item51)
            button.grid(row=3, column=11)
            button = Button(product_frame, image=new_image52, command=item52)
            button.grid(row=3, column=12)
            button = Button(product_frame, image=new_image53, command=item53)
            button.grid(row=4, column=0)
            button = Button(product_frame, image=new_image54, command=item54)
            button.grid(row=4, column=1)
            button = Button(product_frame, image=new_image55, command=item55)
            button.grid(row=4, column=2)
            button = Button(product_frame, image=new_image56, command=item56)
            button.grid(row=4, column=3)
            button = Button(product_frame, image=new_image57, command=item57)
            button.grid(row=4, column=4)
            button = Button(product_frame, image=new_image58, command=item58)
            button.grid(row=4, column=5)
            button = Button(product_frame, image=new_image59, command=item59)
            button.grid(row=4, column=6)
            button = Button(product_frame, image=new_image60, command=item60)
            button.grid(row=4, column=7)
            button = Button(product_frame, image=new_image61, command=item61)
            button.grid(row=4, column=8)
            button = Button(product_frame, image=new_image62, command=item62)
            button.grid(row=4, column=9)
            button = Button(product_frame, image=new_image63, command=item63)
            button.grid(row=4, column=10)
            button = Button(product_frame, image=new_image64, command=item64)
            button.grid(row=4, column=11)
            button = Button(product_frame, image=new_image65, command=item65)
            button.grid(row=4, column=12)
            button = Button(product_frame, image=new_image66, command=item66)
            button.grid(row=5, column=0)
            button = Button(product_frame, image=new_image67, command=item67)
            button.grid(row=5, column=1)
            button = Button(product_frame, image=new_image68, command=item68)
            button.grid(row=5, column=2)
            button = Button(product_frame, image=new_image69, command=item69)
            button.grid(row=5, column=3)
            button = Button(product_frame, image=new_image70, command=item70)
            button.grid(row=5, column=4)
            button = Button(product_frame, image=new_image71, command=item71)
            button.grid(row=5, column=5)
            button = Button(product_frame, image=new_image72, command=item72)
            button.grid(row=5, column=6)
            button = Button(product_frame, image=new_image73, command=item73)
            button.grid(row=5, column=7)
            button = Button(product_frame, image=new_image74, command=item74)
            button.grid(row=5, column=8)
            button = Button(product_frame, image=new_image75, command=item75)
            button.grid(row=5, column=9)
            button = Button(product_frame, image=new_image76, command=item76)
            button.grid(row=5, column=10)
            button = Button(product_frame, image=new_image77, command=item77)
            button.grid(row=5, column=11)
            button = Button(product_frame, image=new_image78, command=item78)
            button.grid(row=5, column=12)
            button = Button(product_frame, image=new_image79, command=item79)
            button.grid(row=6, column=0)
            button = Button(product_frame, image=new_image80, command=item80)
            button.grid(row=6, column=1)
            button = Button(product_frame, image=new_image81, command=item81)
            button.grid(row=6, column=2)
            button = Button(product_frame, image=new_image82, command=item82)
            button.grid(row=6, column=3)
            button = Button(product_frame, image=new_image83, command=item83)
            button.grid(row=6, column=4)
            button = Button(product_frame, image=new_image84, command=item84)
            button.grid(row=6, column=5)
            button = Button(product_frame, image=new_image85, command=item85)
            button.grid(row=6, column=6)
            button = Button(product_frame, image=new_image86, command=item86)
            button.grid(row=6, column=7)
            button = Button(product_frame, image=new_image87, command=item87)
            button.grid(row=6, column=8)
            button = Button(product_frame, image=new_image88, command=item88)
            button.grid(row=6, column=9)
            button = Button(product_frame, image=new_image89, command=item89)
            button.grid(row=6, column=10)
            button = Button(product_frame, image=new_image90, command=item90)
            button.grid(row=6, column=11)
            button = Button(product_frame, image=new_image91, command=item91)
            button.grid(row=6, column=12)
            button = Button(product_frame, image=new_image92, command=item92)
            button.grid(row=7, column=0)
            button = Button(product_frame, image=new_image93, command=item93)
            button.grid(row=7, column=1)
            button = Button(product_frame, image=new_image94, command=item94)
            button.grid(row=7, column=2)
            button = Button(product_frame, image=new_image95, command=item95)
            button.grid(row=7, column=3)
            button = Button(product_frame, image=new_image96, command=item96)
            button.grid(row=7, column=4)
            button = Button(product_frame, image=new_image97, command=item97)
            button.grid(row=7, column=5)
            button = Button(product_frame, image=new_image98, command=item98)
            button.grid(row=7, column=6)
            button = Button(product_frame, image=new_image99, command=item99)
            button.grid(row=7, column=7)
            button = Button(product_frame, image=new_image100, command=item100)
            button.grid(row=7, column=8)
            button = Button(product_frame, image=new_image101, command=item101)
            button.grid(row=7, column=9)
            button = Button(product_frame, image=new_image102, command=item102)
            button.grid(row=7, column=10)
            button = Button(product_frame, image=new_image103, command=item103)
            button.grid(row=7, column=11)
            button = Button(product_frame, image=new_image104, command=item104)
            button.grid(row=7, column=12)

            root.mainloop()
        else:
            messagebox.showerror('Error', 'Invalid username or password. Please check your credentials and try again.')
            usernameEntry.delete(0, END)
            passwordEntry.delete(0, END)
            usernameEntry.focus_set()

title = Label(login, text='EMPLOYEE LOGIN', font=('TIMES', 30),
              bg='light blue')
title.place(x=550, y=220)

# create label
usernameLabel = Label(login,
                      text='Username: ', font=('TIMES', 15),
                      bg='light blue')
usernameLabel.place(x=557,
                    y=300)
passwordLabel = Label(login,
                      text='Password: ', font=('TIMES', 15),
                      bg='light blue')
passwordLabel.place(x=557, y=350)

# create an entry
usernameEntry = Entry(login, borderwidth=5)
usernameEntry.place(x=657, y=300,
                    width=230, height=28)
usernameEntry.focus_set()
passwordEntry = Entry(login, show='*', borderwidth=5)
passwordEntry.place(x=657, y=350,
                    width=230, height=28)

# create a button
login_button = Button(login, text='Login',
                      command=login_function,
                      borderwidth=1,
                      highlightthickness=1)
login_button.place(x=657, y=400,
                   width=110)
login_button.bind('<Button-1>', login_function)

cancel_button = Button(login,
                       text='Exit',
                       command=close_window,
                       borderwidth=1,
                       highlightthickness=1)
cancel_button.place(x=779, y=400,
                    width=110)

login.bind('<Return>', login_function, add='+')
login.bind('<Escape>', close_window)
cancel_button.bind('<Return>', close_window)

login.mainloop()
