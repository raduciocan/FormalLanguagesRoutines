package com.hammersoft.formal_languages.language_programs;

import java.util.Objects;

public class AFD_Transition {
    private int fromState;
    private int toState;
    private char valueOfState;

    public AFD_Transition(int fromState, int toState, char valueOfState) {
        this.fromState = fromState;
        this.toState = toState;
        this.valueOfState = valueOfState;
    }

    public int getFromState() {
        return fromState;
    }
    public void setFromState(int fromState) {
        this.fromState = fromState;
    }
    public int getToState() {
        return toState;
    }
    public void setToState(int toState) {
        this.toState = toState;
    }
    public char getValueOfState() {
        return valueOfState;
    }
    public void setValueOfState(char valueOfState) {
        this.valueOfState = valueOfState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromState, toState, valueOfState);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AFD_Transition other = (AFD_Transition) obj;
        if (fromState != other.fromState)
            return false;
        if (toState != other.toState)
            return false;
        return valueOfState == other.valueOfState;
    }
}
