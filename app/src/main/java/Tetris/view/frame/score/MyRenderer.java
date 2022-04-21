package Tetris.view.frame.score;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class MyRenderer extends DefaultTableCellRenderer{
    private String Date;
    MyRenderer(String D){
        this.Date = D;
    }
    @Override 
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        if (! table.isRowSelected(row))
        { 
            if(table.getValueAt(row, 3).toString().indexOf(Date)!=-1) {
                c.setBackground(Color.GRAY); 
                return c;
            }else{
                c.setBackground(Color.BLACK);
            }
        }
        return c;

    }
}