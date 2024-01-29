package team6.epicenergyspa.config;

import com.aspose.cells.Workbook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CSVreaderConfig {
    @Bean
    public Workbook workbook(){
        return new Workbook();
    }
}
