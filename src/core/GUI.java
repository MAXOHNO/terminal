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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
	JLabel labels[] = new JLabel[10];
	JButton button = new JButton();
	JTextField textfield = new JTextField();

	Executer exec = new Executer();
	FaceitStats faceit = new FaceitStats();
	Visiter visit = new Visiter();
	Opener open = new Opener();
	Searcher search = new Searcher();
	YouTuber yt = new YouTuber();

	String inputPrefix = " input:      ";
	String reportPrefix = " output:    ";

	Font font = new Font("Trebuchet MS", Font.BOLD, 32);

	Color panelBack = Color.decode("#2b2d42");
	
	Color labelsColor = panelBack;
	Color labelsColorBorder = Color.decode("#8d99ae");
	Color labelsColorText = Color.decode("#DCE0DF");
	
	Color textColor = Color.decode("#edf2f4");
	Color textColorBorder = Color.decode("#8d99ae");
	Color textColorText = panelBack;

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
		float magicNumber = 0.083f;
		float spaceBetween = 0.01f;
		
		// Initiate labels (visual logs)
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel("");
			panel.add(labels[i]);
			labels[i].setBounds((int) (width * 0.025), (int) ((i) * (int) (height * magicNumber) + height * 0.025),
					(int) (width * 0.95), (int) (height * (magicNumber - spaceBetween)));
			labels[i].setOpaque(true);
			labels[i].setBackground(labelsColor);
			labels[i].setForeground(labelsColorText);
			labels[i].setFont(font);
			labels[i].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, labelsColorBorder));
		}

		// Initiate textfield
		
		panel.add(textfield);
		textfield.setBounds((int) (width * 0.025), (int) (labels.length * (int) (height * magicNumber) + height * 0.04),
				(int) (width * 0.95), (int) (height * (magicNumber - spaceBetween)));
		textfield.setOpaque(true);
		textfield.setFont(font);
		textfield.setBackground(textColor);
		textfield.setForeground(textColorText);
		
		textfield.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, textColorBorder));

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
				SoundManager.playSound(Terminal.getSoundConfirm(), -10f);
			}

		});

		textfield.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

				SoundManager.playSound(Terminal.getSoundRemove(), -20f);

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				SoundManager.playSound(Terminal.getSoundType(), -20f);

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

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
			if (cmds[0].equals("help")) {
				reportConsole("cmds1: first page of commands");
				reportConsole("cmds2: - comming soon -");
			} else if (cmds[0].equals("cmds1")) {
				reportConsole("Commands:");
				reportConsole("add <prefix> <object> <path>");
				reportConsole("execute <program>");
				reportConsole("visit <website>");
				reportConsole("search/google/gg <string>");
				reportConsole("youtube/yt <string>");
				reportConsole("faceitstats/fs <faceit_username>");
			} else if (cmds[0].equals("7355608")) {
				reportConsole("u r cool");
			} else if (cmds[0].equals("exit") || cmds[0].contains("quit")) {
				System.exit(0);
			} else if (cmds[0].equals("clear")) {
				for (int i = 0; i < labels.length - 1; i++) {
					scrollLabels();
				}
			} else {
				reportConsole("ERROR: Not enough arguments given - type 'help' for help");
				return;
			}
		}

		if (cmds[0].equals("exec") || cmds[0].equals("execute")) {
			Executer exec = new Executer();
			exec.execute(cmds[1]);
		} else if (cmds[0].equals("visit")) {
			Visiter visit = new Visiter();
			visit.visitWebpage(cmds[1]);
		} else if (cmds[0].equals("add")) {
			Adder tempAdd = new Adder();
			String tempContent = "";
			for (int i = 1; i < cmds.length; i++) {
				tempContent += cmds[i] + " ";
			}
			tempAdd.addCommand(tempContent);
		} else if (cmds[0].equals("open")) {
			Opener open = new Opener();
			open.openFolder(cmds[1]);
		} else if (cmds[0].equals("search") || cmds[0].equals("google") || cmds[0].equals("gg")) {
			Searcher search = new Searcher();
			String tempContent = "";
			for (int i = 1; i < cmds.length; i++) {
				tempContent += cmds[i] + "+";
			}
			search.searchGoogle(tempContent);
		} else if (cmds[0].equals("youtube") || cmds[0].equals("yt")) {
			YouTuber yt = new YouTuber();
			String tempContent = "";
			for (int i = 1; i < cmds.length; i++) {
				tempContent += cmds[i] + "+";
			}
			yt.youtube(tempContent);
		} else if (cmds[0].equals("faceitstats") || cmds[0].equals("fs")) {
			FaceitStats faceit = new FaceitStats();
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
