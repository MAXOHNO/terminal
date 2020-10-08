package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {

	JLabel labels[] = new JLabel[8];
	JButton button = new JButton();
	JTextField textfield = new JTextField();
	Execute exec = new Execute();
	Add add = new Add();
	
	String inputPrefix = " u: ";
	String reportPrefix = " c: ";
	
	Font font = new Font("Trebuchet MS", Font.BOLD, 40);
	

	Color panelBack = new Color(44, 62, 80);
	Color labelsColor = new Color(4, 62, 80);
	Color labelsColorForeground = new Color(236, 240, 241);

	public GUI(int width, int height) {

		// setBounds(x, y, widht, height)

		for (JLabel label : labels) {
			label = new JLabel();
		}

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setLayout(null);
		panel.setBackground(panelBack);

		// ******************************** Label array für Chat log wird hier generiert
		// ******************
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel("");
			panel.add(labels[i]);
			labels[i].setBounds((int) (width * 0.025), (i) * (int) (height * 0.10) + 20, (int) (width * 0.95),
					(int) (height * 0.09));
			// labels[i].setBounds(50, i * 120, 800, 100);
			labels[i].setOpaque(true);
			labels[i].setBackground(labelsColor);
			labels[i].setForeground(labelsColorForeground);
			labels[i].setFont(font);
			labels[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		}

		panel.add(button); 
		button.setBounds((int) (width * 0.85), (int) (height * 0.85), (int) (width * 0.125), (int) (height * 0.09));
		button.setFont(font);

		panel.add(textfield);
		textfield.setBounds((int) (width * 0.025), (int) (height * 0.85), (int) (width * 0.8), (int) (height * 0.09));
		textfield.setFont(font);

		
		// ********************** Action Listener *******************************
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				submitInput();
			}
		});
		
		textfield.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				submitInput();
				
			}
		});

		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public void submitInput() {
		scrollLabels();
		labels[labels.length - 1].setText(inputPrefix + textfield.getText());
		listenForCommand(textfield.getText());
		textfield.setText("");
	}
	
	public void listenForCommand(String cmd) {
		
		String[] cmds = cmd.split(" ");
		
		if (cmds.length <= 1) {
			if (cmds[0].contains("help")) {
				reportConsole("*************************");
				reportConsole("Commands:");
				reportConsole("add <prefix> <object> <path>");
				reportConsole("execute <program>");
				reportConsole("visit <website>");
				reportConsole("*************************");
			} else {
				reportConsole("ERROR: Not enough arguments given");
				return;
			}
		}
		
		if (cmds[0].contains("exec")) {
			exec.execute(cmds[1]);
		} else if (cmds[0].contains("visit")) {
			System.out.println(cmds[0]);
		} else if (cmds[0].contains("add")) {
			String tempContent = "";
			for (int i = 1; i < cmds.length; i++) {
				tempContent += cmds[i] + " ";
			}
			add.addCommand(tempContent);
		}
		
	}
	
	public void reportConsole(String arg) {
		scrollLabels();
		labels[labels.length - 1].setText(reportPrefix + arg);
	}

	public void scrollLabels() {
		for (int i = 0; i < labels.length; i++) {
			if (i < labels.length - 1) {
				labels[i].setText(labels[i + 1].getText());
			} else {
				labels[i].setText("");
			}
		}
	}

}
