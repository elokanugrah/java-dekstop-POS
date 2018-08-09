/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
import javax.swing.table.*; 
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Elok Anugrah
 */
public class renderingTengah extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
    super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
    setHorizontalAlignment(SwingConstants.CENTER); return this; }
    
}

