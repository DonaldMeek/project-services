package com.meek.donald.project;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import com.meek.donald.model.projects.Project;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class ProjectServiceImpl implements ProjectService{
	Session session;
	
	public Project getEmployeesByProjectId(Project project) {
		@SuppressWarnings("unchecked")
		Query<Project> query = (Query<Project>) session.createQuery(
				 "SELECT P.techLeadId, P.projMrgId" +  
				 "FROM Project P WHERE P.projId=:projId ");
		query.setParameter("projId", project.getProjId());
		List<Project> projList = query.list();
		return projList.get(0);
	}
	
	public List<Project> getAllActiveProjects() {
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder(); 
		CriteriaQuery<Project> criteria = 
				criteriaBuilder.createQuery(Project.class);
		 Root<Project> root = criteria.from(Project.class);
		 criteria.select(root).where(criteriaBuilder.equal(root.get("active"), true));
		 Query<Project> query = session.createQuery(criteria);
		 return query.getResultList();
	}
	
	public Project getProjectByExample(Project project) {
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder(); 
		CriteriaQuery<Project> criteria = 
				criteriaBuilder.createQuery(Project.class);
		 Root<Project> root = criteria.from(Project.class);
		 Predicate activePred= criteriaBuilder.equal(root.get("active"), 
				 project.getActive());
		 Predicate projTypePred = criteriaBuilder.like(root.<String>get("projectType"), 
				 project.getProjectType());
		 Predicate projNamePred = criteriaBuilder.like(root.<String>get("projectName"), 
				 project.getProjectName());
		 criteria.select(root).where(criteriaBuilder.and(activePred, projTypePred,
				 projNamePred));
		 Query<Project> query = session.createQuery(criteria);
		 return query.getResultList().get(0);
	}
}
