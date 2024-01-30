package team6.epicenergyspa.CSVReader;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team6.epicenergyspa.model.Municipality;
import team6.epicenergyspa.model.Province;
import team6.epicenergyspa.repository.MunicipalityDAO;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class CSVReader {
    @Autowired
    MunicipalityDAO municipalityDAO;


    private Workbook workbook;
    private Worksheet worksheet;


    public List<Municipality> readComuni( String file) throws Exception {
        workbook = new Workbook(file);
        WorksheetCollection wc = workbook.getWorksheets();
        List<Municipality> municipalityList = new ArrayList<>();
        //Qua otteniamo il foglio di lavoro
        for (int collectionIndex = 0; collectionIndex < wc.getCount(); collectionIndex++) {
            worksheet = wc.get(collectionIndex);
            //Ora otteniamo il numero di colonne e righe
            int row = worksheet.getCells().getMaxDataRow();
            int column = worksheet.getCells().getMaxDataColumn();
            for (int rwindex = 0; rwindex < row + 1; rwindex++) {
                Municipality com = new Municipality();
                if (rwindex == 0) {
                    continue;
                }
                for (int cellindex = 0; cellindex < column + 1; cellindex++) {
                    System.out.println(worksheet.getCells().get(rwindex, cellindex).getValue() + ", ");
                 /*
                    com.setCodiceProivincia((int) worksheet.getCells().get(rwindex, 0).getIntValue());
                    com.setCodiceProgressivoComune((String) worksheet.getCells().get(rwindex, 1).getStringValue());
                    com.setNameComune((String) worksheet.getCells().get(rwindex, 2).getStringValue());
                    com.setProvincia((String) worksheet.getCells().get(rwindex, 3).getStringValue());
                     */
                    com.setProvinceCode((int) worksheet.getCells().get(rwindex, 0).getIntValue());
                    com.setProgressiveMunicipalityCode((String) worksheet.getCells().get(rwindex, 1).getStringValue());
                    com.setMunicipalityName((String) worksheet.getCells().get(rwindex, 2).getStringValue());
                    com.setProvince((String) worksheet.getCells().get(rwindex, 3).getStringValue());
                    municipalityList.add(com);



                }
            }
        }

        return municipalityList;
    }
    public List<Province> readProvince(String file) throws Exception {
        workbook = new Workbook(file);
        WorksheetCollection wc = workbook.getWorksheets();
        List<Province> provinceList = new ArrayList<>();
        //Qua otteniamo il foglio di lavoro
        for (int collectionIndex = 0; collectionIndex < wc.getCount(); collectionIndex++) {
            worksheet = wc.get(collectionIndex);
            //Ora otteniamo il numero di colonne e righe
            int row = worksheet.getCells().getMaxDataRow();
            int column = worksheet.getCells().getMaxDataColumn();
            for (int rwindex = 0; rwindex < row + 1; rwindex++) {
                Province pro = new Province();
                if (rwindex == 0) {
                    continue;
                }
                for (int cellindex = 0; cellindex < column + 1; cellindex++) {
                    System.out.println(worksheet.getCells().get(rwindex, cellindex).getValue() + ", ");
             //       pro.setSiglaProvincia(worksheet.getCells().get(rwindex, 0).getStringValue());
                      pro.setProvinceAbbreviation(worksheet.getCells().get(rwindex, 0).getStringValue());
              //      pro.setProvincia(worksheet.getCells().get(rwindex, 1).getStringValue());
                      pro.setProvince(worksheet.getCells().get(rwindex, 1).getStringValue());
             //       pro.setRegione(worksheet.getCells().get(rwindex, 2).getStringValue());
                      pro.setRegion(worksheet.getCells().get(rwindex, 1).getStringValue());


                    provinceList.add(pro);
                }
            }
        }

        return provinceList;
    }

}
