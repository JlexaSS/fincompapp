package net.fincomp.spring.fincomp.service;

import net.fincomp.spring.fincomp.model.Producer;
import net.fincomp.spring.fincomp.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService {
    @Autowired
    ProducerRepository producerRepository;

    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    public void saveProducer(Producer producer) {
        producerRepository.save(producer);
    }


    public void deleteById(Long producer) {
        producerRepository.deleteById(producer);
    }

    public void saveProducer(Producer producer, String name, String country) {
        producer.setName(name);
        producer.setCountry(country);
        producerRepository.save(producer);
    }

    public List<Producer> findByNameContaining(String filter) {
        return producerRepository.findByNameContaining(filter);
    }
}
