package foodbank.it.web.dto;

public class BanqueFeadReportDto {
    private String bankShortName;
    private Long orgCount;
    private Long orgFeadCount;
    private Long orgAgreedCount;
    private Long orgFeadFromUsCount;

    public BanqueFeadReportDto(String bankShortName) {
        this.bankShortName = bankShortName;
        this.orgCount = 0L;
        this.orgAgreedCount = 0L;
        this.orgFeadCount = 0L;
        this.orgFeadFromUsCount = 0L;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public Long getOrgCount() {
        return orgCount;
    }

    public void setOrgCount(Long orgCount) {
        this.orgCount = orgCount;
    }

    public Long getOrgFeadCount() {
        return orgFeadCount;
    }

    public void setOrgFeadCount(Long orgFeadCount) {
        this.orgFeadCount = orgFeadCount;
    }

    public Long getOrgAgreedCount() {
        return orgAgreedCount;
    }

    public void setOrgAgreedCount(Long orgAgreedCount) {
        this.orgAgreedCount = orgAgreedCount;
    }

    public Long getOrgFeadFromUsCount() {
        return orgFeadFromUsCount;
    }

    public void setOrgFeadFromUsCount(Long orgFeadFromUsCount) {
        this.orgFeadFromUsCount = orgFeadFromUsCount;
    }
}
