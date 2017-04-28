package io.resourcepool.hvsz.service;

import org.springframework.stereotype.Service;

@Service
public class ZombieServiceImpl implements ZombieService {

    @Override
    public boolean kill() {
        return true;
    }
}
