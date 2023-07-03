# simple-kafka-java-example
Simple way to create topic, consumer and producer to Apache Kafka

It's not big deal, but... when someone have start Apache Kafka adventure - it's a great gate to start this adventure...
Java gate...

In this project I use:
* Java 17,
* Maven,
* [Kafka Clients](https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients) v. 3.5,
* Docker.

In my other repo You can find identical example for other language (Python).

## What we find in this repo:
1) how to prepare development environment (using docker),
2) how to create topic from Java using Admin,
2) how to create simple Apache Kafka producer,
3) how to create simple Apache Kafka consumer,
4) how to write unite test for this topic ;).

And that's all... in other my branch You can find some real project...

## Ad. 1 - How to prepare development env using Docker.

Docker is a great soft to use it in cases when we mast use Apache Kafka, but we don't have it... Then, we can run 
Apache Kafka locally in simple way. Just You must do:

a) Install docker on your machine

>e.g [Docker Desktop](https://docs.docker.com/desktop/) on:
> * [Windows](https://docs.docker.com/desktop/install/windows-install/),
> * [Linux](https://docs.docker.com/desktop/install/linux-install/),
> * [Mac](https://docs.docker.com/desktop/install/mac-install/).
 

b) Run script: docker-compose.yml from our source - [simple-kafka-java-example/kafka_env/docker-compose.yml](kafka_env%2Fdocker-compose.yml).
Use command `docker compose up -d` from file localization. 

>What this script do:
>- get docker images from repository,
>- configure env in docker container,
>- additionally add our test topic.
>
>What software we have:
>- [Apache Kafka](https://kafka.apache.org/) (of course) - our main goal - message broker,
>- [Apache ZooKeeper](https://zookeeper.apache.org/) - it's not necessary, but very useful open-source server to coordinate whole our Zoo names Apache Kafka (
>I'm convinced that you use IT in the near future...),
>- [AKHQ](https://akhq.io/) - this software will let you in simple way manage and view data inside your Apache Kafka. 

>> IMPORTANT:
>>- (!) In this project I use very basic, essential configuration.
>>- (!) Whole project have configuration localized in class (close to subject). That approach it's not correct but allow better focus on subject problem.
>>- (!) In general - it's not clean code... it's education code! (for education, sometime better is create dirty code...)

## Ad. 2 - How to create topic from Java using Admin


