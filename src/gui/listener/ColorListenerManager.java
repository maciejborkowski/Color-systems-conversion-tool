package gui.listener;

import java.util.ArrayList;
import java.util.List;

public class ColorListenerManager {
	private boolean changing = false;
	ColorChangeListener mainListener;
	List<ColorChangeListener> otherlisteners = new ArrayList<>();

	public boolean isChanging() {
		return changing;
	}

	public void setChanging(boolean changing) {
		this.changing = changing;
	}

	public void fireMainListener() {
		mainListener.changedUpdate(null);
	}

	public void addListener(ColorChangeListener listener) {
		otherlisteners.add(listener);
	}

	public void setMainListener(ColorChangeListener listener) {
		mainListener = listener;
	}

}
