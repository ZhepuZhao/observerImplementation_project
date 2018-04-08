package a7jedi;

import java.util.NoSuchElementException;

import a7jedi.PlateEvent.EventType;
import comp401.observable.Observable;
import comp401.sushi.Plate;

public class Belt extends Observable{

	private Plate belt[];
	private Customer customer[];
	
	public Belt(int size) {
		if (size < 1) {
			throw new IllegalArgumentException("Belt size must be greater than zero.");
		}

		belt = new Plate[size];
		customer = new Customer[size];
	}

	public int getSize() {
		return belt.length;
	}

	public Plate getPlateAtPosition(int position) {
		position = normalizePosition(position);
		return belt[position];
	}
	
	public Customer getCustomerAtPosition(int position) {
		position = normalizePosition(position);
		return customer[position];
	}

	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		position = normalizePosition(position);

		if (plate == null) {
			throw new IllegalArgumentException("Plate is null");
		}

		if (belt[position] != null) {
			throw new BeltPlateException(position, plate, this);
		}
		belt[position] = plate;
		setChanged();
		notifyObservers(new PlateEvent(EventType.PLATE_PLACED, plate, position));
	}

	public void clearPlateAtPosition(int position) {
		position = normalizePosition(position);
		belt[position] = null;
	}

	public Plate removePlateAtPosition(int position) {
		Plate plate = getPlateAtPosition(position);
		if (plate == null) {
			throw new NoSuchElementException();
		}
		clearPlateAtPosition(position);
		setChanged();
		notifyObservers(new PlateEvent(EventType.PLATE_REMOVED, plate, position));
		return plate;
	}

	public int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException {
		for (int i=0; i<getSize(); i++) {
			try {
				setPlateAtPosition(plate, position);
				return normalizePosition(position);
			} catch (BeltPlateException e) {
				position += 1;
			}
		}
		throw new BeltFullException(this);
	}

	public void registerCustomerAtPosition(Customer c, int position) {
		position = normalizePosition(position);
		if (c == null) {
			throw new IllegalArgumentException();
		}
		if (customer[position] != null) {
			throw new RuntimeException();
		}
		for (int i = 0; i < customer.length; i++) {
			if (customer[i] == c && i != position) {
				throw new RuntimeException();
			}
		}
		customer[position] = c;
	}
	public Customer unregisterCustomerAtPosition(int position) {
		position = normalizePosition(position);
		Customer tempCustomer; 
		if (customer[position] == null) {
			return null;
		} else {
			tempCustomer = customer[position];
			customer[position] = null;
			return tempCustomer;
		}
	}
	
	private int normalizePosition(int position) {
		int normalized_position = position%getSize();

		if (position < 0) {
			normalized_position += getSize();
		}

		return normalized_position;
	}

	public void rotate() {
		Plate last_plate = belt[getSize()-1];
		for (int i=getSize()-1; i>0; i--) {
			belt[i] = belt[i-1];
			
		}
		belt[0] = last_plate;
		
		for (int i = 0; i < customer.length; i++) {
			if (customer[i] != null) {
				customer[i].observePlateOnBelt(this, belt[i], i);
			}
		}
	}
}
