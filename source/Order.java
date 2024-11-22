package com.aspire;
import java.util.ArrayList;
import java.util.List;
public class Order {
    protected User user;
    private List<OrderItem> items;
    protected String deliveryAddress;
    protected String paymentOption;
    public Order(User user, String deliveryAddress) 
    {
        this.user = user;
        items = new ArrayList<>();
        this.deliveryAddress = deliveryAddress;
        this.paymentOption = paymentOption;
    }
    public void addItem(MenuItem item, int quantity)
    {
        OrderItem orderItem = new OrderItem(item, quantity);
        items.add(orderItem);
    }
    public void generateReceipt()
    {
        System.out.println("************ RECEIPT ************");
        double  total = 0;
        for (OrderItem orderItem : items)
        {
        	
            MenuItem item = orderItem.getItem();
            int quantity = orderItem.getQuantity();
            double itemTotal = item.getPrice() * quantity;
            total += itemTotal;
            System.out.println("Item " + item.getName());
            System.out.println("Quantity  " + quantity);
            System.out.println("Item Total " + itemTotal);
            System.out.println("---------------------------------");
     }
        System.out.println("Total: " + total);
        System.out.println("*********************************");
    }
}