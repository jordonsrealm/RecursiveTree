package components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import start.ExecuteTree;


public class TreePanel extends JComponent implements MouseListener{
	
	
	private static final long serialVersionUID = 1L;

	//Minimum length that stops the recursive function made of a certain number of pixels
	private int MINIMUM_LENGTH = 20;
	
	//Angle use to draw each line from
	private double THETA;
	
	//Percentage to reduce the limb for each recursive call
	private float PERCENTAGE_LENGTH = (float) 0.85;
	
	//Used to ensure during repaint that the component is only painted using super()
	public boolean canClear;
	
	//Used to store the length of each trunk used during recursion
	private int LENGTHNEW = 0;
	
	//This is a counter used to place apples at a set interval and not every single branch
	int appleNodeCounter;
	
	//Images used when drawing the tree completely
	private Image apple, leaf;

	
	//Default Constructor
	public TreePanel(ExecuteTree et){
		try {
			leaf = ImageIO.read(new File("res/20_leaf.png"));
			apple = ImageIO.read(new File("res/20_apple.png"));
		} catch (IOException e) {
		}
		
	}
	
	/***********************************************************
	 * METHOD USED TO PAINT THE COMPONENT EACH TIME IT IS CALLED
	 ***********************************************************/

	//Method used to paint the custom component, called during repaint
	@Override
	protected void paintComponent(Graphics g) {
		
		//Calls the super method from JComponent parent class
		super.paintComponent(g);
		
			//See if the canClear boolean value is set to true
			if(canClear){
				
				//Immediately set false the boolean value so I doesn't happen unless someone clicks the "Clear" button
				canClear = false;
			}
			else{
			
			//Method call to the recursive function to draw tree
			drawLimbs(new Point(		//New Point that is in the center of the screen and at the bottom of it
								this.getWidth()/2,		// Sets the x point to the middle of the of the x-axis
								this.getHeight()),		// Sets the y point to the bottom of the screen or y-axis
								LENGTHNEW,		//This is the starting trunk length that the user inputs in the textfield
								90,		//This is the starting angle which the main trunk starts out as and then uses the angle inputted by user
								(Graphics2D)g		//This is used to draw "thick" lines to mimic an actual trunk
								);		
			}
	}
	
	/**
	 * End Of paintComponent Method
	 * */

	//Method used clear the panel
	public void clearPanel(){
		
		//Set the boolean canClear value to true
		canClear = true;
		
		//Repaint the component
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
		
		//Return the angle
		THETA = angle;
	}
	
	/*********************
	 * GETTERS
	 *********************/
	
	//Returns the minimum length of each successive trunk
	public int getMinimumLength(){
		
		//Return the minimum length set for the tree
		return MINIMUM_LENGTH;
	}
	
	//Returns the angle used for recursive tree generation
	public double getAngle(){
		
		//Return the angle set to draw the limbs
		return THETA;
	}
	
	@Override
	public Dimension getPreferredSize() {
		
		//Return the value called from super method
		return super.getPreferredSize();
	}
	 @Override
	public void setPreferredSize(Dimension size){
		 
		 //Return the value called form super method
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
	public void drawLimbs(Point startingPoint, int lengthLeftOver, double theta, Graphics2D g){
		
		//Terminating condition where if the minimum length of the trunk is met then just return
		if(lengthLeftOver <= MINIMUM_LENGTH){
			
			/**End the recursion with drawing leaves on the ends of it*/
				g.drawImage(leaf,
							startingPoint.x, 
							startingPoint.y, 
							null);
				
				/*g.drawImage(leaf, 
						    startingPoint.x + (int)(lengthLeftOver/10 * Math.cos(Math.toRadians(theta))), 
							startingPoint.y - (int)(lengthLeftOver/10 * Math.sin(Math.toRadians(theta))), 
							null);*/
				
				//If the mod of the appleNodeCounter is zero then place an apple there
				if((appleNodeCounter % 30) == 0){
					//Draw an apple on this particular limb
					g.drawImage(apple, 
								startingPoint.x, 
								startingPoint.y, 
								null);
				}
				
				
				//Used to count the number of nodes in which we want to put apples on
				appleNodeCounter++;
			
			return;
		}
		else {
			
			//Sets the color of the graphics object to brown to make it look more real
			g.setColor(new Color(91, 65, 40));
			
			//Set the stroke width for the new "smaller limb"
			g.setStroke((new BasicStroke(lengthLeftOver / 25)));
			
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
			
			
			//
			// Edited
			//
			double randomNum = Math.random();
			
			//Call on itself again using the new parameters for a trunk on each side of the main trunk
			drawLimbs(new Point(
						  (int)(startingPoint.x + (lengthLeftOver * randomNum) * Math.cos(Math.toRadians(previousTheta))),
						  (int)(startingPoint.y - (lengthLeftOver * randomNum) * Math.sin(Math.toRadians(previousTheta)))),
					      		lengthLeftOver,
					      		theta,
					      		g
					      		);
			
			//Change the theta to the opposite side of trunk using an operation twice
			theta-=THETA;
			theta-=THETA;
			
			//
			// Edited
			//
			randomNum = Math.random();
			
			//Call on itself again using the new parameters
			drawLimbs(new Point(
						  (int)(startingPoint.x + (lengthLeftOver * randomNum) * Math.cos(Math.toRadians(previousTheta))), 
						  (int)(startingPoint.y - (lengthLeftOver * randomNum) * Math.sin(Math.toRadians(previousTheta)))),
						  		lengthLeftOver, 
						  		theta,
						  		g
						  		);
		}
	}

}
