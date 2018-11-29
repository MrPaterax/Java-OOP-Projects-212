import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

import java.io.File;
import java.io.FileNotFoundException;

public class FileMenuHandler implements ActionListener 
{

    // Save the reference to the gui object this FileMenuHandler is 
    // associated with
    private DateGUI gui;
   
    // Constructor that takes as its paramaeter the gui associated 
    // with this FileMenuHandler
    public FileMenuHandler(DateGUI gui) 
    {
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        // Get the command name from the event
        String menuName = event.getActionCommand();
        

        if (menuName.equals("Open")) {
            
            // Create the object that will choose the file
            JFileChooser fc = new JFileChooser();

            // Attempt to open the file
            int returnVal = fc.showOpenDialog(null);

            // If user selected a file, create File object and pass it to
            // the gui's fileHandler method
            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                try {
                    File file = fc.getSelectedFile();
                    this.gui.fileHandler(file);
                }
                catch(FileNotFoundException e) {
                    e.printStackTrace();
                }
            } 
            else if (returnVal == JFileChooser.CANCEL_OPTION)
            {
                System.out.println("Open command cancelled by user.");
            }
        } 
        else if (menuName.equals("Quit"))
        {
            JOptionPane.showMessageDialog(null,"You clicked on Quit");
            System.exit(1);
        } 
    } 
}