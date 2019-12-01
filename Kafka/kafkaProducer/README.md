## A Kafka Producer
* In this directory, a Kafka Producer where it is possible to send all lines of a single file to any Kafka topic.

* The file responsible to download and manage all jars needed to deploy a Kafka Producer is describe at file build.gradle. 

* Usage: java -jar kafkaProducer.jar \<topic\> \<file path to be sent\>

* Change the variable bootstrap.servers (kafka brokers) in the producer.propeties files to adequate to your infrastructure.

* For a deeper details about Kafka Producer API, see the link bellow :
### https://kafka.apache.org/10/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html
