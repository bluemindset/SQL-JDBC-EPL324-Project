package com.horses.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

public class TimeStampCellRenderer extends DefaultTableCellRenderer {

    DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");

    public TimeStampCellRenderer() {
        super();
    }

    public void setValue(Object value) {
        if (formatter == null) {
            formatter = DateFormat.getDateInstance();
        }
        setText((value == null) ? "" : formatter.format(value));
    }
}