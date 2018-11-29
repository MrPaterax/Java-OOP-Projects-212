import java.awt.*;
import javax.swing.*;

import java.io.FileNotFoundException;
import java.io.File;

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.StringTokenizer;

 class DateGUI extends JFrame {
    static TextArea textAreaForDates;
    static TextArea textAreaForSortedDates;
    public DateGUI() {
	
        super("Date Manipulator");
	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,500)); //Set preferred size works more efficiently compared to setSize.
        setLocation(0, 0);
        setLayout(new BorderLayout(20, 0)); // set the layout

        // Create file menu objects
        JMenuItem open = new JMenuItem("Open");
        JMenuItem quit = new JMenuItem("Quit");
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        FileMenuHandler fmh = new FileMenuHandler(this);
        
        // Add the action listener to the menu items 
        open.addActionListener(fmh);
        quit.addActionListener(fmh);
        
        // Add the menu items to the file menu
        fileMenu.add(open);
        fileMenu.addSeparator();
        fileMenu.add(quit);

        // Add file menu to the menu bar, and set this gui's
        // menu bar to the menuBar we created above
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        Container myContentPane = getContentPane(); //Container to hold the text area. 
        textAreaForDates = new TextArea();
        textAreaForSortedDates = new TextArea();
        myContentPane.add(textAreaForDates, BorderLayout.WEST); //Adds text to content Pane
        myContentPane.add(textAreaForSortedDates, BorderLayout.EAST);
		
		pack();
		setVisible(true);
	}

    public static void fileHandler(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        
        ArrayList<StringTokenizer> datesPreParsed = new ArrayList<>(); //Arraylist for dates to be parsed by the tokenizer 
        UnsortedDate212List dates = new UnsortedDate212List(); //list for dates parsed in Date212 object
        SortedDate212List datesOrdered = new SortedDate212List(); //list for dates to in order in Date212 object.
        
        parsingString(scanner, datesPreParsed, dates, datesOrdered); //parsing our file with tokenizer and returning Date212 Object Lists.
        
        try {
            if(!file.exists()) { // Throws exception if the file doesn't exist.
                throw new FileNotFoundException();
            }
        }
        
        catch (FileNotFoundException e) {  // catch FileNotFoundException and print stack trace
            e.printStackTrace();
        }

        for (Date212Node i =  dates.head.next; i != null; i = i.next) { //Start off with our next node from the dummy node, then loop through
            if(i.data.validDate) { //If the date is valid, add to the GUI
                textAreaForDates.append(i.data.toString() + "\n");
            }
        }

        for (Date212Node i =  datesOrdered.head.next; i != null; i = i.next){ //Second loop to get our ordered list added.
                if(i.data.validDate) { 
                        textAreaForSortedDates.append(i.data.toString() + "\n");
                }
        }
    }

    //Parsing the stringtokenizer arraylist and assigning it ito the Date212 Lists
    public static void parsingString(Scanner scanner, ArrayList<StringTokenizer> datesPreParsed, UnsortedDate212List dates, SortedDate212List datesOrdered) {
        int numOfLine = 0;
        String line;
        String token;
        while(scanner.hasNextLine()) { // Checks if our next line is empty and returns false when there is no longer a next line 
            line = scanner.nextLine();
            datesPreParsed.add(new StringTokenizer(line, ",")); //adds new Tokenizer per line to our arraylist by separating by comma.
            
            /*While loop keeps running until there are no more tokens per line and we use get()
            to run through our arraylist of stringtokenizers. */
            while(datesPreParsed.get(numOfLine).hasMoreTokens()) { 
                token = datesPreParsed.get(numOfLine).nextToken();
                dates.add(new Date212(token)); //datesPreParsed have tokens that are strings, they get added in per token per line.
                datesOrdered.add(new Date212(token)); //Same thing done to DatesOrdered
            }
            numOfLine++; //Used to keep track of what line we're in our datesPreParsed Arraylist to loop through.
        }
    }
}