import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * this class for implementing interface for medical workers.
 */
public class MedicalInterface {
    /**
     * this method is for adding components to medical interface
     * input: red-black tree for searching patient's information by given Id
     */
    public static void MedicalPage(Red_Black_tree rb, Max_Heap max_heap){
        JFrame.setDefaultLookAndFeelDecorated(true);

        // set up interface
        JFrame frame = new JFrame("Medical Page");
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
        //for the more dangerous patient
        JButton next = new JButton("Who should be treated next");
        next.setBounds(10,50,200,25);
        panel.add(next);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //jump to dangerous patient
                frame.dispose();
                new MedicalInterface().NextPage(rb,max_heap);
            }
        });

        //for searching function
        JButton search = new JButton("search record");
        search.setBounds(10, 100, 200, 25);
        panel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //jump to search function page
                frame.dispose();
                new MedicalInterface().SearchPage(rb, max_heap);
            }
        });
        //for adding new record
        JButton insert = new JButton("add new record");
        insert.setBounds(10, 150, 200, 25);
        panel.add(insert);
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new MedicalInterface().AddPage(rb, max_heap);
            }
        });

        frame.setVisible(true);
    }

    /**
     * design "add record" page and add a new record to red black tree. this is not one of my three functionalities
     */
    private void AddPage(Red_Black_tree rb, Max_Heap max_heap) {
        JFrame frame = new JFrame("Add Page");
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
                new MedicalInterface().MedicalPage(rb, max_heap);
            }
        });


        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20,40,20,20);
        JTextField addLable = new JTextField("",40);
        addLable.setBounds(120,40,200,20);

        JLabel postcodeLabel = new JLabel("Postcode:");
        postcodeLabel.setBounds(20,80, 80, 20);
        JTextField addpostcode = new JTextField(40);
        addpostcode.setBounds(120,80,200,20);

        JLabel age = new JLabel("Age:");
        age.setBounds(20,120,60,20);
        JTextField addAge = new JTextField(40);
        addAge.setBounds(120,120,200,20);

        JLabel level = new JLabel("Level:");
        level.setBounds(20,160,40,20);
        JTextField addlevel = new JTextField(40);
        addlevel.setBounds(120,160,200,20);

        JLabel  state = new JLabel("State:");
        state.setBounds(400,80,50,30);
        JLabel s = new JLabel("Waite");
        s.setBounds(455,80,80,30);

        JButton submit = new JButton("Submit");
        submit.setBounds(30,200,200,50);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(addLable.getText());
                int postcode = Integer.parseInt(addpostcode.getText());
                int age = Integer.parseInt(addAge.getText());
                int level = Integer.parseInt(addlevel.getText());
                Record_Type n = new Record_Type(id,postcode,age,level);
                rb.insert(n);
                max_heap.Max_Heap_Insert(n);
                s.setText("Success");
            }
        });



        JButton onemore = new JButton("Add more");
        onemore.setBounds(260,200,200,50);
        onemore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addLable.setText("");
                addAge.setText("");
                addlevel.setText("");
                addpostcode.setText("");
                s.setText("Waite");
            }
        });

        panel.add(idLabel);
        panel.add(addLable);
        panel.add(postcodeLabel);
        panel.add(addpostcode);
        panel.add(age);
        panel.add(addAge);
        panel.add(level);
        panel.add(addlevel);
        panel.add(submit);
        panel.add(onemore);
        panel.add(state);
        panel.add(s);
        frame.setVisible(true);
        
    }
    /**
     * design "Next Patient" page and delete record from this red black tree.
     */
    private void NextPage(Red_Black_tree rb, Max_Heap max_heap){
        JFrame frame = new JFrame("Find patient Page");
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
                new MedicalInterface().MedicalPage(rb, max_heap);
            }
        });

        JLabel show = new JLabel("Waiting");
        show.setBounds(20,150,500,30);
        panel.add(show);

        JLabel ins = new JLabel("Please click this button.");
        ins.setBounds(20,40,200,30);
        panel.add(ins);
        JButton jb = new JButton("Next Patient");
        jb.setBounds(220,40,200,30);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(max_heap.size == 0){
                    show.setText("Congratulations! there is no patient so far. ");
                }else{
                Record_Type next = max_heap.Heap_Exact_Max();
                show.setText("Id: "+next.id + "  "+ "  Postcode: "+ next.postcode + "  Age: "+next.age + "  Level: "+ next.level );
                }
            }
        });
        panel.add(jb);


        frame.setVisible(true);

    }
    /**
     * interface for searching patients' record.
     */
    public  static void SearchPage(Red_Black_tree rb, Max_Heap max_heap){
        JFrame frame = new JFrame("Search Page");
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
                new MedicalInterface().MedicalPage(rb, max_heap);
            }
        });

        JLabel idLabel = new JLabel("Patient's ID:");
        idLabel.setBounds(10,20,80,25);
        panel.add(idLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        JTextField re = new JTextField(40);
        re.setBounds(10,60,250,100);
        re.setText("Please input an ID.");
        panel.add(re);

        JButton search = new JButton("Search");
        search.setBounds(280, 20, 165, 25);
        panel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               int  id = Integer.parseInt(userText.getText());
               Record_Type result;
                /**
                 * call red black tree search method to find record by given id.
                 */
               result = rb.search(id);
               if (result==null){
                   re.setText("Check your input ID");
               }else {
                   String r = "id: " + result.id + "  age: " + result.age + " postcode: " + result.postcode;
                   re.setText(r);
               }
            }
        });
        frame.setVisible(true);
    }


}
