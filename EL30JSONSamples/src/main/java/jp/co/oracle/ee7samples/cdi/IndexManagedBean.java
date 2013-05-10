package jp.co.oracle.ee7samples.cdi;

import java.util.ArrayList;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import jp.co.oracle.ee7samples.model.Person;

@Named
@ViewScoped
public class IndexManagedBean {

    private ArrayList data;
    private Integer ageFileter;

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    public Integer getAgeFileter() {
        if (ageFileter == null) {
            return new Integer(0);
        }
        return ageFileter;
    }

    public void setAgeFileter(Integer ageFileter) {
        this.ageFileter = ageFileter;
    }

    public String getAllData() {
        setData(Person.createDummyData());
        return "";
    }

    public String updateData() {
        return "";
    }
}
