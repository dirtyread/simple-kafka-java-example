package pl.dirtyread.sandbox.java.kafka.admins;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;

import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.core.StringContains.containsString;

/**
 * @author Dirty Read
 */
public class TopicsCreatorTest {
    @Container
    private static final KafkaContainer KAFKA_CONTAINER =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"));

    @Test
    public void shouldGivenProperlyTopicName_whenCreateNewTopic() throws IOException, InterruptedException {
        //given
        String topicName = "test-topic";
        System.out.println(KAFKA_CONTAINER.getExposedPorts());
        TopicsCreator.addTopic(KAFKA_CONTAINER.getBootstrapServers(), topicName, 1, (short) 1);


        //when
        String commandToListingTopics = "/usr/bin/kafka-topics --bootstrap-server=localhost:9092 --list";
        String topicList = KAFKA_CONTAINER.execInContainer("/bin/sh", "-c", commandToListingTopics).getStdout();

        //then
        assertThat(topicList, containsString(topicName));
    }

    @Before
    public void up() {
        KAFKA_CONTAINER.start();
    }

    @After
    public void tearDown() {
        KAFKA_CONTAINER.stop();
    }
}