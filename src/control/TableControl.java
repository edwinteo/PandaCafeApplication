/*
Author : Shim Wei Hean & Ong Boon Fong
Class  : DC02 D2
*/
package control;
import da.TableDA;
import domain.TableDomain;

public class TableControl {
    
    private TableDA tableDa;
    
    public TableControl(){
        tableDa = new TableDA();
    }
    
    public TableDomain getTableInfo(String tableid){
        return tableDa.getTableInfo(tableid);
    }
}
