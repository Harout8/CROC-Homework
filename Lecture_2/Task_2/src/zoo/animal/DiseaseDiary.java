package zoo.animal;

import java.time.LocalDate;
import java.util.Arrays;

public class DiseaseDiary {
    private Animal animal;
    private String[] diseases;
    private LocalDate[] dates;


    // Конструктор по умолчанию
    public DiseaseDiary() {
        super();
    }

    public DiseaseDiary(Animal animal) {
        this.animal = animal;
        animal.setDiseaseDiary(this);
    }


    public void addDisease(String disease, LocalDate date) {
        if (diseases == null) {
            diseases = new String[0];
            dates = new LocalDate[0];
        }

        diseases = Arrays.copyOf(diseases, diseases.length + 1);
        diseases[diseases.length - 1] = disease;

        dates = Arrays.copyOf(dates, dates.length + 1);
        dates[dates.length - 1] = date;
    }


    public void showDiseaseInfo() {
        if (diseases != null) {
            System.out.println("Diseases of '" + animal.getTitle() + "':");
            for (int i = 0; i < diseases.length; i++) {
                System.out.println("   " + (i + 1) + ". " + diseases[i] + " (" + dates[i] + ")");
            }
        } else {
            System.out.println(animal.getTitle() + " is healthy");
        }
    }
}
