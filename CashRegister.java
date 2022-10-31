import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CashRegister implements ActionListener {

    JFrame frame;
    JPanel pane1, pane2, pane3, pane4;
    CardLayout card;
    Database data = new Database();
    int totalPrice = 0;
    JButton start, cancel, checkout, end, cancel2, removeItem, addItem, back;
    JLabel products, costs, total, total2, total3, total4;
    JTextField code;
    JRadioButton cash, debit, credit;
    ButtonGroup pay = new ButtonGroup();
    ArrayList<String> itemList = new ArrayList<String>();

    public CashRegister() {
        //---------------------
        // Setting up JFrame
        //---------------------
        frame = new JFrame("Cash Register GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminates program upon closing JFrame
        frame.setSize(500, 400);

        //---------------------
        // Pane 1 - Start Session
        //---------------------
        pane1 = new JPanel();
        pane1.setLayout(null);
        pane1.setBackground(Color.WHITE);

        start = new JButton("Start Session");
        start.setBounds(150, 130, 200, 100);
        pane1.add(start);    

        //---------------------
        // Pane 2 - Adding Items
        //---------------------
        pane2 = new JPanel();
        pane2.setLayout(null);
        pane2.setBackground(Color.WHITE);

        products = new JLabel("<html>Product: ");
        products.setBounds(20, 20, 215, 220);
        products.setOpaque(true); // allows background color to be changed
        products.setBackground(Color.LIGHT_GRAY);
        pane2.add(products);

        costs = new JLabel("<html>Cost: ");
        costs.setBounds(245, 20, 220, 220);
        costs.setOpaque(true);
        costs.setBackground(Color.LIGHT_GRAY);
        pane2.add(costs);
        
        total = new JLabel("SubTotal: $0.00");
        total.setBounds(350, 260, 115, 30);
        total.setOpaque(true);
        total.setBackground(Color.LIGHT_GRAY);
        pane2.add(total);

        code = new JTextField("Enter Item Name");
        code.setBounds(20, 260, 135, 30);
        pane2.add(code);

        addItem = new JButton("Add");
        addItem.setBounds(165, 260, 65, 30);
        addItem.setBackground(Color.GREEN);
        pane2.add(addItem);

        removeItem = new JButton("Remove");
        removeItem.setBounds(255, 260, 85, 30);
        removeItem.setBackground(Color.RED);
        pane2.add(removeItem);

        cancel = new JButton("Cancel Session");
        pane2.add(cancel);
        cancel.setBounds(20, 300, 210, 50);

        checkout = new JButton("Proceed To Checkout");
        pane2.add(checkout);
        checkout.setBounds(255, 300, 210, 50);

        //---------------------
        // Pane 3 - Payment and Termination
        //---------------------
        pane3 = new JPanel();
        pane3.setLayout(null);
        pane3.setBackground(Color.WHITE);

        total2 = new JLabel("SubTotal: $");
        total2.setBounds(120, 10, 245, 70);
        total2.setOpaque(true);
        total2.setBackground(Color.LIGHT_GRAY);
        pane3.add(total2);

        total3 = new JLabel("Hst: $");
        total3.setBounds(120, 90, 245, 70);
        total3.setOpaque(true);
        total3.setBackground(Color.LIGHT_GRAY);
        pane3.add(total3);

        total4 = new JLabel("Total: $");
        total4.setBounds(120, 170, 245, 70);
        total4.setOpaque(true);
        total4.setBackground(Color.LIGHT_GRAY);
        pane3.add(total4);

        back = new JButton("Go Back");
        back.setBounds(20, 210, 90, 30);
        pane3.add(back);

        end = new JButton("Confirm Payment");
        end.setBounds(255, 300, 210, 50);
        pane3.add(end);

        cancel2 = new JButton("Cancel Session");
        cancel2.setBounds(20, 300, 210, 50);
        pane3.add(cancel2);

        cash = new JRadioButton("Cash");
        cash.setBounds(22, 260, 140, 30);

        debit = new JRadioButton("Debit");
        debit.setBounds(173, 260, 140, 30);

        credit = new JRadioButton("Credit");
        credit.setBounds(325, 260, 140, 30);

        //Adding radio buttons to group and then to panel
        pay.add(cash); pane3.add(cash);
        pay.add(credit); pane3.add(credit);
        pay.add(debit); pane3.add(debit);

        //---------------------
        // Pane 4 - Holds panels 1-3, enables card layout to be used
        //---------------------
        pane4 = new JPanel();
        card = new CardLayout();
        pane4.setLayout(card);
        pane4.add(pane1, "First Pane");
        pane4.add(pane2, "Second Pane");
        pane4.add(pane3, "Third Pane");

        //---------------------
        // Event Listeners, actions that occur when a specific buttons is pressed
        //---------------------
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                    card.next(pane4);
                }
            });

        // Shows product information on screen when product name is entered
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                scan("+");
            }
        });

        // Unscans an item
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                scan("-");
            }
        });

        // Resets 2nd panel
        cancel.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            restart();
            }
        });

        // Calculates prices when confirm button is pressed
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                calculatePrices();
            }
        });

        // Resets everything
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Receipt s = new Receipt();
                if (cash.isSelected() == true)
                {
                    s.printReceipt(makeReceipt("cash"));
                    JOptionPane.showMessageDialog(frame, "Payment Verified. Printing Receipt.");
                    restart();
                }
                else if (debit.isSelected() == true)
                {
                    s.printReceipt(makeReceipt("debit"));
                    JOptionPane.showMessageDialog(frame, "Payment Verified. Printing Receipt.");
                    restart();
                }
                else if (credit.isSelected() == true)
                {
                    s.printReceipt(makeReceipt("credit"));
                    JOptionPane.showMessageDialog(frame, "Payment Verified. Printing Receipt.");
                    restart();
                }
            }
        });

        // Resets everything but used by a different button
        cancel2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                restart();
            }
        });

        // Goes back from payment page to add product page
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                card.previous(pane4);
            }
        });

        //---------------------
        // Adds panels to frame and lets frame appear on screen
        //---------------------
        frame.add(pane4);
        frame.setVisible(true);
    }

    //---------------------
    // Methods
    //---------------------
        
    // Displays name and price of product entered, Takes "+" to add and "-" to remove
    public void scan(String sign){
        String name = code.getText();
        String price = data.findPrice(name);
        
        if (!price.equals("Product Not In Database"))
        {
            if (sign == "+") //If adding an item
            {
                String productList = products.getText() + "<br>" + name;
                String costList = costs.getText() + "<br>+ " + price + ".00";
                costs.setText(costList);
                products.setText(productList);
                itemList.add(name);
                
                totalPrice = totalPrice + Integer.parseInt(price.substring(1));
                total.setText("SubTotal: $" + totalPrice + ".00");
            }
            else if (sign == "-") //If removing an item
            {
                if (itemList.remove(name) == true)
                {
                    String productList = products.getText() + "<br>" + name;
                    String costList = costs.getText() + "<br>- " + price + ".00";
                    costs.setText(costList);
                    products.setText(productList);

                    totalPrice = totalPrice - Integer.parseInt(price.substring(1));
                    total.setText("SubTotal: $" + totalPrice + ".00");
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Product Does Not Exist In Cart!");
                }
            }
        }
        else //If item is not found in the database
        {
            JOptionPane.showMessageDialog(frame, "Item Name Does Not Exist In Database!");
        }

    }

    // Resets everything and moves to first panel
    public void restart(){
        products.setText("<html>Product: ");
        costs.setText("<html>Cost: ");
        total.setText("SubTotal: $0.00");
        code.setText("Enter Item Name");
        total2.setText("SubTotal: $");
        total3.setText("Hst: $");
        total4.setText("Total: $");
        totalPrice = 0;
        card.first(pane4);
    }

    // Calculates subtotal, tax, and final price
    public void calculatePrices(){
        card.next(pane4);
        total2.setText("Subtotal: $" + totalPrice + ".00");
        total3.setText("HST: $" + ((float)totalPrice * 0.13));
        total4.setText("Total: $" + ((float)totalPrice * 0.13 + totalPrice));
    }

    // Makes receipt
    public String makeReceipt(String type){
        Set<String> distinct = new HashSet<>(itemList); // Contains only unique items in itemList
        String receipt = "Paid by " + type + "\n";

        // Adds item name, quantity, and cost for each item to string
        for (String s: distinct) {
            String price = data.findPrice(s).substring(1);
            int qty = Collections.frequency(itemList, s);

            receipt += "Product: " + s + " ";
            receipt += "Quantity: " + qty + " ";
            receipt += "Cost: $" + (qty * Integer.parseInt(price)) + ".00\n";
        }

        // Adds prices to string
        receipt += "\nSubTotal: " + totalPrice + ".00";
        receipt += "\nHST: " + ((float)totalPrice * 0.13);
        receipt += "\nTotal: " + ((float)totalPrice * 0.13 + totalPrice);

        return receipt;
    }
    
}