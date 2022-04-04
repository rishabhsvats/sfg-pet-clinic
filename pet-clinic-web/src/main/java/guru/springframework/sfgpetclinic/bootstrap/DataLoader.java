package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class DataLoader implements CommandLineRunner {

    private final VetService vetService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(VetService vetService, OwnerService ownerService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService){
        this.vetService = vetService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if(count == 0){
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Rishabh");
        owner1.setLastName("Singh");
        owner1.setAddress("Mahidanda Uttarkashi");
        owner1.setCity("Uttarkashi");
        owner1.setTelephone("12345667");

        Pet rishabhPet = new Pet();
        rishabhPet.setPetType(savedDogPetType);
        rishabhPet.setOwner(owner1);
        rishabhPet.setBirthDate(LocalDate.now());
        rishabhPet.setName("Roco");
        owner1.getPets().add(rishabhPet);


        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Cristiano");
        owner2.setLastName("Ronaldo");
        owner2.setAddress("Mahidanda Uttarkashi");
        owner2.setCity("Uttarkashi");
        owner2.setTelephone("12345667");

        Pet ronaldoPet = new Pet();
        ronaldoPet.setPetType(savedCatPetType);
        ronaldoPet.setOwner(owner2);
        ronaldoPet.setBirthDate(LocalDate.now());
        ronaldoPet.setName("Catty");
        owner2.getPets().add(ronaldoPet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(ronaldoPet);
        catVisit.setLocalDate(LocalDate.now());
        catVisit.setDescription("Sneezy cat");

        visitService.save(catVisit);

        System.out.println("Loaded owners....");


        Vet vet1 = new Vet();
        vet1.setFirstName("Lionel");
        vet1.setLastName("Messi");
        vet1.getSpecialities().add(savedSurgery);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Bruno");
        vet2.setLastName("Fernandes");
        vet2.getSpecialities().add(savedDentistry);
        vetService.save(vet2);

        System.out.println("Loaded vets....");
    }
}
