package selenium_api;

public class Customer {
	String name;
	String birth;
	String address;
	String city;
	String state;
	String pin;
	String email;
	String mobileNumber;
	String passWord;
	
	public Customer() {
		super();
		this.name = "Linh Nguyen";
		this.birth = "15/06/1996";
		this.address = "101 Nguyen Van Dau";
		this.city = "HCM";
		this.state = "HCM";
		this.pin = "123456";
		this.email = RandomEmail.getRandomEmail();
		this.mobileNumber = "0975151444";
		this.passWord = "Linhnguyen01";
	}



	
}