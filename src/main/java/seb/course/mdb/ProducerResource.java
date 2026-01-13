package seb.course.mdb;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.io.Serializable;

@Path("/mdb")
public class ProducerResource {
    @Inject
    private JMSContext context;

    @Resource(lookup = "java:app/jms/OrderQueue")
    private Queue queue;

    public static class MessageRequest implements Serializable {
        public String key;
        public String message;
    }

    @Path("/publish")
    @POST
    public String publishMessage(MessageRequest msg) {
        context.createProducer().send(queue, msg);
        return "Message published!";
    }
}
