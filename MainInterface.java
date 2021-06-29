import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * this class for implementing welcome interface.
 */
public class MainInterface {
    /**
     * create a new welcome interface.
     */
    public static void createAndShowGUI(Red_Black_tree rb, Max_Heap max_heap) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        // set interface
        JFrame frame = new JFrame("Welcome");
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);

        JPanel panel = new JPanel();
        frame.add(panel);
        //design this interface
        designInterface(panel,frame,rb, max_heap);
        // show interface
        frame.setVisible(true);
    }
    /**
     * add components to this welcome interface.
     */
    private static void designInterface(JPanel panel, JFrame jf, Red_Black_tree rb, Max_Heap max_heap) {
        panel.setLayout(null);
        // welcome text
        JLabel wellabel = new JLabel("Welcome to Covid-19 Safe System !");
        wellabel.setBounds(5,20,500,25);
        panel.add(wellabel);
        //for medical workers
        JButton workerButton = new JButton("I am a medical worker in ACT");
        workerButton.setBounds(10, 80, 250, 25);
        panel.add(workerButton);
        workerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //jump to medical page
                jf.dispose();
                new MedicalInterface().MedicalPage(rb, max_heap);
            }
        });
        //for residences
        JButton residenceButton = new JButton("I am a residence in ACT");
        residenceButton.setBounds(290, 80, 200, 25);
        panel.add(residenceButton);
        residenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jf.dispose();
                new ResidenceInterface().ResidencePage(rb, max_heap);
            }
        });
        JButton tester = new JButton("I am a tester");
        tester.setBounds(10,200,200,25);
        panel.add(tester);
        tester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jf.dispose();
                new TestInterface().TestPage(rb,max_heap);
            }
        });

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                /**
                 * Init red balck tree and max heap
                 */
                Red_Black_tree rb = new Red_Black_tree();
                Max_Heap max_heap =  new Max_Heap();
                createAndShowGUI(rb,max_heap);
            }
        });

    }
    /**
     * back to this welcome interface.
     */
    public void back(Red_Black_tree rb, Max_Heap max_heap){
        JFrame.setDefaultLookAndFeelDecorated(true);

        // set interface
        JFrame frame = new JFrame("Welcome");
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);


        JPanel panel = new JPanel();
        frame.add(panel);
        //design this interface
        designInterface(panel,frame,rb, max_heap);
        // show interface
        frame.setVisible(true);
    }

}

