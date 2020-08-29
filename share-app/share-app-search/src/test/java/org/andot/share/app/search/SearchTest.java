package org.andot.share.app.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.andot.share.common.utils.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.resource.HttpResource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
public class SearchTest {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("/Users/andot/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/d4b01b1ae02a260711313ef77293c5f0/Message/MessageTemp/ab18e0166eb196a6d826e585a2975627/File/SRP-data_Kotex_V14f - 副本.xlsx");
        Workbook workbook = new XSSFWorkbook(inputStream);
        Iterator<Row> rows = workbook.getSheetAt(0).rowIterator();
        List<String> header = new ArrayList<>(256);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("org.andot.share.app.search");
        applicationContext.refresh();
        RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf8");

        int i = 0, total = 209;
        while (rows.hasNext()) {
            Row row = rows.next();
            Iterator<Cell> cells = row.cellIterator();

            JSONObject jsonObject = new JSONObject();
            for (int j = 0; j < total; j++) {
                Cell cell = cells.next();
                if (i == 0) {
                    header.add(cell.getStringCellValue().replace(".", "@"));
                    //map.put(header.get(j), Collections.emptyMap());
                } else {
                    jsonObject.put(header.get(j), getValue(header.get(j), cell));
                }
            }

            if (i == 0) {
                /*Map<String, Object> data = new HashMap<String, Object>(){{
                   put("\"properties\"", map);
                }};
                HttpEntity<String> requestEntity = new HttpEntity<>(data.toString(), headers);*/
                //restTemplate.postForEntity("http://10.137.95.8:9200/srp_data/_mapping", requestEntity, String.class);
            } else {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                httpHeaders.add("Accept", "application/json;charset=utf8");
                HttpEntity<String> requestEntity = new HttpEntity<>(jsonObject.toJsonString(), httpHeaders);
                ResponseEntity<Map> resource = restTemplate.postForEntity("http://10.137.95.8:9200/srp_data/_doc", requestEntity, Map.class);
                System.err.println(resource);

            }
            if(i >= 219){
                break;
            }
            i++;

        }



        System.err.println();

    }

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Object getValue(String field, Cell cell){
        Object returnValue;
        if(field.toLowerCase().contains("time")){
            returnValue = FORMAT.format(cell.getDateCellValue());
        } else {
            if (CellType.NUMERIC.compareTo(cell.getCellType()) == 0) {
                returnValue = String.valueOf(cell.getNumericCellValue());
            } else if (CellType.BLANK.compareTo(cell.getCellType()) == 0) {
                returnValue = "";
            } else {
                returnValue = cell.getStringCellValue();
            }
            try {
                if(!returnValue.toString().contains("Status") && returnValue.toString().indexOf(",") > -1) {
                    String[] strings = returnValue.toString().split(",");
                    returnValue = strings;
                }
            } catch (Exception exception) {
                System.err.println(returnValue+"抛出异常："+exception.getMessage());
            }
        }
        return returnValue;
    }
}
