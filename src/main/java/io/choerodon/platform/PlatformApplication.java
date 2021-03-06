package io.choerodon.platform;

import org.hzero.autoconfigure.platform.EnableHZeroPlatform;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableHZeroPlatform
public class PlatformApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(PlatformApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
