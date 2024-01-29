package team6.epicenergyspa.CSVReader;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team6.epicenergyspa.model.Comuni;
import team6.epicenergyspa.repository.ComuniDAO;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class CSVReader {
    @Autowired
    ComuniDAO comuniDAO;


    private Workbook workbook;
    private Worksheet worksheet;


    public List<Comuni> readComuni(String file) throws Exception {
        workbook = new Workbook(file);
        WorksheetCollection wc = workbook.getWorksheets();
        List<Comuni> comuniList = new ArrayList<>();
        //Qua otteniamo il foglio di lavoro
        for (int collectionIndex = 0; collectionIndex < wc.getCount(); collectionIndex++) {
            worksheet = wc.get(collectionIndex);
            //Ora otteniamo il numero di colonne e righe
            int row = worksheet.getCells().getMaxDataRow();
            int column = worksheet.getCells().getMaxDataColumn();
            for (int rwindex = 0; rwindex < row + 1; rwindex++) {
                Comuni com = new Comuni();
                if (rwindex == 0) {
                    continue;
                }
                for (int cellindex = 0; cellindex < column + 1; cellindex++) {
                    System.out.println(worksheet.getCells().get(rwindex, cellindex).getValue() + ", ");
                    com.setCodiceProivincia((int) worksheet.getCells().get(rwindex, 0).getIntValue());
                    com.setCodiceProgressivoComune((String) worksheet.getCells().get(rwindex, 1).getStringValue());
                    com.setNameComune((String) worksheet.getCells().get(rwindex, 2).getStringValue());
                    com.setProvincia((String) worksheet.getCells().get(rwindex, 3).getStringValue());
                    comuniList.add(com);
                }
            }
        }

        return comuniList;
    }

}
