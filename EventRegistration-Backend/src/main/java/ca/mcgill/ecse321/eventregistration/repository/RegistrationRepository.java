package ca.mcgill.ecse321.eventregistration.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.model.Registration.RegistrationId;

public interface RegistrationRepository extends CrudRepository<Registration, RegistrationId> {

	public Registration findRegistrationById(RegistrationId id);
	
}
