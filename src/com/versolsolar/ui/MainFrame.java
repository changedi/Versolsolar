/**
 * 
 */
package com.versolsolar.ui;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
/**
 * @author Administrator
 *
 */
public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3107716260165933667L;

	private JTabbedPane tabbedPane;
	
	public MainFrame() {
		super("�ֽṹ����������v1.0");
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Բ�θ�", new OvalPanel());
		tabbedPane.addTab("H�θ�", null);
		tabbedPane.addTab("���θ�", null);
		
		this.add(tabbedPane,BorderLayout.CENTER);
		this.setSize(450, 180);
		this.setLocationRelativeTo(null);
	}
}
