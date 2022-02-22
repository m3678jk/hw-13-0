package currency.dto;

public class CurrencyItem {
    private CCY ccy;
    private CCY base_ccy;
    private float buy;
    private float sale;

    public CCY getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(CCY base_ccy) {
        this.base_ccy = base_ccy;
    }

    public CCY getCcy() {
        return ccy;
    }

    public void setCcy(CCY ccy) {
        this.ccy = ccy;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public float getBuy() {
        return buy;
    }

    // {"ccy":"USD","base_ccy":"UAH","buy":"28.25000","sale":"28.90173"}
    public enum CCY{
        USD,
        EUR,
        RUR,
        BTC,
        UAH
    }

    @Override
    public String toString() {
        return "CurrencyItem{" +
                "ccy=" + ccy +
                ", base_ccy=" + base_ccy +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}
