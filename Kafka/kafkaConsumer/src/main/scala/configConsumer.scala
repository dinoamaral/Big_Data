import java.util
import java.util.{Calendar, Date, Properties}

import com.fasterxml.jackson.core.{JsonFactory, JsonToken}
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.log4j.{LogManager, PropertyConfigurator}
import org.apache.kafka.common.TopicPartition
import scala.collection.Iterator
import scala.collection.JavaConverters._

object Log {
  val logger = LogManager.getLogger(this.getClass.toString)
}

class configConsumer {

  def kafkaConsumer(topics: Seq[String]) = {
    val props = new Properties()
    Log.logger.debug("===>>> Conectando no broker...")
    props.load(this.getClass.getResourceAsStream("/consumer.properties"))
    val consumer = new KafkaConsumer[String, String](props)
    consumer.subscribe(topics.asJava)
    //consumer.poll(0)
    //consumer.assignment.asScala.foreach(x => consumer)
    Iterator.continually(consumer.poll(Long.MaxValue).asScala.map(_.value)).flatten
  }
}

class JSONKafka {
  def parseJson(line: String) = {
    Log.logger.debug("===>>> Inicializado o parser do JSON ...")
    val jsonFactory = new JsonFactory
    val jp = jsonFactory.createParser(line)
    jp.nextToken // != JsonToken.START_OBJECT
    Iterator.continually(jp).takeWhile(_.nextToken != JsonToken.END_OBJECT).filter(x => x.getCurrentName != x.getText).map(x => (x.getCurrentName,x.getText)).toMap
  }
}