package org.tips4java;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledEditorKit;

public class CompoundUndoManagerDemo
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI()
    {
		//  Create a simple toolbar

		JToolBar toolBar = new JToolBar();
		toolBar.add( createButtonFromAction( new StyledEditorKit.BoldAction() ) );
		toolBar.add( createButtonFromAction( new StyledEditorKit.ItalicAction() ) );
		toolBar.add( createButtonFromAction( new StyledEditorKit.UnderlineAction() ) );

		//  Create a text pane

		final JTextPane textPane = new JTextPane();
		textPane.setPreferredSize( new Dimension(200, 200) );

		String text =
			"public class SomeClass\n" +
			"{\n\t//  Enter code here\n}";
		textPane.setText(text);
		CompoundUndoManager undoManager = new CompoundUndoManager( textPane );

		//  Create undo/redo buttons

		JPanel buttons = new JPanel();
		JButton undo = new JButton(undoManager.getUndoAction());
		buttons.add( undo );
		JButton redo = new JButton(undoManager.getRedoAction());
		buttons.add( redo );

		//  Add components to the frame

		JPanel main = new JPanel();
		main.setLayout( new BorderLayout() );
		main.add(toolBar, BorderLayout.NORTH);
		main.add( new JScrollPane(textPane), BorderLayout.CENTER );
		main.add(buttons, BorderLayout.SOUTH);

        JFrame frame = new JFrame("Compound Undo Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(main);
        frame.setSize(450, 240);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

	private static JButton createButtonFromAction(Action action)
	{
		JButton button = new JButton( action );
		button.setRequestFocusEnabled(false);
		return button;
	}
}
