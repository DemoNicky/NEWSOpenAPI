package com.dobudobu.article_management_service.Util.GeneratedCode;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Predicate;

@Component
public class GeneratedCodeUtil {

    public String generateCode(Predicate<String> existsByCodeChecker){
        while (true){
            String uuid = UUID.randomUUID().toString();
            String cutUid = uuid.substring(0, 10);

            if (!existsByCodeChecker.test(cutUid)){
                return cutUid;
            }
        }
    }
}
