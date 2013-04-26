package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Form;
import beans.Person;
import event.AddEvent;

public class TesterWindow extends JFrame {

	private JPanel contentPane;
	Person examplePerson = new Person();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesterWindow frame = new TesterWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TesterWindow() {
		examplePerson.setFirstName("Jeanne");
		examplePerson.setLastName("Doe");
		examplePerson.setBestFriend(new Person());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		
		Form jbform = new Form().ignore("bestFriend");
		jbform.setAddEvent(new AddEvent() {
			@Override
			public Object onAdd(Class<?> newClassType) {
				System.out.println(newClassType);
				if(newClassType.equals(Integer.class)) {
					return new Integer(123);
				}
				else if(newClassType.equals(String.class)) {
					return new String("New value");
				}
				else if(newClassType.equals(File.class)) {
					return new File("");
				}
				return null;
			}
		});
		jbform.makeForm(examplePerson);
		contentPane.add(jbform, BorderLayout.CENTER);
	}

}
