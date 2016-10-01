package start;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import components.TreePanel;


public class ExecuteTree extends JFrame implements ActionListener{

	//Serial needed for class
	private static final long serialVersionUID = 1L;
	
	//Minimum length that stops the recursive function made of a certain number
	//of pixels
	int MINIMUM_LENGTH;
	
	//Panels that are 
	public TreePanel treePanel;
	public JPanel parametersPanel;
	
	//TextField
	JTextField angleTextField;
	JTextField lengthTextField;
	
	/**************************************
	 ******                           *****
	 ******   		END FIELDS        *****
	 ******   				          *****
	 **************************************/
	
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	//
	//
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	
	/**************************************
	 ******                           *****
	 ******     BEGIN CONSTRUCTORS    *****
	 ******   				          *****
	 **************************************/
	
	public ExecuteTree(){
		
		//Sets the way the frame closes
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Populates the left side with components the user
		//interacts with
		populateLeftSide();
		
		//Sets the screen in the middle of the screen
		this.setLocationRelativeTo(null);
		
		//Set a title for the JFrame
		this.setTitle("Recursive Tree");
		
		//Makes the frame visible to user
		this.setVisible(true);
		
		//Packs the frame size according to the preferred size
		//specs for each component
		this.pack();
	}
	
	/**************************************
	 ******                           *****
	 ******      END CONSTRUCTOR      *****
	 ******   				          *****
	 **************************************/
	
	
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	//
	//
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	
	
	/**************************************
	 ******                           *****
	 ******   BEGIN GENERAL METHODS   *****
	 ******   				          *****
	 **************************************/
	
	private void populateLeftSide(){
		
		//The panel used to change the parameters
		parametersPanel = new JPanel();
		
		//Set the layout to add the 4 components to the panel
		parametersPanel.setLayout(new GridLayout(4,1));
		
		//Create the input elements to add to the panel
		JButton clearPanel = new JButton("Clear Button");
		JButton computeButton = new JButton("Draw");
		
		//Create a titled border JPanel for angle text field
		angleTextField = new JTextField();
		angleTextField.setBorder(new TitledBorder("Angle (Degrees)"));
		
		//Create a titled border JPanel for length of tree field
		lengthTextField = new JTextField();
		lengthTextField.setBorder(new TitledBorder("Length Of Tree"));
		
		//Add actionListeners to each button to trigger events
		clearPanel.addActionListener(this);
		computeButton.addActionListener(this);
		
		//Add the components to panel
		parametersPanel.add(clearPanel);
		parametersPanel.add(computeButton);
		parametersPanel.add(angleTextField);
		parametersPanel.add(lengthTextField);
		parametersPanel.setMaximumSize(new Dimension(150,400));
		parametersPanel.setPreferredSize(new Dimension(100,300));
		
		//The panel that draws the tree
		treePanel = new TreePanel(this);
		
		
		//Make sure to set a preferred size otherwise it won't redraw
		treePanel.setPreferredSize(new Dimension(400,400));
		
		//Now combine the two components into the single JFrame
		this.setLayout(new BorderLayout());
		this.add(parametersPanel, BorderLayout.WEST);
		this.add(treePanel, BorderLayout.CENTER);
	}
	
	/**************************************
	 ******                           *****
	 ******    END GENERAL METHODS    *****
	 ******   				          *****
	 **************************************/
	
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	//
	//
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	
	/**************************************
	 ******                           *****
	 ******   		MAIN METHOD       *****
	 ******   				          *****
	 **************************************/
	
	public static void main(String[] args){
		ExecuteTree executeTree = new ExecuteTree();
		executeTree.setResizable(true);		//Make sure the user can change the dimensions of the JFrame
	}
	
	/**************************************
	 ******                           *****
	 ******   	 END MAIN METHOD      *****
	 ******   				          *****
	 **************************************/
	
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	//
	//
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------

	/**************************************
	 ******                           *****
	 ******   BEGIN ACTION LISTENER   *****
	 ******   				          *****
	 **************************************/
	
	@Override
	public void actionPerformed(ActionEvent click) {		//Listens to button clicks
		
		//Find the component that triggered the event
		JButton btnClicked = (JButton) click.getSource();
		
		//String of the button that is clicked
		String btnName = btnClicked.getText();
		
		//Tests if the "Clear" button is pressed
		if(btnName.equals("Clear Button")){
			treePanel.clearPanel();
		}
		
		//Tests if the "Draw" button is pressed
		else{
			
			//If the textfield aren't blank then continue with drawing
			if(!blankTextFields()){
				
				//
				// Create variables to use in recursion  drawLimbs(Point pt, int Length, float theta)
				// Point pt = to start drawing from ( by defualt it is the middle of the screen starting on the bottom)
				// int Length =  the length to draw the line to
				// float theta = the angle to extend the line from the starting Point
				//
				
				//The length is the one provided from the textfield earlier
				int length = Integer.valueOf(lengthTextField.getText());
				
				//Then angle to start the angle from, let's start from zero
				double theta = (double) Double.valueOf(angleTextField.getText());
				
				//Load the parameters before calling on function to redraw
				treePanel.setMinimumLength(length);
				treePanel.setAngle(theta);
			
				//Call on JComponent to redraw itself
				treePanel.repaint();
			}
			
		}
		
	}
	
	/**************************************
	 ******                           *****
	 ******    END ACTION LISTENER    *****
	 ******   				          *****
	 **************************************/
	
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	//
	//
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	
	
	/**************************************
	 ******                           *****
	 ******   BEGIN PRIVATE METHODS   *****
	 ******   				          *****
	 **************************************/
	
	//Check to see if there is a blank field to continue further
	private boolean blankTextFields() {
		
		//Variable used to return to the calling function
		boolean txtLength = false;
		
		//The length is the one provided from the textfield earlier
		int length = Integer.valueOf(lengthTextField.getText());
		
		//Then angle to start the angle from, let's start from zero
		double theta = (double) Double.valueOf(angleTextField.getText());
		
		//Test the angle textfield
		if(angleTextField.getText().length() < 1){
			txtLength = true;
		} else{
			if(theta > 35) txtLength = true;
		}
		
		//Test the length textfield
		if(lengthTextField.getText().length() < 1){
			txtLength = true;
		} else {
			if(length > 250) txtLength = true;
		}
		
		//return the boolean result
		return txtLength;
	}
	
	/**************************************
	 ******                           *****
	 ******    END PRIVATE METHODS    *****
	 ******   				          *****
	 **************************************/
	
	
}
