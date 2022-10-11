package com.company;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class WindowCloser extends WindowAdapter{
    public void windowClosing(WindowEvent evt) {
	System.exit(0);
    }
}
