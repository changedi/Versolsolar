/**
 * 
 */
package com.versolsolar.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author Administrator
 *
 */
public class MainUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new MainFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setVisible(true);
			}
		});
	}

}
