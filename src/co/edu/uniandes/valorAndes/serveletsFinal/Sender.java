package co.edu.uniandes.valorAndes.serveletsFinal;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Sender {
	private final static String QUEUE_NAME = "CF";

	public static void main(String[] argv) throws java.io.IOException {

		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
//		factory.setHost("157.253.220.84");
		factory.setPort(5672);
		factory.setUsername("tavo");
		factory.setPassword("tavo");
		
		Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    String message = "hola hola";
	   
	    //channel.queueDeclare("SC", false, false, false, null);
		
	    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");
	    channel.close();
    	connection.close();
    	
  	}
}
