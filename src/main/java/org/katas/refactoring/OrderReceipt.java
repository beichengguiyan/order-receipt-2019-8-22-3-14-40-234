package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address,
 * description, quantity, price and amount. It also calculates the sales tax @
 * 10% and prints as part of order. It computes the total order amount (amount
 * of individual lineItems + total sales tax) and prints it.
 */
public class OrderReceipt {
	private Order order;

	public OrderReceipt(Order order) {
		this.order = order;
	}

	public String printReceipt() {

		double totSalesTx = calculateSalesTax();
		double tot = calculateTotalAmount();
		StringBuilder output = new StringBuilder();

		output.append("======Printing Orders======\n");
		output.append(order.getCustomerName());
		output.append(order.getCustomerAddress());
		for (LineItem lineItem : order.getLineItems()) {
			output.append(lineItem.getDescription());
			output.append('\t');
			output.append(lineItem.getPrice());
			output.append('\t');
			output.append(lineItem.getQuantity());
			output.append('\t');
			output.append(lineItem.totalAmount());
			output.append('\n');
		}
		output.append("Sales Tax").append('\t').append(totSalesTx);
		output.append("Total Amount").append('\t').append(tot);
		
		return output.toString();
	}

	public double calculateSalesTax() {
		double totSalesTx = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			double salesTax = lineItem.totalAmount() * .10;
			totSalesTx += salesTax;
		}
		return totSalesTx;
	}

	public double calculateTotalAmount() {
		double totSalesTx = this.calculateSalesTax();
		double tot = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			double sales = lineItem.totalAmount();
			tot += sales;
		}
		tot += totSalesTx;
		return tot;
	}

}