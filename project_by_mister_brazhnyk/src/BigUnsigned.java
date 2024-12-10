import java.math.BigInteger;

public class BigUnsigned implements NumberInterface {
    private BigInteger value;

    public BigUnsigned(BigInteger value) {
        if (value.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        this.value = value;
    }

    @Override
    public NumberInterface add(NumberInterface other) {
        if (!(other instanceof BigUnsigned)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        BigUnsigned o = (BigUnsigned) other;
        return new BigUnsigned(this.value.add(o.value));
    }

    @Override
    public NumberInterface subtract(NumberInterface other) {
        if (!(other instanceof BigUnsigned)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        BigUnsigned o = (BigUnsigned) other;
        if (this.value.compareTo(o.value) < 0) {
            throw new IllegalArgumentException("Result cannot be negative");
        }
        return new BigUnsigned(this.value.subtract(o.value));
    }

    @Override
    public NumberInterface multiply(NumberInterface other) {
        if (!(other instanceof BigUnsigned)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        BigUnsigned o = (BigUnsigned) other;
        return new BigUnsigned(this.value.multiply(o.value));
    }

    @Override
    public NumberInterface divide(NumberInterface other) {
        if (!(other instanceof BigUnsigned)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        BigUnsigned o = (BigUnsigned) other;
        if (o.value.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return new BigUnsigned(this.value.divide(o.value));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public BigInteger getValue() {
        return value;
    }
}
