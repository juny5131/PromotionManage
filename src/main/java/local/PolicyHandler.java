package local;

import local.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    PromotionRepository promotionRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationRequested_ReservationCollect(@Payload ReservationCompleted completed){

        if(completed.isMe()){
            //  홍보 데이터 수집
            if(completed.isMe()){
                Promotion prm = new Promotion();
                prm.setCustNm(completed.getCustNm());
                prm.setHospitalId(Long.parseLong(completed.getHospitalId()));
                prm.setHospitalNm(completed.getHospitalNm());
                prm.setResvid(completed.getId());
                promotionRepository.save(prm);
            }
        }
    }


}
