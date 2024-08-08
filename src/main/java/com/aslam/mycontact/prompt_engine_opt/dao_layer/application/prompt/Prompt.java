package com.aslam.mycontact.prompt_engine_opt.dao_layer.application.prompt;

import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTask;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(uniqueConstraints =
                {
                        @UniqueConstraint(
                                name = "UniquePromptIdAndAppTask",
                                columnNames = {"promptId","appTask"})
                })
public class Prompt {
   @Id
   @SequenceGenerator(name = "prompt_id_seq",
                      sequenceName = "prompt_id_seq",
                      allocationSize=1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE,
                   generator = "prompt_id_seq")
   private Integer promptId;
   private String promptName;
   private String template;
   private String examplePrompt;
   private String outputFormat;
   @ManyToOne(optional = false)
   @JoinColumn( name="app_task",
                referencedColumnName = "taskId")
   private AppTask appTask;
   private List<String> templateParameters;

    public Prompt() {
    }

    public Prompt(String promptName,
                  String template,
                  String examplePrompt,
                  AppTask appTask) {
        this.promptName = promptName;
        this.template = template;
        this.examplePrompt = examplePrompt;
        this.appTask = appTask;
    }
    public Prompt(String promptName,
                  String template,
                  List<String> templateParameters,
                  String examplePrompt,
                  AppTask appTask) {
        this.promptName = promptName;
        this.template = template;
        this.templateParameters=templateParameters;
        this.examplePrompt = examplePrompt;
        this.appTask = appTask;
    }



    public Prompt(String promptName,
                  String template,
                  List<String> templateParameters,
                  String examplePrompt,
                  String outputFormat,
                  AppTask appTask) {
        this.promptName = promptName;
        this.template = template;
        this.templateParameters=templateParameters;
        this.examplePrompt = examplePrompt;
        this.outputFormat = outputFormat;
        this.appTask = appTask;
    }

    public Integer getPromptId() {
        return promptId;
    }

    public void setPromptId(Integer promptId) {
        this.promptId = promptId;
    }

    public String getPromptName() {
        return promptName;
    }

    public void setPromptName(String promptName) {
        this.promptName = promptName;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getExamplePrompt() {
        return examplePrompt;
    }

    public void setExamplePrompt(String examplePrompt) {
        this.examplePrompt = examplePrompt;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public AppTask getAppTask() {
        return appTask;
    }

    public void setAppTask(AppTask appTask) {
        this.appTask = appTask;
    }

    public List<String> getTemplateParameters() {
        return templateParameters;
    }

    public void setTemplateParameters(List<String> templateParameters) {
        this.templateParameters = templateParameters;
    }
}
