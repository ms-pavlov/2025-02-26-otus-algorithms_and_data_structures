import java.math.BigInteger;

public class BinaryPower implements Power{
    @Override
    public Double power(Double base, BigInteger power) {

        double result = 1.;

        while(power.compareTo(BigInteger.ZERO) > 0) {
            if (power.mod(BigInteger.TWO).compareTo(BigInteger.ONE) == 0) {
                result = result * base;
            }
            base = base*base;
            power = power.divide(BigInteger.TWO);
        }

        return result;
    }


}
