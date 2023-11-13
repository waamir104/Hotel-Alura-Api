package dev.waamir.hotelaluraapi.application.service.Guest;

import java.util.List;
import java.util.Optional;

import dev.waamir.hotelaluraapi.domain.model.Guest;
import dev.waamir.hotelaluraapi.domain.port.IGuestRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GuestServiceImpl implements IGuestService<Guest> {

    private final IGuestRepository<Guest> guestRepository;

    @Override
    public Guest create(Guest guest) {
        return guestRepository.create(guest);
    }

    @Override
    public Optional<Guest> getById(Long id) {
        return guestRepository.getById(id);
    }

    @Override
    public Optional<Guest> getByEmail(String email) {
        return guestRepository.getByEmail(email);
    }

    @Override
    public void delete(Guest guest) {
        guestRepository.delete(guest);
    }

    @Override
    public void update(Guest guest) {
        guestRepository.update(guest);
    }

    @Override
    public List<Guest> list() {
        return guestRepository.list();
    }
    
}
