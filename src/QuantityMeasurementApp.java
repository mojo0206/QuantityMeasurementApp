public class QuantityMeasurementApp {

    // ✅ ENUM (base = FEET)
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double valueInFeet) {
            return valueInFeet / toFeetFactor;
        }
    }

    // ✅ Quantity Class (immutable)
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toFeet(value);
        }

        // ✅ Convert to another unit (instance method)
        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double base = this.toBase();
            double converted = targetUnit.fromFeet(base);

            return new Quantity(converted, targetUnit);
        }

        // ✅ Equality (unchanged logic)
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        // ✅ toString override
        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ✅ STATIC API METHOD (core UC5 requirement)
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        double base = source.toFeet(value);
        return target.fromFeet(base);
    }

    // ✅ Method Overloading (as asked)
    public static double demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        return convert(value, from, to);
    }

    public static double demonstrateLengthConversion(Quantity q, LengthUnit to) {
        return q.convertTo(to).value;
    }

    // ✅ Demo
    public static void main(String[] args) {

        System.out.println(convert(1.0, LengthUnit.FEET, LengthUnit.INCH));   // 12
        System.out.println(convert(3.0, LengthUnit.YARD, LengthUnit.FEET));   // 9
        System.out.println(convert(36.0, LengthUnit.INCH, LengthUnit.YARD));  // 1
        System.out.println(convert(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH)); // ~0.3937
    }
}