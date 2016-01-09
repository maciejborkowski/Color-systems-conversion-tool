package gui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public interface DragListener extends MouseMotionListener {

	@Override
	public default void mouseMoved(MouseEvent e) {
	}
}
