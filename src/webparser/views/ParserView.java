
package webparser.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumn;

import webparser.classes.TableCreator;
import webparser.controller.ControllerInterface;
import webparser.model.ParserModelInterface;

/**
 * This class contains all the methods for building a graphical interface and
 * interact with.
 */
public class ParserView implements ActionListener, ParserObserver {

    ParserModelInterface model;
    ControllerInterface controller;
    TableCreator tableCreator;
    ArrayList allLinks;
    private JTextArea text;
    private JTextField field;
    private JButton buttonPause;
    private JButton buttonStart;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JButton buttonLoad;
    private JLabel infoLabel;
    private JTable table;
    private DefaultListModel lm;
    private JList list;

    public ParserView(ControllerInterface controller, ParserModelInterface model) {
        this.controller = controller;
        this.model = model;
        tableCreator = new TableCreator();
        lm = new DefaultListModel();
        table = new JTable(tableCreator);
        model.registerObserver(this);
        allLinks = new ArrayList();
    }

    public void update(ArrayList link) {
        tableCreator.addText(link);
        allLinks.add(link);
        infoLabel.setText(Integer.toString(allLinks.size()));
    }

    public void update(boolean finish) {
        controller.finish();
    }

    public void enableStopButton() {
        buttonPause.setEnabled(true);
    }

    public void disableStopButton() {
        buttonPause.setEnabled(false);
    }

    public void enableLoadButton() {
        buttonLoad.setEnabled(true);
    }

    public void disableLoadButton() {
        buttonLoad.setEnabled(false);
    }

    public void enableDeleteButton() {
        buttonDelete.setEnabled(true);
    }

    public void disableDeleteButton() {
        buttonDelete.setEnabled(false);
    }

    public void enableStartButton() {
        buttonStart.setEnabled(true);
    }

    public void disableStartButton() {
        buttonStart.setEnabled(false);
    }

    public void enableSaveButton() {
        buttonSave.setEnabled(true);
    }

    public void disableSaveButton() {
        buttonSave.setEnabled(false);
    }

    public void createList(ArrayList links) {
        for (int x = 0; x < links.size(); x++) {
            lm.addElement(links.get(x));
        }
    }

    public void clearTable() {
        tableCreator.deleteData();
        createTable();
    }

    public void clearLinksArray() {
        allLinks.clear();
    }

    public void addToList(String url) {
        DefaultListModel lm = (DefaultListModel) list.getModel();
        lm.addElement(url);
    }

    public void removeFromList(String url) {
        DefaultListModel lm = (DefaultListModel) list.getModel();
        lm.removeElement(url);
    }

    public void confirmMessage(String header, String message) {
        JOptionPane.showConfirmDialog(null, message, header, JOptionPane.PLAIN_MESSAGE);
    }

    public void createTable() {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn col1 = table.getColumnModel().getColumn(0);
        TableColumn col2 = table.getColumnModel().getColumn(1);
        TableColumn col3 = table.getColumnModel().getColumn(2);
        col1.setPreferredWidth(250);
        col2.setPreferredWidth(100);
        col3.setPreferredWidth(100);
    }

    @Override
    public void createView() {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());

        JLabel infoText = new JLabel("Pages on site: ");
        infoLabel = new JLabel("0");
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        field = new JTextField(15);

        text = new JTextArea(7, 35);

        createTable();

        JScrollPane scroller = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(450, 200));


        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        list = new JList(lm);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroller2 = new JScrollPane(list);
        scroller2.setPreferredSize(new Dimension(250, 40));
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Dimension bd = new Dimension(70, 25);

        buttonStart = new JButton("Start");
        buttonPause = new JButton("Stop");
        buttonSave = new JButton("Save");
        buttonLoad = new JButton("Load");
        buttonDelete = new JButton("Del");

        buttonStart.setPreferredSize(bd);
        buttonPause.setPreferredSize(bd);
        buttonSave.setPreferredSize(bd);
        buttonLoad.setPreferredSize(bd);
        buttonDelete.setPreferredSize(bd);

        buttonStart.addActionListener(this);
        buttonPause.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonDelete.addActionListener(this);
        buttonLoad.addActionListener(this);

        JLabel label = new JLabel("Insert site url: ");

        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();

        box1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        box2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        box3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        box4.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        box5.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        box1.add(label);
        box1.add(Box.createRigidArea(new Dimension(7, 0)));
        box1.add(field);
        box1.add(Box.createRigidArea(new Dimension(7, 0)));
        box1.add(buttonStart);
        box1.add(Box.createRigidArea(new Dimension(4, 0)));
        box1.add(buttonPause);

        box2.add(infoText);
        box2.add(infoLabel);

        box3.add(scroller);

        box4.add(Box.createRigidArea(new Dimension(400, 0)));
        box4.add(buttonSave);

        box5.add(scroller2);
        box5.add(Box.createRigidArea(new Dimension(67, 0)));
        box5.add(buttonLoad);
        box5.add(Box.createRigidArea(new Dimension(7, 0)));
        box5.add(buttonDelete);


        frame.getContentPane().add(box1);
        frame.getContentPane().add(box2);
        frame.getContentPane().add(box3);
        frame.getContentPane().add(box4);
        frame.getContentPane().add(box5);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocation(screenWidth / 3, screenHeight / 3);
        frame.setVisible(true);
    }

    /**
     * The method allows to communicate with the controller.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String buttonText = clickedButton.getText();
        String url = field.getText();
        Object value = list.getSelectedValue();

        if (buttonText.equals("Start")) {
            controller.start(url);
        } else if (buttonText.equals("Stop")) {
            controller.stop();
        } else if (buttonText.equals("Save")) {
            controller.save(url, allLinks);
        } else if (buttonText.equals("Del")) {
            if (value == null) {
                controller.errorMessage();
                return;
            }
            controller.delete(value.toString());
        } else if (buttonText.equals("Load")) {
            if (value == null) {
                controller.errorMessage();
                return;
            }
            controller.load(value.toString());
        }

    }
}
