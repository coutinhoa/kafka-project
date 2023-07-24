package kafka.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.entities.ProductMessage;
import kafka.repositories.ProductMessageRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private final ProductMessageRepository messageRepository;

    public KafkaListeners(ProductMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @KafkaListener(topics = "product", groupId = "foo")
    void listener(String data) throws JsonProcessingException {
        System.out.println(("Listener received" + data));
        ProductMessage obj = new ObjectMapper().readValue(data, ProductMessage.class);
        messageRepository.save(obj);
    }
}
