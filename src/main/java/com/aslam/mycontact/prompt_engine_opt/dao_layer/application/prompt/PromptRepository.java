package com.aslam.mycontact.prompt_engine_opt.dao_layer.application.prompt;

import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTask;
import org.hibernate.query.spi.QueryOptionsAdapter;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromptRepository extends JpaRepository<Prompt,Integer> {
    @Query("select prompts from Prompt prompts where prompts.appTask=?1")
    List<Prompt> findPromptsByAppTask(AppTask appTask);
    @Query("select prompt from Prompt prompt where prompt.appTask=?1 and prompt.promptName=?2")
    Optional<Prompt> findPromptByPromptName(AppTask appTask,String promptName);

}
