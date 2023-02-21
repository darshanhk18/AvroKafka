import com.pack.ClickInformation;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class AvroConsumer {

  public static void main(String[] args) {

    Properties prop = new Properties();

    prop.put("bootstrap.servers", "localhost:9092");

    prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

    prop.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");

    prop.put("group.id", "grp2");   //to create consumer group

    prop.put("schema.registry.url", "http://localhost:8081");

    prop.put("specific.avro.reader", true);

    Consumer<String, ClickInformation> consumer = new KafkaConsumer<>(prop);

    consumer.subscribe(Arrays.asList("avrotopic"));

    while (true) {

      ConsumerRecords<String, ClickInformation> records = consumer.poll(100);

      for (ConsumerRecord<String, ClickInformation> record : records) {

        System.out.println("Session Id = " + record.value().getSessionid()

            + " Channel = " + record.value().getChannel()

            + " Referrer = " + record.value().getReferrer());

      }

    }


  }


}
