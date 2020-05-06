package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.data.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
