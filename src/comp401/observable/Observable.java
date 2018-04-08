package comp401.observable;

import java.util.ArrayList;
import java.util.List;

public class Observable {

	private List<Observer> observers;
	private boolean changed;
	
	public Observable() {
		observers = new ArrayList<Observer>();
		changed = false;
	}
	
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	protected void clearChanged() {
		changed = false;
	}
	
	public int countObservers() {
		return observers.size();
	}
	
	public void deleteObserver(Observer o) {
		while (observers.remove(o)) {};
	}
	
	public void deleteObservers() {
		observers = new ArrayList<Observer>();
	}

	public boolean hasChanged() {
		return changed;
	}
	
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	public void notifyObservers(Object arg) {
		if (hasChanged()) {
			for (Observer o: observers) {
				o.update(this, arg);
			}
			clearChanged();
		}
	}
	
	protected void setChanged() {
		changed = true;
	}
	
}
