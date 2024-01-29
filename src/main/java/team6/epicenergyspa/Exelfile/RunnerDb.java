package team6.epicenergyspa.Exelfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import team6.epicenergyspa.CSVReader.CSVReader;
import team6.epicenergyspa.model.Comuni;
import team6.epicenergyspa.repository.ComuniDAO;

import java.util.List;

@Component
public class RunnerDb implements CommandLineRunner {
    @Autowired
    ComuniDAO comuniDAO;

    @Override
    public void run(String... args) throws Exception {
        CSVReader cs=new CSVReader();
        List<Comuni> comuni= cs.readComuni("src/main/java/team6/epicenergyspa/Exelfile/comuni-italiani.xlsx");
        comuni.stream().forEach(comuni1 -> comuniDAO.save(comuni1));

    }
}
