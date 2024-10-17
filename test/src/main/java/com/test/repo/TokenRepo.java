package com.test.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.entity.Token;

@Repository
public interface TokenRepo extends JpaRepository<Token,Long> {
    
    @Query(value ="""
            select * from tokens t inner join users u 
            on t.userid = u.user_id 
            where t.userid = :userID and (t.expired = false and t.revoked = false);
            """,nativeQuery = true)
    List<Token> findAllValidTokenByUser(@Param("userID") Long userId);

    
    Optional<Token> findByToken(String token);

}
