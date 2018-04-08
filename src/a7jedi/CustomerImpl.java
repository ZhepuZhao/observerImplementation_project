package a7jedi;

import comp401.observable.Observable;
import comp401.observable.Observer;
import comp401.sushi.Plate;

public class CustomerImpl implements Observer, Customer {

	private Belt belt;
	public CustomerImpl(Belt b) {
		// TODO Auto-generated constructor stub
		belt = b;
		belt.addObserver(this);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Belt belt = (Belt) o;
		PlateEvent plateEvent = (PlateEvent) arg;
		observePlateOnBelt(belt, plateEvent.getPlate(), plateEvent.getPosition());
	}

	@Override
	public void observePlateOnBelt(Belt b, Plate p, int position) {
		// TODO Auto-generated method stub
		if (p == null) {
			System.out.println("There is not plate in front of you");
		} else {
			System.out.println("There is a plate!");
		}
	}

}
