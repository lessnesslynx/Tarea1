package controller;

import model.Tactician;

import java.util.List;

public interface Observer {

    void update(List<Tactician> tacticians);
}
