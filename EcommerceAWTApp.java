import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

class Product {
    String name;
    int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

public class EcommerceAWTApp extends Frame {
    CardLayout layout = new CardLayout();
    Panel mainPanel = new Panel();

    // User Authentication
    HashMap<String, String> users = new HashMap<>();
    String loggedInUser = "";

    // Shopping cart and order details
    ArrayList<String> cart = new ArrayList<>();
    ArrayList<Integer> cartPrices = new ArrayList<>();
    String selectedCategory = "";
    String customerName = "";
    String customerAddress = "";

    final HashMap<String, List<Product>> categorizedProducts = new HashMap<>();

    EcommerceAWTApp() {
        setTitle("AWT E-Commerce Platform");
        setSize(1000, 700);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        mainPanel.setLayout(layout);
        prepareProductData();

        // Add default users for testing
        users.put("user1", "password1");
        users.put("user2", "password2");

        // Add pages
        mainPanel.add(loginPanel(), "Login");
        mainPanel.add(signupPanel(), "Signup");
        mainPanel.add(categoryPanel(), "Categories");
        mainPanel.add(productPanel(), "Products");
        mainPanel.add(cartPanel(), "Cart");
        mainPanel.add(checkoutPanel(), "Checkout");
        mainPanel.add(orderPlacedPanel(), "OrderPlaced");

        Panel marginPanel = new Panel(new BorderLayout());
        marginPanel.setBackground(Color.WHITE);
        marginPanel.setLayout(new BorderLayout());
        marginPanel.add(new Panel(), BorderLayout.NORTH);
        marginPanel.add(new Panel(), BorderLayout.SOUTH);
        marginPanel.add(new Panel(), BorderLayout.EAST);
        marginPanel.add(new Panel(), BorderLayout.WEST);
        marginPanel.add(mainPanel, BorderLayout.CENTER);

        add(marginPanel);

        layout.show(mainPanel, "Login"); // Start by showing the login page

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    void prepareProductData() {
        categorizedProducts.put("Technology", Arrays.asList(
                new Product("Laptop", 75000), new Product("Smartphone", 25000), new Product("Headphones", 3000),
                new Product("Keyboard", 1500), new Product("Mouse", 800), new Product("Charger", 1200)
        ));
        categorizedProducts.put("Books", Arrays.asList(
                new Product("Fiction Book", 500), new Product("Science Book", 750), new Product("Math Book", 900),
                new Product("History Book", 600), new Product("Children Book", 400), new Product("Language Book", 700)
        ));
        categorizedProducts.put("Food", Arrays.asList(
                new Product("Burger", 150), new Product("Pizza", 300), new Product("Pasta", 250),
                new Product("Bread", 50), new Product("Milk", 60), new Product("Rice", 100)
        ));
        categorizedProducts.put("Household", Arrays.asList(
                new Product("Sofa", 30000), new Product("Chair", 1200), new Product("Table", 4000),
                new Product("Lamp", 850), new Product("Clock", 700), new Product("Curtains", 1500)
        ));
    }

    // Login Panel
    Panel loginPanel() {
        Panel panel = new Panel(new GridLayout(3, 2));
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        passwordField.setEchoChar('*');

        Button loginButton = new Button("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            // Authenticate User
            if (users.containsKey(username) && users.get(username).equals(password)) {
                loggedInUser = username;
                showPopup("Welcome " + loggedInUser + "!");
                layout.show(mainPanel, "Categories");
            } else {
                showPopup("Invalid credentials, please try again.");
            }
        });

        Button signupButton = new Button("Signup");
        signupButton.addActionListener(e -> layout.show(mainPanel, "Signup"));

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signupButton);

        return panel;
    }

    // Signup Panel
    Panel signupPanel() {
        Panel panel = new Panel(new GridLayout(3, 2));
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        passwordField.setEchoChar('*');

        Button signupButton = new Button("Signup");
        signupButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            // Check if the username is already taken
            if (users.containsKey(username)) {
                showPopup("Username already exists! Please try a different one.");
            } else {
                users.put(username, password);
                showPopup("Signup successful! You can now log in.");
                layout.show(mainPanel, "Login");
            }
        });

        Button loginButton = new Button("Login");
        loginButton.addActionListener(e -> layout.show(mainPanel, "Login"));

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(signupButton);
        panel.add(loginButton);

        return panel;
    }

    // Categories Panel
    Panel categoryPanel() {
        Panel panel = new Panel(new GridLayout(2, 2, 10, 10));
        String[] categories = {"Technology", "Books", "Food", "Household"};
        for (String category : categories) {
            Button btn = new Button(category);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(e -> {
                selectedCategory = category;
                mainPanel.remove(1);
                mainPanel.add(productPanel(), "Products", 1);
                layout.show(mainPanel, "Products");
            });
            panel.add(btn);
        }
        return panel;
    }

    // Products Panel
    Panel productPanel() {
        Panel panel = new Panel(new BorderLayout());
        Panel grid = new Panel(new GridLayout(3, 2, 10, 10));

        if (!categorizedProducts.containsKey(selectedCategory)) return panel;

        for (Product product : categorizedProducts.get(selectedCategory)) {
            String name = product.name;
            int cost = product.price;
            String price = "₹" + cost;

            Panel itemPanel = new Panel(new BorderLayout());
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setPreferredSize(new Dimension(200, 150));

            Label label = new Label(name + " - " + price, Label.CENTER);
            Button add = new Button("Add to Cart");

            add.addActionListener(e -> {
                cart.add(name);
                cartPrices.add(cost);
                showPopup("Added to cart: " + name);
            });

            itemPanel.add(label, BorderLayout.CENTER);
            itemPanel.add(add, BorderLayout.SOUTH);
            grid.add(itemPanel);
        }

        Button viewCart = new Button("View Cart");
        viewCart.addActionListener(e -> {
            mainPanel.remove(2); // Update cart panel
            mainPanel.add(cartPanel(), "Cart", 2);
            layout.show(mainPanel, "Cart");
        });

        Button backToCategories = new Button("Back to Categories");
        backToCategories.addActionListener(e -> layout.show(mainPanel, "Categories"));

        Panel navigation = new Panel();
        navigation.add(backToCategories);
        navigation.add(viewCart);

        panel.add(grid, BorderLayout.CENTER);
        panel.add(navigation, BorderLayout.SOUTH);
        return panel;
    }

    // Cart Panel
    Panel cartPanel() {
        Panel panel = new Panel(new BorderLayout());
        TextArea cartArea = new TextArea();
        cartArea.setEditable(false);

        Button delete = new Button("Remove Last Item");
        Button checkout = new Button("Proceed to Checkout");
        Button back = new Button("Back to Products");

        delete.addActionListener(e -> {
            if (!cart.isEmpty()) {
                String removed = cart.remove(cart.size() - 1);
                cartPrices.remove(cartPrices.size() - 1);
                showPopup("Removed: " + removed);
                updateCart(cartArea);
            }
        });

        checkout.addActionListener(e -> {
            mainPanel.remove(3); // Update checkout panel
            mainPanel.add(checkoutPanel(), "Checkout", 3);
            layout.show(mainPanel, "Checkout");
        });

        back.addActionListener(e -> layout.show(mainPanel, "Products"));

        Panel btns = new Panel();
        btns.add(delete);
        btns.add(checkout);
        btns.add(back);

        panel.add(cartArea, BorderLayout.CENTER);
        panel.add(btns, BorderLayout.SOUTH);

        updateCart(cartArea);
        return panel;
    }

    void updateCart(TextArea area) {
        StringBuilder sb = new StringBuilder("Your Cart:\n");
        int total = 0;
        for (int i = 0; i < cart.size(); i++) {
            sb.append(cart.get(i)).append(" - ₹").append(cartPrices.get(i)).append("\n");
            total += cartPrices.get(i);
        }
        sb.append("\nTotal: ₹").append(total);
        area.setText(sb.toString());
    }

    // Checkout Panel
    Panel checkoutPanel() {
        Panel panel = new Panel(new BorderLayout());

        TextArea billArea = new TextArea();
        billArea.setEditable(false);

        String[] payments = {"Credit Card", "UPI", "Cash on Delivery"};
        Choice paymentChoice = new Choice();
        for (String pay : payments) paymentChoice.add(pay);

        // Customer Name & Address Fields
        Panel customerInfo = new Panel(new GridLayout(2, 2));
        Label nameLabel = new Label("Enter Name:");
        TextField nameField = new TextField();
        Label addressLabel = new Label("Enter Address:");
        TextField addressField = new TextField();

        customerInfo.add(nameLabel);
        customerInfo.add(nameField);
        customerInfo.add(addressLabel);
        customerInfo.add(addressField);

        Button payBtn = new Button("Pay Now");
        payBtn.addActionListener(e -> {
            customerName = nameField.getText().trim();
            customerAddress = addressField.getText().trim();

            if (customerName.isEmpty() || customerAddress.isEmpty()) {
                showPopup("Please enter both Name and Address before placing the order.");
                return;
            }

            String payType = paymentChoice.getSelectedItem();
            showPopup("Order placed successfully for " + customerName + " at " + customerAddress + " via " + payType);
            cart.clear();
            cartPrices.clear();
            layout.show(mainPanel, "OrderPlaced");
        });

        int total = 0;
        StringBuilder sb = new StringBuilder("----- Bill -----\n");
        for (int i = 0; i < cart.size(); i++) {
            sb.append(cart.get(i)).append(" - ₹").append(cartPrices.get(i)).append("\n");
            total += cartPrices.get(i);
        }
        sb.append("\nTotal: ₹").append(total);
        billArea.setText(sb.toString());

        Panel bottom = new Panel();
        bottom.add(new Label("Select Payment Method:"));
        bottom.add(paymentChoice);
        bottom.add(payBtn);

        panel.add(customerInfo, BorderLayout.NORTH);
        panel.add(billArea, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    // Order Placed Panel
    Panel orderPlacedPanel() {
        Panel panel = new Panel(new BorderLayout());
        Label orderConfirmation = new Label("Your order has been placed successfully!", Label.CENTER);
        orderConfirmation.setFont(new Font("Arial", Font.BOLD, 18));
        Button backToCategories = new Button("Back to Categories");

        backToCategories.addActionListener(e -> layout.show(mainPanel, "Categories"));

        panel.add(orderConfirmation, BorderLayout.CENTER);
        panel.add(backToCategories, BorderLayout.SOUTH);
        return panel;
    }

    // Show pop-up dialog
    void showPopup(String message) {
        Dialog dialog = new Dialog(this, "Notification", true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(300, 100);
        dialog.add(new Label(message));
        Button ok = new Button("OK");
        ok.addActionListener(e2 -> dialog.setVisible(false));
        dialog.add(ok);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new EcommerceAWTApp();
    }
}
