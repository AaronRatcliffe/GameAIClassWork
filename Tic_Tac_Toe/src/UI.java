import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextPane;

import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;

import java.awt.TextArea;

public class UI {

	private JFrame frmTictactoe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmTictactoe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		Bord bord = new Bord();
		initialize(bord);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Bord bord) {
		frmTictactoe = new JFrame();
		frmTictactoe.setTitle("Tic_Tac_Toe");
		frmTictactoe.getContentPane().setBackground(Color.BLACK);
		frmTictactoe.setBackground(Color.WHITE);
		frmTictactoe.setBounds(100, 100, 596, 623);
		frmTictactoe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTictactoe.getContentPane().setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bord.set(0,0);
				button.setVisible(false);
				JTextArea textArea = new JTextArea();
				if(bord.get(0, 0) == 0){
					textArea.setText("         X");
				}
				else{
					textArea.setText("         O");
				}
				textArea.setBounds(42, 72, 98, 20);
				frmTictactoe.getContentPane().add(textArea);
			}
		});
		button.setBounds(10, 11, 170, 170);
		frmTictactoe.getContentPane().add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bord.set(1,0);
				button_1.setVisible(false);
				JFormattedTextField frmtdtxtfldx1 = new JFormattedTextField();
				if(bord.get(1, 0) == 0){
					frmtdtxtfldx1.setText("         X");
				}
				else{
					frmtdtxtfldx1.setText("         O");
				}
				frmtdtxtfldx1.setBounds(223, 76, 126, 21);
				frmTictactoe.getContentPane().add(frmtdtxtfldx1);
			}
		});
		
		button_1.setBounds(199, 11, 170, 170);
		frmTictactoe.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bord.set(2,0);
				button_2.setVisible(false);
				JFormattedTextField frmtdtxtfldx2 = new JFormattedTextField();
				if(bord.get(2, 0) == 0){
					frmtdtxtfldx2.setText("         X");
				}
				else{
					frmtdtxtfldx2.setText("         O");
				}
				frmtdtxtfldx2.setBounds(427, 72, 98, 20);
				frmTictactoe.getContentPane().add(frmtdtxtfldx2);
			}
		});
		button_2.setBounds(393, 11, 170, 170);
		frmTictactoe.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bord.set(0,1);
				button_3.setVisible(false);
				JFormattedTextField frmtdtxtfldx3 = new JFormattedTextField();
				if(bord.get(0, 1) == 0){
					frmtdtxtfldx3.setText("         X");
				}
				else{
					frmtdtxtfldx3.setText("         O");
				}
				frmtdtxtfldx3.setBounds(22, 269, 138, 30);
				frmTictactoe.getContentPane().add(frmtdtxtfldx3);
			}
		});
		button_3.setBounds(10, 203, 170, 170);
		frmTictactoe.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bord.set(1,1);
				button_4.setVisible(false);
				JFormattedTextField frmtdtxtfldx4 = new JFormattedTextField();
				if(bord.get(1, 1) == 0){
					frmtdtxtfldx4.setText("         X");
				}
				else{
					frmtdtxtfldx4.setText("         O");
				}
				frmtdtxtfldx4.setBounds(217, 269, 132, 20);
				frmTictactoe.getContentPane().add(frmtdtxtfldx4);
			}
		});
		button_4.setBounds(199, 203, 170, 170);
		frmTictactoe.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bord.set(2,1);
				button_5.setVisible(false);
				JFormattedTextField frmtdtxtfldx5 = new JFormattedTextField();
				if(bord.get(2, 1) == 0){
					frmtdtxtfldx5.setText("         X");
				}
				else{
					frmtdtxtfldx5.setText("         O");
				}
				frmtdtxtfldx5.setBounds(416, 269, 114, 20);
				frmTictactoe.getContentPane().add(frmtdtxtfldx5);
			}
		});
		button_5.setBounds(393, 203, 170, 170);
		frmTictactoe.getContentPane().add(button_5);
		
		JButton button_6 = new JButton("");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bord.set(0,2);
				button_6.setVisible(false);
				JFormattedTextField frmtdtxtfldx6 = new JFormattedTextField();
				if(bord.get(0, 2) == 0){
					frmtdtxtfldx6.setText("         X");
				}
				else{
					frmtdtxtfldx6.setText("         O");
				}
				frmtdtxtfldx6.setBounds(25, 467, 139, 28);
				frmTictactoe.getContentPane().add(frmtdtxtfldx6);
			}
		});
		button_6.setBounds(10, 403, 170, 170);
		frmTictactoe.getContentPane().add(button_6);
		
		JButton button_7 = new JButton("");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bord.set(1,2);
				button_7.setVisible(false);
				JFormattedTextField frmtdtxtfldx8 = new JFormattedTextField();
				if(bord.get(1, 2) == 0){
					frmtdtxtfldx8.setText("         X");
				}
				else{
					frmtdtxtfldx8.setText("         O");
				}
				frmtdtxtfldx8.setBounds(217, 471, 126, 24);
				frmTictactoe.getContentPane().add(frmtdtxtfldx8);
			}
		});
		button_7.setBounds(199, 403, 170, 170);
		frmTictactoe.getContentPane().add(button_7);
		
		JButton button_8 = new JButton("");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bord.set(2,2);
				button_8.setVisible(false);
				JFormattedTextField frmtdtxtfldx8 = new JFormattedTextField();
				if(bord.get(2, 2) == 0){
					frmtdtxtfldx8.setText("         X");
				}
				else{
					frmtdtxtfldx8.setText("         O");
				}
				frmtdtxtfldx8.setBounds(416, 471, 126, 24);
				frmTictactoe.getContentPane().add(frmtdtxtfldx8);
			}
		});
		button_8.setBounds(393, 403, 170, 170);
		frmTictactoe.getContentPane().add(button_8);
		
	}
}
