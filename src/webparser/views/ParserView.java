package webparser.views;

import webparser.common.Observer;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

import webparser.views.helper.TableCreator;
import webparser.common.Сontroller;
import webparser.controller.ParserController;
import webparser.common.Model;
import webparser.common.Observable;
import webparser.common.View;
import webparser.entity.Page;

public class ParserView extends View implements ActionListener, Observer {

    TableCreator tableCreator;
    List<List> allLinks;
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
    
    
    public ParserView() {
        tableCreator = new TableCreator();
        lm = new DefaultListModel();
        table = new JTable(tableCreator);
        allLinks = new ArrayList();
    }
    
    @Override
    public void attachToModel() {
        if (null != model) {
            model.getManager().registerObserver(this);
        }
    }
    
    @Override
    public void update(Page link) {
        List p = new ArrayList<>();
        p.add(link.getUrl());
        p.add(link.getLevel());
        p.add(link.getLinks());
        tableCreator.addText(p);
        allLinks.add(p);
        infoLabel.setText(Integer.toString(allLinks.size()));
    }

    @Override
    public void update(boolean finish) {
        controller.finish();
    }

    @Override
    public void enableStopButton() {
        buttonPause.setEnabled(true);
    }

    @Override
    public void disableStopButton() {
        buttonPause.setEnabled(false);
    }

    @Override
    public void enableLoadButton() {
        buttonLoad.setEnabled(true);
    }

    @Override
    public void disableLoadButton() {
        buttonLoad.setEnabled(false);
    }

    @Override
    public void enableDeleteButton() {
        buttonDelete.setEnabled(true);
    }

    @Override
    public void disableDeleteButton() {
        buttonDelete.setEnabled(false);
    }

    @Override
    public void enableStartButton() {
        buttonStart.setEnabled(true);
    }

    @Override
    public void disableStartButton() {
        buttonStart.setEnabled(false);
    }

    @Override
    public void enableSaveButton() {
        buttonSave.setEnabled(true);
    }

    @Override
    public void disableSaveButton() {
        buttonSave.setEnabled(false);
    }

    @Override
    public void createList(List links) {
        for (int x = 0; x < links.size(); x++) {
            lm.addElement(links.get(x));
        }
    }

    @Override
    public void clearTable() {
        tableCreator.deleteData();
        createTable();
    }

    @Override
    public void clearLinksArray() {
        allLinks.clear();
    }

    @Override
    public void addToList(String url) {
        DefaultListModel lm = (DefaultListModel) list.getModel();
        lm.addElement(url);
    }

    @Override
    public void removeFromList(String url) {
        DefaultListModel lm = (DefaultListModel) list.getModel();
        lm.removeElement(url);
    }

    @Override
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
        JFrame frame = new JFrame();
        frame.setResizable(false);
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

        JButton[] buttons = {buttonStart, buttonPause, buttonSave, buttonLoad, buttonDelete};

        for (JButton b : buttons) {
            b.setPreferredSize(bd);
            b.addActionListener(this);
        }

        JLabel label = new JLabel("Insert site url: ");

        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();

        Box[] boxes = {box1, box2, box3, box4, box5};

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

        for (Box b : boxes) {
            frame.getContentPane().add(b);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocation(screenWidth / 3, screenHeight / 3);
        frame.setVisible(true);
    }

    /**
     * The method allows view to communicate with the controller.
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
                confirmMessage("Error", "Select an url from the list!");
                return;
            }
            controller.delete(value.toString());
        } else if (buttonText.equals("Load")) {
            if (value == null) {
                confirmMessage("Error", "Select an url from the list!");
                return;
            }
            controller.load(value.toString());
        }

    }
}
