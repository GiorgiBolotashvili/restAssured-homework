package Models.Responses;

import Utils.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

public class ResponseUser {
    public String name;
    public String job;
    public int id;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime createdAt;

    @Override
    public String toString() {
        return "ResponseUser{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", id=" + id +
                ", createdAt=" + createdAt +
                '}';
    }
}
