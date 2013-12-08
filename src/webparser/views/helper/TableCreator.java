package webparser.views.helper;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * This class lets you create and edit interactive tables
 */
public class TableCreator extends AbstractTableModel {

    List<List> textData = new ArrayList<>();
    String colNames[] = {"Page name", "Level", "External links"};

    public void addText(List<String> text) {
        textData.add(text);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return textData.size();
    }

    @Override
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

    @Override
    public Object getValueAt(int row, int column) {
        List info = (ArrayList) textData.get(row);
        if (column == 0) {
            return info.get(0);
        } else if (column == 1) {
            return info.get(1);
        } else {
            return info.get(2);
        }
    }
}