package me.krymz0n.anarchyanticheat.utils;

public class BoolReference {
    private Boolean nastyBool = false;
    private Boolean hasEdited = false;

    public BoolReference(Boolean nastyBool) {
        this.nastyBool = nastyBool;
    }

    public void set(Boolean hasEdited) {
        this.nastyBool = hasEdited;
        this.hasEdited = true;
    }

    public Boolean get() {
        return nastyBool;
    }

    public void reset() {
        this.hasEdited = false;
    }

    public Boolean hasBeenEdited() {
        return this.hasEdited;
    }
}
