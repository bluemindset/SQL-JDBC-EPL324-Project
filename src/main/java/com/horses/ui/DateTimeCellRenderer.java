package com.horses.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableCellRenderer;


	public class DateTimeCellRenderer extends DefaultTableCellRenderer {

	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy , HH:mm:ss:SSSS");

	    public DateTimeCellRenderer() {
	        super();
	    }

	    public void setValue(Object value) {
	        if (formatter == null) {
	            formatter = DateFormat.getDateInstance();
	        }
	        setText((value == null) ? "" : formatter.format(value));
	    }
	}