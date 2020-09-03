package local;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Promotion_table")
public class Promotion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long resvid;
    private String custNm;
    private String hospitalNm;
    private String hospitalId;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getResvid() {
        return resvid;
    }

    public void setResvid(Long resvid) {
        this.resvid = resvid;
    }
    public String getCustNm() {
        return custNm;
    }

    public void setCustNm(String custNm) {
        this.custNm = custNm;
    }
    public String getHospitalNm() {
        return hospitalNm;
    }

    public void setHospitalNm(String hospitalNm) {
        this.hospitalNm = hospitalNm;
    }
    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }



    @PostUpdate
    public void onPostUpdate(){
        IncrementRequested incrementRequested = new IncrementRequested();
        BeanUtils.copyProperties(this, incrementRequested);
        incrementRequested.publishAfterCommit();
    }

}
