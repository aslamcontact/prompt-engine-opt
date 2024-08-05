package com.aslam.mycontact.prompt_engine_opt.dao_layer.application;


import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTask;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class App {


    @Id
    @SequenceGenerator( name="app_id_seq",
                        sequenceName = "app_id_seq",
                        allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
                     generator = "app_id_seq")
    private Integer appId;
    @Column(unique = true)
    private String appName;
    private String descriptions;

    @OneToMany( mappedBy = "application",
                cascade = CascadeType.ALL)
    List<AppTask> appTasks;


    public App() {
    }

    public App(String appName,String descriptions) {
        this.descriptions = descriptions;
        this.appName = appName;
    }

    public List<AppTask> getTasks() {
        return appTasks;
    }

    public void setTasks(List<AppTask> appTasks) {
        this.appTasks = appTasks;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
