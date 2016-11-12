package GUI;

import BusinessObjects.*;
import Commands.*;
import Controller.EditBusinessProcessesController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.Label;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTree;

public class RETGUI extends JFrame {

	protected JTree tree = null;
	protected DefaultMutableTreeNode root;
	protected DefaultTreeModel treeModel;
	public ArrayList<String> arrBP = new ArrayList<String>();
	public ArrayList<String> arrSP = new ArrayList<String>();
	public ArrayList<String> arrAC = new ArrayList<String>();

	Repository repository = Repository.getInstance();

	private JPanel treePane;
	DefaultMutableTreeNode BusinessProcess = null;
	DefaultMutableTreeNode Step = null;
	DefaultMutableTreeNode Action = null;

	Highlighter highlighter;
	JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RETGUI frame = new RETGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RETGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 715);

		treePane = new JPanel();
		treePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(treePane);
		treePane.setLayout(null);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(10, 27, 704, 300);
		textArea.setFont(new Font("Calibri", Font.ITALIC, 20));
		textArea.setSelectedTextColor(Color.red);
		treePane.add(textArea);

		highlighter = textArea.getHighlighter();

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(textArea, popupMenu);

		
		JMenuItem mntmHighlightVerb = new JMenuItem("Highlight Verb");
		JMenuItem mntmHighlightNoun = new JMenuItem("Highlight Noun");
		JMenuItem mntmAddBuisnessProcess = new JMenuItem("Add Business Process");
		JMenuItem mntmAddStep = new JMenuItem("Add Step");
		JMenuItem mntmAddAction = new JMenuItem("Add Action");

		mntmHighlightVerb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					HighlightSelectedWord();
				} catch (BadLocationException e) {

					e.printStackTrace();
				}
			}

			public void HighlightSelectedWord() throws BadLocationException {

				highlighter.addHighlight(textArea.getSelectionStart(), textArea.getSelectionEnd(),
						new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));

			}

		});

		mntmHighlightNoun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				try {
					HighlightSelectedWord();
				} catch (BadLocationException e) {

					e.printStackTrace();
				}
			}

			public void HighlightSelectedWord() throws BadLocationException {

				highlighter.addHighlight(textArea.getSelectionStart(), textArea.getSelectionEnd(),

						new DefaultHighlighter.DefaultHighlightPainter(Color.green));

			}

		});

		JFrame currentFrame = this;

		mntmAddBuisnessProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg2) {
				try {
					AddVerbNounPairAsBusinessProcess();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

			public void AddVerbNounPairAsBusinessProcess() throws BadLocationException {

				
				Phrase tempPhrase = getPhrase();
				CreateBusinessProcessDialog bpDialog = new CreateBusinessProcessDialog((RETGUI)currentFrame,tempPhrase);
				
				bpDialog.setLocationRelativeTo(currentFrame);
				bpDialog.setVisible(true);
			
			}

		});

		mntmAddStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg3) {
				try {
					
					if(Repository.getInstance().getBusinessProcessList().size()!=0)
						AddVerbNounPairAsStep();
					else
						JOptionPane.showMessageDialog(new JFrame(), "You need to add a Business Process first!", "Dialog",
						        JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

			public void AddVerbNounPairAsStep() throws BadLocationException {
				
				Phrase tempPhrase = getPhrase();
				CreateStepDialog stepDialog = new CreateStepDialog((RETGUI)currentFrame,tempPhrase);
				stepDialog.setLocationRelativeTo(currentFrame);
				stepDialog.setVisible(true);
			

			}
		});

		mntmAddAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg4) {
				try {
					if(Repository.getInstance().getBusinessProcessList().size()!=0){
						int num = 0;
						for(int i = 0 ; i < Repository.getInstance().getBusinessProcessList().size();i++){
							num = num + 
									Repository.getInstance().getBusinessProcessList().get(i)
									.getStepsList().size();
						}
						
						if(num!=0)
							AddVerbNounPairAsAction();
						else
							JOptionPane.showMessageDialog(new JFrame(), "You need to add a Step first!", "Dialog",
							        JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(new JFrame(), "You need to add a Step first!", "Dialog",
						        JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

			public void AddVerbNounPairAsAction() throws BadLocationException {
				Phrase tempPhrase = getPhrase();
				CreateActionDialog actionDialog = new CreateActionDialog((RETGUI)currentFrame,tempPhrase);
				actionDialog.setLocationRelativeTo(currentFrame);
				actionDialog.setVisible(true);
				
			

			}
		});

		popupMenu.add(mntmHighlightVerb);
		popupMenu.add(mntmHighlightNoun);
		popupMenu.add(mntmAddBuisnessProcess);
		popupMenu.add(mntmAddStep);
		popupMenu.add(mntmAddAction);

		JLabel lblInputDescription = new JLabel("Input Description");
		lblInputDescription.setBounds(18, 6, 144, 20);
		treePane.add(lblInputDescription);

	

		JButton btnNewButton_1 = new JButton("Import File");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				String FileName = null;
				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("txt Files (*.txt)", "txt");
				
				jfc.setFileFilter(txtFilter);
				int returnval = jfc.showOpenDialog(null);
				if (JFileChooser.APPROVE_OPTION == returnval) {
					File file = jfc.getSelectedFile();
					FileName = file.getAbsolutePath();
				}
				String strInputFile = new String();
				ImportFile objImpFile = new ImportFile();
				strInputFile = objImpFile.FetchInputData(FileName);
				if (strInputFile != null) {
					textArea.setText(strInputFile);
				} else {
					textArea.setText("");
				}
			}
		});

		btnNewButton_1.setBounds(726, 29, 204, 29);
		treePane.add(btnNewButton_1);

		JButton btnGenerateRequirements = new JButton("Generate Requirement");
		btnGenerateRequirements.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new GenerateDialog().setVisible(true);;
				
			
			}

		
		});
		btnGenerateRequirements.setBounds(726, 154, 204, 29);
		treePane.add(btnGenerateRequirements);

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(tree.getLastSelectedPathComponent());
				
				

				Object editedObject = tree.getLastSelectedPathComponent();
				
				if(editedObject instanceof BusinessProcess )
				{
					
					EditBusinessProcessDialog editBpDialog=new EditBusinessProcessDialog((RETGUI)currentFrame, ((BusinessProcess) editedObject));
					editBpDialog.setLocationRelativeTo(currentFrame);
					editBpDialog.setVisible(true);
					
				}
				else if(editedObject instanceof Step)
				{
					EditStepDialog editStepDialog=new EditStepDialog((RETGUI)currentFrame, ((Step) editedObject));
					editStepDialog.setLocationRelativeTo(currentFrame);
					editStepDialog.setVisible(true);
					

				}
				else if(editedObject instanceof Action)
				{
					EditActionDialog editActionDialog=new EditActionDialog((RETGUI)currentFrame, ((Action) editedObject));
					editActionDialog.setLocationRelativeTo(currentFrame);
					editActionDialog.setVisible(true);
					
				
				}
					
				
			}
		});
		editButton.setBounds(726, 72, 204, 29);
		treePane.add(editButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object removedObj = tree.getLastSelectedPathComponent();
				
				EditBusinessProcessesController businessProcessesController=new EditBusinessProcessesController();
				businessProcessesController.removeRequirementComponent((RequirementComponent)removedObj);
				refreshTree();
			
			}
		});
		removeButton.setBounds(726, 113, 204, 29);
		treePane.add(removeButton);


	}

	public void refreshTree() {
		treeModel = new DefaultTreeModel(Repository.getInstance());
		if (tree == null) {
			
			tree = new JTree(treeModel);
			treePane.add(tree);
			tree.setBounds(10, 343, 704, 300);

			tree.addTreeSelectionListener(new TreeSelectionListener() {
				 //TODO
				 public void valueChanged(TreeSelectionEvent e) {
					 
					 
				 } });

			
		} else {
			
			tree.setModel(treeModel);
			
			
			expandAllNodes(tree, 0, tree.getRowCount());
			

		}

	}
	
	private void expandAllNodes(JTree tree, int startingIndex, int rowCount){
	    for(int i=startingIndex;i<rowCount;++i){
	        tree.expandRow(i);
	    }

	    if(tree.getRowCount()!=rowCount){
	        expandAllNodes(tree, rowCount, tree.getRowCount());
	    }
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	private Phrase getPhrase() throws BadLocationException {
		String verb = null, noun = null;
		for (Highlight highlight : highlighter.getHighlights()) {
			if (highlight.getStartOffset() >= textArea.getSelectionStart()
					&& highlight.getEndOffset() <= textArea.getSelectionEnd()) {
				if (((DefaultHighlighter.DefaultHighlightPainter) highlight.getPainter()).getColor() == Color.yellow) {
					verb = textArea.getText(highlight.getStartOffset(),
							highlight.getEndOffset() - highlight.getStartOffset());
				} else if (((DefaultHighlighter.DefaultHighlightPainter) highlight.getPainter())
						.getColor() == Color.green) {
					noun = textArea.getText(highlight.getStartOffset(),
							highlight.getEndOffset() - highlight.getStartOffset());
				}
			}
		}
		return new Phrase(verb, noun);
	}
	
	
	public void createNodes() {
		BusinessProcess bp1 = new BusinessProcess(new Phrase("verb1", "noun1"));
		BusinessProcess bp2 = new BusinessProcess(new Phrase("verb2", "noun2"));
		BusinessProcess bp3 = new BusinessProcess(new Phrase("verb3", "noun3"));

		Step s1 = new Step(new Phrase("verb4", "noun4"));
		Step s2 = new Step(new Phrase("verb5", "noun5"));
		Step s3 = new Step(new Phrase("verb6", "noun6"));

		Action a1 = new Action(new Phrase("verbA1", "nounA1"));
		Action a2 = new Action(new Phrase("verbA1", "nounA1"));
		Action a3 = new Action(new Phrase("verbA1", "nounA1"));

		ListCommand cmd1 = new AddComponent(bp1, s1, 0);
		cmd1.execute();
		cmd1 = new AddComponent(bp1, s2, 1);
		cmd1.execute();
		cmd1 = new AddComponent(bp1, s3, 1);
		cmd1.execute();
		cmd1 = new AddComponent(s1, a1, 0);
		cmd1.execute();
		cmd1 = new AddComponent(s2, a2, 0);
		cmd1.execute();
		cmd1 = new AddComponent(s3, a3, 0);
		cmd1.execute();
		cmd1 = new AddComponent(repository, bp1, 0);
		cmd1.execute();
		cmd1 = new AddComponent(repository, bp2, 1);
		cmd1.execute();
		cmd1 = new AddComponent(repository, bp3, 1);
		cmd1.execute();

		for (int i = 0; i < arrBP.size(); i++) {
			BusinessProcess = new DefaultMutableTreeNode(arrBP.get(i));
			repository.add(BusinessProcess);

			BusinessProcess.setUserObject(arrBP);
			for (int j = 0; j < arrSP.size(); j++) {
				Step = new DefaultMutableTreeNode(arrSP.get(j));
				BusinessProcess.add(Step);

				for (int k = 0; k < arrAC.size(); k++) {
					Action = new DefaultMutableTreeNode(arrAC.get(k));
					Step.add(Action);
				}

			}

		}

	}
}
