package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select * from tarefa where pessoa_id is null order by prazo asc limit 3", nativeQuery = true)
    List<Task> listPendingTasks();

    @Query(value = "select coalesce(SUM(extract(epoch from COALESCE(t.data_hora_fim, data_hora_inicio) - (t.data_hora_inicio))/3600),0) as diff_hours from tarefa t where t.pessoa_id  = :personId", nativeQuery = true)
    Long getTotalHoursByPersonId(Long personId);

    @Query(value = "select coalesce(AVG(extract(epoch from COALESCE(t.data_hora_fim, data_hora_inicio) - (t.data_hora_inicio))/3600),0) as diff_hours from tarefa t where t.pessoa_id  = :personId", nativeQuery = true)
    Long getAverageHoursByPersonId(Long personId);

}
