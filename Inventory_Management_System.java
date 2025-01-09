import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String displayDetails() {
        return "Name: " + name + ", Price: $" + price + ", Quantity: " + quantity;
    }
}

class Inventory {
    private Product[] inventory;
    private int count;

    public Inventory(int capacity) {
        inventory = new Product[capacity];
        count = 0;
    }

    public boolean addProduct(Product product) {
        if (count < inventory.length) {
            inventory[count++] = product;
            return true;
        }
        return false;
    }

    public boolean updateProduct(String name, double newPrice, int newQuantity) {
        for (int i = 0; i < count; i++) {
            if (inventory[i].getName().equalsIgnoreCase(name)) {
                inventory[i].setPrice(newPrice);
                inventory[i].setQuantity(newQuantity);
                return true;
            }
        }
        return false;
    }

    public String displayAllProducts() {
        if (count == 0) {
            return "Inventory is empty.";
        }
        StringBuilder details = new StringBuilder();
        for (int i = 0; i < count; i++) {
            details.append(inventory[i].displayDetails()).append("\n");
        }
        return details.toString();
    }
}


public class Inventory_Management_System {
    public static void main(String[] args) {
        Inventory inventory = new Inventory(3);

        JFrame frame = new JFrame("Inventory Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 400);

        // Components
        JTextField product_name = new JTextField(15);
        JTextField item_price = new JTextField(15);
        JTextField amount = new JTextField(15);
        JTextArea out = new JTextArea(10, 30);
        out.setEditable(false);

        JButton addButton = new JButton("Add Product");
        JButton updateButton = new JButton("Update Product");
        JButton displayButton = new JButton("Display Products");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("        Product Name:"));
        panel.add(product_name);
        panel.add(new JLabel("        Price:"));
        panel.add(item_price);
        panel.add(new JLabel("        Quantity:"));
        panel.add(amount);
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(displayButton);
        panel.add(out);

        frame.add(panel);

        addButton.addActionListener(e -> {
            String name = product_name.getText();
            String priceText = item_price.getText();
            String quantityText = amount.getText();
            if (!priceText.isEmpty() && !quantityText.isEmpty()) {
                double price = Double.parseDouble(priceText);
                int quantity = Integer.parseInt(quantityText);
                Product product = new Product(name, price, quantity);
                if (inventory.addProduct(product)) {
                    out.setText("Product added successfully.");
                } else {
                    out.setText("Inventory is full.");
                }
            } else {
                out.setText("Please enter valid price and quantity.");
            }
        });

        updateButton.addActionListener(e -> {
            String name = product_name.getText();
            String priceText = item_price.getText();
            String quantityText = amount.getText();
            if (!priceText.isEmpty() && !quantityText.isEmpty()) {
                double price = Double.parseDouble(priceText);
                int quantity = Integer.parseInt(quantityText);
                if (inventory.updateProduct(name, price, quantity)) {
                    out.setText("Product updated successfully.");
                } else {
                    out.setText("Product not found.");
                }
            } else {
                out.setText("Incorrect Date. Please check once more and enter valid price and quantity.");
            }
        });

        displayButton.addActionListener(e -> {
            out.setText(inventory.displayAllProducts());
        });

        frame.setVisible(true);
    }
}
