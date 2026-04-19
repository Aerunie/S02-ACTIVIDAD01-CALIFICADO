package controllers;

import controllers.core.Controller;
import models.SchedulerEvent;
import models.SchedulerIO;
import views.EventListView;
import views.NewGuessView;

import javax.swing.*;


/**
 * Responsible for {@link NewGuessView} behavior.
 */
public class NewGuessController extends Controller 
{
	//-----------------------------------------------------------------------
	//		Attributes
	//-----------------------------------------------------------------------
	private NewGuessView newGuessView;

	
	//-----------------------------------------------------------------------
	//		Constructor
	//-----------------------------------------------------------------------
	
	public NewGuessController()
	{
	}
	
	
	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void run() 
	{
		newGuessView = new NewGuessView(this);
	}
	
	/**
	 * Creates a new {@link SchedulerEvent} and puts it on {@link EventListView}.
	 * 
	 * @param event Event to be added
	 */
	public void addEvent(SchedulerEvent event)
	{
		Object[] metadata = new Object[5];
		metadata[0] = event.getDate();
		metadata[1] = event.getEventDesc();
		metadata[2] = event.getFrequency();
		metadata[3] = event.getFwdEmail();
		metadata[4] = event.getAlarm() ? "ON" : "OFF";

		try {
			SchedulerIO schedulerIO = new SchedulerIO();
			schedulerIO.attach(newGuessView);
			schedulerIO.saveEvent(event);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR", e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//-----------------------------------------------------------------------
	//		Getters
	//-----------------------------------------------------------------------
	/**
	 * Gets the {@link NewGuessView view associated with this controller}.
	 * 
	 * @return View associated with this controller
	 */
	public NewGuessView getView()
	{
		return newGuessView;
	}
}
