package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    public final VetService vetService;
    public final OwnerService ownerService;

    public DataLoader(){

        vetService = new VetServiceMap();
        ownerService = new OwnerServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Rishabh");
        owner1.setLastName("Singh");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Cristiano");
        owner2.setLastName("Ronaldo");

        ownerService.save(owner2);

        System.out.println("Loaded owners....");


        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Lionel");
        vet1.setLastName("Messi");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Bruno");
        vet2.setLastName("Fernandes");

        vetService.save(vet2);

        System.out.println("Loaded vets....");

    }
}
