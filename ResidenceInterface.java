import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * this class for implementing interface for residences.
 */
public class ResidenceInterface {
    /**
     * this method is for adding components to residence interface
     * input: red-black tree for keeping records.
     */
    public void ResidencePage(Red_Black_tree rb, Max_Heap max_heap) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        // set up interface
        JFrame frame = new JFrame("Residence Page");
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);

        JButton back = new JButton("<-");
        back.setBounds(0, 0, 30, 25);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new MainInterface().back(rb, max_heap);
            }
        });

        JButton search = new JButton("find the shortest path to nearest hospital");
        search.setBounds(10, 50, 350, 25);
        panel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //jump to find path function page
                frame.dispose();
                new ResidenceInterface().findPath(rb, max_heap);
            }
        });



        frame.setVisible(true);
    }
    /**
     * design "Find the shortest path" page. And call Dijkstra algorithm to find the shortest path.
     *
     */
    private void findPath(Red_Black_tree rb, Max_Heap max_heap) {
        JFrame frame = new JFrame("Find Path Page");
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);


        JButton back = new JButton("<-");
        back.setBounds(0, 0, 30, 25);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new ResidenceInterface().ResidencePage(rb, max_heap);
            }
        });

        JLabel idLabel = new JLabel("Provide your postcode : ");
        idLabel.setBounds(10,20,180,25);
        panel.add(idLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(200,20,165,25);
        panel.add(userText);

        JTextArea re = new JTextArea();
        re.setWrapStyleWord(true);
        re.setLineWrap(true);
        re.setBounds(10,60,400,100);
        re.setText("Please input an postcode");
        re.setRows(10);
        panel.add(re);

        JButton search = new JButton("Find");
        search.setBounds(380, 20, 65, 25);
        panel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(userText.getText());
                /**
                 * call Dijkstra algorithm to find the shortest path to the nearest hospital
                 */
                Dijkstra djk = new Dijkstra();
                djk.Init();
                if (djk.postcode.contains(id)){
                    String result = djk.Find_path(id,false,null,null,null,0);
                    re.setText(result);
                }else{
                    re.setText("Check your input");
                }



            }
        });


        frame.setVisible(true);
    }

}
