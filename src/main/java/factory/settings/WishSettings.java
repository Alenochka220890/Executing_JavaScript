package factory.settings;

import java.util.Random;

public final class WishSettings {
    private static final Random random = new Random();
    public static String wishName() {
        return "wish_" + random.nextInt(1000) + random.nextInt(1000);
    }
    public static String giftName() {
        return "gift_" + random.nextInt(1000) + random.nextInt(1000);
    }
    public static String wishNameDel() {
        return "wish_" + random.nextInt(1000) + random.nextInt(1000);
    }


    public static String wishDiscription() {
        return "discriptionWish_" + random.nextInt(1000) + random.nextInt(1000);
    }
    public static String wishDiscriptionDel() {
        return "discriptionWish_" + random.nextInt(1000) + random.nextInt(1000);
    }
    public static String giftDiscription() {
        return "discriptionGift_" + random.nextInt(1000) + random.nextInt(1000);
    }

    private static final String WISHNAME = WishSettings.wishName();
    private static final String WISHDISCRIPTIONAL = WishSettings.wishDiscription();
    private static final String WISHNAMEDEL = WishSettings.wishNameDel();
    private static final String WISHDISCRIPTIONALDEL = WishSettings.wishDiscriptionDel();
    private static final String GIFTNAME = WishSettings.giftName();
    private static final String GIFTDISCRIPTIONAL = WishSettings.giftDiscription();

    // Геттеры
    public static String getWishname() {
        return WISHNAME;
    }
    public static String getWishdiscriptional() {
        return WISHDISCRIPTIONAL;
    }
    public static String getWishnameDel() {
        return WISHNAMEDEL;
    }
    public static String getWishdiscriptionalDel() {
        return WISHDISCRIPTIONALDEL;
    }
    public static String getGiftname() {
        return GIFTNAME;
    }
    public static String getGiftdiscriptional() {
        return GIFTDISCRIPTIONAL;
    }

    // Запрещаем создание экземпляров класса
    private WishSettings() {
    }

}

