package views;

import controllers.NewGuessController;
import controllers.core.Model;
import controllers.core.View;
import models.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;


/**
 * View responsible for a creation of a new event.
 */
@SuppressWarnings("serial")
public class NewGuessView extends JPanel implements View
{
	//-----------------------------------------------------------------------
	//		Attributes
	//-----------------------------------------------------------------------
	private NewGuessController newGuessController;
	private JTextField tf_guess_nombre;
	private JTextField tf_guess_celular;
	private JTextField tf_guess_direccion;
	private JFormattedTextField tf_date;
	private JCheckBox cbx_terminos;
	private JRadioButton rbtn_femenino;
	private JRadioButton rbtn_masculino;


	//-----------------------------------------------------------------------
	//		Constructor
	//-----------------------------------------------------------------------
	/**
	 * @param newGuessController Controller of this view
	 */
	public NewGuessView(NewGuessController newGuessController)
	{
		this.newGuessController = newGuessController;
		
		make_frame();
		make_field_guess_nombre();
		make_field_guess_celular();
		make_field_guess_genero();
		make_field_date();
		make_field_guess_direccion();
		make_field_terminos();
		make_btn_save();
		make_btn_clean();
	}

	
	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void update(Model model, Object data) 
	{
		if (data != null) {
			String notice = (String) data;
			JOptionPane.showMessageDialog(null, notice);
		}
	}
	
	/**
	 * Reset all fields.
	 */
	private void cleanFields() 
	{
		tf_date.setText("");				// Erases date field
		tf_guess_nombre.setText("");			// Erases event description field
		cbx_terminos.setSelected(false);		// Unchecks check box
		tf_guess_celular.setText("");		// Erases forward email field
		rbtn_masculino.setSelected(true);		// Select radio button default
		tf_guess_direccion.setText("");
	}
	
	/**
	 * Creates view's frame.
	 */
	private void make_frame() { setLayout(null); }
	
	/**
	 * Creates event description field.
	 */
	private void make_field_guess_nombre()
	{
		// Makes label
		JLabel lbl_guess_nombre = new JLabel("Ingrese Nombre");
		lbl_guess_nombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_guess_nombre.setBounds(29, 29, 134, 14);
		add(lbl_guess_nombre);
		
		// Makes text field
		tf_guess_nombre = new JTextField();
		tf_guess_nombre.setBounds(200, 26, 196, 20);
		add(tf_guess_nombre);
		tf_guess_nombre.setColumns(10);
	}
	
	/**
	 * Creates forward email field.
	 */
	private void make_field_guess_celular()
	{
		// Makes label
		JLabel lbl_guess_celular = new JLabel("Ingrese número de celular:");
		lbl_guess_celular.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_guess_celular.setBounds(29, 61, 180, 14);
		add(lbl_guess_celular);

		// Makes text field
		tf_guess_celular = new JTextField();
		tf_guess_celular.setBounds(200, 58, 196, 20);
		add(tf_guess_celular);
		tf_guess_celular.setColumns(10);
	}

	private void make_field_guess_direccion()
	{
		// Makes label
		JLabel lbl_guess_direccion = new JLabel("Dirección:");
		lbl_guess_direccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_guess_direccion.setBounds(29, 155, 104, 14);
		add(lbl_guess_direccion);

		// Makes text field
		tf_guess_direccion = new JTextField();
		tf_guess_direccion.setBounds(200, 155, 196, 20);
		add(tf_guess_direccion);
		tf_guess_direccion.setColumns(10);
	}
	
	/**
	 * Creates date field.
	 */
	private void make_field_date()
	{
		// Makes label
		JLabel lbl_date = new JLabel("Fecha de Nacimiento: ");
		lbl_date.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_date.setBounds(29, 129, 78, 14);
		add(lbl_date);

		// Makes text field
		try {
			tf_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
			tf_date.setBounds(200, 125, 96, 20);
			add(tf_date);
			tf_date.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates frequency field.
	 */
	private void make_field_guess_genero()
	{
		final ButtonGroup btng_periodicity = new ButtonGroup();
		
		// Género label
		JLabel lbl_guessgenero = new JLabel("Género");
		lbl_guessgenero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_guessgenero.setBounds(29, 94, 78, 14);
		add(lbl_guessgenero);
		
		// Masculino option
		rbtn_masculino = new JRadioButton("Masculino");
		btng_periodicity.add(rbtn_masculino);
		rbtn_masculino.setSelected(true);
		rbtn_masculino.setBounds(200, 91, 100, 23);
		add(rbtn_masculino);

		// Femenino option
		rbtn_femenino = new JRadioButton("Femenino");
		btng_periodicity.add(rbtn_femenino);
		rbtn_femenino.setBounds(301, 91, 100, 23);
		add(rbtn_femenino);
	}
	
	/**
	 * Creates alarm check box.
	 */
	private void make_field_terminos()
	{
		// Makes check box
		cbx_terminos = new JCheckBox("Acepta Términos y Condiciones");
		cbx_terminos.setBounds(29, 180, 250, 23);
		add(cbx_terminos);
	}
	
	/**
	 * Creates save button.
	 */
	private void make_btn_save()
	{
		// Makes button
		JButton btn_save = new JButton("Save");
		btn_save.setBounds(127, 220, 89, 23);
		add(btn_save);

		// Add action listener
		btn_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Guess guess = new Guess();
				
				guess.setDate(SchedulerUtil.getDateFromString(tf_date.getText()));
				guess.setName(tf_guess_nombre.getText());
				guess.setTerminos(cbx_terminos.isSelected());
				guess.setCelular(tf_guess_celular.getText());
				guess.setDirection(tf_guess_direccion.getText());
				
				if (rbtn_masculino.isSelected()) {
					guess.setGenre(Genre.MALE);
				} else if (rbtn_femenino.isSelected()) {
					guess.setGenre(Genre.FEMALE);
				}
				
				newGuessController.addGuess(guess);
				cleanFields();
			}
		});
	}
	
	/**
	 * Creates clear button.
	 */
	private void make_btn_clean()
	{
		// Makes button
		JButton btn_clean = new JButton("Clean");
		btn_clean.setBounds(253, 220, 89, 23);
		add(btn_clean);

		// Add action listener
		btn_clean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cleanFields();
			}
		});
	}
}
