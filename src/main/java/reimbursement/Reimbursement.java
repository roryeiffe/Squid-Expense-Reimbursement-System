package reimbursement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reimbursement {
    @Id
    @GeneratedValue
    private int id;
    // reference employee who submitted this request:
    private int empId;
    // title of this reimbursement request:
    private String title;
    // descrption of this request:
    private String description;
    // amount of the reimbursement request
    private int amount;
    private String status;

    public Reimbursement() {}

    public Reimbursement(int empId, String title, String description, int amount, String status) {
        this.empId = empId;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "reimbursement.Reimbursement{" +
                "id=" + id +
                ", empId=" + empId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
