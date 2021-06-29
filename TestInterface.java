import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * this class for implementing interface for tester.
 */
public class TestInterface {
    public void TestPage(Red_Black_tree rb, Max_Heap max_heap){
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Test Page");
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

        JButton t1 = new JButton("Test find the patient with the most urgent circumstance");
        t1.setBounds(10, 50, 400, 25);
        panel.add(t1);
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //jump to find path function page
                frame.dispose();
                new TestInterface().Test2(rb, max_heap);
            }
        });

        JButton t2 = new JButton("Test search record");
        t2.setBounds(10, 100, 200, 25);
        panel.add(t2);
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //jump to search function page
                frame.dispose();
                new TestInterface().Test1(rb, max_heap);
            }
        });

        JButton t3 = new JButton("Test the shortest path");
        t3.setBounds(10, 150, 200, 25);
        panel.add(t3);
        t3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new TestInterface().Test3(rb, max_heap);
            }
        });
        frame.setVisible(true);
    }
    /**
     * design "test the shortest path " page. And call Dijkstra class to find the shortest path.
     */
    private void Test3(Red_Black_tree rb, Max_Heap max_heap) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Test Page");
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
                new TestInterface().TestPage(rb, max_heap);
            }
        });

        JButton button=new JButton("Select file ");
        button.setBounds(10,30,100,20);
        panel.add(button);

        JLabel ad = new JLabel("Your file address :");
        panel.add(ad);
        ad.setBounds(10,90,400,20);

        JTextArea add = new JTextArea("Please select a file to read and run ");
        panel.add(add);
        add.setBounds(10,120,530,40);
        add.setWrapStyleWord(true);
        add.setLineWrap(true);



        final String[] address = {""};
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "file", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(frame);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    address[0] = chooser.getSelectedFile().getAbsolutePath();
                    add.setText(address[0]);
                }
            }
        });
        JLabel re = new JLabel("Result :");
        panel.add(re);
        re.setBounds(10,180,400,20);

        JTextArea result = new JTextArea("Waiting. Select a file and  run ");
        panel.add(result);
        result.setLineWrap(true);
        result.setWrapStyleWord(true);
        result.setBounds(10,220,530,40);

        Dijkstra dj = new Dijkstra();
        JButton run=new JButton("run");
        panel.add(run);
        run.setBounds(200,30,100,20);
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                File file = new File(address[0]);
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(file));
                    String tempString = null;
                    int n ;
                    int start;
                    int [] des ;
                    String[] arr2 = reader.readLine().split("\\s+");
                    n = Integer.parseInt(arr2[0]);
                    start = Integer.parseInt(arr2[1]);
                    des = new int[Integer.parseInt(arr2[2])];
                    for(int i=0;i<arr2.length-3;i++){
                       des[i] = Integer.parseInt(arr2[i+3]);
                    }
                    int counter = 0;
                    int counter1 = 0;
                    int [][] matrix = new int[n][n];
                    int [][] edges = new int[n][];
                    int [][] dis = new int[n][];
                    while ((tempString = reader.readLine()) != null) {
                        String[] arr = tempString.split("\\s+");
                        int[] arr1 = new int[arr.length];
                        for (int i = 0; i < arr.length; i++) {
                            arr1[i] = Integer.parseInt(arr[i]);
                        }
                        if (counter != n) {
                            edges[counter] = arr1;
                            counter++;
                        }else{
                            dis[counter1] = arr1;
                            counter1++;
                        }
                    }
                    reader.close();
                    int inf = 9999999;
                    for(int i=0; i<n;i++){
                        for(int j=0; j<n;j++){
                            matrix[i][j] = inf;
                        }
                    }
                    for (int i =0;i<edges.length; i++){
                        for(int j=0; j<edges[i].length;j++){
                            matrix[i][edges[i][j]] = dis[i][j];
                            matrix[edges[i][j]][i] = dis[i][j];
                        }
                    }
                    result.setText(dj.Find_path(start,true,des,matrix,edges,n));
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e1) {
                        }
                    }
                }
            }
        });

        frame.setVisible(true);
    }
    /**
     * design "test Search method" page. And call red-black tree to realise
     */
    private void Test2(Red_Black_tree rb, Max_Heap max_heap) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Test Page");
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
                new TestInterface().TestPage(rb, max_heap);
            }
        });

        JButton button=new JButton("Select file ");
        button.setBounds(10,30,100,20);
        panel.add(button);

        JLabel ad = new JLabel("Your file address :");
        panel.add(ad);
        ad.setBounds(10,90,400,20);

        JTextArea add = new JTextArea("Please select a file to read and run ");
        panel.add(add);
        add.setBounds(10,120,530,40);
        add.setLineWrap(true);
        add.setWrapStyleWord(true);



        final String[] address = {""};
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "file", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(frame);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    address[0] = chooser.getSelectedFile().getAbsolutePath();
                    add.setText(address[0]);
                }
            }
        });
        JLabel re = new JLabel("Result :");
        panel.add(re);
        re.setBounds(10,180,400,20);

        JTextArea result = new JTextArea("Waiting. Select a file and  run ");
        panel.add(result);
        result.setBounds(10,220,530,40);

        Max_Heap max_heap1 = new Max_Heap();
        JButton run=new JButton("run");
        panel.add(run);
        run.setBounds(200,30,100,20);
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                File file = new File(address[0]);
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(file));
                    String tempString = null;
                    while ((tempString = reader.readLine()) != null) {
                        max_heap1.Max_Heap_Insert(new Record_Type(0,0,0,Integer.parseInt(tempString)));
                    }
                    reader.close();
                    Record_Type recordType = max_heap1.Heap_Exact_Max();
                    String sss = "id: "+ String.valueOf(recordType.id) + " postcode: "+ String.valueOf(recordType.postcode)
                            + " age: "+String.valueOf(recordType.age) + " level: "+String.valueOf(recordType.level);
                    result.setText(sss);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e1) {
                        }
                    }
                }
            }
        });

        frame.setVisible(true);

    }
    /**
     * design "test Find the patient with the most urgent circumstance" page. And call max-heap class to find the maximum number.
     */
    private void Test1(Red_Black_tree rb, Max_Heap max_heap) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Test Page");
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
                new TestInterface().TestPage(rb, max_heap);
            }
        });
        JButton button=new JButton("Select file ");
        button.setBounds(10,30,100,20);
        panel.add(button);

        JLabel id = new JLabel("Search ID");
        panel.add(id);
        id.setBounds(10,60,100,20);

        JTextArea idd = new JTextArea("Put an ID and click run ");
        panel.add(idd);
        idd.setBounds(150,60,200,20);

        JLabel ad = new JLabel("Your file address :");
        panel.add(ad);
        ad.setBounds(10,90,400,20);

        JTextArea add = new JTextArea("Please select a file to read ");
        panel.add(add);
        add.setBounds(10,120,530,40);
        add.setWrapStyleWord(true);
        add.setLineWrap(true);


        final String[] address = {""};
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "file", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(frame);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    address[0] = chooser.getSelectedFile().getAbsolutePath();
                    add.setText(address[0]);
                }
            }
        });

        JLabel re = new JLabel("Result :");
        panel.add(re);
        re.setBounds(10,180,400,20);

        JTextArea result = new JTextArea("Waiting. Select a file, input an id and  run ");
        panel.add(result);
        result.setBounds(10,220,530,40);

        Red_Black_tree tree = new Red_Black_tree();

        JButton run=new JButton("run");
        panel.add(run);
        run.setBounds(200,30,100,20);
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                File file = new File(address[0]);
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(file));
                    String tempString = null;
                    while ((tempString = reader.readLine()) != null) {
                        tree.insert(new Record_Type(Integer.parseInt(tempString),0,0,0));
                    }
                    reader.close();
                    Record_Type recordType = tree.search(Integer.parseInt(idd.getText()));
                    String sss = "id: "+ String.valueOf(recordType.id) + " postcode: "+ String.valueOf(recordType.postcode)
                            + " age: "+String.valueOf(recordType.age) + " level: "+String.valueOf(recordType.level);
                    result.setText(sss);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e1) {
                        }
                    }
                }
            }
        });

        frame.setVisible(true);

    }


}
