package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import start.ExecuteTree;


public class TreePanel extends JComponent implements MouseListener{
	
	
	private static final long serialVersionUID = 1L;

	//Minimum length that stops the recursive function made of a certain number
	//of pixels
	private int MINIMUM_LENGTH = 10;
	
	//Angle use to draw each line from
	private double THETA;
	
	//Percentage to reduce the limb for each recursive call
	private float PERCENTAGE_LENGTH = (float) 0.85;
	
	//Used to ensure during repaint that the component is only painted using super()
	public boolean canClear;
	
	//Used to store the length of each trunk used during recursion
	private int LENGTHNEW = 0;

	
	//Default Constructor
	public TreePanel(ExecuteTree et){
	}
	
	/***********************************************************
	 * METHOD USED TO PAINT THE COMPONENT EACH TIME IT IS CALLED
	 ***********************************************************/

	@Override
	protected void paintComponent(Graphics g) {		//Method used to paint the custom component, called during repaint
		super.paintComponent(g);		//Calls the super method from JComponent parent class
			if(canClear){
				
				//Immediately set false the boolean value so I doesn't happen unless someone clicks the "Clear" button
				canClear = false;
			}
			else{
			System.out.println("Got here");
				
			//Sets the color of the lines
			g.setColor(Color.BLACK);
			
			//Method call to the recursive function to draw tree
			drawLimbs(new Point(
					this.getWidth()/2,
					this.getHeight()),
					LENGTHNEW,
					90,
					g);
			}
	}
	
	/**
	 * End Of paintComponent Method
	 * */

	//Method used clear the panel
	public void clearPanel(){
		canClear = true;
		this.repaint();

	}
	
	/*********************
	 * SETTERS
	 *********************/
	
	//Set the minimum length of each trunk
	public void setMinimumLength(int minLength){
		LENGTHNEW = minLength;
	}
	
	public void setAngle(double angle){
		THETA = angle;
	}
	
	/*********************
	 * GETTERS
	 *********************/
	
	//Returns the minimum length of each successive trunk
	public int getMinimumLength(){
		return MINIMUM_LENGTH;
	}
	
	//Returns the angle used for recursive tree generation
	public double getAngle(){
		return THETA;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return super.getPreferredSize();
	}
	 @Override
	public void setPreferredSize(Dimension size){
		 super.setPreferredSize(size);
	 }

	/************************
	 * MOUSE LISTENER METHODS
	 ************************/
	

	 /******************************************
	  * Used for later function related 
	  * to the user clicking the treePanel 
	  * and using that point as the starting 
	  * point
	  ******************************************/
	@Override
	public void mouseClicked(MouseEvent e) {
		//Get the number of clicks
		int numberOfClicks = e.getClickCount();
		
		//Make sure not to draw only on single clicks
		if(numberOfClicks < 2){
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	/*******************************
	 * RECURSIVE METHOD TO DRAW TREE
	 *******************************/
	public void drawLimbs(Point startingPoint, int lengthLeftOver, double theta, Graphics g){
		
		//Terminating condition where if the minimum length of the trunk is met then just return
		if(lengthLeftOver <= MINIMUM_LENGTH){
			return;
		}
		else {
			
			//Sets the color of the graphics object
			g.setColor(Color.RED);
			
			//Draws the trunk of the tree
			g.drawLine(startingPoint.x,
					startingPoint.y,
					startingPoint.x + (int)(lengthLeftOver * Math.cos(Math.toRadians(theta))),
					startingPoint.y - (int)(lengthLeftOver * Math.sin(Math.toRadians(theta)))
					);
			
			// New length is percentage of the original
			lengthLeftOver *= PERCENTAGE_LENGTH;
			
			//Saves a temp variable used for recursion in the below method calls
			double previousTheta = theta;
			
			//Change the theta to one side of the trunk
			theta+=THETA;
			
			//Call on itself again using the new parameters for a trunk on each side of the main trunk
			drawLimbs(new Point(
					(int)(startingPoint.x + lengthLeftOver * Math.cos(Math.toRadians(previousTheta))), 
					(int)(startingPoint.y - lengthLeftOver * Math.sin(Math.toRadians(previousTheta)))),
					lengthLeftOver, 
					theta,
					g);
			
			//Change the theta to the opposite side of trunk using an operation twice
			theta-=THETA;
			theta-=THETA;
			
			//Call on itself again using the new parameters
			drawLimbs(new Point(
					(int)(startingPoint.x + lengthLeftOver * Math.cos(Math.toRadians(previousTheta))), 
					(int)(startingPoint.y - lengthLeftOver * Math.sin(Math.toRadians(previousTheta)))),
					lengthLeftOver, 
					theta,
					g);
		}
	}

}
