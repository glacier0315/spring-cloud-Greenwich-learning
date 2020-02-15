package com.glacier.auth.settings;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-14 22:18
 */
@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "settings.security")
public class SecuritySettings implements Serializable {
    private static final long serialVersionUID = -2994114879313546449L;

    private List<String> permitAll;
}
