package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.prompt_generator;

import org.springframework.stereotype.Component;

@Component
public class PromptGeneratorV1 extends PromptTemplateGenerator {




     /*   ------Generate Prompt for user need --------
            what prompt template is required to do this task "task".
            Generate dynamic prompts based on following prompt templates,use [] for parameters
            "Example 1 :I am a developer with [proficiency level] in [programming language/framework].
            I need to implement [specify feature/functionality] in my project.
            Can you provide guidance on how to approach this implementation using
             [preferred technique/design_pattern]?.I am looking
             for [desired outcome/example].",
            "Example 2: I want to learn [topic],with in [days]".
            Make the outputs in JSON format eg: {promptTemplates:[{name:string,template:string,example:string}]}.
            strictly you show only json.
            */


    public PromptGeneratorV1() {

    }


    @Override
    public String sent(String prompt) {
        String finalPrompt=getTemplate(prompt);
        return this.callLlm(finalPrompt);
    }



    private String getTemplate(String prompt)
    {

        String template,example,format;

        example=this.getExamples();

        format=this.getFormat();
        template= "what prompt templates is required to do this task \""+prompt+"\"." +
                "Generate dynamic prompt templates based on following " +
                "prompt templates and must use [] for parameters. " +example+"\n"+format+".";

        return template;
    }


    private String  getExamples()
    {
        return " \"\nExample 1 :I am a developer with [proficiency level] " +
                "in [programming language/framework]. " +
                "I need to implement [specify feature/functionality] " +
                "in my project." +
                "Can you provide guidance on how to approach this " +
                "implementation using [preferred technique/design_pattern]? " +
                "I am looking for [desired outcome/example].\", " +
                "\"Example 2: I want to learn [topic],with in [days].\".";
    }

    private String getFormat()
    {
        return   "Make the outputs in JSON format " +
                "eg: {promptTemplates:[{name:string,template:string,example:string}]}." +
                "strictly you show only json";
    }



}
