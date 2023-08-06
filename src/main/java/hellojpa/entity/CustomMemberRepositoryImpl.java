package hellojpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomMemberRepositoryImpl implements CustomMemberRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Member findMethod(Long id){
        return (Member) entityManager.createQuery("FROM Member m WHERE m.id = :id")
                .setParameter("id",id)
                .getSingleResult();
    }

}
