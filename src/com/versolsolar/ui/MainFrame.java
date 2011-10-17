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
		super("钢结构参数计算器v1.0");
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("圆形钢", new OvalPanel());
		tabbedPane.addTab("H形钢", null);
		tabbedPane.addTab("Σ形钢", null);
		
		this.add(tabbedPane,BorderLayout.CENTER);
		this.setSize(450, 180);
		this.setLocationRelativeTo(null);
	}
}
