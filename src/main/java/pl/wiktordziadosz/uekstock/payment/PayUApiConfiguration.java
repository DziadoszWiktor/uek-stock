package pl.wiktordziadosz.uekstock.payment;

public class PayUApiConfiguration {
    private String merchantPosId;
    private String apiUrl;

    public PayUApiConfiguration(String merchantPosId, String apiUrl) {
        this.merchantPosId = merchantPosId;
        this.apiUrl = apiUrl;
    }

    public String getMerchantPosId() {
        return merchantPosId;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
