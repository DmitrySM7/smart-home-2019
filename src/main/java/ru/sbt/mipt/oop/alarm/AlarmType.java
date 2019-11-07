package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.alarm.ActivationState;
import ru.sbt.mipt.oop.alarm.AlarmState;
import ru.sbt.mipt.oop.alarm.DeactivationState;
import ru.sbt.mipt.oop.alarm.State;

public enum AlarmType {
    ON{
        public State getState() {
            return new ActivationState();
        }
    },
    OFF {
        public State getState() {
            return new DeactivationState();
        }
    },
    ALARM {
        public State getState() {
            return new AlarmState();
        }
    };

    public abstract State getState();

}
