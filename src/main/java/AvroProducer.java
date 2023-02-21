import com.pack.ClickInformation;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class AvroProducer {

  public static void main(String[] args) {
    Properties prop=new Properties();

    prop.put("bootstrap.servers","localhost:9092");

    prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    prop.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");

    prop.put("schema.registry.url", "http://localhost:8081");



    Producer<String, ClickInformation> producer=new KafkaProducer<>(prop);

    ClickInformation cr=new ClickInformation();

    try {

      cr.setSessionid("100");

      cr.setChannel("Facebook");

      cr.setIp("192.23.14.23");

      ProducerRecord<String,ClickInformation> record=new ProducerRecord<>("avrotopic",cr.getSessionid().toString(),cr);

      producer.send(record);

      System.out.println("AvroProducer completed");

    }

    catch(Exception e) {

      e.printStackTrace();

    }

    finally {

      producer.close();

    }



  }


}

