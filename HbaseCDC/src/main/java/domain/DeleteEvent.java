package domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author wang zhen
 * @version 2018/12/18
 */
public class DeleteEvent implements Serializable {
    private String nameSpace;
    private String tableName;
    private String rowkey;
    private List<String> families;
    private String qualifier;
    private String value;
    private String timeStamp;
    private String type;

    public DeleteEvent(String nameSpace, String tableName, String rowkey, List<String> families, String qualifier, String value, String timeStamp, String type) {
        this.nameSpace = nameSpace;
        this.tableName = tableName;
        this.rowkey = rowkey;
        this.families = families;
        this.qualifier = qualifier;
        this.value = value;
        this.timeStamp = timeStamp;
        this.type = type;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public List<String> getFamilies() {
        return families;
    }

    public void setFamilies(List<String> families) {
        this.families = families;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
