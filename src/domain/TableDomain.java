/*
Author : Shim Wei Hean & Ong Boon Fong
Class  : DC02 D2
*/
package domain;

public class TableDomain {

    private String ID;
    private String occupyStatus;
    private String payStatus;

    public TableDomain(String id, String occupyStatus, String payStatus) {
        this.ID = id;
        this.occupyStatus = occupyStatus;
        this.payStatus = payStatus;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOccupyStatus() {
        return occupyStatus;
    }

    public void setOccupyStatus(String occupyStatus) {
        this.occupyStatus = occupyStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

}
