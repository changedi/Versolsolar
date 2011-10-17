/**
 * 
 */
package com.versolsolar.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.versolsolar.main.MainCalc;

/**
 * @author Administrator
 * 
 */
public class OvalPanel extends JPanel implements ActionListener {

	private JLabel NLabel;

	private JLabel RLabel;

	private JLabel tLabel;

	private JLabel IxLabel;

	private JLabel IyLabel;

	private JLabel LLabel;

	private JLabel sigmaLabel;

	private JLabel stababilityLabel;

	private JLabel stableLabel;

	private JLabel sigmaSatLabel;

	private JTextField NText;

	private JTextField RText;

	private JTextField tText;

	private JTextField IxText;

	private JTextField IyText;

	private JTextField LText;

	private JTextField sigmaText;

	private JTextField stababilityText;

	private JButton calcBtn;

	private JButton clrBtn;

	private JPanel dataPanel;

	private JPanel btnPanel;

	private MainCalc calc;

	public OvalPanel() {
		calc = new MainCalc();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		NLabel = new JLabel("N:");
		RLabel = new JLabel("R:");
		tLabel = new JLabel("t:");
		IxLabel = new JLabel("Ix:");
		IyLabel = new JLabel("Iy:");
		LLabel = new JLabel("l:");
		sigmaLabel = new JLabel("截面应力:");
		stababilityLabel = new JLabel("稳定性:");

		stableLabel = new JLabel();
		sigmaSatLabel = new JLabel();

		NText = new JTextField(6);
		RText = new JTextField(6);
		tText = new JTextField(6);
		IxText = new JTextField(6);
		IyText = new JTextField(6);
		LText = new JTextField(6);
		sigmaText = new JTextField(6);
		stababilityText = new JTextField(6);

		sigmaText.setEditable(false);
		stababilityText.setEditable(false);

		this.setLayout(new BorderLayout());

		dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(3, 6));
		dataPanel.add(NLabel);
		dataPanel.add(NText);
		dataPanel.add(RLabel);
		dataPanel.add(RText);
		dataPanel.add(tLabel);
		dataPanel.add(tText);
		dataPanel.add(IxLabel);
		dataPanel.add(IxText);
		dataPanel.add(IyLabel);
		dataPanel.add(IyText);
		dataPanel.add(LLabel);
		dataPanel.add(LText);
		dataPanel.add(sigmaLabel);
		dataPanel.add(sigmaText);
		dataPanel.add(sigmaSatLabel);
		dataPanel.add(stababilityLabel);
		dataPanel.add(stababilityText);
		dataPanel.add(stableLabel);

		this.add(dataPanel, BorderLayout.CENTER);
		calcBtn = new JButton("计算");
		clrBtn = new JButton("清空");

		calcBtn.addActionListener(this);
		clrBtn.addActionListener(this);

		btnPanel = new JPanel();
		btnPanel.add(calcBtn);
		btnPanel.add(clrBtn);
		this.add(btnPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == calcBtn) {
			String tt = tText.getText();
			String rr = RText.getText();
			String nn = NText.getText();
			String iixx = IxText.getText();
			String iiyy = IyText.getText();
			String ll = LText.getText();
			if (checkInput(tt, rr, nn, iixx, iiyy, ll)) {
				double t = Double.valueOf(tt);
				double R = Double.valueOf(rr);
				double N = Double.valueOf(nn);
				double Ix = Double.valueOf(iixx);
				double Iy = Double.valueOf(iiyy);
				double L = Double.valueOf(ll);
				calc.calcSigma(t, R, N);
				calc.calcL0(0, L);// 取默认值
				calc.calcFai(Ix, Iy);
				sigmaText.setText(String.valueOf(calc.getSigma()));
				if (calc.isSigmaSat()) {
					sigmaSatLabel.setText("满足");
				} else {
					sigmaSatLabel.setText("不满足");
				}
				stababilityText.setText(String.valueOf(calc.getStabability()));
				if (calc.isStable(N)) {
					stableLabel.setText("满足");
				} else {
					stableLabel.setText("不满足");
				}
			}
		}
		if (e.getSource() == clrBtn) {
			clearText();
		}
	}

	private boolean checkInput(String tt, String rr, String nn, String iixx,
			String iiyy, String ll) {
		if (tt.isEmpty() || rr.isEmpty() || nn.isEmpty() || iixx.isEmpty()
				|| iiyy.isEmpty() || ll.isEmpty()) {
			JOptionPane.showInternalMessageDialog(this.getParent().getParent(),
					"您有值未输入，请输入!", "info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		try {
			Double.valueOf(tt);
			Double.valueOf(rr);
			Double.valueOf(nn);
			Double.valueOf(iixx);
			Double.valueOf(iiyy);
			Double.valueOf(ll);
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(this.getParent().getParent(),
					"您输入的值不是数字哦，请重新输入!", "info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}

	private void clearText() {
		NText.setText("");
		RText.setText("");
		tText.setText("");
		IxText.setText("");
		IyText.setText("");
		LText.setText("");
		sigmaText.setText("");
		stababilityText.setText("");
		stableLabel.setText("");
		sigmaSatLabel.setText("");
	}
}
