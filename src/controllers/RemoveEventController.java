package controllers;

import controllers.core.Controller;
import models.Frequency;
import models.SchedulerEvent;
import models.SchedulerIO;
import models.SchedulerUtil;
import views.RemoveEventView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
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

	private EventListController eventListController;

	public RemoveEventController(EventListController eventListController){
		this.eventListController = eventListController;
	}
	
	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void run() 
	{
		table = new JTable();
		table = customModelTable(getDataColumns(), getNameColumns());
		removeEventView = new RemoveEventView(this, table);
	}

	public void listEvents()
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while(model.getRowCount() > 0){
			model.removeRow(0);
		}

		for (Vector<Object> row : getDataColumns()){
			model.addRow(row);
		}

		table.setModel(model);

		eventListController.listEvents();
	}

	public void cancel(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = 0; i < model.getRowCount(); i++) {
			model.setValueAt(Boolean.FALSE, i, 5);
		}

		table.setModel(model);
	}

	public void removeEvent(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		List<SchedulerEvent> events = new ArrayList<>();
		for (int i = 0; i < model.getRowCount(); i++) {
			Boolean selected = (Boolean) model.getValueAt(i, 5);
			if(!selected){
				SchedulerEvent event = new SchedulerEvent();
				event.setDate(SchedulerUtil.getDateFromString(model.getValueAt(i, 0).toString()));
				event.setEventDesc(model.getValueAt(i, 1).toString());
				event.setFrequency((Frequency) model.getValueAt(i, 2));
				event.setFwdEmail(model.getValueAt(i, 3).toString());
				event.setAlarm(model.getValueAt(i, 4).toString().equals("ON"));

				events.add(event);
			}
		}

		try {
			SchedulerIO schedulerIO = new SchedulerIO();
			schedulerIO.attach(removeEventView);
			schedulerIO.syncEvents(events);

			JOptionPane.showMessageDialog(null, "Evento eliminado", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR", e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

		listEvents();
	}

	public void selectAll(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = 0; i < model.getRowCount(); i++) {
			model.setValueAt(Boolean.TRUE, i, 5);
		}

		table.setModel(model);
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

	private JTable customModelTable(Vector<Vector<Object>> data, Vector<String> columns){
		DefaultTableModel model = new DefaultTableModel(data, columns) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 5) { // columna Boolean
					return Boolean.class;
				}
				return String.class;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 5; // solo editable el checkbox
			}
		};

		return new JTable(model);
	}

}
