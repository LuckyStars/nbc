
package doctrans.form;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import doctrans.Processor;

public class MainForm extends javax.swing.JFrame {

	private static final long serialVersionUID = 5211367079920624195L;
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.JButton chooseButton;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JTextField pathText;
	private javax.swing.JButton startButton;
	private javax.swing.JLabel statusLabel;
	private JFileChooser fileChooser;
	
	//the processor is current running
	private volatile boolean isProcessing = Boolean.FALSE;
	
	public MainForm() {
		init();
	}

	
	/**
	 * 按下开始
	 * @param evt
	 */
	private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if(this.pathText.getText().length()<=0){
			JOptionPane.showMessageDialog(this, "pleas choose a folder first");
			return;
		}
		if(this.isProcessing){
			JOptionPane.showMessageDialog(this, "the last task is processing now ,please wait a moment");
			return;
		}
		//result is 0 when confirmed
		int result = JOptionPane.showConfirmDialog(this, "confirm run?");
		if(result==0){
			this.isProcessing=Boolean.TRUE;
			new Thread(new Processor(this)).start();
		}
	}
	/**
	 * 按下浏览
	 * @param evt
	 */
	private void chooseButtonActionPerformed(java.awt.event.ActionEvent evt) {
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("打开文件夹");
		int ret = fileChooser.showOpenDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION) {
			//文件夹路径
			this.pathText.setText(fileChooser.getSelectedFile()
					.getAbsolutePath());
		}
	}
	/**
	 * 当处理完成时通知完成
	 */
	public void notifyProcessDone(){
		this.getLogger().replace("处理完成");
		this.isProcessing=Boolean.FALSE;
	}
	
	private void init() {

		pathText = new javax.swing.JTextField();
		statusLabel = new javax.swing.JLabel();
		chooseButton = new javax.swing.JButton();
		startButton = new javax.swing.JButton();
		menuBar = new javax.swing.JMenuBar();
		helpMenu = new javax.swing.JMenu();
		aboutMenuItem = new javax.swing.JMenuItem();
		this.fileChooser = new JFileChooser(File.listRoots()[0]);

		this.setResizable(false);
		this.pathText.setEditable(Boolean.FALSE);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		pathText.setText("");
		statusLabel.setText("当前状态:请选择schoolapp路径");

		chooseButton.setText("浏览");
		chooseButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chooseButtonActionPerformed(evt);
			}
		});

		startButton.setText("开始");
		startButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startButtonActionPerformed(evt);
			}
		});

		helpMenu.setText("Help");
		aboutMenuItem.setText("About");
		
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);
		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																statusLabel,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				pathText,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				303,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				chooseButton)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				startButton)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(pathText,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(chooseButton).addComponent(
										startButton)).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(statusLabel).addContainerGap(
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		pack();
	}
	
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainForm form = new MainForm();
				form.setVisible(true);
				form.logger = new StatusLogger(form);
			}
		});
	}
	
	private StatusLogger logger ;
	public StatusLogger getLogger(){
		return logger;
	}
	protected javax.swing.JLabel getStatusLabel() {
		return statusLabel;
	}
	public String getSelectedPath(){
		return this.pathText.getText();
	}
	
}