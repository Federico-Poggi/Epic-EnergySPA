package team6.epicenergyspa.CSVReader;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team6.epicenergyspa.model.Municipality;
import team6.epicenergyspa.model.Province;
import team6.epicenergyspa.repository.MunicipalityDAO;
import team6.epicenergyspa.repository.ProvinceDAO;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class CSVReaderDB {
    @Autowired
    MunicipalityDAO municipalityDAO;
    @Autowired
    ProvinceDAO pr;


    private Workbook workbook;
    private Worksheet worksheet;

    public void saveComuni() throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader("src/main/java/team6/epicenergyspa/exelfile/comuni-italiani" +
                                                                ".csv"));
        String[] data;
        while ((data = reader.readNext()) != null) {
            Municipality mun = new Municipality();
            String municipallyName = data[0];
            int progressiveMunicipalityCode = Integer.parseInt(data[1]);
            String municipalityName = data[2];
            String provinceName = data[3];

            Province prov = pr.getProvincesByProvince(provinceName);


            mun.setMunicipalityName(municipalityName);
            mun.setProgressiveMunicipalityCode(progressiveMunicipalityCode);
            mun.setMunicipalityName(municipallyName);
            mun.setProvinceName(provinceName);
            mun.setProvince(prov);
            municipalityDAO.save(mun);
        }
    }

    public void readProvince(String file) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(file));
        String[] data;


        while ((data = reader.readNext()) != null) {
            if (reader.getLinesRead() == 1) {
                continue;
            }
            Arrays.stream(data).map(d -> d.split(";")).toList().forEach(e -> System.out.println(e.toString()));

            System.out.println(Arrays.stream(reader.readNext()).toList());
            List<String> strings = Arrays.asList(reader.readNext());
            strings.stream().map(string -> string.ch)


            strings.forEach(System.out::println);
            System.out.println(reader.getRecordsRead());


      /*     Province pro=new Province();
        pro.setProvinceAbbreviation(data[0]);
        pro.setProvince(data[1]);
        pro.setRegion(data[2]);
        pr.save(pro);*/


        }

    }
