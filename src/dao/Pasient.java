package dao;

import java.io.Serializable;

public class Pasient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1691023789719273095L;
	private int pasientID;
	private String socialSecurityNumber;
	private String firstname;
	private String lastname;
	private byte[] picture;

	public Pasient(){
		
	}

	public Pasient(int pasientID, String socialSecurityNumber, String firstname,
			String lastname) {
		this.pasientID = pasientID;
		this.socialSecurityNumber = socialSecurityNumber;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Pasient(int pasientID, String socialSecurityNumber, String firstname,
			String lastname, byte[] picture) {
		this.pasientID = pasientID;
		this.socialSecurityNumber = socialSecurityNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.picture = picture;
	}

	public int getPasientID() {
		return pasientID;
	}

	public void setPasientID(int pasientID) {
		this.pasientID = pasientID;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Pasient [pasientID=" + pasientID + ", socialSecurityNumber="
				+ socialSecurityNumber + ", firstname=" + firstname
				+ ", lastname=" + lastname + "]";
	}
}
