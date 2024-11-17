package com.dobudobu.user_service.Util.GeneratedCode;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Predicate;

@Component
public class GeneratedCodeUtil {

    public String generatedCode(Predicate<String> existsByCodeChecker){
        while (true){
            String uuid = UUID.randomUUID().toString();
            String cutUid = uuid.substring(0, 10);

            if (!existsByCodeChecker.test(cutUid)){
                return cutUid;
            }
        }
    }

}
