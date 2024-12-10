public class Unsigned implements NumberInterface {
    private int value;

    public Unsigned(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        this.value = value;
    }

    @Override
    public NumberInterface add(NumberInterface other) {
        if (!(other instanceof Unsigned)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        Unsigned o = (Unsigned) other;
        return new Unsigned(this.value + o.value);
    }

    @Override
    public NumberInterface subtract(NumberInterface other) {
        if (!(other instanceof Unsigned)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        Unsigned o = (Unsigned) other;
        if (this.value < o.value) {
            throw new IllegalArgumentException("Result cannot be negative");
        }
        return new Unsigned(this.value - o.value);
    }

    @Override
    public NumberInterface multiply(NumberInterface other) {
        if (!(other instanceof Unsigned)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        Unsigned o = (Unsigned) other;
        return new Unsigned(this.value * o.value);
    }

    @Override
    public NumberInterface divide(NumberInterface other) {
        if (!(other instanceof Unsigned)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        Unsigned o = (Unsigned) other;
        if (o.value == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return new Unsigned(this.value / o.value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public int getValue() {
        return value;
    }
}
