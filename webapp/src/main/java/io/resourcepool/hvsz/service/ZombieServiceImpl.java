package io.resourcepool.hvsz.service;

import org.springframework.stereotype.Service;

/**
 * Created by ebiz on 27/04/17.
 */
@Service
public class ZombieServiceImpl implements ZombieService {
    @Override
    public boolean kill() {
        return true;
    }
}
