package controllers;

import controllers.core.Controller;
import models.Guess;
import models.GuessIO;
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
	
	public void addGuess(Guess guess)
	{
		try {
			GuessIO guessIO = new GuessIO();
			guessIO.attach(newGuessView);
			guessIO.saveGuess(guess);

			JOptionPane.showMessageDialog(null, "Invitado registrado", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);

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
