package Tests;

import Data.SavableArray;
import SaveData.SaveDataHandler;
import java.io.IOException;

public class Main {

    // Izveido objekta klasi, kuru izmantot serializācijas testā.
    // Simple test implementation for object serialization
    public static class TestObject implements SaveDataHandler.SavableObject {
        private static final long serialVersionUID = 1L;

        String name;
        int value;

        public TestObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String GetSaveFile() {
            return "test_object.dat";
        }

        @Override
        public String toString() {
            return "TestObject{name='" + name + "', value=" + value + "}";
        }
    }

    public static void main(String[] args) {

        //2. tests
        //Testē masīvu saglabāšanu CSV failā.

        System.out.println("=== CSV SAVING TEST (SavableArray) ===");

        Object[][] data = {
                {"Name", "Age", "City"},
                {"Alice", 25, "Riga"},
                {"Bob", 30, "Tallinn"},
                {"Charlie", 28, "Vilnius"}
        };

        SavableArray array = new SavableArray(data);

        // Write CSV
        SaveDataHandler.writeCSV(array);
        System.out.println("CSV written to: " + SaveDataHandler.GetSaveFilePath(array));

        // Print CSV content via toString
        System.out.println("\nCSV Content:");
        System.out.println(array);

        //2. tests
        //Testē objekta serializāciju, objekts tiek serializēts un ierakstīts failā, fails tiek nolasīts un tiek rekonstruēta informācija.

        System.out.println("\n=== SERIALIZATION TEST (SavableObject) ===");

        TestObject obj = new TestObject("DemoObject", 42);

        // Write object
        SaveDataHandler.writeFile(obj);
        System.out.println("Object written to: " + SaveDataHandler.GetSaveFilePath(obj));

        try {
            // Read object back
            SaveDataHandler.SavableObject loaded =
                    SaveDataHandler.readFile(obj);

            System.out.println("Object read from file:");
            System.out.println(loaded);

        } catch (ClassNotFoundException e) {
            System.out.println("Error reading object: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== TEST COMPLETE ===");
    }
}