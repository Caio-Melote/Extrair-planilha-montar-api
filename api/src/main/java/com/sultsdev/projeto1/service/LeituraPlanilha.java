package com.sultsdev.projeto1.service;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sultsdev.projeto1.model.Franquia;
import com.sultsdev.projeto1.model.Segmento;
import com.sultsdev.projeto1.repository.FranquiaRepository;
import com.sultsdev.projeto1.repository.SegmentoRepository;

import lombok.Cleanup;

@Service
public class LeituraPlanilha {
	
	@Autowired
    private FranquiaRepository franquiaRepository;

	@Autowired
    private SegmentoRepository segmentoRepository;
    
	public void lerPlanilha() {

		try {

			@Cleanup // Fecha automaticamente o "file" após usar
			FileInputStream file = new FileInputStream("src/main/resources/ABF-scraper.xlsx");
			
			@Cleanup
			XSSFWorkbook wb = new XSSFWorkbook(file);
			
			XSSFSheet ws = wb.getSheetAt(0);
			
			Iterator<Row> rowIterator = ws.iterator();
			
			if (rowIterator.hasNext()) {
                rowIterator.next();
            }

			while (rowIterator.hasNext()) 
            {
				 Row linha = rowIterator.next();
				 
				 String segmentoNome = linha.getCell(5).getStringCellValue();
				 				 
				 Segmento segmento = segmentoRepository.findByNome(segmentoNome);
	                if (segmento == null) {
	                    segmento = new Segmento();
	                    segmento.setNome(segmentoNome);
	                    segmento = segmentoRepository.save(segmento); 
	                }
	                
	                Cell cell = linha.getCell(4);
	                Double investimentoInicial = Double.parseDouble(cell.getStringCellValue());
	                
	                
	                cell = linha.getCell(8);	                
	                String dataString = cell.getStringCellValue();	              
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	                LocalDateTime dataAtualizacao =  LocalDateTime.parse(dataString, formatter);
				 	              	                
				 	Franquia franquia = new Franquia(null, 
				 			linha.getCell(0).getStringCellValue(),
				 			(int) linha.getCell(1).getNumericCellValue(),
				 			linha.getCell(2).getStringCellValue(),
				 			linha.getCell(3).getStringCellValue(),
				 			investimentoInicial,
				 			linha.getCell(6).getStringCellValue(),
				 			linha.getCell(7).getStringCellValue(),
				 			dataAtualizacao,
				 			linha.getCell(9).getStringCellValue(),
				 			true, 
				 			segmento
				 			);

	                
	                franquiaRepository.save(franquia);	                
            }					
			
		} catch (Exception e) {

			e.printStackTrace();	
		}
	}

}
