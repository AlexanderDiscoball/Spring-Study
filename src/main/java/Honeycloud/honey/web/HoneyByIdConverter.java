package Honeycloud.honey.web;

import Honeycloud.honey.entity.Honey;
import Honeycloud.honey.repository.HoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

public class HoneyByIdConverter implements Converter<String, Honey> {

    private HoneyRepository honeyRepo;

    @Autowired
    public void HoneyByIdConverter(HoneyRepository honeyRepo){
        this.honeyRepo = honeyRepo;
    }

    @Override
    public Honey convert(String id) {
        Optional<Honey> optionalHoney = honeyRepo.findById(id);
        return optionalHoney.isPresent() ? optionalHoney.get() : null;
    }
}
