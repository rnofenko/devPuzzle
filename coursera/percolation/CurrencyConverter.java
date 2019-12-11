import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    private Map<String, ICurrencyConverter> currencyCodeToConverter;

    public CurrencyConverter() {
        currencyCodeToConverter = new HashMap<>();
        currencyCodeToConverter.put("EUR", new EurCurrencyConverter());
        currencyCodeToConverter.put("CAD", new CadCurrencyConverter());
        currencyCodeToConverter.put("CNY", new CnyCurrencyConverter());
    }

    public int convertToUsd(String currencyCode, int amount) {
        ICurrencyConverter converter = currencyCodeToConverter.get(currencyCode);
        if(converter == null) {
            throw new RuntimeException();
        }

        return converter.convertToUsd(amount);
    }


    interface ICurrencyConverter {
        int convertToUsd(int amount);
    }

    class CnyCurrencyConverter implements ICurrencyConverter {

        private final BaseCurrencyConverter baseConverter;

        public CnyCurrencyConverter() {
            baseConverter = new BaseCurrencyConverter(4, null,null);
        }

        @Override
        public int convertToUsd(int amount) {
            if(isChinaMarketOpen()) {
                return baseConverter.convertToUsd(amount);
            } else {
                throw new RuntimeException();
            }
        }

        private boolean isChinaMarketOpen() {
            return true;
        }
    }

    class CadCurrencyConverter extends BaseCurrencyConverter implements ICurrencyConverter {

        public CadCurrencyConverter() {
            super(3, 100, null);
        }
    }

    class EurCurrencyConverter extends BaseCurrencyConverter implements ICurrencyConverter {

        public EurCurrencyConverter() {
            super(2, null, 1000);
        }
    }

    class BaseCurrencyConverter {

        private int factor;
        private Integer minAmount;
        private Integer maxAmount;

        public BaseCurrencyConverter(int factor, Integer minAmount, Integer maxAmount) {

            this.factor = factor;
            this.minAmount = minAmount;
            this.maxAmount = maxAmount;
        }

        public int convertToUsd(int amount) {

            if(minAmount != null && minAmount > amount) {
                throw new RuntimeException();
            }
            if(maxAmount != null && maxAmount < amount) {
                throw new RuntimeException();
            }

            return amount * factor;
        }
    }

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        int cad100 = converter.convertToUsd("CAD", 100);
        System.out.println(cad100);
        System.out.println(converter.convertToUsd("CAD", 10));
    }
}