package pl.dirtyread.sandbox.java.kafka.admins;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Simple class to create topic on Apache Kafka broker.
 *
 * @author Dirty Read
 */
public class TopicsCreator {
    private static final String KAFKA_BOOTSTRAP = "localhost:9092";
    private static final String TOPIC_NAME = "hello-world-topic";
    private static final int NUM_PARTITIONS = 3;
    private static final short REPLICATION_FACTORY = 1;

    public static void main(String[] args) {
        boolean result = addTopic(KAFKA_BOOTSTRAP, TOPIC_NAME, NUM_PARTITIONS, REPLICATION_FACTORY);
        if (result) {
            System.out.println("Topic " + TOPIC_NAME + " was created properly...");
        } else {
            System.out.println("Something went wrong...");
        }
    }

    /**
     * Add topic to broker.
     *
     * @return is added
     */
    public static boolean addTopic(String bootstrapServerConfigs, String topicName, int numPartitions, short replicationFactory) {
        // create admin client and cleaning after
        try (AdminClient adminClient = createAdminClient(bootstrapServerConfigs)) {
            //configure and create topic
            CreateTopicsResult result =
                    adminClient.createTopics(prepareTopic(topicName, numPartitions, replicationFactory));

            System.out.println("Result of creation: " + result.values().toString());
            if (isTopicExist(adminClient, topicName)) {
                return true;
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    /**
     * Prepare list with one topic.
     *
     * @return topics list
     */
    private static List<NewTopic> prepareTopic(String topicName, int numPartition, short replicationFactory) {
        return Collections.singletonList(new NewTopic(topicName, numPartition, replicationFactory));
    }

    /**
     * Configure and create admin client object.
     *
     * @return AdminClient
     */
    private static AdminClient createAdminClient(String bootstrapServerConfigs) {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerConfigs);
        return AdminClient.create(properties);
    }

    private static boolean isTopicExist(AdminClient adminClient, String topicName) throws ExecutionException, InterruptedException {
        return adminClient.listTopics().names().get().contains(topicName);
    }
}
