package dsw.gerumap.app.core;

import dsw.gerumap.app.gui.swing.observer.ISubscriber;


public interface Gui extends ISubscriber {
    void start();
    void enableRedoAction();
    void disableUndoAction();
    void disableRedoAction();
    void enableUndoAction();


}
