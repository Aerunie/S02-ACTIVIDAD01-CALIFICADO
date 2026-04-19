package controllers;

import controllers.core.Controller;
import models.SchedulerIO;
import views.RemoveEventView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;


/**
 * Responsible for {@link RemoveEventView} behavior.
 */
public class RemoveEventController extends Controller 
{
	//-----------------------------------------------------------------------
	//		Attributes
	//-----------------------------------------------------------------------
	private RemoveEventView removeEventView;
	private JTable table;
	
	
	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void run() 
	{
		table = new JTable(getDataColumns(), getNameColumns());
		removeEventView = new RemoveEventView(this, table);
	}
	
	/**
	 * Adds a new row in a {@link JTable} with the values informed.
	 * 
	 * @param values Values to be add in {@link JTable}
	 */
	public void addNewRow(Object[] values) 
	{
		((DefaultTableModel) table.getModel()).addRow(values);
	}
	
	
	//-----------------------------------------------------------------------
	//		Getters
	//-----------------------------------------------------------------------
	/**
	 * Gets the {@link RemoveEventView view associated with this controller}.
	 * 
	 * @return View associated with this controller
	 */
	public RemoveEventView getView()
	{
		return removeEventView;
	}
	
	/**
	 * Returns the names of the columns of the events list.
	 * 
	 * @return Table metadata in a list
	 */
	public Vector<String> getNameColumns() 
	{
		Vector<String> nameColumns = new Vector<String>();
		
		nameColumns.add("Date");
		nameColumns.add("Description");
		nameColumns.add("Frequency");
		nameColumns.add("E-mail");
		nameColumns.add("Alarm");
		nameColumns.add("Boolean");
		
		return nameColumns;
	}
	
	/**
	 * Returns events list data.
	 * 
	 * @return Table data in a list of lists (matrix)
	 */
	public Vector<Vector<Object>> getDataColumns() 
	{
		Vector<Vector<Object>> dataColumns = null;

		try {
			SchedulerIO schedulerIO = new SchedulerIO();
			schedulerIO.attach(removeEventView);
			dataColumns = schedulerIO.getEventsForRemove();
		} catch (Exception ex) { }

		return dataColumns;
	}
}
