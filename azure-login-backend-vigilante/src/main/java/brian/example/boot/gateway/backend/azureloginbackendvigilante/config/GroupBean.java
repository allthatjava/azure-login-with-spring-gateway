package brian.example.boot.gateway.backend.azureloginbackendvigilante.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class GroupBean {

    @Value("${security.group.1}")
    private String group1;
    @Value("${security.group.2}")
    private String group2;
//    private String group1="Random1";
//    private String group2="Random2";

    private String claimField="groups";
}
