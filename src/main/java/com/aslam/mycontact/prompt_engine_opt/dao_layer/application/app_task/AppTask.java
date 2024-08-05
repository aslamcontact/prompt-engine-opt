package com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task;

import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.prompt.Prompt;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
        uniqueConstraints =
                {
                @UniqueConstraint(
                        name="UniqueTaskNameAndApplication",
                        columnNames = {"task_name","application"})
                }
)
public class AppTask {

    @Id
    @SequenceGenerator( name="task_id_seq",
                       sequenceName = "task_id_seq",
                       allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
                     generator = "task_id_seq")
  private Integer taskId;
  private String taskName;
  private String aboutTask;
  @ManyToOne(optional = false)
  @JoinColumn( name = "app_id",
               referencedColumnName = "appId")
  private App application;
  @OneToMany( mappedBy = "appTask",
              cascade = CascadeType.ALL)
  private List<Prompt> prompts;


    public AppTask() {
    }

    public AppTask(String taskName, String aboutTask, App application) {
        this.taskName=taskName;
        this.aboutTask = aboutTask;
        this.application = application;
    }

    public App getApplication() {
        return application;
    }

    public void setApplication(App application) {
        this.application = application;
    }

    public String getAboutTask() {
        return aboutTask;
    }

    public void setAboutTask(String aboutTask) {
        this.aboutTask = aboutTask;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return taskName;
    }

    public void setName(String taskName) {
        this.taskName = taskName;
    }
}
