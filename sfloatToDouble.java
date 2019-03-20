/*
* Convert IEEE-11073 16-bit SFLOAT to simple float in Java. - 2019.3.20
*/
public short getExponent(short value)
{
    if (value > 12) & 0x0F) | 0xF0);
    }
    return (short) ((value >> 12) & 0x0F);
}

public short getMantissa(short value)
{
    if ((value & 0x0800) != 0)
    { // if mantissa should be negative
        return (short) ((value & 0x0FFF) | 0xF000);
    }
    return (short) (value & 0x0FFF);
}

public double parseSFLOATtoDouble(short value)
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