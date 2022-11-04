package foodbank.it.web.dto;

public class BanqueOrgCountDto {
    private String bankShortName;
    private Long bankCount;
    private Long orgCount;
    public BanqueOrgCountDto(String bankShortName, Long bankCount,Long orgCount) {
        super();
        this.bankShortName = bankShortName;
        this.bankCount = bankCount;
        this.orgCount = orgCount;
    }
    public String getBankShortName() {
        return bankShortName;
    }
    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public Long getBankCount() {
        return bankCount;
    }

    public void setBankCount(Long bankCount) {
        this.bankCount = bankCount;
    }

    public Long getOrgCount() {
        return orgCount;
    }

    public void setOrgCount(Long orgCount) {
        this.orgCount = orgCount;
    }
}