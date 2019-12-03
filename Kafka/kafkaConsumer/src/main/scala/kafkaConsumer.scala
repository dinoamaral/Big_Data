import java.util
import java.util.{Calendar, Date, Properties}
import com.fasterxml.jackson.core.{JsonFactory, JsonToken}
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.log4j.{LogManager, PropertyConfigurator}
import scala.collection.JavaConverters._

object kafkaConsumer {
  def main(args: Array[String]) {
    val log4j = new Properties()
    log4j.load(this.getClass.getResourceAsStream("log4j.properties"))
    PropertyConfigurator.configure(log4j)
    Log.logger.info("#" * 50)
    Log.logger.info("===>>> Starting data collect") 
    Log.logger.info("===>>> CONSUMER properties initialized")
    Log.logger.info("===>>> Transforming JSON to CSV")
    Log.logger.info("===>>> Showing Consumer properties")
    Log.logger.info("#" * 50)
    // test is the name of kafka topic
    val topics = List("test")
    val consumerKafka = new configConsumer
    val consumer = consumerKafka.kafkaConsumer(topics)
    val jsonKafka = new JSONKafka
    println("Testando JSONKafka")
    //val header_filter = Array("dt_mov","cd_tran",  "hr_tran", "cd_ag_tran", "cd_sbdd", "cd_tip_itce", "cd_tip_trml", "cd_trml", "cd_ag_cli", "cd_sbdd_cli", "nr_ct_cli", "nr_seql_tran", "cd_tran", "nr_mci_cli", "nr_crt", "cd_erro", "cd_rtn", "vl_dnh", "vl_ttl", "tx_seg_cnl_id", "cd_resp_tip_agdt", "cd_tip_cnl")
//    consumer.foreach(x => println(x))
    consumer.foreach(x => println(jsonKafka.parseJson(x).map{case (k, v) => v}.mkString(";")))
//    consumer.foreach(x => println(jsonKafka.parseJson(x).map{case (k, v) => v}.filter(v => v != "" && v != "null").mkString(";")))
 }
}
