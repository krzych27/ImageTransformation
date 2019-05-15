import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.event.*;
import javax.imageio.*;
import java.util.*;

public class ImageTransformation {

    public static void main(String[] args) {

        JFrame frame = new ImageTransformationFrame();
        frame.setTitle("Image Viewer App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}

@SuppressWarnings("serial")
//A frame with a label to show an image
class ImageTransformationFrame extends JFrame {

    JLabel label;
    JFileChooser chooser;
    JMenuBar menubar;
    JMenu menu,functions;
    JMenuItem open,save,exit,fun1,fun2,fun3,fun4;

    BufferedImage image;
    //Constructor
    public ImageTransformationFrame() {

        setSize(500,500);

        //Use a label to display the image
        label = new JLabel();
        add(label);

        //Set up the file chooser
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        //set up the menubar
        menubar = new JMenuBar();
        setJMenuBar(menubar);

        menu = new JMenu("File");
        menubar.add(menu);

        open = new JMenuItem("Open");
        menu.add(open);

        save = new JMenuItem("Save");
        menu.add(save);

        exit = new JMenuItem("Exit");
        menu.add(exit);

        functions = new JMenu("Functions");
        menubar.add(functions);

        fun1 = new JMenuItem("Automatyczne progowanie metodą maksymalnej entropii");
        functions.add(fun1);

        fun2 = new JMenuItem("Filtracja Kirscha.Brzeg - odbicie symetryczne");
        functions.add(fun2);

        fun3 = new JMenuItem("Otwarcie elementem linijnym o zadanej długości i nachyleniu");
        functions.add(fun3);

        fun4 = new JMenuItem("Wypełnianie dziur w obiektach");
        functions.add(fun4);

        open.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                //show file chooser dialog
                int result = chooser.showOpenDialog(null);

                //if file selected, set it as icon of label
                if (result == JFileChooser.APPROVE_OPTION) {
                    File name = chooser.getSelectedFile();
                    try {
                        image = ImageIO.read(name);
                    } catch (IOException e) {
                        System.err.println("Error.Image read function");
                        e.printStackTrace();
                    }
                    label.setIcon(new ImageIcon(image));

                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                int result = chooser.showSaveDialog(null);
                //if file selected, set it as icon of label
                if(result == JFileChooser.APPROVE_OPTION) {
                    File name = chooser.getSelectedFile();
                    //save changed photo to new file "name"
                    // ImageIO.write(bufferedImage, "jpg", name);

                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

}