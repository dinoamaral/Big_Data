import java.util.concurrent.ForkJoinPool

import scala.collection.parallel.ForkJoinTaskSupport
import java.util.Properties
import java.util.logging.LogManager
import scala.util.Random
import scala.io.Source

import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.log4j.PropertyConfigurator
import org.apache.log4j.LogManager

object kafkaProducer {

  def main(args:Array[String]) {

    val usage = """
          ==================================================================================
          ===============================================Producer Kafka=====================
                  Usage: java -jar kafkaProducer.jar <topic> <file path to be sent>
          ==================================================================================
          ==================================================================================
              """

    if (args.length != 2)
    { println(usage)
      System.exit(1)
    }

    val topic = args(0).toString
    val file = args(1).toString

    val log4j = new Properties()
    log4j.load(this.getClass.getResourceAsStream("/log4j.properties"))
    PropertyConfigurator.configure(log4j)

    val kafkaMsg = new configProducer
    val producerConf = kafkaMsg.kafkaProducerConfig
    val producer = new KafkaProducer[String, String](producerConf)

    val msg = Source.fromFile(file).getLines
    //val iter = Iterator.continually(Random.shuffle(msg)).flatten
    Random.shuffle(msg).foreach{x =>
       val data = new ProducerRecord[String, String](topic, x)
       producer.send(data);
    }
    //iter.foreach{x =>
    //  val data = new ProducerRecord[String, String](topic, x)
    //  producer.send(data);
    //  }
    producer.close()
    }
}

