package views;

import controllers.RemoveEventController;
import controllers.core.Model;
import controllers.core.View;

import javax.swing.*;
import java.awt.*;


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
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}
}
