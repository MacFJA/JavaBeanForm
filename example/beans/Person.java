package beans;

import java.awt.Color;
import java.io.File;
import java.util.Calendar;

import ui.ColorUI;
import ui.DateUI;
import ui.FileUI;
import ui.IntoUI;
import ui.NumberUI;
import ui.PasswordUI;
import ui.TextUI;
import validator.PositiveValidator;
import annotation.JBFItem;

public class Person {
	/** Last name of the person */
	private String		lastName		= "Doe";
	/** First name of the person */
	private String		firstName		= "John";
	/** Age of the person */
	private int			age				= 23;
	/** The best friend of the person */
	private Person		bestFriend		= null;
	private String		password		= "";
	private String		description		= "unknow person";
	private Color		favoriteColor	= Color.RED;
	private Calendar	birthday		= Calendar.getInstance();
	private File        photo           = null;

	/**
	 * @return the photo
	 */
	@JBFItem(label = "Photo: ", ui = FileUI.class)
	public File getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(File photo) {
		this.photo = photo;
	}

	/**
	 * @return the birthday
	 */
	@JBFItem(label = "Birthday: ", ui = DateUI.class)
	public final Calendar getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public final void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the favoriteColor
	 */
	@JBFItem(label = "Favorite color:", ui = ColorUI.class)
	public final Color getFavoriteColor() {
		return favoriteColor;
	}

	/**
	 * @param favoriteColor the favoriteColor to set
	 */
	public final void setFavoriteColor(Color favoriteColor) {
		this.favoriteColor = favoriteColor;
	}

	public Person() {
	}

	/**
	 * Get the last name of the person
	 * 
	 * @return the lastName
	 */
	@JBFItem(label = "Last name:")
	public final String getLastName() {
		return lastName;
	}

	/**
	 * Set the last name of the person
	 * 
	 * @param lastName the lastName to set
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the first name of the person
	 * 
	 * @return the firstName
	 */
	@JBFItem(label = "First name:")
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * Set the first name of the person
	 * 
	 * @param firstName the first name to set
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the password
	 */
	@JBFItem(label = "Password:", ui = PasswordUI.class)
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the description
	 */
	@JBFItem(label = "Description:", ui = TextUI.class)
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the age of the person
	 * 
	 * @return the age
	 */
	@JBFItem(label = "Age:", ui = NumberUI.class, validator = PositiveValidator.class)
	public final int getAge() {
		return age;
	}

	/**
	 * Set the age of the person
	 * 
	 * @param age the age to set
	 */
	public final void setAge(int age) {
		this.age = age;
	}

	/**
	 * Get the best friend of the person
	 * 
	 * @return the bestFriend
	 */
	@JBFItem(label = "Best friend:", ui = IntoUI.class)
	public final Person getBestFriend() {
		return bestFriend;
	}

	/**
	 * Set the best friend of the person
	 * 
	 * @param bestFriend the bestFriend to set
	 */
	public final void setBestFriend(Person bestFriend) {
		this.bestFriend = bestFriend;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return lastName + " " + firstName + ", " + age + " year old"
				+ ", best friend: \n\t" + bestFriend;
	}

}
