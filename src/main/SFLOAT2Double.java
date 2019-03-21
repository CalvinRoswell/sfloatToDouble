package main;

public class SFLOAT2Double {

    public static void main(String []args) {
        System.out.println(parseSFLOATtoDouble(0xB30F));
    }

    public static short getExponent(int value)
    {
        {
            if (((value >> 12) & 0x8) != 0)
            {
                return (short) -((~(value >> 12) & 0x0F) + 1 );
            }
        }
        return (short) ((value >> 12) & 0x0F);
    }

    public static short getMantissa(int value)
    {
        if ((value & 0x0800) != 0)
        { // if mantissa should be negative
            return (short) -((~value & 0x0FFF) + 1 );
        }
        return (short) (value & 0x0FFF);
    }

    public static double parseSFLOATtoDouble(int value)
    {
        // NaN
        if (value == 0x07FF)
        {
            return Double.NaN;
        }
        // NRes (not at this resolution)
        else if (value == 0x0800)
        {
            return Double.NaN;
        }
        // +INF
        else if (value == 0x07FE)
        {
            return Double.POSITIVE_INFINITY;
        }
        // -INF
        else if (value == 0x0802)
        {
            return Double.NEGATIVE_INFINITY;
        }
        // Reserved
        else if (value == 0x0801)
        {
            return Double.NaN;
        }
        else
        {
            return ((double) getMantissa(value)) * Math.pow(10, getExponent(value));
        }
    }

}
