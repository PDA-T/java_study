package JDBC.项目测试.model;

import java.util.Date;
/**
 * 交易信息表
 */
public class Transinfo {
    private Integer id;
    private Integer sourceId;
    private String sourceAccount;
    private Integer destinationId;
    private String desinationAccount;
    private Double amount;
    private Date createAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getDesinationAccount() {
        return desinationAccount;
    }

    public void setDesinationAccount(String desinationAccount) {
        this.desinationAccount = desinationAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Transinfo{" +
                "id=" + id +
                ", sourceId=" + sourceId +
                ", sourceAccount='" + sourceAccount + '\'' +
                ", destinationId=" + destinationId +
                ", desinationAccount='" + desinationAccount + '\'' +
                ", amount=" + amount +
                ", createAt=" + createAt +
                '}';
    }
}
