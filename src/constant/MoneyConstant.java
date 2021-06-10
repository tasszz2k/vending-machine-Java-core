package constant;

/**
 * @author tasszz2k
 * @version 1.0
 * @date 09/06/2021 18:38
 */
public enum MoneyConstant {

    VND_10K(10000),
    VND_20K(20000),
    VND_50K(50000),
    VND_100K(100000),
    VND_200K(200000);

    private final Integer value;

    MoneyConstant(Integer value) {
        this.value = value;
    }

    public static int getMinValue() {
        return VND_10K.value;
    }

    public static int getMaxValue() {
        return VND_10K.value * 100;
    }
}
