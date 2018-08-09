/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Elok Anugrah
 */
public class DateRenderer extends DefaultTableCellRenderer {
private static final long serialVersionUID = 1L;
private Date dateValue;
private SimpleDateFormat sdfNewValue = new SimpleDateFormat("dd MMM yyyy");
private String valueToString = "";

@Override
public void setValue(Object value) {
    if ((value != null)) {
        String stringFormat = value.toString();
        try {
            dateValue = new SimpleDateFormat("yyyy-MM-dd").parse(stringFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        valueToString = sdfNewValue.format(dateValue);
        value = valueToString;
    }
    super.setValue(value);
}
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
    super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
    setHorizontalAlignment(SwingConstants.CENTER); return this; }
}
