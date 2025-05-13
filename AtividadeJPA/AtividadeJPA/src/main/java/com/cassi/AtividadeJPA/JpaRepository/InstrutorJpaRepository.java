package com.cassi.AtividadeJPA.JpaRepository;

import com.cassi.AtividadeJPA.JpaModel.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrutorJpaRepository extends JpaRepository<Instrutor, Long> {

}
