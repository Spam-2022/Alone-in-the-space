package utilities;

public enum EnumInt {
    WIDTH(2730),
    HEIGHT(1536);

    private final int value;

    private EnumInt(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
