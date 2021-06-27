package nl.rabobank.com.stockexchange.repository;

import nl.rabobank.com.stockexchange.models.ERole;
import nl.rabobank.com.stockexchange.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
