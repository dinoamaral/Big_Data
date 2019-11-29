import java.util.Properties
import org.apache.log4j.LogManager


object Log {
  val logger = LogManager.getLogger(this.getClass.toString)
}

class configProducer {

  def kafkaProducerConfig = {
    val props = new Properties()
    Log.logger.debug("===>>> Propriedades do PRODUCER inicializadas ...")
    props.load(this.getClass.getResourceAsStream("/producer.properties"))
    props
  }
}


