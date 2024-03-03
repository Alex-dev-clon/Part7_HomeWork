package controller;

import model.MyRepository;

public class AppController {
    private final MyRepository repository;

    public AppController(MyRepository repository) {
        this.repository = repository;
    }

    public void checkSizeValue(String value) {
        repository.checkSizeValue(value);
    }

    public String[] getArrayForWriter(String[] value) {
        return repository.getArrayForWriter(value);
    }

    public void writeResultArray(String[] value) {
        repository.writeResultArray(value);
    }
}
