package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BusinessObjects.Action;
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

public class EditActionDialog extends JDialog {
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
	private JLabel lblStep;
	private JComboBox<Step> cbStep;



	/**
	 * Create the dialog.
	 */
	public EditActionDialog(RETGUI parent,Action action) {
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		{
			{
				{
					txtVerb = new JTextField(action.getPhrase().getVerb());
					txtVerb.setBounds(171, 44, 86, 22);
					getContentPane().add(txtVerb);
					lblVerb = new JLabel("Verb : ");
					lblVerb.setBounds(104, 41, 71, 28);
					getContentPane().add(lblVerb);
			
					txtNoun = new JTextField(action.getPhrase().getNoun());
					txtNoun.setBounds(171, 79, 86, 22);
					getContentPane().add(txtNoun);
					lblNoun = new JLabel("Noun : ");
					lblNoun.setBounds(104, 82, 64, 22);
					getContentPane().add(lblNoun);
					txtSentance = new JTextField(action.getPhrase().getSentence());
					txtSentance.setBounds(171, 114, 216, 22);
					getContentPane().add(txtSentance);

					lblSentance=new JLabel("Sentence : ");
					lblSentance.setBounds(80, 114, 71, 22);
					getContentPane().add(lblSentance);

					cbSequenceNumber = new JComboBox();
					cbSequenceNumber.setBounds(171, 146, 86, 22);
					getContentPane().add(cbSequenceNumber);

					lblSequenceNo = new JLabel("Sequence No : ");
					lblSequenceNo.setBounds(57, 146, 93, 16);
					getContentPane().add(lblSequenceNo);

					lblBusinessProcess = new JLabel("Business Process :");
					lblBusinessProcess.setBounds(34, 178, 121, 28);
					getContentPane().add(lblBusinessProcess);

					cbBusinessProcess = new JComboBox<BusinessProcess>();
					cbBusinessProcess.setBounds(171, 178, 216, 22);
					getContentPane().add(cbBusinessProcess);			

					lblStep = new JLabel("Step :");
					lblStep.setBounds(104, 210, 121, 28);
					getContentPane().add(lblStep);

					cbStep = new JComboBox<Step>();
					cbStep.setBounds(171, 210, 216, 22);
					getContentPane().add(cbStep);					


					JButton saveButton = new JButton("Save Action");
					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
						}
					});
					saveButton.setBounds(164, 242, 93, 22);
					getContentPane().add(saveButton);
					saveButton.setActionCommand("OK");
					getRootPane().setDefaultButton(saveButton);


					DefineBusinessProcessController dbpController= new DefineBusinessProcessController();

					List<BusinessProcess> businessProcesses=dbpController.getBusinessProcesses();

					
					
					Step tempStep=(Step)action.getParent();

					for(int i=0; i < businessProcesses.size(); i++)
						cbBusinessProcess.addItem(businessProcesses.get(i));		
					
					cbBusinessProcess.setSelectedItem(tempStep.getParent());
					
					List<Step> steps = dbpController.getSteps(((BusinessProcess)cbBusinessProcess.getSelectedItem()));
					
					for(int i=0; i < steps.size(); i++)
						cbStep.addItem(steps.get(i));	
					
					for(int i = 1; i <= (tempStep.getChildCount()); i++)
						cbSequenceNumber.addItem(i);

					
					
					cbSequenceNumber.setSelectedIndex(tempStep.getActionsList().indexOf(action));
			
					cbStep.setSelectedItem(tempStep);
					
					

					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							
							
							EditBusinessProcessesController editBPController=new EditBusinessProcessesController();
							
							System.out.println( "new selected Index "+cbSequenceNumber.getSelectedIndex());
							
							if(isBoxValid())
							{
								
								editBPController.editAction(action,(Step) cbStep.getSelectedItem(),txtVerb.getText(), txtNoun.getText(), txtSentance.getText(), cbSequenceNumber.getSelectedIndex());
								parent.refreshTree();

								dispose();
							}
							else
							{
								
								JOptionPane.showMessageDialog(new JFrame(), "Invalid Entry", "Dialog",
								        JOptionPane.ERROR_MESSAGE);
								
							}
								
						
						}
					});


					cbBusinessProcess.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
		

							BusinessProcess selectedBusinessProcess = (BusinessProcess)cbBusinessProcess.getSelectedItem();

							List<Step> steps = dbpController.getSteps(selectedBusinessProcess);

							cbStep.removeAllItems();
						
							
							
							if(steps.size() > 0)
							{
								for(int i = 0; i < steps.size(); i++)
									cbStep.addItem(steps.get(i));	
								
								cbStep.setSelectedIndex(0);
								
								Step selectedStep = (Step)cbStep.getSelectedItem();

								cbSequenceNumber.removeAllItems();

								if(action.getParent() == selectedStep )
								{

									for(int i = 0; i < selectedStep.getChildCount(); i++)
										cbSequenceNumber.addItem(i+1);
									cbSequenceNumber.setSelectedIndex(selectedStep.getActionsList().indexOf(action));
									
								}
								else
								{

									for(int i = 0; i <= selectedStep.getChildCount(); i++)
										cbSequenceNumber.addItem(i+1);
									
									cbSequenceNumber.setSelectedIndex(selectedStep.getChildCount());	
								}
								
							}
						
							
						
						}
					});		

					cbStep.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							if(cbStep.getItemCount() == 0) return;

							
								Step selectedStep = (Step)cbStep.getSelectedItem();
								cbSequenceNumber.removeAllItems();

								if(action.getParent() == selectedStep )
								{

									for(int i = 0; i < selectedStep.getChildCount(); i++)
										cbSequenceNumber.addItem(i+1);
									cbSequenceNumber.setSelectedIndex(selectedStep.getActionsList().indexOf(action));
									
								}
								else
								{

									for(int i = 0; i <= selectedStep.getChildCount(); i++)
										cbSequenceNumber.addItem(i+1);
									
									cbSequenceNumber.setSelectedIndex(selectedStep.getChildCount());	
								}

							
								
						}
					});

				}
			}
		}
	}

	public boolean isBoxValid(){
		if(cbStep==null||cbStep.getSelectedItem()==null)
			return false;
		
		if(txtVerb.getText()==null || txtVerb.getText().equals(""))
			return false;
		
		if(txtNoun.getText()==null || txtNoun.getText().equals(""))
			return false;
		
		if(txtSentance.getText()==null || txtSentance.getText().equals(""))
			return false;
		
		if(cbSequenceNumber==null||cbSequenceNumber.getItemCount()==0)
			return false;
		
		return true;
		
	}
}
