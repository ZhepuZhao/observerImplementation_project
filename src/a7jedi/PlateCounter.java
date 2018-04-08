package a7jedi;

import a7jedi.PlateEvent.EventType;
import comp401.observable.Observable;
import comp401.observable.Observer;
import comp401.sushi.Plate;

public class PlateCounter implements Observer {

	private Belt belt;
	public PlateCounter(Belt b) {
		// TODO Auto-generated constructor stub
		if (b == null) {
			throw new IllegalArgumentException();
		}
		this.belt = b;
		belt.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
//		Belt belt =  (Belt) o;
		PlateEvent plateEvent = (PlateEvent) arg;
		if ((plateEvent.getType() == EventType.PLATE_PLACED)) {
			switch (plateEvent.getPlate().getColor()) {
			case RED:
				getRedPlateCount();
				break;
			case GREEN:
				getGreenPlateCount();
				break;
			case BLUE:
				getBluePlateCount();
				break;
			case GOLD:
				getGoldPlateCount();
				break;
			}
		}
		if ((plateEvent.getType() == EventType.PLATE_REMOVED)) {
			switch (plateEvent.getPlate().getColor()) {
			case RED:
				getRedPlateCount();
				break;
			case GREEN:
				getGreenPlateCount();
				break;
			case BLUE:
				getBluePlateCount();
				break;
			case GOLD:
				getGoldPlateCount();
				break;
			}
		}
		
	}
	
	public int getRedPlateCount() {
		int redCount = 0;
		for (int i = 0; i < this.belt.getSize(); i++) {
			if (belt.getPlateAtPosition(i) == null) {
				continue;
			}
			if (belt.getPlateAtPosition(i).getColor() == Plate.Color.RED) {
				redCount ++;
			}
		}
		return redCount;
	}
	public int getGreenPlateCount() {
		int greenCount = 0;
		for (int i = 0; i < this.belt.getSize(); i++) {
			if (belt.getPlateAtPosition(i) == null) {
				continue;
			}
			if (belt.getPlateAtPosition(i).getColor() == Plate.Color.GREEN) {
				greenCount ++;
			}
		}
		return greenCount;
	}
	public int getBluePlateCount() {
		int blueCount = 0;
		for (int i = 0; i < this.belt.getSize(); i++) {
			if (belt.getPlateAtPosition(i) == null) {
				continue;
			}
			if (belt.getPlateAtPosition(i).getColor() == Plate.Color.BLUE) {
				blueCount ++;
			}
		}
		return blueCount;
	}
	public int getGoldPlateCount() {
		int goldCount = 0;
		for (int i = 0; i < this.belt.getSize(); i++) {
			if (belt.getPlateAtPosition(i) == null) {
				continue;
			}
			if (belt.getPlateAtPosition(i).getColor() == Plate.Color.GOLD) {
				goldCount ++;
			}
		}
		return goldCount;
	}

}
