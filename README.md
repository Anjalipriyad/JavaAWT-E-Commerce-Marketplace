

# 🛒 Java AWT E-Commerce Application

## 📌 Overview

This project is a **basic E-Commerce platform built using Java AWT (Abstract Window Toolkit)**.
It demonstrates how to create a simple shopping application with features like **user authentication, product browsing, shopping cart, checkout, and order placement**.

The application runs as a **desktop GUI app** and uses **AWT layouts, panels, and dialogs** for user interaction.

---

## 🚀 Features

* **User Authentication**

  * Login & Signup functionality with credential storage.
* **Category Browsing**

  * Products divided into categories (Technology, Books, Food, Household).
* **Shopping Cart**

  * Add products, remove items, and view cart details.
* **Checkout**

  * Enter customer name & address.
  * Select payment method (Credit Card, UPI, COD).
  * Generate a simple bill with total amount.
* **Order Placement**

  * Confirmation message displayed after successful order.
* **Pop-up Notifications**

  * Informative dialogs for login, signup, add/remove cart items, and checkout events.

---

## 🛠️ Tech Stack

* **Java AWT** – GUI framework
* **Core Java** – Data structures (HashMap, ArrayList, List)
* **OOP Concepts** – Classes, objects, encapsulation

---

## 📂 Project Structure

```
EcommerceAWTApp.java    # Main application file
Product class           # Represents products with name & price
Panels:
  - Login Panel
  - Signup Panel
  - Category Panel
  - Product Panel
  - Cart Panel
  - Checkout Panel
  - Order Placed Panel
```

---

## ▶️ How to Run

1. Ensure **Java JDK 8+** is installed on your system.
2. Save the code as `EcommerceAWTApp.java`.
3. Open terminal/command prompt in the project directory.
4. Compile:

   ```bash
   javac EcommerceAWTApp.java
   ```
5. Run:

   ```bash
   java EcommerceAWTApp
   ```

---

## 📸 App Flow

1. **Login / Signup**
2. **Select Category**
3. **View & Add Products**
4. **Manage Cart**
5. **Proceed to Checkout**
6. **Place Order**
7. **Order Confirmation**

---

## 💡 Future Enhancements

* Add product images and descriptions.
* Implement database (e.g., MySQL) for users and products.
* Add quantity selection for products.
* Apply discounts, coupons, and taxes.
* Modernize UI with **Java Swing** or **JavaFX**.

