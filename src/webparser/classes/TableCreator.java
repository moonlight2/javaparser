/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webparser.classes;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ilia
 */
/**
 * Class lets you create and edit interactive tables
 */
public class TableCreator extends AbstractTableModel {

    Vector textData = new Vector();
    String colNames[] = {"Page name", "Level", "External links"};

    public void addText(ArrayList text) {
        textData.addElement(text);
        fireTableDataChanged();
    }

    public int getRowCount() {
        return textData.size();
    }

    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int c) {
        return colNames[c];
    }

    public void deleteData() {
        textData.clear();
        fireTableChanged(null);
    }

    public Object getValueAt(int row, int column) {
        ArrayList info = (ArrayList) textData.elementAt(row);
        if (column == 0) {
            return info.get(0);
        } else if (column == 1) {
            return info.get(1);
        } else {
            return info.get(2);
        }
    }
}