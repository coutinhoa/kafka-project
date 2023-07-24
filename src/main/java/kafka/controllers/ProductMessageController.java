package kafka.controllers;

import kafka.entities.ProductMessage;
import kafka.repositories.ProductMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ProductMessageController {
    private final ProductMessageRepository messageRepository;
    private final KafkaTemplate<String, ProductMessage> kafkaTemplate;

    ProductMessageController(KafkaTemplate<String, ProductMessage> kafkaTemplate, ProductMessageRepository messageRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductMessage>> getMessages() {
        List<ProductMessage> messages = messageRepository.findAll();
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<ProductMessage> sendMessage(@RequestBody ProductMessage message) {

        kafkaTemplate.send("product", message);
        log.info("Message sent");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
