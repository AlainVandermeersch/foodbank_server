package foodbank.it.persistence.model;

public class DonsCount {
    private long count;
    private long totalAmount;

    public DonsCount(long count, long totalAmount) {
        super();
        this.count = count;
        this.totalAmount = totalAmount;
    }
    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }
    public long getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
