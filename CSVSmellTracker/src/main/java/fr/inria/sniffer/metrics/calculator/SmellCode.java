package fr.inria.sniffer.metrics.calculator;

/**
 * Describe available smells
 * and their offset in the output CSV file.
 */
public enum SmellCode {
    HMU(0),
    IOD(1),
    IWR(2),
    LIC(3),
    MIM(4),
    NLMR(5),
    UCS(6),
    UHA(7),
    UIO(8),
    IGS(9),
    BLOB(10),
    SAK(11),
    LM(12),
    CC(13);

    public final int offset;

    SmellCode(int offset) {
        this.offset = offset;
    }
}
