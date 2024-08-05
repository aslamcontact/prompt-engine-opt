package com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task;

import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AppTaskRepository extends JpaRepository<AppTask,Integer> {

    @Query("select appTask from AppTask appTask where appTask.application=?1")
    List<AppTask> findTasksByApp(App application);
    @Query("select appTask from AppTask appTask where appTask.application=?1 and appTask.taskName=?2")
    Optional<AppTask> findTaskByTaskName(App application,String taskName);
}
