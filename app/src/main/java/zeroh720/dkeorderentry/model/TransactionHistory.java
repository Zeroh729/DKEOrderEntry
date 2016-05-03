package zeroh720.dkeorderentry.model;

import java.util.HashMap;

public class TransactionHistory {
    String id;
    String merchId;
    String name;
    String branch;
    long time;
    String status;
    HashMap<String, HashMap> items;

    public TransactionHistory() {
    }

    public enum StatusStates{
        pending, cancelled, deliveryConfirmed, deliveryCancelled, returned
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public HashMap<String, HashMap> getItems() {
        return items;
    }

    public void setItems(HashMap<String, HashMap> items) {
        this.items = items;
    }
}
