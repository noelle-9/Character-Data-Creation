// Name:Noelle Dacayo
// Date: April 06, 2023
// App Name: CharacterCreation
// Description: An app that loads/saves character data to a file

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class CharacterCreation {
    static JFrame window;
    static JPanel panel;
    static JLabel nameLabel;
    static JLabel levelLabel;
    static JLabel paddingLabel;
    static JLabel paddingLabel2;
    static JTextField nameTextField;
    static GridBagConstraints gridbag;
    static JSpinner levelSpinner;
    static JButton saveButton, loadButton;

    /**
     * Executed when [Save] button is clicked
     * Shows an error in case the user doesn't have a name
     * Save the character in a save file
     */
    static void saveClick() {
        // Variables
        String name = nameTextField.getText();
        int level = (int) levelSpinner.getValue(); // (int) Explicit conversion
        String fileName;
        FileWriter fileWriter;
        File file;

        // Error in case the character name is empty
        if (name.equals("")) {
            JOptionPane.showMessageDialog(window, "Character name cannot be left empty!", "Error!",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            // Format the file name
            // Ada Wong -> Ada.save
            fileName = name.split(" ")[0] + ".save";

            // Create a new file
            file = new File(fileName);

            try {
                // Prepare to write in the file
                fileWriter = new FileWriter(file);

                // Start writing in the file
                fileWriter.write(name + "\n" + level);

                // Finished writing, close the file
                fileWriter.close();

                // Tell the user that the character was saved in the file.save
                JOptionPane.showMessageDialog(window, "Character Saved as: " + fileName, "Save Complete!",
                        JOptionPane.INFORMATION_MESSAGE);

                // Error in case we can't write in the file
            } catch (Exception e) {
                JOptionPane.showMessageDialog(window, "Cannot write in the file: " + fileName, "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }

            // Clear the GUI
            nameTextField.setText("");
            levelSpinner.setValue(0);

        }

    }

    /**
     * Executes when the [Load] button is pressed
     * Open a File Chooser Dialogue
     * Read information from the file
     * Populate the GUI with said informaiton
     **/
    static void loadClick() {
        JFileChooser fileChooser;
        File file;
        Scanner fileScanner;
        String characterName = "";
        int characterLevel = 0;

        // Creating a fileChooser dialogue
        fileChooser = new JFileChooser("."); // . means current directory

        // File filter that only shows files ".save"
        fileChooser.setFileFilter(new FileNameExtensionFilter("Saved Characters", "save"));

        // Window is the parent component
        // User chose a file and clicked [open]
        if (fileChooser.showOpenDialog(window) == JFileChooser.APPROVE_OPTION) {
            // Opens the selected file
            file = fileChooser.getSelectedFile();

            try {
                // Start reading the file
                fileScanner = new Scanner(file);

                // Read data from the file
                characterName = fileScanner.nextLine();
                characterLevel = fileScanner.nextInt();

                // Finished reading the file
                fileScanner.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(window, "Could not open " + file.getName());
            }

            // Update the GUI
            nameTextField.setText(characterName);
            levelSpinner.setValue(characterLevel);
        }

    }

    public static void main(String[] args) {

        window = new JFrame("Character Creation - Noelle Dacayo");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setIconImage(new ImageIcon("leon.png").getImage());
        window.setResizable(false);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Colours
        Color Dgreen = new Color(49, 61, 50);
        Color Dcream = new Color(186, 183, 149);
        Color textbox = new Color(23, 31, 24);
        Color outline = new Color(128, 117, 88);

        Font labels = new Font("Monospaced", Font.PLAIN, 20);
        Font buttons = new Font("Monospaced", Font.PLAIN, 16);
        Font text = new Font("Monospaced", Font.PLAIN, 18);
        Font level = new Font("Monospaced", Font.PLAIN, 35);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PANEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(outline, 8, true));
        gridbag = new GridBagConstraints();
        panel.setBackground(Dgreen);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // NAME LABEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        nameLabel = new JLabel("CHARACTER NAME: ");
        nameLabel.setFont(labels);
        nameLabel.setOpaque(true);
        nameLabel.setForeground(Dcream);
        nameLabel.setBackground(textbox);
        nameLabel.setBorder(BorderFactory.createLineBorder(outline, 2, true));

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PADDING LABEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        paddingLabel = new JLabel("");
        paddingLabel.setFont(labels);
        paddingLabel.setOpaque(true);
        paddingLabel.setBackground(textbox);
        paddingLabel.setPreferredSize(new Dimension(455, 105));
        paddingLabel.setBorder(BorderFactory.createLineBorder(outline, 8, true));

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // NAME TEXTFIELD
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        nameTextField = new JTextField();
        // nameTextField.setColumns(40);
        nameTextField.setBackground(textbox);
        nameTextField.setBorder(BorderFactory.createLineBorder(outline, 3, true));
        nameTextField.setForeground(Dcream);
        nameTextField.setPreferredSize(new Dimension(430, 45));
        nameTextField.setFont(text);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // LEVEL LABEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        levelLabel = new JLabel("CHARACTER LEVEL: ");
        levelLabel.setFont(labels);
        levelLabel.setOpaque(true);
        levelLabel.setForeground(Dcream);
        levelLabel.setBackground(textbox);
        levelLabel.setBorder(BorderFactory.createLineBorder(outline, 2, true));

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PADDING2 LABEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        paddingLabel2 = new JLabel("");
        paddingLabel2.setFont(labels);
        paddingLabel2.setOpaque(true);
        paddingLabel2.setForeground(Dcream);
        paddingLabel2.setBackground(textbox);
        paddingLabel2.setPreferredSize(new Dimension(455, 105));
        paddingLabel2.setBorder(BorderFactory.createLineBorder(outline, 8, true));

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // LEVEL SPINNER
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        levelSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        levelSpinner.getEditor().getComponent(0).setForeground(Dcream);
        levelSpinner.setFont(level);
        levelSpinner.getEditor().getComponent(0).setBackground(textbox);
        levelSpinner.getComponent(0).setBackground(Dcream);
        levelSpinner.getComponent(1).setBackground(Dcream);
        levelSpinner.setBorder(BorderFactory.createLineBorder(outline, 8, true));
        levelSpinner.setForeground(Dcream);
        levelSpinner.setPreferredSize(new Dimension(120, 70));

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // SAVE BUTTON
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        saveButton = new JButton("Save");
        saveButton.addActionListener(event -> saveClick());
        saveButton.setPreferredSize(new Dimension(100, 30));
        saveButton.setBackground(outline);
        saveButton.setForeground(Dcream);
        saveButton.setBorder(BorderFactory.createLineBorder(Dcream, 3, true));
        saveButton.setFont(buttons);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // LOAD BUTTON
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        loadButton = new JButton("Load");
        loadButton.addActionListener(event -> loadClick());
        loadButton.setPreferredSize(new Dimension(100, 30));
        loadButton.setBackground(outline);
        loadButton.setForeground(Dcream);
        loadButton.setBorder(BorderFactory.createLineBorder(Dcream, 3, true));
        loadButton.setFont(buttons);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PLACE PANEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        window.add(panel);
        gridbag.anchor = GridBagConstraints.WEST;

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PLACE NAME LABEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        gridbag.gridy = 0;
        gridbag.gridx = 0;
        gridbag.insets = new Insets(0, 20, 45, 0);
        panel.add(nameLabel, gridbag);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PLACE NAME TEXTFIELD
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        gridbag.gridy = 0;
        gridbag.insets = new Insets(50, 18, 5, 0);
        panel.add(nameTextField, gridbag);
        gridbag.insets = new Insets(10, 5, 5, 5);
        panel.add(paddingLabel, gridbag);
        gridbag.insets = new Insets(0, 0, 0, 0);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PLACE LEVEL LABEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        gridbag.gridy = 2;
        gridbag.gridx = 0;
        gridbag.insets = new Insets(10, 20, 45, 0);
        panel.add(levelLabel, gridbag);
        gridbag.insets = new Insets(0, 0, 0, 0);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PLACE LEVEL SPINNER
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        gridbag.gridy = 2;
        gridbag.insets = new Insets(32, 320, 15, 0);
        panel.add(levelSpinner, gridbag);
        gridbag.insets = new Insets(15, 5, 0, 5);
        panel.add(paddingLabel2, gridbag);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PLACE SAVE BUTTON
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        gridbag.gridy = 5;
        gridbag.gridx = 0;
        gridbag.insets = new Insets(20, 65, 10, 65);
        panel.add(saveButton, gridbag);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PLACE LOAD BUTTON
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        gridbag.anchor = GridBagConstraints.EAST;
        panel.add(loadButton, gridbag);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        window.pack();
        window.setVisible(true);
    }
}
