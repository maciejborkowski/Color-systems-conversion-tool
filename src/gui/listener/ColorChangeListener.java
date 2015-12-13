package gui.listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class ColorChangeListener implements DocumentListener {
	private final ColorListenerManager manager;

	public ColorChangeListener(ColorListenerManager manager) {
		this.manager = manager;
		if (isMainColor()) {
			manager.setMainListener(this);
		} else {
			manager.addListener(this);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		processChange(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		processChange(e);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		processChange(e);
	}

	private void processChange(DocumentEvent e) {
		if (!manager.isChanging()) {
			manager.setChanging(true);
			stateChanged(e);
			manager.setChanging(false);
			if (!isMainColor()) {
				manager.fireMainListener();
			}
		}
	}

	protected abstract void stateChanged(DocumentEvent e);

	protected boolean isMainColor() {
		return false;
	}

}
