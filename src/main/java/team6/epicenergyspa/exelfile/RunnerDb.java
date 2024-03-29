package team6.epicenergyspa.exelfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import team6.epicenergyspa.CSVReader.CSVReaderDB;
import team6.epicenergyspa.repository.MunicipalityDAO;
import team6.epicenergyspa.repository.ProvinceDAO;

@Component
public class RunnerDb implements CommandLineRunner {
    ProvinceDAO provinceDAO;
    MunicipalityDAO municipalityDAO;

    @Autowired
    public RunnerDb(ProvinceDAO provinceDAO, MunicipalityDAO municipalityDAO) {
        this.provinceDAO = provinceDAO;
        this.municipalityDAO = municipalityDAO;
    }

    @Override
    public void run(String... args) throws Exception {

        CSVReaderDB cs = new CSVReaderDB();
        try {
           /* cs.readProvince("src/main/java/team6/epicenergyspa/exelfile/province-italiane" + ".csv", provinceDAO);
            cs.saveComuni(provinceDAO, municipalityDAO);*/
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
