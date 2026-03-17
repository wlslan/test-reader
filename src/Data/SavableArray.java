package Data;

import SaveData.SaveDataHandler;

import javax.imageio.ImageIO;
import java.io.*;

public class SavableArray {
    static final String defaultName="results.csv";
    static final char defaultDelim =  ',';

    Object[][] a;
    String name=defaultName;
    char delim = defaultDelim;
    public SavableArray (Object[][] arr) {
        a=arr;
    }
    public String GetSaveFile() {
        return name;
    }
    @Override
    public String toString () {
        StringBuilder ans = new StringBuilder();
        int n=a.length;
        for (int i=0;i<n;i++) {
            int m =a[i].length;
            if (i>0) {
                ans.append('\n');
            }
            for (int j=0;j<m;j++) {
                ans.append(a[i][j]);
                if (j>0) {
                    ans.append(delim);
                }
            }
        }
        return ans.toString();
    }
}
