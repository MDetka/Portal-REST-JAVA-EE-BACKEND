package model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int accountid;
    private String type;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date registerdate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date validdate;
    @OneToOne(mappedBy = "accountid")
    private Portaluser portaluser;
    public Account(){
    }

    public Account(String type,Date validdate,Date registerdate){
        setType(type);
        setValiddate(validdate);
        setRegisterdate(registerdate);
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public Date getValiddate() {
        return validdate;
    }

    public void setValiddate(Date validdate) {
        this.validdate = validdate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountid=" + accountid +
                ", type='" + type + '\'' +
                ", registerdate=" + registerdate +
                ", validdate=" + validdate +
                '}';
    }
}
