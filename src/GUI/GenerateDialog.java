package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Controller.GenerateRequirementController;

public class GenerateDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	
	private GenerateRequirementController controller;
	
	public GenerateDialog(){
		setBounds(100, 100, 600, 900);
		
		controller = new GenerateRequirementController();
		String requirement = controller.generateRequirement();
		
		BoxLayout layout=new BoxLayout(contentPanel, BoxLayout.Y_AXIS); 
		contentPanel.setLayout(layout);

        JTextArea text = new JTextArea();
        text.setBounds(0, 0, 500, 500);
        text.setText(requirement);
        
        JScrollPane scrollPane = new JScrollPane(text);
    	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        JButton export = new JButton("Export");
        
        
		contentPanel.add(text);
		contentPanel.add(export);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		

	    
	}
}
