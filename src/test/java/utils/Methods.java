package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class Methods {
    public static String readExcelCell(String excelFilePath, int rowNumber, int columnNumber) {
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            // İlk sayfayı al (Varsayılan olarak)
            Sheet sheet = workbook.getSheetAt(0);

            // Belirtilen satır ve sütunu al
            Row row = sheet.getRow(rowNumber - 1); // Java'da indeksler 0'dan başlar, bu yüzden -1
            if (row != null) {
                Cell cell = row.getCell(columnNumber - 1); // Aynı şekilde sütun indeksi için de -1
                if (cell != null) {
                    return getCellValueAsString(cell);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Hücrenin değerini String olarak alma
    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    public static void writeProductToFile(String productName, String productPrice) {
        // Dosya yolu, masaüstünde bir txt dosyası oluşturur (örneğin: C:\Users\<KullanıcıAdı>\Desktop\products.txt)
        String filePath = System.getProperty("user.home") + "\\Desktop\\products.txt";

        try {
            // Dosya yazma işlemi için bir BufferedWriter oluştur
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            // Ürün adı ve fiyatını dosyaya yaz
            writer.write("Ürün adı: " + productName + ", Ürün fiyatı: " + productPrice + "\n");

            // Writer'ı kapat
            writer.close();

            System.out.println("Ürün bilgileri başarıyla dosyaya yazıldı.");
        } catch (IOException e) {
            System.err.println("Dosyaya yazma işlemi sırasında bir hata oluştu: " + e.getMessage());
        }
    }
    public static double parsePrice(String priceString) {
        // String içerisindeki tüm karakterlerin sayısal, nokta ve virgül karakteri olup olmadığını kontrol edin
        String numericString = priceString.replaceAll("[^\\d,]", "");

        // Virgül karakterini noktaya dönüştürün
        numericString = numericString.replace(',', '.');

        // Eğer boş bir string dönerse, fiyat bilgisi bulunamadığı için 0.0 döndürün
        if (numericString.isEmpty()) {
            return 0.0;
        }

        // String'i double değere dönüştürün
        double price = Double.parseDouble(numericString);
        return price;
    }
}
