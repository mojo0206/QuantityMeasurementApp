public class QuantityMeasurementApp {

    // ✅ Feet Class
    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // ✅ Inches Class (NEW for UC2)
    static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // ✅ Separate methods (as required)
    public static boolean compareFeet(double a, double b) {
        Feet f1 = new Feet(a);
        Feet f2 = new Feet(b);
        return f1.equals(f2);
    }

    public static boolean compareInches(double a, double b) {
        Inches i1 = new Inches(a);
        Inches i2 = new Inches(b);
        return i1.equals(i2);
    }

    // ✅ Main method
    public static void main(String[] args) {

        // Feet comparison
        boolean feetResult = compareFeet(1.0, 1.0);
        System.out.println("Feet Equal: " + feetResult);

        // Inches comparison
        boolean inchResult = compareInches(1.0, 1.0);
        System.out.println("Inches Equal: " + inchResult);
    }
}