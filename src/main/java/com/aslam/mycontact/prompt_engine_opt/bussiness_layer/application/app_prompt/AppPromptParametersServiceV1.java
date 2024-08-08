package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class AppPromptParametersServiceV1 implements AppPromptParametersService{
   private final Pattern pattern=Pattern.compile("\\[(.*?)]");
   private Matcher matcher;
    @Override
    public List<String> extractParameters(String promptTemplate) {
        List<String> parameters=new ArrayList<>();
        matcher=pattern.matcher(promptTemplate);
        while (matcher.find())
            parameters.add(matcher.group(1));
        return parameters;
    }

    @Override
    public String replaceParameters(String promptTemplate, Map<String, String> parametersAndValues) {
        matcher=pattern.matcher(promptTemplate);
        String templateResult=promptTemplate;
        while (matcher.find())
        {
         String searchText="["+matcher.group(1)+"]";
         String replaceText=parametersAndValues.get(matcher.group(1));
         templateResult=  templateResult.replace(searchText,replaceText);
        }

        return templateResult;
    }
}
