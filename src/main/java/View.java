import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;


public class View extends JFrame implements ActionListener{
	private JLabel introMsg = new JLabel("Selectati operatia pe care doriti sa o realizati:");
	private JLabel versionLbl = new JLabel("Version 1.0.0");
	private JButton additionBtn = new JButton("+");
	private JButton subtractBtn = new JButton("-");
	private JButton multiplyBtn = new JButton("*");
	private JButton divideBtn = new JButton("/");
	private JButton derivativeBtn = new JButton("'");
	private JButton integrateBtn = new JButton("\u222B");
	
	private JPanel mainPanel = new JPanel();			// Panel folosit ca meniu cand deschidem aplicatia
	private JPanel panel2 = new JPanel();				// Panel folosit pentru operatiile: +, -, *, /
	private JPanel panel3 = new JPanel();				// Panel folosit pentru operatiile: deriv, integr
	
	private ArrayList<Boolean> operation = new ArrayList<Boolean>(); // se stocheaza info despre care buton a fost apasat (pt. operatie)
																	 // 1 -> adunare, 2 -> scadere, 3 -> inmultire, 4 -> impartire, 5 -> derivare, 6 -> integrare
	
	// interfata pentru new window atunci cand apasam pe +, -, *, /  -> operatiile cu 2 operatori
	private JTextField firstPolynomial2 = new JTextField(10);
	private JTextField secondPolynomial2 = new JTextField(10);
	private JTextField result2 = new JTextField(10);
	private JLabel firstPol2 = new JLabel("Polynomial 1: ");
	private JLabel secondPol2 = new JLabel("Polynomial 2: ");
	private JLabel res2 = new JLabel("Result: ");
	private JButton giveResult2 = new JButton("Give result");
	private JButton backBtn2 = new JButton("\u261A");
	private JLabel versionLbl2 = new JLabel("Version 1.0.0");
	private JLabel titlu2 = new JLabel("PolyCalc");
	
	// interfata pentru new window atunci cand apasam pe ', integrare -> operatiile cu 1 operator
	private JTextField firstPolynomial3 = new JTextField(10);
	private JTextField secondPolynomial3 = new JTextField(10);
	private JTextField result3 = new JTextField(10);
	private JLabel firstPol3 = new JLabel("Polynomial: ");
	private JLabel secondPol3 = new JLabel("Polynomial 2: ");
	private JLabel res3 = new JLabel("Result: ");
	private JButton giveResult3 = new JButton("Give result");
	private JButton backBtn3 = new JButton("\u261A");
	private JLabel versionLbl3 = new JLabel("Version 1.0.0");
	private JLabel titlu3 = new JLabel("PolyCalc");
	
	
	View(){
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setResizable(false);
		this.setTitle("Polynomial Calculator");
		
		// Fiecare element reprezinta o operatie: +, -, *, /, deriv., integr.
		operation.add(false);
		operation.add(false);
		operation.add(false);
		operation.add(false);
		operation.add(false);
		operation.add(false);

		/// Primul panel:
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(235, 255, 179));
		
		introMsg.setBounds(10, 10, 700, 30);
		introMsg.setFont(new Font("Serif", Font.PLAIN, 24));
		versionLbl.setBounds(360, 320, 150, 50);
		versionLbl.setFont(new Font("Serif", Font.ITALIC, 20));
		additionBtn.setBounds(30, 70, 100, 70);
		additionBtn.setFont(new Font("Serif", Font.BOLD, 50));
		subtractBtn.setBounds(150, 70, 100, 70);
		subtractBtn.setFont(new Font("Serif", Font.BOLD, 50));
		multiplyBtn.setBounds(30, 160, 100, 70);
		multiplyBtn.setFont(new Font("Serif", Font.BOLD, 50));
		divideBtn.setBounds(150, 160, 100, 70);
		divideBtn.setFont(new Font("Serif", Font.BOLD, 50));
		derivativeBtn.setBounds(30, 250, 100, 70);
		derivativeBtn.setFont(new Font("Serif", Font.BOLD, 50));
		integrateBtn.setBounds(150, 250, 100, 70);
		integrateBtn.setFont(new Font("Serif", Font.BOLD, 50));
		
		additionBtn.addActionListener(this);
		subtractBtn.addActionListener(this);
		multiplyBtn.addActionListener(this);
		divideBtn.addActionListener(this);
		derivativeBtn.addActionListener(this);
		integrateBtn.addActionListener(this);
		backBtn2.addActionListener(this);
		backBtn3.addActionListener(this);
		
		
		mainPanel.add(introMsg);
		mainPanel.add(versionLbl);
		mainPanel.add(additionBtn);
		mainPanel.add(subtractBtn);
		mainPanel.add(multiplyBtn);
		mainPanel.add(divideBtn);
		mainPanel.add(derivativeBtn);
		mainPanel.add(integrateBtn);
		
		
		// Adaugam primul panel ca view:
		this.add(mainPanel);
		
		// Al doilea panel:
		panel2.setLayout(null);
		panel2.setBackground(new Color(235, 255, 179));
		
		backBtn2.setBounds(10, 10, 100, 40);
		backBtn2.setFont(new Font("Serif", Font.PLAIN, 50));
		backBtn2.setBackground(new Color(0, 175, 221));
		firstPol2.setBounds(10, 75, 200, 30);
		firstPol2.setFont(new Font("Serif", Font.PLAIN, 18));
		secondPol2.setBounds(10, 135, 200, 30);
		secondPol2.setFont(new Font("Serif", Font.PLAIN, 18));
		firstPolynomial2.setBounds(125, 75, 500, 30);
		firstPolynomial2.setFont(new Font("Serif", Font.PLAIN, 18));
		secondPolynomial2.setBounds(125, 135, 500, 30);
		secondPolynomial2.setFont(new Font("Serif", Font.PLAIN, 18));
		giveResult2.setBounds(10, 175, 200, 40);
		giveResult2.setFont(new Font("Serif", Font.PLAIN, 25));
		giveResult2.setBackground(Color.LIGHT_GRAY);
		res2.setBounds(10, 240, 200, 30);
		res2.setFont(new Font("Serif", Font.PLAIN, 18));
		result2.setBounds(70, 240, 555, 30);
		result2.setFont(new Font("Serif", Font.PLAIN, 18));
		versionLbl2.setBounds(560, 280, 150, 30);
		versionLbl2.setFont(new Font("Serif", Font.ITALIC, 20));
		titlu2.setBounds(300, 0, 300, 60);
		titlu2.setFont(new Font("Serif", Font.BOLD, 40));
		
		panel2.add(backBtn2);
		panel2.add(firstPol2);
		panel2.add(secondPol2);
		panel2.add(firstPolynomial2);
		panel2.add(secondPolynomial2);
		panel2.add(giveResult2);
		panel2.add(res2);
		panel2.add(result2);
		panel2.add(versionLbl2);
		panel2.add(titlu2);
		
		// Al treilea panel:
		panel3.setLayout(null);
		panel3.setBackground(new Color(235, 255, 179));
		
		backBtn3.setBounds(10, 10, 100, 40);
		backBtn3.setFont(new Font("Serif", Font.PLAIN, 50));
		backBtn3.setBackground(new Color(0, 175, 221));
		firstPol3.setBounds(10, 75, 200, 30);
		firstPol3.setFont(new Font("Serif", Font.PLAIN, 18));
		firstPolynomial3.setBounds(125, 75, 500, 30);
		firstPolynomial3.setFont(new Font("Serif", Font.PLAIN, 18));
		giveResult3.setBounds(10, 125, 200, 40);
		giveResult3.setFont(new Font("Serif", Font.PLAIN, 20));
		giveResult3.setBackground(Color.LIGHT_GRAY);
		res3.setBounds(10, 190, 200, 30);
		res3.setFont(new Font("Serif", Font.PLAIN, 18));
		result3.setBounds(70, 190, 555, 30);
		result3.setFont(new Font("Serif", Font.PLAIN, 18));
		versionLbl3.setBounds(560, 230, 150, 30);
		versionLbl3.setFont(new Font("Serif", Font.ITALIC, 20));
		titlu3.setBounds(300, 0, 300, 60);
		titlu3.setFont(new Font("Serif", Font.BOLD, 40));
		
		panel3.add(backBtn3);
		panel3.add(firstPol3);
		panel3.add(firstPolynomial3);
		panel3.add(giveResult3);
		panel3.add(res3);
		panel3.add(result3);
		panel3.add(versionLbl3);
		panel3.add(titlu3);
		
		
	}
	
	private void getSecondPanel() {
		this.mainPanel.setVisible(false);
		this.panel3.setVisible(false);
		this.setSize(700, 350);
		this.add(panel2);
		this.panel2.setVisible(true);
	}

	private void getThirdPanel() {
		this.mainPanel.setVisible(false);
		this.panel2.setVisible(false);
		this.setSize(700, 300);
		this.add(panel3);
		this.panel3.setVisible(true);
	}

	private void getMainPanel() {
		this.panel2.setVisible(false);
		this.panel3.setVisible(false);
		this.setSize(500, 400);
		this.mainPanel.setVisible(true);
	}
	
	public String getFirstPol2() {
		return this.firstPolynomial2.getText();
	}
	
	public String getSecondPol2() {
		return this.secondPolynomial2.getText();
	}
	
	public String getFirstPol3() {
		return this.firstPolynomial3.getText();
	}
	
	public String getSecondPol3() {
		return this.secondPolynomial3.getText();
	}
	
	// ActionListener pt butoanele de giveResult
	
	public void getResultDispatcher(ActionListener getResult) {
		giveResult2.addActionListener(getResult);
		giveResult3.addActionListener(getResult);
	}
	
	public JButton getResultBtn2() {
		return this.giveResult2;
	}
	public JButton getResultBtn3() {
		return this.giveResult3;
	}
	public void setResult2(String s){
		this.result2.setText(s);
	}
	public void setResult3(String s) {
		this.result3.setText(s);
	}
	
	// urmeaza pt buton de calculat: void addCalculateListener(ActionListener listenForCalcButton);
	public ArrayList getOperationList() {
		return this.operation;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == additionBtn || e.getSource() == subtractBtn || e.getSource() == multiplyBtn || e.getSource() == divideBtn) {
			this.getSecondPanel();
			if(e.getSource() == additionBtn) operation.set(0, true);
			else if(e.getSource() == subtractBtn) operation.set(1, true);
			else if(e.getSource() == multiplyBtn) operation.set(2, true);
			else if(e.getSource() == divideBtn) operation.set(3, true);
		}
		else if(e.getSource() == derivativeBtn || e.getSource() == integrateBtn) {
			this.getThirdPanel();
			if(e.getSource() == derivativeBtn) operation.set(4, true);
			else if(e.getSource() == integrateBtn) operation.set(5, true);
		}
		else if(e.getSource() == backBtn2 || e.getSource() == backBtn3) {
			this.getMainPanel();
			
			int current_el = 0;
			while(current_el < 6) {
				operation.set(current_el, false);
				current_el ++;
			}
		}
	}
}
