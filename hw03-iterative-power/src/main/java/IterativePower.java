import java.math.BigInteger;

public class IterativePower implements Power{
    @Override
    public Double power(Double base, BigInteger power) {
        double result = 1.;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(power) < 0; i = i.add(BigInteger.ONE)) {
            result = result * base;
        }

        return result;
    }
}
