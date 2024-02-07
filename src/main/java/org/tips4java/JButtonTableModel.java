package org.tips4java;

import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class JButtonTableModel extends RowTableModel<JButton>
{
	private static String[] COLUMN_NAMES =
	{
		"Text",
		"Tool Tip Text",
		"Enabled",
		"Visible"
	};

	JButtonTableModel()
	{
		super( Arrays.asList(COLUMN_NAMES) );
		setRowClass( JButton.class );

		setColumnClass(2, Boolean.class);
		setColumnClass(3, Boolean.class);
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		JButton button = getRow(row);

		switch (column)
		{
			case 0: return button.getText();
			case 1: return button.getToolTipText();
			case 2: return button.isEnabled();
			case 3: return button.isVisible();
			default: return null;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int column)
	{
		JButton button = getRow(row);

		switch (column)
		{
			case 0: button.setText((String)value); break;
			case 1: button.setToolTipText((String)value); break;
			case 2: button.setEnabled((Boolean)value); break;
			case 3: button.setVisible((Boolean)value); break;
		}

		fireTableCellUpdated(row, column);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args)
	{
		JButton one = new JButton("One");
		JButton two = new JButton("Two");
		JButton three = new JButton("Three");

		// toggle this between true and false to see different models in action
		final boolean useJButtonModel = false;

		final RowTableModel model = useJButtonModel 
				//  Use the custom model
			? new JButtonTableModel()
					//  Use the BeanTableModel
			: new BeanTableModel<JButton>(JButton.class, java.awt.Component.class);

		model.addRow(one);
		model.addRow(two);
		model.addRow(three);

		JTable table = new JTable(model);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		JScrollPane scrollPane = new JScrollPane( table );

		JPanel south = new JPanel();
		south.add(one);
		south.add(two);
		south.add(three);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.getContentPane().add( scrollPane );
		frame.getContentPane().add( south, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);

		JButton first = (JButton) model.getRow(0);
		System.out.println(first);
	}
}
