package models;

import controllers.core.Model;
import controllers.core.View;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * Responsible for reading / writing guesss saved.
 */
public class GuessIO implements Model
{
	//-----------------------------------------------------------------------
	//		Attributes
	//-----------------------------------------------------------------------
	private static final String DIRECTORY = ".";
	private static final String FILE = "guess.txt";
	private List<View> views = new ArrayList<>();
	private String notice;

	
	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void attach(View view) 
	{
		views.add(view);
	}

	@Override
	public void detach(View view) 
	{
		views.remove(view);
	}

	@Override
	public void notifyViews() 
	{
		for (View v : views) {
			v.update(this, notice);
		}
	}
	
	public void saveGuess(Guess guess) throws Exception
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY, FILE), true));
			writer.write(guess.toString(), 0, guess.toString().length());
			writer.newLine();
			writer.close();
		} catch (FileNotFoundException fnfe) {
			notice = "File not found"; 
			notifyViews();
		} catch (Exception ex) {
			notice = "Error while writing the file";
			notifyViews();
		}
	}
}