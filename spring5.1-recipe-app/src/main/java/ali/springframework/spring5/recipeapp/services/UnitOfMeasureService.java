package ali.springframework.spring5.recipeapp.services;

import java.util.Set;

import ali.springframework.spring5.recipeapp.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}