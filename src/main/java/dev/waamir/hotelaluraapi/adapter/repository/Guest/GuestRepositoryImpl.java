package dev.waamir.hotelaluraapi.adapter.repository.Guest;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelaluraapi.domain.model.Guest;
import dev.waamir.hotelaluraapi.domain.port.IGuestRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GuestRepositoryImpl implements IGuestRepository<Guest> {
    
    private final IGuestJpaRepository guestJpaRepository;

    @Override
    public Guest create(Guest guest) {
        return guestJpaRepository.save(guest);
    }

    @Override
    public Optional<Guest> getById(Long id) {
        return guestJpaRepository.findById(id);
    }

    @Override
    public Optional<Guest> getByEmail(String email) {
        return guestJpaRepository.findByEmail(email);
    }

    @Override
    public void delete(Guest guest) {
        guestJpaRepository.delete(guest);
    }

    @Override
    public void update(Guest guest) {
        guestJpaRepository.save(guest);
    }

    @Override
    public Page<Guest> list(Pageable pagination) {
        return guestJpaRepository.findAll(pagination);
    }

}
