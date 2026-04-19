package views;

import controllers.RemoveEventController;
import controllers.core.Model;
import controllers.core.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * View responsible for the list of events.
 */
@SuppressWarnings("serial")
public class RemoveEventView extends JPanel implements View
{
	//-----------------------------------------------------------------------
	//		Attributes
	//-----------------------------------------------------------------------
	@SuppressWarnings("unused")
	private RemoveEventController removeEventController;
	private JTable table;


	//-----------------------------------------------------------------------
	//		Constructor
	//-----------------------------------------------------------------------
	/**
	 * It will show the list of saved events.
	 *
	 * @param removeEventController Controller responsible for this view
	 * @param table Table with saved events
	 */
	public RemoveEventView(RemoveEventController removeEventController, JTable table)
	{
		this.removeEventController = removeEventController;
		this.table = table;
		
		make_frame();
		make_btn_cancel();
		make_btn_remove();
		make_btn_select_all();
	}
	
	
	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void update(Model model, Object data) 
	{
		if (data != null) {
			String notice = (String) data;
			JOptionPane.showMessageDialog(this, notice);
		}
	}
	
	/**
	 * Creates view's frame.
	 */
	private void make_frame()
	{
		table.setPreferredScrollableViewportSize(new Dimension(450, 200));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	private void make_btn_cancel()
	{
		// Makes button
		JButton btn_cancel = new JButton("Cancel");
		add(btn_cancel);

		// Add action listener
		btn_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeEventController.cancel();
			}
		});
	}

	private void make_btn_remove()
	{
		// Makes button
		JButton btn_cancel = new JButton("Remove");
		add(btn_cancel);

		// Add action listener
		btn_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeEventController.removeEvent();
			}
		});
	}

	private void make_btn_select_all()
	{
		// Makes button
		JButton btn_cancel = new JButton("Seleccionar Todos");
		add(btn_cancel);

		// Add action listener
		btn_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeEventController.selectAll();
			}
		});
	}
}
