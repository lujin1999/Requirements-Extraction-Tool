package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BusinessObjects.BusinessProcess;
import BusinessObjects.Phrase;
import BusinessObjects.Repository;
import Controller.DefineBusinessProcessController;
import Controller.EditBusinessProcessesController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditBusinessProcessDialog extends JDialog {
	private JLabel lblVerb;
	private JLabel lblNoun;
	private JTextField txtVerb;
	private JTextField txtNoun;
	private JLabel lblSentance;
	private JTextField txtSentance;
	private JLabel lblSequenceNumber;
	private JComboBox cbSequenceNumber;

	

	/**
	 * Create the dialog.
	 */
	public EditBusinessProcessDialog(RETGUI parent,BusinessProcess bp) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			{
				
				{
					txtVerb = new JTextField(bp.getPhrase().getVerb());
					txtVerb.setBounds(171, 44, 86, 22);
					getContentPane().add(txtVerb);
					lblVerb = new JLabel("Verb : ");
					lblVerb.setBounds(104, 41, 71, 28);
					getContentPane().add(lblVerb);
					
					txtNoun = new JTextField(bp.getPhrase().getNoun());
					txtNoun.setBounds(171, 79, 86, 22);
					getContentPane().add(txtNoun);
					lblNoun = new JLabel("Noun : ");
					lblNoun.setBounds(104, 82, 64, 22);
					getContentPane().add(lblNoun);
					txtSentance = new JTextField(bp.getPhrase().getSentence());
					txtSentance.setBounds(171, 114, 216, 22);
					getContentPane().add(txtSentance);
					
					lblSentance=new JLabel("Sentence : ");
					lblSentance.setBounds(104, 114, 71, 22);
					getContentPane().add(lblSentance);
					
					JComboBox cbSequenceNumber = new JComboBox();
					cbSequenceNumber.setBounds(171, 146, 86, 22);
					getContentPane().add(cbSequenceNumber);
					
					JLabel lblNewLabel = new JLabel("Sequence No : ");
					lblNewLabel.setBounds(104, 149, 56, 16);
					getContentPane().add(lblNewLabel);
				
						JButton saveButton = new JButton("Save");
						saveButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
							}
						});
						saveButton.setBounds(164, 201, 93, 22);
						getContentPane().add(saveButton);
						saveButton.setActionCommand("OK");
						getRootPane().setDefaultButton(saveButton);
					
					

					lblSequenceNumber = new JLabel("Sequence Number : ");
				
					
					DefineBusinessProcessController dbpController= new DefineBusinessProcessController();
					int numberOfBusinessProcess=dbpController.getBusinessProcesses().size();
					
					for(int i = 1; i <=( numberOfBusinessProcess); i++)
						cbSequenceNumber.addItem(i);
					
					int tempBp=Repository.getInstance().getBusinessProcessList().indexOf(bp);
					System.out.println("currently selected" + bp + " "+ tempBp);
					
					//TODO
					cbSequenceNumber.setSelectedIndex(tempBp);
			
					
					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							
							EditBusinessProcessesController editBPController=new EditBusinessProcessesController();
							System.out.println( "new selected Index "+cbSequenceNumber.getSelectedIndex());
							editBPController.editBusinessProcess(bp, txtVerb.getText(), txtNoun.getText(), txtSentance.getText(), cbSequenceNumber.getSelectedIndex());
							
							parent.refreshTree();
							
							dispose();
							
						}
					});
					
					
				}
			}
		}
	}
}
