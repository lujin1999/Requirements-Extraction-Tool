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
import BusinessObjects.Step;
import Controller.DefineBusinessProcessController;
import Controller.EditBusinessProcessesController;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class EditStepDialog extends JDialog {
	private JLabel lblVerb;
	private JLabel lblNoun;
	private JTextField txtVerb;
	private JTextField txtNoun;
	private JLabel lblSentance;
	private JTextField txtSentance;
	private JLabel lblSequenceNumber;
	private JComboBox cbSequenceNumber;
	private JLabel lblSequenceNo;
	private JLabel lblBusinessProcess;
	private JComboBox<BusinessProcess> cbBusinessProcess;

	
	
	/**
	 * Create the dialog.
	 */
	public EditStepDialog(RETGUI parent,Step step) {
		setBounds(100, 100, 450, 324);
		getContentPane().setLayout(null);
		{
			{
				
				
				{
					txtVerb = new JTextField(step.getPhrase().getVerb());
					txtVerb.setBounds(171, 44, 86, 22);
					getContentPane().add(txtVerb);
					lblVerb = new JLabel("Verb : ");
					lblVerb.setBounds(104, 41, 71, 28);
					getContentPane().add(lblVerb);
				
					txtNoun = new JTextField(step.getPhrase().getNoun());
					txtNoun.setBounds(171, 79, 86, 22);
					getContentPane().add(txtNoun);
					lblNoun = new JLabel("Noun : ");
					lblNoun.setBounds(104, 82, 64, 22);
					getContentPane().add(lblNoun);
					txtSentance = new JTextField(step.getPhrase().getSentence());
					txtSentance.setBounds(171, 114, 216, 22);
					getContentPane().add(txtSentance);
					
					lblSentance=new JLabel("Sentence : ");
					lblSentance.setBounds(80, 114, 71, 22);
					getContentPane().add(lblSentance);
					
					cbSequenceNumber = new JComboBox();
					cbSequenceNumber.setBounds(171, 146, 86, 22);
					getContentPane().add(cbSequenceNumber);
					
					lblSequenceNo = new JLabel("Sequence No : ");
					lblSequenceNo.setBounds(57, 149, 93, 16);
					getContentPane().add(lblSequenceNo);
					
					lblBusinessProcess = new JLabel("Business Process :");
					lblBusinessProcess.setBounds(34, 178, 121, 28);
					getContentPane().add(lblBusinessProcess);
					
					cbBusinessProcess = new JComboBox<BusinessProcess>();
					cbBusinessProcess.setBounds(171, 181, 216, 22);
					getContentPane().add(cbBusinessProcess);					
				
						JButton saveButton = new JButton("Save Step");
						saveButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
							}
						});
						saveButton.setBounds(164, 223, 93, 22);
						getContentPane().add(saveButton);
						saveButton.setActionCommand("OK");
						getRootPane().setDefaultButton(saveButton);
					
									
					DefineBusinessProcessController dbpController= new DefineBusinessProcessController();
					BusinessProcess parentBP=(BusinessProcess) step.getParent();
					List<BusinessProcess> businessProcesses=dbpController.getBusinessProcesses();
					System.out.println(businessProcesses.size());
					for(int i=0; i < (businessProcesses.size()); i++)
						cbBusinessProcess.addItem(businessProcesses.get(i));
					
					int tempIndexBp=Repository.getInstance().getBusinessProcessList().indexOf(parentBP);
					
					cbBusinessProcess.setSelectedItem(tempIndexBp);
					
					
					
					for(int i = 1; i <= (parentBP.getStepsList().size() ); i++)
						cbSequenceNumber.addItem(i);
					
					int tempIndexStep=((BusinessProcess)(step.getParent())).getStepsList().indexOf(step);
					System.out.println("index : " +tempIndexStep);
					cbSequenceNumber.setSelectedIndex(tempIndexStep);
						
					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							
							
							EditBusinessProcessesController editBPController=new EditBusinessProcessesController();
							System.out.println( "new selected Index "+cbSequenceNumber.getSelectedIndex());
							editBPController.editStep(step, (BusinessProcess)cbBusinessProcess.getSelectedItem(), txtVerb.getText(), txtNoun.getText(), txtSentance.getText(), cbSequenceNumber.getSelectedIndex());
							
							System.out.println("now refreshing tree");
							
							
							parent.refreshTree();

							dispose();
							
						}
					});
					
					
					cbBusinessProcess.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							
							BusinessProcess selectedBusinessProcess = (BusinessProcess)cbBusinessProcess.getSelectedItem();
							
							cbSequenceNumber.removeAllItems();
							
							for(int i = 1; i <= selectedBusinessProcess.size()+1; i++)
								cbSequenceNumber.addItem(i);
							
							cbSequenceNumber.setSelectedIndex(selectedBusinessProcess.size());									
						}
					});										
					
				}
			}
		}
	}
}
