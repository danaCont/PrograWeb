package pe.edu.upc.spring.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Request;


@Repository
public interface IRequestRepository extends JpaRepository<Request, Integer> {

	
	@Query("From Request r where r.eventPlanner.planner.nombre like %:nombre%")
	List<Request> SearchByNamePlanner(@Param("nombre") String name);
	
	@Query("From Request r where r.eventPlanner.event.nameEvent like %:nameEvent%")
	List<Request> SearchByNameEvent(@Param("nameEvent") String nameEvent);
	
}