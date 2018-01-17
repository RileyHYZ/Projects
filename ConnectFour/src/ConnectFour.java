import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ConnectFour extends JPanel implements ActionListener, MouseListener, KeyListener
{
    static JFrame frame;
    AudioClip backGroundSound;
    AudioClip hello;
	AudioClip goodbye;
    final int BANANA = -1;
    final int STRAWBERRY = 1;
    final int EMPTY = 0;
    int SQUARE_SIZE = 60;
    final int TOP_OFFSET = 42;
    final int BORDER_SIZE = 4;
    String music = "Press 'B' - Background Sound";

    int cc = 3;
    int[] [] board;
    int a=6,b=7;
    int currentPlayer;
    int currentColumn;
    Image firstImage, secondImage, bg;
    
    Timer timer;

    // For drawing images offScreen (prevents Flicker)
    // These variables keep track of an off screen image object and
    // its corresponding graphics object
    Image offScreenImage;
    Graphics offScreenBuffer;

    boolean gameOver;

    public ConnectFour ()
    {
    	hello = Applet.newAudioClip (getCompleteURL ("hello.wav"));
		goodbye = Applet.newAudioClip (getCompleteURL ("goodbye.wav"));
		backGroundSound = Applet.newAudioClip (getCompleteURL ("background.wav"));
		
		
		bg =  Toolkit.getDefaultToolkit ().getImage ("image.jpg");
		
		
	    // Setting the defaults for the panel
	    setPreferredSize (new Dimension ((a+1) * SQUARE_SIZE + 2 * BORDER_SIZE + 1, b * SQUARE_SIZE + TOP_OFFSET + BORDER_SIZE + 1));
	    setLocation (100, 10);
	    setBackground (Color.white);
	    setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));

	    board = new int [8] [9];

	    // Set up the Menu
	    // Set up the MenuItems
	    JMenu gameMenu, levelMenu, helpMenu;
		JMenuItem beginnerOption, intermediateOption, advancedOption, aboutOption;
	    JMenuItem newOption, exitOption;
	   
	    // Set up the Game Menu
	    gameMenu = new JMenu ("Game");
	    newOption = new JMenuItem ("New");
	    exitOption = new JMenuItem ("Exit");
	    // Set up the Level Menu
	    beginnerOption = new JMenuItem ("Beginner");
		intermediateOption = new JMenuItem ("Intermediate");
		advancedOption = new JMenuItem ("Advanced");
		levelMenu = new JMenu ("Level");
		// Set up the Help Menu
		aboutOption = new JMenuItem ("About...");
		helpMenu = new JMenu ("Help");
		
	    // Add each MenuItem to the Game Menu (with a separator)
	    gameMenu.add (newOption);
	    gameMenu.addSeparator ();
	    gameMenu.add (exitOption);
	    levelMenu.add (beginnerOption);
	    levelMenu.addSeparator ();
		levelMenu.add (intermediateOption);
		levelMenu.addSeparator ();
		levelMenu.add (advancedOption);
		helpMenu.add (aboutOption);

	    JMenuBar mainMenu = new JMenuBar ();
	    mainMenu.add (gameMenu);
	    mainMenu.add (levelMenu);
	    mainMenu.add (helpMenu);
	    
	    // Set the menu bar for this frame to mainMenu
	    frame.setJMenuBar (mainMenu);

	    // Use a media tracker to make sure all of the images are
	    // loaded before we continue with the program
	    MediaTracker tracker = new MediaTracker (this);
	    firstImage = Toolkit.getDefaultToolkit ().getImage ("banana.gif");
	    tracker.addImage (firstImage, 0);
	    secondImage = Toolkit.getDefaultToolkit ().getImage ("strawberry.gif");
	    tracker.addImage (secondImage, 1);
	    

	    //  Wait until all of the images are loaded
	    try
	    {
	        tracker.waitForAll ();
	    }
	    catch (InterruptedException e)
	    {
	    }

	    // Set up the icon image (Tracker not needed for the icon image)
	    Image iconImage = Toolkit.getDefaultToolkit ().getImage ("banana.gif");
	    frame.setIconImage (iconImage);

	    // Start a new game and then make the window visible
	    newGame ();

	    newOption.setActionCommand ("New");
	    newOption.addActionListener (this);
	    exitOption.setActionCommand ("Exit");
	    exitOption.addActionListener (this);
	    aboutOption.setActionCommand ("About");
		aboutOption.addActionListener(this);
		
		beginnerOption.setActionCommand ("Beginner");
		beginnerOption.addActionListener(this);
		intermediateOption.setActionCommand ("Intermediate");
		intermediateOption.addActionListener(this);
		advancedOption.setActionCommand ("Advanced");
		advancedOption.addActionListener(this);

	
	    frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing (java.awt.event.WindowEvent windowEvent) {
		    	goodbye.play ();
				try
				{
					Thread.sleep (1200);
				}
				catch (InterruptedException e)
				{
				}
		    	System.exit(0);
		    }   
		});		
        // Play "hello" when program starts
		hello.play ();
		
	    setFocusable (true); // Need this to set the focus to the panel in order to add the keyListener
	    addKeyListener (this);

	    addMouseListener (this);

    } // Constructor


    private void addImage(Image backImage2) {
    	
	}


	public URL getCompleteURL (String fileName)
	{
		try
		{
			return new URL ("file:" + System.getProperty ("user.dir") + "/" + fileName);
		}
		catch (MalformedURLException e)
		{
			System.err.println (e.getMessage ());
		}
		return null;
	}
    
    // To handle normal menu items
    public void actionPerformed (ActionEvent event)
    {
	    String eventName = event.getActionCommand ();
	    if (eventName.equals ("New"))
	    {
	       newGame ();
	    }
	    else if (eventName.equals ("Exit"))
	    {
	        System.exit (0);
	    }
	    if (eventName.equals ("About"))
			JOptionPane.showMessageDialog (frame, (Object) "The object of the game is to get 4 in a row horizontally, vertically or diagonally. ", "About...", JOptionPane.INFORMATION_MESSAGE);			
	    
	    if (eventName.equals ("Beginner")){
	    	cc=3;
	    	newGame ();
	    	board = new int [8] [9];
	    	a = 6;
	    	b = 7;
	    	SQUARE_SIZE = 60;
	    }
	    else if (eventName.equals ("Intermediate")){
	    	cc=4;
	    	newGame ();
	    	board = new int [11] [12];
	    	a = 9;
	    	b = 10;
	    	SQUARE_SIZE = 42;
	    }
	    else if (eventName.equals ("Advanced")){
	    	cc=5;
	    	newGame ();
	    	board = new int [13] [14];
	    	a = 11;
	    	b = 12;
	    	SQUARE_SIZE = 35;
	    }

    }


    public void newGame ()
    {
	    currentPlayer = BANANA;
	    clearBoard (board);
	    gameOver = false;
	    currentColumn = cc;
	    repaint ();
    }
    
//------------YOUR CODE GOES HERE  ------------------//

    /*description: the method clears the board
     * parameter: the piece in every coordinates
     * return: -
     */
    public void clearBoard (int[] [] board)
    {
    	for(int i = 0; i <= a; i++){
    		for(int o = 0; o <= b; o++)
    			board[i][o] = EMPTY;
    	}
    }

    /*description: the method finds the next empty row to put the piece down
     * parameter: the piece in every coordinates, number of column
     * return: the number of next row
     */
    public int findNextRow (int[] [] board, int column)
    {
    	int row = a;
    	while (board[row][column] != EMPTY){
    		row--;
    	}
        return row;
    }

    /*description: the method checks who wins the game
     * parameter: the piece in every coordinates, the numbers of row and column of the last piece
     * return: the winner
     */
    public int checkForWinner (int[] [] board, int lastRow, int lastColumn)
    {
    	int countV = 1, countH = 1, countD1 =1, countD2 =1;
    	int r = lastRow, c = lastColumn;;
    	while(r<=a && board[r+1][lastColumn]==board[r][lastColumn]){
    		r++;
    		countV++;
    	}//Vertically
    	
    	while(c>1 && board[lastRow][c-1]==board[lastRow][c]){
    		c--;
    		countH++;
    	}
    	c = lastColumn;
    	while(c<=b && board[lastRow][c+1]==board[lastRow][c]){
    		c++;
    		countH++;
    	}//Horizontal
    	
    	r = lastRow;
    	c = lastColumn;
    	while(r<=a && c<=b && board[r+1][c+1]==board[r][c]){
    		r++;
    		c++;
    		countD1++;
    	}
    	r = lastRow;
    	c = lastColumn;
    	while(r>1 && c>1 && board[r-1][c-1]==board[r][c]){
    		r--;
    		c--;
    		countD1++;
    	}//Diagonally(upper left to lower right)
    
    	r = lastRow;
    	c = lastColumn;
    	while(r<=a && c>1 && board[r+1][c-1]==board[r][c]){
    		r++;
    		c--;
    		countD2++;
    	}
    	r = lastRow;
    	c = lastColumn;
    	while(r>1 && c<=b && board[r-1][c+1]==board[r][c]){
    		r--;
    		c++;
    		countD2++;
    	}//Diagonally(upper right to lower left)
    	 
    	
    	int winner = 0;
    	if((countV==4 || countH >= 4 || countD1 >= 4 || countD2 >=4) && board[lastRow][lastColumn]==BANANA)
    		winner = BANANA;
    	else if((countV==4 || countH >= 4 || countD1 >= 4 || countD2 >= 4) && board[lastRow][lastColumn]==STRAWBERRY)
    		winner = STRAWBERRY;
    	return winner;
    }

//----------------------------------------------------//


    public void handleAction (int x, int y)
    {
    	if (gameOver)
    	{
    	    JOptionPane.showMessageDialog (this, "Please Select Game...New to start a new game",
    		    "Game Over", JOptionPane.WARNING_MESSAGE);
    	    return;
    	}

    	int column = (x - BORDER_SIZE) / SQUARE_SIZE + 1;
    	int row = findNextRow (board, column);
    	if (row <= 0)
    	{
    	    JOptionPane.showMessageDialog (this, "Please Select another Column",
    		    "Column is Full", JOptionPane.WARNING_MESSAGE);
    	    return;
    	}

    	animatePiece (currentPlayer, column, row);
    	board [row] [column] =  currentPlayer;

    	int winner = checkForWinner (board, row, column);

    	if (winner == BANANA)
    	{
    	    gameOver = true;
    	    repaint ();
    	    JOptionPane.showMessageDialog (this, "Banana Wins!!!",
    		    "GAME OVER", JOptionPane.INFORMATION_MESSAGE);

    	}
    	else if (winner == STRAWBERRY)
    	{
    	    gameOver = true;
    	    repaint ();
    	    JOptionPane.showMessageDialog (this, "Strawberry Wins!!!",
    		    "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else
    	    // Switch to the other player
    	    currentPlayer *= -1;
    	    currentColumn = cc;

    	    repaint ();
    }


    // MouseListener methods
    public void mouseClicked (MouseEvent e)
    {
	    int x, y;
	    x = e.getX ();
	    y = e.getY ();

	    handleAction (x, y);
    }


    public void mouseReleased (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {
    }


    //KeyListener methods
    public void keyPressed (KeyEvent kp)
    {
    	char key = Character.toUpperCase (kp.getKeyChar ());
    	if (key == 'B') {
			music = "Background music playing  -  Press 'S' to stop";
			backGroundSound.loop ();
			repaint();
    	}
    	else if (key == 'S'){
			music = "Press 'B' - Background Sound";
			backGroundSound.stop ();
			repaint();
		}	
    	
    	if (kp.getKeyCode () == KeyEvent.VK_RIGHT)
	    {
	        if (currentColumn < a)
		        currentColumn++;
	    }
	    else if (kp.getKeyCode () == KeyEvent.VK_DOWN)
	    {
	        handleAction ((currentColumn) * SQUARE_SIZE + BORDER_SIZE, 0);
	    }
	    else if (kp.getKeyCode () == KeyEvent.VK_LEFT)
	    {
	        if (currentColumn > 0)
		    currentColumn--;
	    }
	    else
	        return;
	repaint ();
    }


    public void keyReleased (KeyEvent e)
    {
    }


    public void keyTyped (KeyEvent e)
    {
    }


    public void animatePiece (int player, int column, int finalRow)
    {
	Graphics g = getGraphics ();
	
	// Find the x and y positions for each row and column
	int xPos = (4 - 1) * SQUARE_SIZE + BORDER_SIZE;
	int yPos = TOP_OFFSET + 0 * SQUARE_SIZE;
	offScreenBuffer.clearRect (xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);
	for (double row = 0 ; row < finalRow ; row += 0.10)
	{
		
	    // Find the x and y positions for each row and column
	    xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
	    yPos = (int) (TOP_OFFSET + row * SQUARE_SIZE);
	    // Redraw the grid for this column
	    for (int gridRow = 1 ; gridRow <= a ; gridRow++)
	    {
		// Draw the squares
		offScreenBuffer.setColor (Color.black);
		offScreenBuffer.drawRect ((column - 1) * SQUARE_SIZE + BORDER_SIZE,
			TOP_OFFSET + gridRow * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
	    }

	    // Draw each piece, depending on the value in board
	    if (player == BANANA)
		offScreenBuffer.drawImage (firstImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
	    else if (player == STRAWBERRY)
		offScreenBuffer.drawImage (secondImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
    
	    // Transfer the offScreenBuffer to the screen
	    g.drawImage (offScreenImage, 0, 0, this);
	    delay (3);
	    offScreenBuffer.clearRect (xPos + 1, yPos + 1, SQUARE_SIZE - 2, SQUARE_SIZE - 2);
	    }
	    
    }


    // Avoid flickering -- smoother graphics
    public void update (Graphics g)
    {
	    paint (g);
    }


    public void paintComponent (Graphics g)
    {
    
	// Set up the offscreen buffer the first time paint() is called
	if (offScreenBuffer == null)
	{
	    offScreenImage = createImage (this.getWidth (), this.getHeight ());
	    offScreenBuffer = offScreenImage.getGraphics ();
	}

	// All of the drawing is done to an off screen buffer which is
	// then copied to the screen.  This will prevent flickering
	// Clear the offScreenBuffer first
	offScreenBuffer.clearRect (0, 0, this.getWidth (), this.getHeight ());
	offScreenBuffer.drawImage(bg, 0, 0, this);  
	// Redraw the board with current pieces
	for (int row = 1 ; row <= a ; row++)
	    for (int column = 1 ; column <= b ; column++)
	    {
		// Find the x and y positions for each row and column
		int xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
		int yPos = TOP_OFFSET + row * SQUARE_SIZE;

		// Draw the squares
		offScreenBuffer.setColor (Color.black);
		offScreenBuffer.drawRect (xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);
		offScreenBuffer.drawString(music, 20, 20);

		// Draw each piece, depending on the value in board
		if (board [row] [column] == BANANA)
		    offScreenBuffer.drawImage (firstImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
		else if (board [row] [column] == STRAWBERRY)
		    offScreenBuffer.drawImage (secondImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
	    }

	// Draw next player
	if (!gameOver)
	    if (currentPlayer == BANANA)
		offScreenBuffer.drawImage (firstImage, currentColumn * SQUARE_SIZE + BORDER_SIZE, TOP_OFFSET, SQUARE_SIZE, SQUARE_SIZE, this);
	    else
		offScreenBuffer.drawImage (secondImage, currentColumn * SQUARE_SIZE + BORDER_SIZE, TOP_OFFSET, SQUARE_SIZE, SQUARE_SIZE, this);

	// Transfer the offScreenBuffer to the screen
	g.drawString(music, 20, 20);
	g.drawImage (offScreenImage, 0, 0, this);
	
    }

    /** Purpose: To delay the given number of milliseconds
     * @param milliSec The number of milliseconds to delay
     */
    private void delay (int milliSec)
    {
	try
	{
	    Thread.sleep (milliSec);
	}
	catch (InterruptedException e)
	{
	}
    }

    
    public static void main (String[] args)
    {
	    frame = new JFrame ("Connect Four");
	    ConnectFour myPanel = new ConnectFour ();

	    frame.add (myPanel);
	    frame.pack ();
	    frame.setVisible (true);

    }
} 

