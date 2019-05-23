//Haena Song, CIS 340, 1:30 TTH, MP2
public class Device {
	private String sku;
	private String name;
	private boolean status;

	public String getSku() {
		return sku.toUpperCase();
	}

	public String getName() {
		return name;
	}

	public boolean getAvailability() {
		return status;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAvailability(boolean status) {
		this.status = status;
	}

	// constructor for default value
	public Device() {
		this.sku = "N/A";
		this.name = "N/A";
	}

	public Device(String sku, String name) {
		setSku(sku);
		setName(name);
		setAvailability(true);
	}

	// method for telling the user if the device is available or not
	public String availableDevice() {
		String availability = "";
		if (status == true) {
			availability = "Available";
		}
		if (status == false) {
			availability = ("Checked Out");
		}
		return availability;
	}
}
