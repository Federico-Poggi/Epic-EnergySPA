package team6.epicenergyspa.CSVReader;

import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import team6.epicenergyspa.model.Municipality;
import team6.epicenergyspa.model.Province;
import team6.epicenergyspa.repository.MunicipalityDAO;
import team6.epicenergyspa.repository.ProvinceDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
@Getter
@Setter
@NoArgsConstructor
public class CSVReaderDB {

    /*MunicipalityDAO municipalityDAO;*/

    /*ProvinceDAO pr;*/
 /*   @Autowired
    public CSVReaderDB(MunicipalityDAO municipalityDAO, ProvinceDAO pr) {
        this.municipalityDAO = municipalityDAO;
        this.pr = pr;
    }*/


    public void saveComuni(ProvinceDAO pr, MunicipalityDAO municipalityDAO) throws IOException, CsvValidationException {
        BufferedReader reader = new BufferedReader(new FileReader(
                "src/main/java/team6/epicenergyspa/exelfile/comuni-italiani" + ".csv"));
        String line;
        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            Municipality mun = new Municipality();
            int municipalityCode = Integer.parseInt(data[1]);
            int progressiveMunicipalityCode = Integer.parseInt(data[0]);
            String municipalityName = data[2];
            String provinceName = data[3];

            Province prov = null;
            switch (provinceName) {
                case "Verbano-Cusio-Ossola":
                    prov = pr.getProvincesByProvince("Verbania");
                    mun.setProvince(prov);
                    break;
                case "Valle d'Aosta/Vallée d'Aoste":
                    prov = pr.getProvincesByProvince("Aosta");
                    mun.setProvince(prov);
                    break;
                case "Monza e della Brianza":
                    prov = pr.getProvincesByProvince("Monza-Brianza");
                    mun.setProvince(prov);
                    break;
                case "Bolzano/Bozen":
                    prov = pr.getProvincesByProvince("Bolzano");
                    mun.setProvince(prov);
                    break;
                case "La Spezia":
                    prov = pr.getProvincesByProvince("La-Spezia");
                    mun.setProvince(prov);
                    break;
                case "Reggio nell'Emilia":
                    prov = pr.getProvincesByProvince("Reggio-Emilia");
                    mun.setProvince(prov);
                    break;
                case "Forlì-Cesena":
                    prov = pr.getProvincesByProvince("Forli-Cesena");
                    mun.setProvince(prov);
                    break;
                case "Pesaro e Urbino":
                    prov = pr.getProvincesByProvince("Pesaro-Urbino");
                    mun.setProvince(prov);
                    break;
                case "Ascoli Piceno":
                    prov = pr.getProvincesByProvince("Ascoli-Piceno");
                    mun.setProvince(prov);
                    break;
                case "Reggio Calabria":
                    prov = pr.getProvincesByProvince("Reggio-Calabria");
                    mun.setProvince(prov);
                    break;
                case "Vibo Valentia":
                    prov = pr.getProvincesByProvince("Vibo-Valentia");
                    mun.setProvince(prov);
                    break;
                case "Sud Sardegna":
                    prov = pr.getProvincesByProvince("Cagliari");
                    mun.setProvince(prov);
                    break;
                default:
                    prov = pr.getProvincesByProvince(provinceName);
                    mun.setProvince(prov);
            }


            mun.setMunicipalityName(municipalityName);
            mun.setProgressiveMunicipalityCode(progressiveMunicipalityCode);
            mun.setProvinceCode(municipalityCode);
            mun.setProvinceName(provinceName);
            municipalityDAO.save(mun);
        }
    }

    public void readProvince(String file, ProvinceDAO pr) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        reader.readLine();

        while ((line = reader.readLine()) != null) {

            String[] data = line.split(";");
            Province p = new Province();
            p.setProvinceAbbreviation(data[0]);
            p.setProvince(data[1]);
            p.setRegion(data[2]);
            System.out.println(data[0] + " " + data[1] + " " + data[2]);
            pr.save(p);
        }

    }
}
