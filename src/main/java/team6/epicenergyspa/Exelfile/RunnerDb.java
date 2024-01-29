package team6.epicenergyspa.Exelfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import team6.epicenergyspa.CSVReader.CSVReader;
import team6.epicenergyspa.model.Comuni;
import team6.epicenergyspa.model.Province;
import team6.epicenergyspa.repository.ComuniDAO;
import team6.epicenergyspa.repository.ProvinciaDAO;

import java.util.List;

@Component
public class RunnerDb implements CommandLineRunner {
    ProvinciaDAO provinciaDAO;
    ComuniDAO comuniDAO;
    @Autowired
    public RunnerDb(ProvinciaDAO provinciaDAO, ComuniDAO comuniDAO) {
        this.provinciaDAO = provinciaDAO;
        this.comuniDAO = comuniDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        CSVReader cs=new CSVReader();
        List<Province> province= cs.readProvince("src/main/java/team6/epicenergyspa/Exelfile/province-italiane.xlsx");
        province.stream().forEach(province1 -> provinciaDAO.save(province1));

    }
}
