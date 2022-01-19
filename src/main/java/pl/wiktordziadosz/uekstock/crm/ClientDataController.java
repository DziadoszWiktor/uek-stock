package pl.wiktordziadosz.uekstock.crm;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientDataController {
    private ClientDataRepository repository;

    ClientDataController(ClientDataRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/api/clients")
    List<ClientData> clients() {
        return repository.findAll();
    }

    @PostMapping("/api/clients")
    void createClient(@RequestBody ClientData clientData) {
        repository.save(clientData);
    }

    @GetMapping("/api/clients/{id}")
    ClientData read(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException());
    }

    @DeleteMapping("/api/clients/{id}")
    void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
