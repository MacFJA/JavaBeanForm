package ui;

import java.awt.Component;

import javax.swing.JPanel;

import event.AddEvent;
import event.UpdateEvent;
import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

public class FileUI extends JPanel implements UI {
	private File file;
	private JTextField textField;
	private JButton btnSelect;
	private UpdateEvent updateEvent = null;
	
	public FileUI() {
		setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setEditable(false);
		add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		btnSelect = new JButton("Select...");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(Boolean.FALSE);
				if(chooser.showDialog(FileUI.this, btnSelect.getText()) == JFileChooser.APPROVE_OPTION) {
					file = chooser.getSelectedFile();
					setValueOfUI(chooser.getSelectedFile());
					if(updateEvent != null) updateEvent.onChange(file);
				}
			}
		});
		add(btnSelect, BorderLayout.EAST);
		
		DropTargetListener dtl = new DropTargetListener() {
			@Override
			public void dropActionChanged(DropTargetDragEvent dtde) {}
			
			@Override
			public void drop(DropTargetDropEvent event) {
		        event.acceptDrop(DnDConstants.ACTION_REFERENCE);
		        Transferable transferable = event.getTransferable();
		        DataFlavor[] flavors = transferable.getTransferDataFlavors();

		        for (DataFlavor flavor : flavors) {
		            try {
		                if (flavor.isFlavorJavaFileListType()) {
		                    @SuppressWarnings("unchecked")
							List<File> files = (List<File>) transferable.getTransferData(flavor);
		                    for (File file : files) {
		                    		FileUI.this.setValueOfUI(file);
		                    }
		                }
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		        event.dropComplete(true);
			}
			
			@Override
			public void dragOver(DropTargetDragEvent dtde) {}
			
			@Override
			public void dragExit(DropTargetEvent dte) {}
			
			@Override
			public void dragEnter(DropTargetDragEvent dtde) {}
		};
		
		new DropTarget(textField, dtl);
	}

	@Override
	public void setUpdateEvent(UpdateEvent updateEvent) {
		this.updateEvent = updateEvent;
	}

	@Override
	public void setAddEvent(AddEvent addEvent) {}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public void setValueOfUI(Object value) {
		if(String.class.isInstance(value)) {
			file = new File((String)value);
		}
		else if(File.class.isInstance(value)) {
			file = (File)value;
		}
		else {
			file = null;
		}
		
		textField.setText(file==null?"":file.getPath());
	}

}
