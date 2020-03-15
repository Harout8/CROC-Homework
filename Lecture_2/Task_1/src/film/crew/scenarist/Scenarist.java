package film.crew.scenarist;


import film.scenarios.Scenario;

import java.util.Arrays;


/**
 *  Класс, описывающий сценариста
 */
public class Scenarist {
    private String name;
    private Scenario[] scenarios;


    public Scenarist(String name) {
        this.name = name;
    }

    public Scenarist(String name, Scenario[] scenarios) {
        this.name = name;
        this.scenarios = scenarios;
    }


    // Getters & Setters:

    public String getName() {
        return name;
    }

    public Scenario[] getScenarios() {
        return scenarios;
    }

    // Добавить сценарий
    public void addScenario(Scenario scenario) {
        if (scenarios == null) {
            scenarios = new Scenario[0];
        }

        scenarios = Arrays.copyOf(scenarios, scenarios.length + 1);
        scenarios[scenarios.length - 1] = scenario;
    }

    // Удалить сценарий
    public void deleteScenario(Scenario scenario) {
        Scenario[] newScenarios = new Scenario[scenarios.length - 1];

        for (int i = 0, j = 0; i < scenarios.length; i++) {
            if (scenarios[i] != scenario) {
                newScenarios[j++] = scenarios[i];
            }
        }

        scenarios = newScenarios;
    }
}
