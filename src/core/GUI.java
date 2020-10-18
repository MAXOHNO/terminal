package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import algorithms.Adder;
import algorithms.Executer;
import algorithms.FaceitStats;
import algorithms.Logger;
import algorithms.Opener;
import algorithms.Searcher;
import algorithms.Visiter;
import algorithms.YouTuber;

public class GUI {

	JFrame frame;
	JLabel labels[] = new JLabel[8];
	JButton button = new JButton();
	JTextField textfield = new JTextField();
	
	
	Executer exec = new Executer();
	FaceitStats faceit = new FaceitStats();
	Visiter visit = new Visiter();
	Opener open = new Opener();
	Searcher search = new Searcher();
	YouTuber yt = new YouTuber();
	
	String inputPrefix = " u: ";
	String reportPrefix = " c: ";
	
	Font font = new Font("Trebuchet MS", Font.BOLD, 28);
	

	Color panelBack = new Color(44, 62, 80);
	Color labelsColor = new Color(4, 62, 80);
	Color labelsColorForeground = new Color(236, 240, 241);

	public GUI(int width, int height) {

		// setBounds(x, y, widht, height)

		for (JLabel label : labels) {
			label = new JLabel();
			label.setOpaque(true);
		}

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        frame.setExtendedState(JFrame.ICONIFIED);
		    }
		});

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setLayout(null);
		panel.setBackground(panelBack);

		// ******************************** Label array für Chat log wird hier generiert
		// ******************
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel("");
			panel.add(labels[i]);
			labels[i].setBounds((int) (width * 0.025), (int) ((i) * (int) (height * 0.10) + height * 0.025), (int) (width * 0.95),
					(int) (height * 0.09));
			// labels[i].setBounds(50, i * 120, 800, 100);
			labels[i].setBackground(labelsColor);
			labels[i].setForeground(labelsColorForeground);
			labels[i].setFont(font);
			labels[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		}

		//panel.add(button); 
		//button.setBounds((int) (width * 0.85), (int) (height * 0.85), (int) (width * 0.125), (int) (height * 0.09));
		//button.setFont(font);

		panel.add(textfield);
		//textfield.setBounds((int) (width * 0.025), (int) (height * 0.85), (int) (width * 0.8), (int) (height * 0.09));
		textfield.setBounds((int) (width * 0.025), (int) (labels.length * (int) (height * 0.10) + height * 0.04), (int) (width * 0.95),
					(int) (height * 0.09));
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
		
		Logger tempLOG = new Logger();
		tempLOG.addLog(cmd);
		
		String[] cmds = cmd.split(" ");
		
		if (cmds.length <= 1) {
			if (cmds[0].contains("help")) {
				reportConsole("cmds1: first page of commands");
				reportConsole("cmds2: - comming soon -");
			} else if (cmds[0].contains("cmds1")) {
				reportConsole("Commands:");
				reportConsole("add <prefix> <object> <path>");
				reportConsole("execute <program>");
				reportConsole("visit <website>");
				reportConsole("search/google/gg <string>");
				reportConsole("youtube/yt <string>");
				reportConsole("faceitstats/fs <faceit_username>");
			} else if (cmds[0].contains("7355608")){ 
				reportConsole("u r cool");
			} else if (cmds[0].contains("exit") || cmds[0].contains("quit")){ 
				System.exit(0);
			} else if (cmds[0].contains("clear")){ 
				for(int i = 0; i < labels.length - 1; i++) {
					scrollLabels();
				}
			} else {
				reportConsole("ERROR: Not enough arguments given - type 'help' for help");
				return;
			}
		}
		
		if (cmds[0].contains("exec")) {
			exec.execute(cmds[1]);
		} else if (cmds[0].contains("visit")) {
			visit.visitWebpage(cmds[1]);
		} else if (cmds[0].contains("add")) {
			Adder tempAdd = new Adder();
			String tempContent = "";
			for (int i = 1; i < cmds.length; i++) {
				tempContent += cmds[i] + " ";
			}
			// TODO: Fix add
			tempAdd.addCommand(tempContent);
		} else if (cmds[0].contains("open")) {
			open.openFolder(cmds[1]);
		} else if (cmds[0].contains("search") || cmds[0].contains("google") || cmds[0].contains("gg")) {
			String tempContent = "";
			for (int i = 1; i < cmds.length; i++) {
				tempContent += cmds[i] + "+";
			}
			search.searchGoogle(tempContent);
		} else if (cmds[0].contains("youtube") || cmds[0].contains("yt")) {
			String tempContent = "";
			for (int i = 1; i < cmds.length; i++) {
				tempContent += cmds[i] + "+";
			}
			yt.youtube(tempContent);
		} else if (cmds[0].contains("faceitstats") || cmds[0].contains("fs")) {
			faceit.getStats(cmds[1]);
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
	
	public void changeTitle(String title) {
		frame.setTitle(title);
	}

}
