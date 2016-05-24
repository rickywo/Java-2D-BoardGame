package model.gameModel;

import model.gameModel.skills.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blahblah Team on 2016/5/24.
 */
public class ObservationSubject {
        private List<InvokeObserverInterface> observers;

        public ObservationSubject() {
            observers = new ArrayList<InvokeObserverInterface>();
        }

        public void addObserver(InvokeObserverInterface obs) {
            observers.add(obs);
        }

        public void removeObserver(InvokeObserverInterface obs) {
            observers.remove(obs);
        }

        public void commandInvoked(CommandType type) {
            notifyObservers(type);
        }

        private void notifyObservers(CommandType type) {
            for (InvokeObserverInterface obs : observers) {
                obs.update(type);
            }
        }
}
