package domain;

/**
 * 审计元数据的数据格式
 * @author wang zhen
 * @date 2018/12/28 10:28
 */
public class AuditData {
    private String action;
    private long actionTimeStamp;
    private String machine;
    private String type;
    private String fullTableName;
    private String primary;
    private long numRecords;
    private long numBytes;
    private String summaryCaluation;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getActionTimeStamp() {
        return actionTimeStamp;
    }

    public void setActionTimeStamp(long actionTimeStamp) {
        this.actionTimeStamp = actionTimeStamp;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullTableName() {
        return fullTableName;
    }

    public void setFullTableName(String fullTableName) {
        this.fullTableName = fullTableName;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public long getNumRecords() {
        return numRecords;
    }

    public void setNumRecords(long numRecords) {
        this.numRecords = numRecords;
    }

    public long getNumBytes() {
        return numBytes;
    }

    public void setNumBytes(long numBytes) {
        this.numBytes = numBytes;
    }

    public String getSummaryCaluation() {
        return summaryCaluation;
    }

    public void setSummaryCaluation(String summaryCaluation) {
        this.summaryCaluation = summaryCaluation;
    }
}
