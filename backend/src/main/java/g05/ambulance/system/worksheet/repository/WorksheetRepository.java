package g05.ambulance.system.worksheet.repository;

import g05.ambulance.system.worksheet.entity.*;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WorksheetRepository extends JpaRepository<Worksheet, Long> {}