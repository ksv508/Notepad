import javax.swing.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.*;

class note extends JFrame implements ActionListener{
    JFrame f; // frame Deceleration
    JTextArea t ; //textArea deceleration
    JMenuBar mb; // menuBar deceleration
    note(){  //constructor
        f = new JFrame("KsvPad");  // initialization the frame
        t = new JTextArea(); // initialization the text Area
        mb = new JMenuBar(); //MenuBar deceleration

        JMenu file = new JMenu("File"); // creating the file menu

        // creating  options for file
        JMenuItem f1 = new JMenuItem("New");
        JMenuItem f2 = new JMenuItem("Open");
        JMenuItem f3 = new JMenuItem("Save");
        JMenuItem f4 = new JMenuItem("Print");

        // adding actionListener to each of the options
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        // adding the options to the file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        // same we create Edit options
        JMenu edit = new JMenu("Edit"); // creating the Edit menu

        // creating  options for file
        JMenuItem e1 = new JMenuItem("Cut");
        JMenuItem e2 = new JMenuItem("Copy");
        JMenuItem e3 = new JMenuItem("Paste");

        // adding actionListener to each of the options
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);

        // adding the options to the edit menu
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);

        // creating close menu and adding it to menuBar
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this);
        // adding all file , edit and close to menubar
        mb.add(file);
        mb.add(edit);
        mb.add(close);

        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(1280,720);
        f.show();  // or f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand(); // this store the action performed on menubar or where user clicks
        switch (s){
            case "New" :
                t.setText("");
                break;
            case "Cut" :
                t.cut();
                break;
            case "Copy" :
                t.copy();
                break;
            case "Paste" :
                t.paste();
                break;
            case "Close" :
                f.setVisible(false);
                break;
            case "Print" :
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Save" :
                // current directory where file will be saved (JFileChooser) or creating the object of jFileChooser
                // class with starting path to desktop
                JFileChooser j = new JFileChooser("C:\\Users\\ksv50\\OneDrive\\Desktop");

                // invoke saved dialogbox
                int r = j.showSaveDialog(null);
                // r contains status of dialog box 1 if clicked on save
                if (r == 0) {
                    // declare file object and store fie location
                    File fi = new File(j.getSelectedFile().getAbsolutePath());
                    try {
                        FileWriter fw = new FileWriter((fi)); // creating new file to selected location and also text to fw
                        BufferedWriter bw = new BufferedWriter(fw); // copying text to buffer writer from text Area
                        bw.write(t.getText()); // buffer writer write its text to file
                        bw.flush(); // clear buffer writer
                        bw.close(); // delete buffer writer

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                else {
                    JOptionPane.showMessageDialog(f,"the user has cancelled the operation");
                }
                break;

            case "Open" :
                // current directory where file will be saved (JFileChooser) or creating the object of jFileChooser
                // class with starting path to desktop
                JFileChooser ji = new JFileChooser("C:\\Users\\ksv50\\OneDrive\\Desktop");

                // invoke saved dialogbox
                int ri = ji.showOpenDialog(null);
                // ri contains status of dialog box 1 if clicked on save
                if (ri == 0) {
                    // declare file object and store fie location
                    File fi = new File(ji.getSelectedFile().getAbsolutePath());
                    try {
                        FileReader fw = new FileReader(fi); // creating new file to selected location and also text to fw
                        BufferedReader br = new BufferedReader(fw); // copying text to buffer writer from text Area
                         String s1 =  "" ,s2 = "";
                         // first line stored in s1
                        s1 = br.readLine();
                        while((s2 = br.readLine()) != null){
                            s1 = s1 + "\n" + s2;
                        }
                        // all the content of the file copied into s1
                        t.setText(s1);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                else {
                    JOptionPane.showMessageDialog(f,"the user has cancelled the operation");
                }
                break;
        }

    }
    public static void main(String[] args) {
        note n = new note();
    }
}