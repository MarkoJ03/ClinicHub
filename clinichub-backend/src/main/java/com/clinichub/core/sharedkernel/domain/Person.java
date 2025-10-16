package com.clinichub.core.sharedkernel.domain;




import com.clinichub.core.shared.types.PersonId;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class Person {

	
	private PersonId id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Address address;
	
	
	public Person(PersonId id,
            String firstName,
            String lastName,
            String email,
            String phone,
            Address address) {
  setFirstName(firstName);
  setLastName(lastName);
  setEmail(email);
  setPhone(phone);
  if (address == null) throw new IllegalArgumentException("address");
  this.id = id;
  this.address = address;
}

	public void changeFirstName(String firstName) { setFirstName(firstName); }
	public void changeLastName(String lastName)   { setLastName(lastName); }
	public void changeEmail(String email)         { setEmail(email); }
	public void changePhone(String phone)         { setPhone(phone); }
	public void changeAddress(Address address) {
	  if (address == null) throw new IllegalArgumentException("address");
	  this.address = address;
	}
	
	public void changeNames(String firstName, String lastName) {
	  setFirstName(firstName);
	  setLastName(lastName);
	}
	

	private void setFirstName(String v) {
	  if (v == null || v.isBlank()) throw new IllegalArgumentException("firstName");
	  this.firstName = v.trim();
	}
	private void setLastName(String v) {
	  if (v == null || v.isBlank()) throw new IllegalArgumentException("lastName");
	  this.lastName = v.trim();
	}
	private void setEmail(String v) {
	  if (v == null || v.isBlank() || !v.contains("@")) throw new IllegalArgumentException("email");
	  this.email = v.trim();
	}
	private void setPhone(String v) {
	  if (v == null || v.isBlank()) throw new IllegalArgumentException("phone");
	  this.phone = v.trim();
	}
}
