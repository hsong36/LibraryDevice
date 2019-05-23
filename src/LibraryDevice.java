//Haena Song, CIS 340, 1:30 TTH, MP2 
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryDevice {
	Scanner scan = new Scanner(System.in);

	private static ArrayList<Device> deviceLibrary; // declare an ArrayList
	Utilities myUtilities = new Utilities();

	public static void main(String[] args) {
		LibraryDevice myLibrary = new LibraryDevice();
		myLibrary.inializeArray();
		myLibrary.preloadSampleData();
		myLibrary.loadSystem();
	}// end of main

	public void inializeArray() { // initialize the ArrayList
		deviceLibrary = new ArrayList<Device>(10); // hardcode the size of the arraylist to 10
	}

	private void addNewDevices() { // method for adding devices from 5
		if (deviceLibrary.size() > 9) { // allow the user to only add 10 devices
			System.out.println("\nOnly ten devices in the list.");
		} else {
			String sku;
			String name;
			sku = askSKU();
			name = askName();
			addDevice(sku, name);
			System.out.printf("\nAdded %s to the Catalog.\n", name);
		}
	}

	// method for adding devices
	private void addDevice(String sku, String name) {
		Device tmpDevice = new Device(sku, name);
		deviceLibrary.add(tmpDevice);
	}

	private String askSKU() {
		String sku;
		System.out.print("\nSku: "); // ask the user for the SKU
		sku = scan.nextLine();
		return sku;
	}

	private String askName() {
		String name;
		System.out.print("Name: "); // ask the user for the name
		name = scan.nextLine();
		return name;
	}

	private int askDeviceNumber() {
		int number = 0;
		number = myUtilities.readInteger("\nEnter device number: ") - 1;
		// decrease the number to match the index number
		return number;
	}

	private void preloadSampleData() { // method for preloading existing devices
		addDevice("6757A", "Apple 9.7-inch iPad Pro");
		addDevice("93P51B", "Amazon Kindle Fire Kids Edition");
		addDevice("10N8C", "LeapFrog Epic Learning Tablet");
		addDevice("85U20", "Amazon Kindle Fire HD 8");
		addDevice("91H2D", "HP Envy 8 Note");
		deviceLibrary.get(3).setAvailability(false); // set the device #4 to checked out
	}

	private void displayList() { // method for displaying each devices in the list
		int number = 0;
		System.out.println("\n\n\n\n\n\tLibrary Device Checkout System - List\n\n");
		System.out.println("  # SKU\t      Name");
		// print each device in the arrayList
		for (Device d : deviceLibrary) {
			System.out.printf("  %d %-10s%-40s%s\n", ++number, d.getSku(), d.getName(), d.availableDevice());
		}
	}

	private void displayHeader() {
		System.out.println("\t\tLibrary Device Checkout System\n");
	}

	private void editDevice() { // method for editing the device
		int number;
		String sku;
		String name;
		displayList();
		// call the method for checking if it is valid integer
		number = myUtilities.readInteger("\nEnter Device number to edit: ") - 1;
		if (number > deviceLibrary.size() || number < 0) {
			myUtilities.invalidNumber(); // tell the user to print a valid number
		} else {
			sku = askSKU();
			name = askName();
			deviceLibrary.get(number).setSku(sku); // edit sku
			deviceLibrary.get(number).setName(name); // edit name
			System.out.println("\nDevice information updated.");
		}
	}

	private void searchDevice() { // method for searching the device using contains function
		String keyword; // search for the keyword
		int number = 0; // device number
		System.out.println("\n\n\n\n\n\t\tLibrary Device Check Out System - Search\n\n");
		System.out.print("Enter Device to search for: ");
		keyword = scan.nextLine().toLowerCase(); // convert it to lower case
		System.out.printf("\nListings for '%s'\n", keyword);
		System.out.println(" # SKU       Name");
		for (Device d : deviceLibrary) {
			++number; // increment the device number
			// print the devices containing the keyword
			if (d.getName().toLowerCase().contains(keyword)) {
				System.out.printf(" %d %-10s%s\n", number, d.getSku(), d.getName());
			}
		}
	}

	private void checkOutDevice() { // method for checking out device
		int number = 0;
		System.out.println("\n\n\n\n\n\t\tLibrary Device Check Out System - Check Out Device");
		System.out.println("\nAvailable Devices\n\n # SKU\t     Name\n");
		for (Device d : deviceLibrary) { // print the available devices
			++number;
			if (d.getAvailability() == true) {
				System.out.printf(" %d %-10s%s\n", number, d.getSku(), d.getName());
			}
		}
		try {
			number = askDeviceNumber();
			// if the device is not available (false)
			if (deviceLibrary.get(number).getAvailability() == false) {
				System.out.println("Device is not available");
			} else { // otherwise, set the availability to false
				deviceLibrary.get(number).setAvailability(false);
				System.out.print("\nDevice Checked Out.");
			}
		} catch (Exception ex) {
			myUtilities.invalidNumber(); // if the number is out of bounds
		}
	}

	private void checkInDevice() { // method for check in devices
		int number = 0;
		System.out.println("\n\n\n\n\n\t\tLibrary Device Check Out System - Check In Device");
		System.out.println("\nChecked Out Devices\n\n # SKU\t     Name");
		for (Device d : deviceLibrary) { // print the devices that are checked out
			++number;
			if (d.getAvailability() == false) {
				System.out.printf(" %d %-10s%s\n", number, d.getSku(), d.getName());
			}
		}
		try {
			number = askDeviceNumber();
			if (deviceLibrary.get(number).getAvailability() == true) {
				System.out.println("Device is already Checked In.");
			} else {
				deviceLibrary.get(number).setAvailability(true);
				System.out.print("\nDevice Checked In.");
			}
		} catch (Exception ex) {
			myUtilities.invalidNumber();
		}
	}

	private void displayMenu() { /// method for displaying the menu
		System.out.print(
				"\n1. List Device by Title\n2. Add New Devices\n3. Edit Device Information\n4. Search by Device Name\n5. Check Out Devices\n6. Check In Devices\n7. Exit\n\n");
	}

	private void loadSystem() {
		int input = 0; // for user menu choice
		boolean contLoop = false;
		do {
			displayHeader();
			displayMenu();
			input = myUtilities.readInteger("\nSelect menu options 1-7: ");
			switch (input) {
			case 1:
				displayList();
				break;
			case 2:
				addNewDevices();
				break;
			case 3:
				editDevice();
				break;
			case 4:
				searchDevice();
				break;
			case 5:
				checkOutDevice();
				break;
			case 6:
				checkInDevice();
				break;
			case 7:
				System.out.print("\nGood bye!");
				System.exit(0);
				contLoop = false;
				break;
			default: // if the user press wrong number
				myUtilities.invalidNumber();
			}
			System.out.print("\nPress enter to continue... ");
			scan.nextLine();
			System.out.println("\n\n\n\n\n");
		} while (contLoop = true); // continue till the user enters 7 for exit
	}
}// end of class
