/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.exceptions.NonexistentEntityException;
import control.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Curso;
import modelo.Users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Estudante;

/**
 *
 * @author ali_sualei
 */
public class EstudanteJpaController implements Serializable {

    public EstudanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudante estudante) throws PreexistingEntityException, Exception {
        if (estudante.getUsersList() == null) {
            estudante.setUsersList(new ArrayList<Users>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso idcurso = estudante.getIdcurso();
            if (idcurso != null) {
                idcurso = em.getReference(idcurso.getClass(), idcurso.getIdcurso());
                estudante.setIdcurso(idcurso);
            }
            List<Users> attachedUsersList = new ArrayList<Users>();
            for (Users usersListUsersToAttach : estudante.getUsersList()) {
                usersListUsersToAttach = em.getReference(usersListUsersToAttach.getClass(), usersListUsersToAttach.getUtilizador());
                attachedUsersList.add(usersListUsersToAttach);
            }
            estudante.setUsersList(attachedUsersList);
            em.persist(estudante);
            if (idcurso != null) {
                idcurso.getEstudanteList().add(estudante);
                idcurso = em.merge(idcurso);
            }
            for (Users usersListUsers : estudante.getUsersList()) {
                Estudante oldIdestudanteOfUsersListUsers = usersListUsers.getIdestudante();
                usersListUsers.setIdestudante(estudante);
                usersListUsers = em.merge(usersListUsers);
                if (oldIdestudanteOfUsersListUsers != null) {
                    oldIdestudanteOfUsersListUsers.getUsersList().remove(usersListUsers);
                    oldIdestudanteOfUsersListUsers = em.merge(oldIdestudanteOfUsersListUsers);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstudante(estudante.getIdestudante()) != null) {
                throw new PreexistingEntityException("Estudante " + estudante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudante estudante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudante persistentEstudante = em.find(Estudante.class, estudante.getIdestudante());
            Curso idcursoOld = persistentEstudante.getIdcurso();
            Curso idcursoNew = estudante.getIdcurso();
            List<Users> usersListOld = persistentEstudante.getUsersList();
            List<Users> usersListNew = estudante.getUsersList();
            if (idcursoNew != null) {
                idcursoNew = em.getReference(idcursoNew.getClass(), idcursoNew.getIdcurso());
                estudante.setIdcurso(idcursoNew);
            }
            List<Users> attachedUsersListNew = new ArrayList<Users>();
            for (Users usersListNewUsersToAttach : usersListNew) {
                usersListNewUsersToAttach = em.getReference(usersListNewUsersToAttach.getClass(), usersListNewUsersToAttach.getUtilizador());
                attachedUsersListNew.add(usersListNewUsersToAttach);
            }
            usersListNew = attachedUsersListNew;
            estudante.setUsersList(usersListNew);
            estudante = em.merge(estudante);
            if (idcursoOld != null && !idcursoOld.equals(idcursoNew)) {
                idcursoOld.getEstudanteList().remove(estudante);
                idcursoOld = em.merge(idcursoOld);
            }
            if (idcursoNew != null && !idcursoNew.equals(idcursoOld)) {
                idcursoNew.getEstudanteList().add(estudante);
                idcursoNew = em.merge(idcursoNew);
            }
            for (Users usersListOldUsers : usersListOld) {
                if (!usersListNew.contains(usersListOldUsers)) {
                    usersListOldUsers.setIdestudante(null);
                    usersListOldUsers = em.merge(usersListOldUsers);
                }
            }
            for (Users usersListNewUsers : usersListNew) {
                if (!usersListOld.contains(usersListNewUsers)) {
                    Estudante oldIdestudanteOfUsersListNewUsers = usersListNewUsers.getIdestudante();
                    usersListNewUsers.setIdestudante(estudante);
                    usersListNewUsers = em.merge(usersListNewUsers);
                    if (oldIdestudanteOfUsersListNewUsers != null && !oldIdestudanteOfUsersListNewUsers.equals(estudante)) {
                        oldIdestudanteOfUsersListNewUsers.getUsersList().remove(usersListNewUsers);
                        oldIdestudanteOfUsersListNewUsers = em.merge(oldIdestudanteOfUsersListNewUsers);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estudante.getIdestudante();
                if (findEstudante(id) == null) {
                    throw new NonexistentEntityException("The estudante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudante estudante;
            try {
                estudante = em.getReference(Estudante.class, id);
                estudante.getIdestudante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudante with id " + id + " no longer exists.", enfe);
            }
            Curso idcurso = estudante.getIdcurso();
            if (idcurso != null) {
                idcurso.getEstudanteList().remove(estudante);
                idcurso = em.merge(idcurso);
            }
            List<Users> usersList = estudante.getUsersList();
            for (Users usersListUsers : usersList) {
                usersListUsers.setIdestudante(null);
                usersListUsers = em.merge(usersListUsers);
            }
            em.remove(estudante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudante> findEstudanteEntities() {
        return findEstudanteEntities(true, -1, -1);
    }

    public List<Estudante> findEstudanteEntities(int maxResults, int firstResult) {
        return findEstudanteEntities(false, maxResults, firstResult);
    }

    private List<Estudante> findEstudanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudante.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Estudante findEstudante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudante.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudante> rt = cq.from(Estudante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
