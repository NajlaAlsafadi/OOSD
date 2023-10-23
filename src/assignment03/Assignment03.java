package assignment03;
//Najla Alsafadi
//c20312866
//oosd3 
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;


public class Assignment03 extends JFrame {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Naj's Pizzeria");
		JTextArea textArea = new JTextArea("");
		frame.add(textArea);

	
	
		JMenu ingredientsMenu = new JMenu("Toppings");
		ingredientsMenu.add(new JCheckBoxMenuItem("Salsa"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Mozzerella Cheese"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Cheddar Cheese"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Olives"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Mushrooms"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Onions"));
		ingredientsMenu.addSeparator( );
		ingredientsMenu.add(new JCheckBoxMenuItem("Chicken"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Beef"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Salami"));
		
		Set<String> meatIngredients = new HashSet<>();
		meatIngredients.add("Chicken");
		meatIngredients.add("Beef");
		meatIngredients.add("Salami");
		
		
		for (int x =0; x<=ingredientsMenu.getItemCount()-1; x++)
		{
			JCheckBoxMenuItem nextMI = (JCheckBoxMenuItem) ingredientsMenu.getItem(x);
			if (nextMI != null)	
			{
				
				
				nextMI.addItemListener(new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			         
			          textArea.append("Ingredient ["+nextMI.getText()+"] has been ["+
			        		  (nextMI.isSelected()?"checked":"unchecked")+"]!"+"\n");
			        }
			      });
			}
		}
		
		JMenu chilliMenu = new JMenu("Chilli Level");
		chilliMenu.add(new JRadioButtonMenuItem("Level 0: No chilli", null, true));
		chilliMenu.add(new JRadioButtonMenuItem("Level 1: Mild chilli"));
		chilliMenu.add(new JRadioButtonMenuItem("Level 2: Hot chilli"));
		chilliMenu.add(new JRadioButtonMenuItem("Level 3: Extra-hot chilli"));
		chilliMenu.add(new JRadioButtonMenuItem("Level 4: Habanero-hot"));
		
		ButtonGroup group = new ButtonGroup();
		
		for (int x =0; x<=chilliMenu.getItemCount()-1; x++)
		{
			JRadioButtonMenuItem nextMI = (JRadioButtonMenuItem) chilliMenu.getItem(x);
			group.add(nextMI);
				
			nextMI.addItemListener(new ItemListener() {
		        public void itemStateChanged(ItemEvent e) {
		        	if (nextMI.isSelected())
		          textArea.append("New chilli hotness level selected: ["+nextMI.getText()+"]"+"\n");
		        }
		      });

		}
		
		JMenu ordersMenu = new JMenu("Orders");
		JMenuItem prepareRegPizza = new JMenuItem("Prepare Regular Pizza");
		ordersMenu.add(prepareRegPizza);
		
		prepareRegPizza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean containsMeat = false;
				String ingredients = "";
				for (int x =0; x<ingredientsMenu.getItemCount()-1; x++)
				{
					JCheckBoxMenuItem nextMI = (JCheckBoxMenuItem) ingredientsMenu.getItem(x);
					if (nextMI != null && nextMI.isSelected())
					{
						if (ingredients.length()>0) 
							{
								ingredients +=",";
							}
						ingredients += nextMI.getText();
						if (meatIngredients.contains(nextMI.getText()))
						{
							containsMeat = true;
							break;
						}
					}
				}
				
				if (!containsMeat)
				{
					JOptionPane.showMessageDialog(frame, 
						      "No meat selected.", "Your pizza does not contain any type of meat!", 	     	
						       JOptionPane.INFORMATION_MESSAGE);
				}

				String chilliLevel = "";
		        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
		            AbstractButton button = buttons.nextElement();

		            if (button.isSelected()) {
		            	chilliLevel = button.getText();
		            }
		        }
				
				textArea.append("Preparing a regular pizza with the ingredients: ["+
						ingredients+"] and the level of chilli hotness: ["+chilliLevel+"]."+"\n");
			}});
		
		prepareRegPizza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean orderAction = true;
				String order = textArea.getText();
				if (order.equals("")) {
					orderAction=false;
				}
				if (orderAction==true) {
					
					Restaurant.runPizzeria(order);
				}
				}
		});

		
		JMenuItem prepareVeggiePizza = new JMenuItem("Prepare Veggie Pizza");
		ordersMenu.add(prepareVeggiePizza);

		prepareVeggiePizza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean containsMeat = false;
				String ingredients = "";
				for (int x =0; x<ingredientsMenu.getItemCount()-1; x++)
				{
					JCheckBoxMenuItem nextMI = (JCheckBoxMenuItem) ingredientsMenu.getItem(x);
					if (nextMI != null && nextMI.isSelected())
					{
						if (ingredients.length()>0) 
						{
							ingredients +=",";
						}
						ingredients += nextMI.getText();
						
						if (meatIngredients.contains(nextMI.getText()))
						{
							containsMeat = true;
						}
					}
				}
				prepareVeggiePizza.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean orderAction = true;
						String order = textArea.getText();
						if (order.equals("")) {
							orderAction=false;
						}
						if (orderAction==true) {
							
							Restaurant.runPizzeria(order);
						}
						}
				});
	
				String chilliLevel = "";

				Enumeration<AbstractButton> buttons = group.getElements();
		        while(buttons.hasMoreElements()) {
		            AbstractButton button = buttons.nextElement();

		            if (button.isSelected()) {
		            	chilliLevel = button.getText();
		            }
		        }
				
				if (containsMeat)
				{
					JOptionPane.showMessageDialog(frame, 
						      "Meat selected.", "Your veggie pizza contains meat!", 	     	
						       JOptionPane.ERROR_MESSAGE);
					textArea.append("Your selection of ingredients involves meat, so the veggie pizza cannot be cooked."+"\n");
				}
				else
				{
					textArea.append("Preparing a veggie pizza with the ingredients: ["+
							ingredients+"] and the level of chilli hotness: ["+chilliLevel+"]."+"\n");
				}			

			}});
		

		
		// create a menu bar and use it in this JFrame
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.add(ingredientsMenu);
		menuBar.add(chilliMenu);
		menuBar.add(ordersMenu);
		frame.setJMenuBar(menuBar);
		
	// set visibility
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,400);
		frame.setVisible(true);
	    }

				

}
class Chef extends Thread {
	
	private Restaurant restaurant;
	private Waiter waiter;
	private Order order;
	private Chef chef;
	public Chef(Restaurant r, Waiter w, Order o) {
	restaurant = r;
	waiter = w;
	order = o;
	start(); //start thread
	}
	public void run() {
	System.out.println("Chef is Alive)");
	try {
	sleep(5000);//sleep until chef is doen with food
	} catch(InterruptedException e) {
	throw new RuntimeException(e);
	}
	System.out.println("Waiter is ready with order");
	
	synchronized(waiter) {
		waiter.notify();
		}
	}
}
class Restaurant {
	private ArrayList<Order> orders = new ArrayList<>();// arraylist of orders
	public Order order;
	public Restaurant(Order o) {
	
	}
	public static void runPizzeria(String order1) {
		Order o = new Order(order1);
		Restaurant r = new Restaurant(o);
		Waiter w = new Waiter(r,o);
		Chef c = new Chef(r,w,o);
	}
}


class Waiter extends Thread {
	
	private Restaurant restaurant;
	private Order order;
	private Chef chef;
	public Waiter(Restaurant r, Order o) {
	order = o;
	restaurant = r;
	start();
	}
	public void run() {
	synchronized(this) {
	try {
   
	this.wait();
	} catch(InterruptedException e) {
	throw new RuntimeException(e);}}
	
	try {
		sleep(2000);
		} catch(InterruptedException e) {
		throw new RuntimeException(e);
		}

	}
	
	
}
class Order {
	
		private String order;
		public Order(String order) {
			this.order = order;
		}
		
		
		public String getOrder() {
			return order;
		}
		
		public void setOrder(String order) {
			this.order = order;
		}
		
		@Override
		public String toString() {
			return order + "\n";
		}
}
