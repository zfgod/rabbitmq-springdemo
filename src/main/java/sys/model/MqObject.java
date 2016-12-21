package sys.model;

import java.io.Serializable;

/**
 * author: zf
 * Date: 2016/12/20  14:25
 * Description:
 */
public class MqObject implements Serializable{

    private static final long serialVersionUID = -3600771730738993982L;
    private String message;

    private String exchange;

    private String routingKey;

    private String queue;

    @Override
    public String toString() {
        return "MqObject{" +
                "message='" + message + '\'' +
                ", exchange='" + exchange + '\'' +
                ", routingKey='" + routingKey + '\'' +
                ", queue='" + queue + '\'' +
                '}';
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
