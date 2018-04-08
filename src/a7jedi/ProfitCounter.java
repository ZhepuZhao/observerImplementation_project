package a7jedi;

import a7jedi.PlateEvent.EventType;
import comp401.observable.Observable;
import comp401.observable.Observer;

public class ProfitCounter implements Observer {

	private Belt belt;
	public ProfitCounter(Belt b) {
		// TODO Auto-generated constructor stub
		if (b == null) {
			throw new IllegalArgumentException();
		}
		belt = b;
		belt.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		PlateEvent plateEvent = (PlateEvent) arg;
		if ((plateEvent.getType() == EventType.PLATE_PLACED ||
				plateEvent.getType() == EventType.PLATE_REMOVED)) {
			getTotalBeltProfit();
			getAverageBeltProfit();
		}
	}
	
	public double getTotalBeltProfit() {
		double totalProfit = 0.0;
		for (int i = 0; i < belt.getSize(); i++) {
			if (belt.getPlateAtPosition(i) == null) {
				continue;
			}
			totalProfit += belt.getPlateAtPosition(i).getProfit();
		}
		return totalProfit;
	}
	public double getAverageBeltProfit() {
		int plateNum = 0;
		for (int i = 0; i < belt.getSize(); i++) {
			if (belt.getPlateAtPosition(i) == null) {
				continue;
			}
			plateNum ++;
		}
		if (plateNum == 0) {
			return 0.0;
		} else {
			return getTotalBeltProfit() / plateNum;
		}
	}

}
