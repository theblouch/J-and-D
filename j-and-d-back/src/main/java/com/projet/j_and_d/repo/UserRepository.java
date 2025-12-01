package com.projet.j_and_d.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.projet.j_and_d.model.GM;
import com.projet.j_and_d.model.Player;
import com.projet.j_and_d.model.User;



public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("from Player")
	public List<Player> findAllPlayer();

	@Query("from GM")
	public List<GM> findAllFormateur();

	public User findByLoginAndPassword(String login, String password);

	public Optional<User> findByLogin(String login);
	
	//@Query("SELECT f from Formateur f LEFT JOIN FETCH f.formations where f.id=:id")
    //public Optional<Formateur> formateurWithModules(@Param("id") Integer idFormateur);
}
