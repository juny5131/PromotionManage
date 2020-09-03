package local;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Promotion_table")
public class Promotion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long resvid;
    private String custNm;
    private String hospitalNm;
    private Long hospitalId;
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
    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }



    @PostUpdate
    public void onPostUpdate(){

        // Req / Res : 동기 방식 호출)
        local.external.Hospital hospital = new local.external.Hospital();
        hospital.setHospitalId(getHospitalId());
        // mappings goes here
        PromotionManageApplication.applicationContext.getBean(local.external.HospitalService.class)
                .screeningRequest(hospital.getHospitalId(),hospital);


        IncrementRequested incrementRequested = new IncrementRequested();
        BeanUtils.copyProperties(this, incrementRequested);
        incrementRequested.publishAfterCommit();
    }

}
