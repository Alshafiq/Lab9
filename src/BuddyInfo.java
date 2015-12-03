import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class BuddyInfo implements Serializable {

	private String name;
	private String address;
	private int phoneNum;
	
	public BuddyInfo(String name, String address, int phoneNum)
	{
		this.name = name;
		this.address = address;
		this.setPhoneNum(phoneNum);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String toString()
	{
		return (getName() + "$" + getAddress() + "$" + getPhoneNum());
	}
	
	public String toXML()
	{
		String openTag = "<BuddyInfo>" + "\n\t\t";
		String nameTag = "<Name>" + this.getName() + "</Name>" + "\n\t\t";
		String addressTag = "<Address>" + this.getAddress() + "</Address>" + "\n\t\t";
		String phoneNumTag = "<PhoneNum>" + this.getPhoneNum() + "</PhoneNum>" + "\n\t";
		String closeTag = "</BuddyInfo>";
		String xml = openTag+nameTag+addressTag+phoneNumTag+closeTag;
		return xml;
	}
	
	public static BuddyInfo importB(String buddy)
	{
		@SuppressWarnings("resource")
		Scanner s = new Scanner(buddy).useDelimiter("\\$");
		BuddyInfo bud = new BuddyInfo(s.next(),s.next(),s.nextInt());
		
	    return bud; 
	}
}