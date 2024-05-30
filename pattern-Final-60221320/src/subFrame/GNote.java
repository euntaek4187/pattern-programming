package subFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import global.LocaleData;

public class GNote extends JFrame{
	
	
	public GNote() {
		this.setSize(400, 500);
		this.setLocation(60, 250);
		
		JTextArea noteSpace = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(noteSpace);
        this.add(scrollPane);
        
        JFileChooser fileChooser = new JFileChooser();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(LocaleData.GNote.MENU1);
        JMenuItem newF = new JMenuItem(LocaleData.GNote.MENU2);
        JMenuItem openF = new JMenuItem(LocaleData.GNote.MENU3);
        JMenuItem saveF = new JMenuItem(LocaleData.GNote.MENU4);
        JMenuItem exitF = new JMenuItem(LocaleData.GNote.MENU5);
        
        newF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noteSpace.setText("");
            }
        });

        openF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnInt = fileChooser.showOpenDialog(getParent());
                if (returnInt == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileReader fr = new FileReader(fileChooser.getSelectedFile());
                        BufferedReader br = new BufferedReader(fr);
                        noteSpace.read(br, null);
                        br.close();
                    } catch (IOException ex) {

                    }
                }
            }
        });

        saveF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnInt = fileChooser.showSaveDialog(getParent());
                if (returnInt == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileWriter fw = new FileWriter(fileChooser.getSelectedFile());
                        noteSpace.write(fw);
                        fw.close();
                    } catch (IOException ex) {

                    }
                }
            }
        });

        exitF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        fileMenu.add(newF);
        fileMenu.add(openF);
        fileMenu.add(saveF);
        fileMenu.addSeparator();
        fileMenu.add(exitF);

        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
	}

}
